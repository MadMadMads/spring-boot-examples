package jdk;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: Luo
 * @description:
 * @time: 2020/10/21 13:58
 * Modified By:
 */
public class ReflexTest {
    @Test
    public void test1() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("jdk.Man");
        Constructor<?> constructor = clazz.getConstructor(String.class);
        Object o = constructor.newInstance("123");
        System.out.println(o);
    }
    @Test
    public void test2() throws Exception {
        Man man = new Man();
        Class classobj1 = man.getClass();
        Method method = classobj1.getMethod("eat1", String.class);
        method.invoke(man,"test");
        System.out.println(classobj1.getDeclaredMethods());
    }

}
