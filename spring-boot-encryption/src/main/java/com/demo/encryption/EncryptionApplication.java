package com.demo.encryption;

import com.demo.encryption.service.TestService;
import com.demo.encryption.service.TestServiceImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Luo
 * @description:
 * @time: 2020/12/15 12:37
 * Modified By:
 */
@SpringBootApplication
public class EncryptionApplication {
    public static void main(String[] args) {

        SpringApplication.run(EncryptionApplication.class, args);
        TestService testService = new TestServiceImp();
        testService.test();
    }

}