package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: Luo
 * @description:
 * @time: 2020/10/10 16:01
 * Modified By:
 */
public class DebugInvocationHandler implements InvocationHandler {
    /**
     * 代理类中的真实对象
     */
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("proxy"+proxy.getClass());
        System.out.println("before method " + method.getName());
        Object result = method.invoke(target, args);
        //调用方法之后， 我们同样可以添加自己的操作
        System.out.println("after method " + method.getName());
        return result;
    }
}
