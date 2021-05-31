package com.neo;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloApplicationTests {

	@Test
	public void contextLoads() {
		String a = "(1,2,3)";
		String c = "(1,2,3)";
		List<Integer> list = JSON.parseArray(a, Integer.class);
		List<String> list1 = JSON.parseArray(c, String.class);

		System.out.println(JSON.toJSONString(a));
		System.out.println(JSON.toJSONString(list));
		System.out.println(JSON.toJSONString(c));
		System.out.println(JSON.toJSONString(list1));

	}

}
