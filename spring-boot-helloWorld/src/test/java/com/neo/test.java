package com.neo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: Luozhi
 * @create: 2021-05-13 14:30
 **/
public class test {
    @Test
    public void test1() {
        HashMap<String, Object> maxLengthData;
        List<List<HashMap<String, Object>>> mapList = new ArrayList<>();
        ArrayList<String> objects = new ArrayList<>();
        ArrayList<HashMap<String, Object>> objects1 = new ArrayList<>();
        ArrayList<HashMap<String, Object>> objects2 = new ArrayList<>();
        String first = "{\n" +
                "  \"key1\":\"value1\"\n" +
                "}";
        String second = "{\n" +
                "  \"key1\":\"value1\",\n" +
                "   \"key2\":\"value2\"\n" +
                "}";
        String thrid = "{\n" +
                "  \"key1\":\"value1\",\n" +
                "   \"key2\":\"value2\",\n" +
                "     \"key3\":\"value3\"\n" +
                "}";
        String four = "{\n" +
                "  \"key1\":\"value1\",\n" +
                "   \"key2\":\"value2\",\n" +
                "     \"key3\":\"value3\",\n" +
                "     \"key4\":\"value4\"\n" +
                "}";
        String five = "{\n" +
                "  \"key1\":\"value1\",\n" +
                "   \"key2\":\"value2\",\n" +
                "     \"key3\":\"value3\",\n" +
                "     \"key4\":\"value4\",\n" +
                "       \"key5\":\"5\"\n" +
                "}";
        String six = "{\n" +
                "  \"key1\":\"value1\",\n" +
                "   \"key2\":\"value2\",\n" +
                "     \"key3\":\"value3\",\n" +
                "     \"key4\":\"value4\",\n" +
                "       \"key5\":\"5\",\n" +
                "         \"key6\":\"6\"\n" +
                "}";
        HashMap<String, Object> map1 = JSON.parseObject(first, new TypeReference<HashMap<String, Object>>() {
        });
        HashMap<String, Object> map2 = JSON.parseObject(second, new TypeReference<HashMap<String, Object>>() {
        });
        HashMap<String, Object> map3 = JSON.parseObject(thrid, new TypeReference<HashMap<String, Object>>() {
        });
        HashMap<String, Object> map4 = JSON.parseObject(four, new TypeReference<HashMap<String, Object>>() {
        });
        HashMap<String, Object> map5 = JSON.parseObject(five, new TypeReference<HashMap<String, Object>>() {
        });
        HashMap<String, Object> map6 = JSON.parseObject(six, new TypeReference<HashMap<String, Object>>() {
        });
        objects.add(JSON.toJSONString(map1));
        objects.add(JSON.toJSONString(map5));
        objects1.add(map1);
        objects1.add(map6);
        objects2.add(map2);
        objects2.add(map4);
        mapList.add(objects2);
//        mapList.add(objects);
        mapList.add(objects1);
        List<HashMap<String, Object>> mapList1 = JSON.parseObject(JSON.toJSONString(objects), new TypeReference<List<HashMap<String, Object>>>() {
        });
        System.out.println(mapList1);
        List<List<HashMap<String, Object>>> list = JSON.parseObject(JSON.toJSONString(mapList), new TypeReference<List<List<HashMap<String, Object>>>>() {
        });

        /*maxLengthData = mapList.stream().max(Comparator.comparingInt(vo -> {
            HashMap<String, Object> map = vo.stream().max(Comparator.comparingInt(e -> e.size())).get();
            return map.size();
        })).get().stream().max(Comparator.comparingInt(vo -> vo.size())).get();
        System.out.println(maxLengthData);*/
    }
}
