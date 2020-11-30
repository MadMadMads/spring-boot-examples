package com.neo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisAnnotationApplicationTests {

	@Test
	public void contextLoads1() {
		contextLoads2();
	}

	@Test
	public void contextLoads2() {
		contextLoads3();
	}
	@Test
	public void contextLoads3() {
		StackTraceElement[] stackTraceElements = (new Throwable()).getStackTrace();
		for (int i = 0; i < stackTraceElements.length; i++) {
			StackTraceElement stackTraceElement = stackTraceElements[i];
			System.out.println("index=" + i + "----------------------------------");
			System.out.println("className=" + stackTraceElement.getClassName());
			System.out.println("fileName=" + stackTraceElement.getFileName());
			System.out.println("methodName=" + stackTraceElement.getMethodName());
			System.out.println("lineNumber=" + stackTraceElement.getLineNumber());
		}
		System.out.println("hello world");
	}
}
