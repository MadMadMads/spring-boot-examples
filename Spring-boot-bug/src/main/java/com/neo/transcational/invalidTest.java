package com.neo.transcational;

import com.neo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.neo.po.Student;

import java.util.Date;

/**
 * @author: Luo
 * @description:
 * @time: 2020/11/16 12:13
 * Modified By:
 */
public class invalidTest {
    @Autowired
    StudentMapper studentMapper;
    @Transactional
    public void A() {
        studentMapper.insert(new Student("123","罗智",new Date(1996-11-23),"男"));
    }
    @Transactional
    public void B() {
        studentMapper.insert(new Student("456","罗智",new Date(1996-11-23),"男"));
    }

}
