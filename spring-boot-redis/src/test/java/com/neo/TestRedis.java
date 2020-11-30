package com.neo;

import com.neo.model.FileTest;
import com.neo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
	@Autowired
    private RedisTemplate redisTemplate;

	@Test
    public void testZset() throws InterruptedException {
        FileTest file1 = new FileTest("test",System.currentTimeMillis());
        FileTest file2 = new FileTest("test",System.currentTimeMillis()*2);
        redisTemplate.opsForZSet().add("Zset",file1,System.currentTimeMillis());
        redisTemplate.opsForZSet().add("Zset",file2,System.currentTimeMillis()+10);
        Thread.sleep(1000);
        Set<FileTest> set = redisTemplate.opsForZSet().rangeByScore("Zset",0,System.currentTimeMillis(),0,System.currentTimeMillis() - 100);
        System.out.println();
    }

	@Test
    public void redisQueue() {
	    
    }

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }
    @Test
    public void hashtest() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        map.put("key5","value5");
        redisTemplate.opsForHash().putAll("map1",map);
        Map<String,String> resultMap= redisTemplate.opsForHash().entries("map1");
        List<String> reslutMapList=redisTemplate.opsForHash().values("map1");
        Set<String> resultMapSet=redisTemplate.opsForHash().keys("map1");
        String value=(String)redisTemplate.opsForHash().get("map1","key1");
        System.out.println("value:"+value);
        System.out.println("resultMapSet:"+resultMapSet);
        System.out.println("resultMap:"+resultMap);
        System.out.println("resulreslutMapListtMap:"+reslutMapList);
    }
    @Test
    public void testObj() throws Exception {
        User user=new User("aa@126.com", "aa", "aa123456", "aa","123");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.neo.f", user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        System.out.println(operations.get("com.neox"));
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neo.f");
        if(exists){
        	System.out.println("exists is true");
        }else{
        	System.out.println("exists is false");
        }
       // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }
}