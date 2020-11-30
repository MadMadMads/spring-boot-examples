package com.neo;

import com.neo.Service.Imp.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Luo
 * @description:
 * @time: 2020/9/15 15:14
 * Modified By:
 */
@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private ExampleService exampleService;

    @GetMapping("/input")
    public String input(String word){
        return exampleService.wrap(word);
    }

}