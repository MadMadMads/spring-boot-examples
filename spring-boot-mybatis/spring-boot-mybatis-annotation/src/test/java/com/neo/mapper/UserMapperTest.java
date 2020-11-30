package com.neo.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neo.Contotller.name;
import com.neo.model.Copy;
import com.neo.model.SbProject;
import com.neo.model.Temp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private CopyMapper copyMapper;

    @Autowired
    SbProjectMapper sbProjectMapper;

    @Autowired
    TempMapper tempMapper;

    @Autowired
    TestMapper testMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        List<Map<String, Object>> list =  jdbcTemplate.queryForList("select * from a");
        System.out.println();
    }

    @Test
    public void test1() {
        SbProject sbProject = sbProjectMapper.selectMore("“1+X”证书制度下中职汽修专业“双融合四推进”人才培养模式建构与实践", "胡萍");
        System.out.println();
    }

    @Test
    public void jky() {
        List<Copy> list = new ArrayList<>();
        List<Temp> temp = tempMapper.selectMore();
        List<SbProject> projects = new ArrayList<>();
        for (Temp l1 : temp) {
            SbProject project = sbProjectMapper.selectMore(l1.getName(), l1.getZuozhe());
            projects.add(project);
        }
        System.out.println("");
    }
}