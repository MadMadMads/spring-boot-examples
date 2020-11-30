package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {

    @Test
    public void contextLoads() {
        int i = 123;
        Object j = (Object)i;
        System.out.println(j);
    }

}
