package cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/9/13.
 */
public class ShipProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    /**
     * 通过class获取代理对象
     *
     * */
    public Object getProxy(Class c){
        enhancer.setSuperclass(c);
        enhancer.setCallback(this);
        return  enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("日志开始...");
        //代理类调用父类的方法
        methodProxy.invokeSuper(o, objects);

        System.out.println("日志结束...");
        return null;
    }
}
