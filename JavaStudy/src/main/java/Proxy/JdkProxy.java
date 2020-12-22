package Proxy;

import java.lang.reflect.Proxy;

/**
 * @author: Luo
 * @description:
 * @time: 2020/10/10 15:59
 * Modified By:
 */
public class JdkProxy {
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标类的类加载
                target.getClass().getInterfaces(),  // 代理需要实现的接口，可指定多个
                new DebugInvocationHandler(target)   // 代理对象对应的自定义 InvocationHandler
        );
    }

    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxy.getProxy(new SmsServiceImpl());
        smsService.send("test");
    }
}
