package com.neo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Luo
 * @description:
 * @time: 2020/10/19 18:48
 * Modified By:
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public Object test() throws InterruptedException {
        System.out.println("进入程序");
        Thread.sleep(10000);
        System.out.println("程序结束");
        return "结束";
    }
}
