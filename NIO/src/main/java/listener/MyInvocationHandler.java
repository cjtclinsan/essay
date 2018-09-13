package listener;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/8/9.
 */
public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    MyInvocationHandler(){super();}

    MyInvocationHandler(Object target){
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if( "quanli".equals(method.getName()) ){
            return method.invoke(target, args);
        }else{
            return method.invoke(target, args);
        }
    }
}
