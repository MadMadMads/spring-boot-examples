package com.neo;

import com.neo.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.neo.po.Student;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ExecutorService;

/**
 * @author: Luo
 * @description:
 * @time: 2020/11/16 12:27
 * Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class transcationalTest {
    @Resource
    StudentMapper mapper;

    @Test
    public void test1() {

    }
    @Transactional
    public void A() {
        mapper.insert(new Student("123","xx",new Date(1233-11-23),"男"));
        B();
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void B() {
        mapper.insert(new Student("456","xx",new Date(1233-11-23),"男"));
        throw new RuntimeException();
    }

}

