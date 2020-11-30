package com.neo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: Luo
 * @description:
 * @time: 2020/10/19 18:40
 * Modified By:
 */
@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;



    public String produce(String key, String msg) {
        Map<String, String> map = new HashMap();
        map.put("fileId", msg);
        redisTemplate.opsForList().leftPush(key, map);
        return "生产者";
    }

    private void processOrderImport(String key) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            while (true) {
                Object object = redisTemplate.opsForList().rightPop(key, 1, TimeUnit.SECONDS);
                if (null == object) {
                    continue;
                }
                executorService.execute(new Task());

            }
        });
    }

    public class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("开始消费");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
