package com.neo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.domain.Copy;
import com.domain.SbProject;
import com.domain.Temp;
import com.neo.mapper.CopyMapper;
import com.neo.mapper.SbProjectMapper;
import com.neo.mapper.TempMapper;
import com.neo.mapper.OtherMapper;
import com.neo.mapper.UserMapper;
import com.neo.model.Other;
import com.neo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OtherMapper otherMapper;

    @Autowired
    private CopyMapper copyMapper;

    @Autowired
    SbProjectMapper sbProjectMapper;

    @Autowired
    TempMapper tempMapper;

    @Test
    public void jky() {
        List<Copy> list = new ArrayList<>();
        List<Temp> temp = tempMapper.selectMore();
        List<SbProject> projects = new ArrayList<>();
        for (Temp l1 : temp) {
            SbProject project = sbProjectMapper.selectMore(l1.getName(),l1.getZuozhe());
            projects.add(project);
        }
        System.out.println("");
    }

    @Test
    public void testSelectOne() {
        Other user = otherMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testRelatedSelect() {
        Object res = userMapper.getRelatedSelect(1L);
        System.out.println(res);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("微笑");
        user.setAge(3);
        user.setEmail("neo@tooool.org");
        assertThat(userMapper.insert(user)).isGreaterThan(0);
        // 成功直接拿会写的 ID
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void testDelete() {
        assertThat(userMapper.deleteById(3L)).isGreaterThan(0);
        assertThat(userMapper.delete(new QueryWrapper<User>()
                .lambda().eq(User::getName, "smile"))).isGreaterThan(0);
    }

    @Test
    public void testUpdate() {
        User user = userMapper.selectById(2);
        assertThat(user.getAge()).isEqualTo(36);
        assertThat(user.getName()).isEqualTo("keep");

        userMapper.update(
                null,
                Wrappers.<User>lambdaUpdate().set(User::getEmail, "123@123").eq(User::getId, 2)
        );
        assertThat(userMapper.selectById(2).getEmail()).isEqualTo("123@123");
    }

    @Test
    public void testSelect() {
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectCondition() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testPage() {
        System.out.println("----- baseMapper 自带分页 ------");
        Page<User> page = new Page<>(1, 2);
        IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>()
                .gt("age", 6));
        assertThat(page).isSameAs(userIPage);
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        print(userIPage.getRecords());
        System.out.println("----- baseMapper 自带分页 ------");
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }


}