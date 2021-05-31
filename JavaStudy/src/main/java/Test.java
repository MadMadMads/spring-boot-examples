import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author: Luo
 * @description:
 * @time: 2020/11/29 17:25
 * Modified By:
 */
public class Test {
    static final  Pattern pattern = Pattern.compile("\\[[^\\]]*\\]");
    private static Boolean isArray(String s) {
        if (s.charAt(0) == '['
                && s.charAt(s.length() - 1) == ']') {
            return true;
        } else {
            return false;
        }
    }
    public static void main(String[] args) throws ClassNotFoundException {
        /*String s = "{\n" +
                "        \"order_keys\": \"[\\\"finish_class_rate\\\"]\",\n" +
                "        \"order_types\": \"[\\\"desc\\\"]\"\n" +
                "    }";
        Map<String, String> map = JSON.parseObject(s, Map.class);
        System.out.println(map.toString());*/
        String[] s = new String[]{"\"1\"","2"};
        for (String str : s) {
            str = str.replaceAll("\"","");
            str = str.replaceAll("\'","");
        }
        List<String> res = new ArrayList<>();
        for (String str : s) {
            str = str.replaceAll("\"","");
            str = str.replaceAll("\'","");
            res.add(str);
        }
        res.toArray(new String[s.length]);
        System.out.println(res.toString());
    }
}
