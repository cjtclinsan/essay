package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2018/8/30.
 */
public class DynamicProxy implements InvocationHandler {
    private Object source;

    public DynamicProxy(Object source) {
        this.source = source;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");

        Method method1 = source.getClass().getDeclaredMethod(method.getName(),method.getParameterTypes());

        method1.setAccessible(true);

        Object result = method1.invoke(source,args);
        System.out.println("after");
        return result;
    }

    public static void main(String[] args) {
        TestInterface object = (TestInterface) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                        new Class[]{TestInterface.class},
                new DynamicProxy(new TestClass()));

        object.method1();
        object.method2();
        object.method3();
    }
}
