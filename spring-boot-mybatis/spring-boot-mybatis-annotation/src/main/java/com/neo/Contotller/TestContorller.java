package com.neo.Contotller;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.xdevapi.JsonArray;
import com.neo.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Luo
 * @description:
 * @time: 2020/9/9 18:44
 * Modified By:
 */
@RestController
public class TestContorller {

    @Autowired
    TestMapper testMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/test")
    public JSONArray test() {
        JSONArray jsonArray = new JSONArray();

        List<Map<String, Object>> list =  jdbcTemplate.queryForList("select * from b");
         for (int i = list.size() - 1; i >= 0; i--) {
            ArrayList<Object> name = new ArrayList();
            name.add(new url(list.get(i).get("url").toString()));
            name.add(new date(list.get(i).get("date").toString()));
            name.add(new content(list.get(i).get("content").toString()));
            Map<String , Object> name1 = new HashMap<>();
            name1.put("name",name);
            jsonArray.add(name1);
         }
        return jsonArray;
    }
}
