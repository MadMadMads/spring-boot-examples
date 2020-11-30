package com.neo.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.neo.model.Copy;
import com.neo.model.SbProject;
import com.neo.model.Temp;
import jdk.nashorn.internal.runtime.FindProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JkyTest {

    @Autowired
    private CopyMapper copyMapper;

    @Autowired
    SbProjectMapper sbProjectMapper;

    @Autowired
    TempMapper tempMapper;

    @Test
    public void test1() {
        SbProject sbProject = sbProjectMapper.selectMore("“1+X”证书制度下中职汽修专业“双融合四推进”人才培养模式建构与实践", "胡萍");
        System.out.println(sbProject);
        Copy copy = new Copy();
        BeanUtils.copyProperties(sbProject,copy);
        System.out.println();
    }

    @Test
    public void jky() {
        List<Copy> list = new ArrayList<>();
        List<Temp> temp = tempMapper.selectMore();
        List<SbProject> projects = new ArrayList<>();
        for (Temp l1 : temp) {
            SbProject project = sbProjectMapper.selectMore(l1.getName(), l1.getZuozhe());
            if (project != null) {
                l1.setName(project.getProName());
            }
            projects.add(project);
        }
        Iterator<SbProject> iterator = projects.iterator();
        while(iterator.hasNext()){
            SbProject project1 = iterator.next();
            if (project1 == null) {
                iterator.remove();
            }
        }
        for (SbProject project : projects) {
            Copy copy = new Copy();
            if (project != null) {
            BeanUtils.copyProperties(project,copy);
            } else {
                System.out.println("空");
            }
            list.add(copy);
        }
        byte a = 0;
        int i = 2661;
        for (Temp temp1 : temp) {
                for (Copy copy : list) {
                    if (copy.getProName() != null &&temp1.getName().contains(copy.getProName())
                            &&temp1.getZuozhe().equals(copy.getPuserName())) {
                        copy.setProNumber(temp1.getId());
                        copy.setPassHasfile(a);
                        copy.setProIsHistory(a);
                        copy.setProStepcode("step7");
                        copy.setPassId(i++);
                        copy.setProStatus((byte) 2);
                        if (temp1.getType().equals("重点有经费")) {
                            copy.setPassType((byte) 1);
                        } else if (temp1.getType().equals("重点无经费")) {
                            copy.setPassType((byte) 2);
                        } else if (temp1.getType().equals("一般规划")) {
                            copy.setPassType((byte) 3);
                        } else if (temp1.getType().equals("重大招标课题")) {
                            copy.setPassType((byte) 4);
                        }
                    }
                }

            }

        List<Temp> list1 = new ArrayList<>();
        for (Temp temp1 : temp) {
            int flag = 0;
            for (Copy copy : list) {
                if (temp1.getName().equals(copy.getProName())) {flag =1;}
            }
            if (flag == 0) {
                list1.add(temp1);
            }
            flag = 0;
        }

        copyMapper.batchInsert(list);
        System.out.println("");
    }

    @Test
    public void jky1() {
        List<Copy> list = new ArrayList<>();
        List<Temp> temp = tempMapper.selectMore1();
        List<SbProject> projects = new ArrayList<>();
        for (Temp l1 : temp) {
           SbProject project = sbProjectMapper.selectMore(l1.getName(),l1.getZuozhe());
           projects.add(project);
        }
        Iterator<SbProject> iterator = projects.iterator();
        while(iterator.hasNext()){
            SbProject project1 = iterator.next();
            if (project1 == null) {
                iterator.remove();
            }
        }
        for (SbProject project : projects) {
            Copy copy = new Copy();
            if (project != null) {
                BeanUtils.copyProperties(project,copy);
            } else {
                System.out.println("空");
            }
            list.add(copy);
        }
        byte a = 0;
        int i = 2661;
        for (Temp temp1 : temp) {
            for (Copy copy : list) {
                if (copy.getProName() != null &&temp1.getName().contains(copy.getProName())
                        &&temp1.getZuozhe().equals(copy.getPuserName())) {
                    copy.setProNumber(temp1.getId());
                    copy.setPassHasfile(a);
                    copy.setProIsHistory(a);
                    copy.setProStepcode("step7");
                    copy.setPassId(i++);
                    copy.setProStatus((byte) 2);
                    if (temp1.getType().equals("重点有经费")) {
                        copy.setPassType((byte) 1);
                    } else if (temp1.getType().equals("重点无经费")) {
                        copy.setPassType((byte) 2);
                    } else if (temp1.getType().equals("一般规划")) {
                        copy.setPassType((byte) 3);
                    } else if (temp1.getType().equals("重大招标课题")) {
                        copy.setPassType((byte) 4);
                    }
                }
            }
        }

        List<Temp> list1 = new ArrayList<>();
        for (Temp temp1 : temp) {
            int flag = 0;
            for (Copy copy : list) {
                if (temp1.getName().equals(copy.getProName())) {flag =1;}
            }
            if (flag == 0) {
                list1.add(temp1);
                System.out.println(temp1.getName()+","+temp1.getZuozhe());
            }
            flag = 0;
        }
        System.out.println("");
        copyMapper.batchInsert(list);
    }
}