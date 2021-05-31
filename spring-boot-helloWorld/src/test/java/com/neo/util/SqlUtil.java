package com.neo.util;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.neo.dto.Constant;
import com.neo.dto.ParamExp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

import static com.neo.util.ParamsUtil.replaceAllParams;

/**
 * @description: Sql识别转换工具
 * @author: Luozhi
 * @create: 2021-04-19 15:03
 **/
@Slf4j
public class SqlUtil {

    @Test
    public void test1() {
        String s = "select * from '${uid}:${zz}_z' where a = ${xx} ";
        List<String> tableNameBySql = getTableNameBySql(s);
        List<TableStat.Condition> whereListBySql = getWhereListBySql(s);
        System.out.println(tableNameBySql);
        System.out.println(whereListBySql);

        ArrayList<Object> objects = new ArrayList<>();
        System.out.println(JSON.toJSONString(objects));
        System.out.println(objects.toString());
    }



    /**
     * 获取sql的表名
     *
     * @param sql
     * @return
     */
    public static List<String> getTableNameBySql(String sql) {
        DbType dbType = DbType.mysql;
        try {
            List<String> tableNameList = new ArrayList<>();
            //格式化输出
            String sqlResult = SQLUtils.format(sql, dbType);
            log.info("格式化后的sql:[{}]", sqlResult);

            List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
            if (CollectionUtils.isEmpty(stmtList)) {
                log.info("stmtList为空无需获取");
                return Collections.emptyList();
            }
            for (SQLStatement sqlStatement : stmtList) {
                MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
                sqlStatement.accept(visitor);
                Map<TableStat.Name, TableStat> tables = visitor.getTables();
                log.info("druid解析sql的结果集:[{}]", tables);
                Set<TableStat.Name> tableNameSet = tables.keySet();
                for (TableStat.Name name : tableNameSet) {
                    String tableName = name.getName();
                    if (!StringUtils.isEmpty(tableName)) {
                        tableNameList.add(tableName);
                    }
                }
            }
            log.info("解析sql后的表名:[{}]", tableNameList);
            return tableNameList;
        } catch (Exception e) {
            log.error("**************异常SQL:[{}]*****************\\n", sql);
            log.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }



    /**
     * 获取sql的where条件
     *
     * @param sql
     * @return
     */
    public static List<TableStat.Condition> getWhereListBySql(String sql) {
        DbType dbType = DbType.mysql;
        try {
            List<TableStat.Condition> whereList = new ArrayList<>();
            //格式化输出
            String sqlResult = SQLUtils.format(sql, dbType);
            log.info("格式化后的sql:[{}]", sqlResult);
            List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
            if (CollectionUtils.isEmpty(stmtList)) {
                log.info("stmtList为空无需获取");
                return Collections.emptyList();
            }
            for (SQLStatement sqlStatement : stmtList) {
                MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
                sqlStatement.accept(visitor);
                List<TableStat.Condition> conditions = visitor.getConditions();
                whereList.addAll(conditions);
            }
            log.info("解析sql后的过滤结果为:[{}]", whereList);
            return whereList;
        } catch (Exception e) {
//            throw new BusinessException("sql 解析异常,请检查sql:{}"+e.getMessage(),sql);
            return Collections.emptyList();
        }
    }

    /**
     * 获取已替换动态参数的sql
     *
     * @param sql
     * @return
     */
    public static String replaceSQLParams(String sql, Map<String, Object> paramsValueMap) {
        if (sql == null || sql.trim().length() < 1) {
            return null;
        }
        // 对#{}加''
        String addSingleQuoteSql = addSQLParamsSingleQuoteMark(sql);
        //  使用正则表达式替换参数
        //  替换#符号
        String repalceTransferredSql = replaceAllParams(addSingleQuoteSql, Constant.SQL_MARK_REG_PARAMS_PATTERN, paramsValueMap, '#', Constant.PARAMS_NO_VALUE_FLAG);
        //  替换$符号
        String finalNotransferredSql = replaceAllParams(repalceTransferredSql, Constant.REG_PARAMS_PATTERN, paramsValueMap, '$', Constant.PARAMS_NO_VALUE_FLAG);
        try {
            MySqlStatementParser parser = new MySqlStatementParser(finalNotransferredSql);
            SQLStatement stmt = parser.parseStatement();
            if (stmt instanceof SQLSelectStatement) {
                //  处理SQL,删除空值
                String finalSQL = deleteEmptyValueCondition(stmt);
                return finalSQL;
            }
        } catch (Exception e) {
//            throw new BusinessException("sql 解析异常,请检查sql:{}"+e.getMessage(),sql);
        }
        return "";
    }

    /**
     * 给SQL参数增加单引号，如果已有单引号则不增加单引号
     *
     * @param sql
     * @return
     */
    private static String addSQLParamsSingleQuoteMark(String sql) {
        List<ParamExp> list = ParamsUtil.findAllParams(sql, Constant.SQL_MARK_REG_PARAMS_PATTERN, false);
        if (list != null && list.size() > 0) {
            for (ParamExp paramExp : list) {
                if (!paramExp.getExp().startsWith("'")) {
                    String noSingleQuoteMark = "";//不带单引号
                    String singleQuoteMark = "";//带单引号
                    noSingleQuoteMark = "\\#\\{" + paramExp.getName() + "\\}";
                    singleQuoteMark = "'\\#\\{" + paramExp.getName() + "\\}'";
                    //先将带单引号的改为无单引号，再将其改为带单引号的，不能直接改是因为带单引号的会出现两个单引号
                    sql = sql.replaceAll(singleQuoteMark, noSingleQuoteMark).replaceAll(noSingleQuoteMark, singleQuoteMark);
                }
            }
        }

        return sql;
    }

    /**
     * 删除sql的条件中有指定标志flag的查询条件
     *
     * @param stmt
     * @return
     */
    private static String deleteEmptyValueCondition(SQLStatement stmt) {
        String sql = SQLUtils.toMySqlString(stmt);
        //  获取where条件
        List<TableStat.Condition> whereList = SqlUtil.getWhereListBySql(sql);
        //  删除控制
        String finalSQL = deleteSQLEmptyFlagCondition(sql, whereList);
        return finalSQL;
    }

    /**
     * 删除SQL语句中带有空值
     *
     * @param sql
     * @param conditionList
     * @return
     */
    private static String deleteSQLEmptyFlagCondition(String sql, List<TableStat.Condition> conditionList) {
        for (TableStat.Condition condition : conditionList) {
            if (Constant.PARAMS_NO_VALUE_FLAG.equals(condition.getValues().get(0))) {
                //  获取condition，并将进行截断，不取表名
                String noTransferString = conditionToNoTransferString(condition);
                String transferString = conditionToTransferString(condition);
                //需在条件前加空格，否则sql中有id=Constant.PARAMS_NO_VALUE_FLAG  and user_id=Constant.PARAMS_NO_VALUE_FLAG会变成
                //1=1  and user_1=1
                //加空格保证了是一个查询条件
                sql = sql.replace(" " + noTransferString, " " + Constant.SQL_NO_VALUE_EXPRESSION_REPLACE_FLAG);
                sql = sql.replace(" " + transferString, " " + Constant.SQL_NO_VALUE_EXPRESSION_REPLACE_FLAG);
            }
        }
        //替换1=1标志
        for (String s : Constant.SQL_EXPRESSION_DELETE_FLAG) {
            sql = sql.replaceAll(s, "");
        }

        sql = sql.trim();
        //替换所有多余的空格
        while (sql.indexOf("  ") != -1) {
            sql = sql.trim().replaceAll("  ", " ");
        }

        //检查是不是 where SQL_NO_VALUE_EXPRESSION_REPLACE_FLAG  结尾  是就删除
        if (sql.endsWith(("WHERE " + Constant.SQL_NO_VALUE_EXPRESSION_REPLACE_FLAG).trim())) {
            sql = sql.replace(("WHERE " + Constant.SQL_NO_VALUE_EXPRESSION_REPLACE_FLAG).trim(), "");
        }

        //替换
        for (String key : Constant.SQL_REPLACE_MAP.keySet()) {
            sql = sql.replace(key, Constant.SQL_REPLACE_MAP.get(key));
        }

        //将Constant.PARAMS_NO_VALUE_FLAG替换为空字符串
        sql = sql.replace(Constant.PARAMS_NO_VALUE_FLAG, "").trim();

        return sql;
    }

    private static String conditionToNoTransferString(TableStat.Condition condition) {
        StringBuilder buf = new StringBuilder();
        buf.append(condition.getColumn().getName());
        buf.append(' ');
        buf.append(condition.getOperator());
        List<Object> values = condition.getValues();
        if (values.size() == 1) {
            buf.append(' ');
            buf.append(String.valueOf(values.get(0)));
        } else if (values.size() > 0) {
            buf.append(" (");
            for (int i = 0; i < values.size(); ++i) {
                if (i != 0) {
                    buf.append(", ");
                }
                Object val = values.get(i);
                if (val instanceof String) {
                    String jsonStr = JSONUtils.toJSONString(val);
                    buf.append(jsonStr);
                } else {
                    buf.append(String.valueOf(val));
                }
            }
            buf.append(")");
        }

        return buf.toString();
    }

    private static String conditionToTransferString(TableStat.Condition condition) {
        StringBuilder buf = new StringBuilder();
        buf.append(condition.getColumn().getName());
        buf.append(' ');
        buf.append(condition.getOperator());
        List<Object> values = condition.getValues();
        if (values.size() == 1) {
            buf.append(' ');
            buf.append('\'');
            buf.append(String.valueOf(values.get(0)));
            buf.append('\'');
        } else if (values.size() > 0) {
            buf.append(" \'(");
            for (int i = 0; i < values.size(); ++i) {
                if (i != 0) {
                    buf.append(", ");
                }
                Object val = values.get(i);
                if (val instanceof String) {
                    String jsonStr = JSONUtils.toJSONString(val);
                    buf.append(jsonStr);
                } else {
                    buf.append(String.valueOf(val));
                }
            }
            buf.append("\')");
        }

        return buf.toString();
    }
}
