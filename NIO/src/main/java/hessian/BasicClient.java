package hessian;

import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;

/**
 * Created by Administrator on 2018/8/18.
 */
public class BasicClient {
    public static void main(String[] args) {

        String url = "http://localhost:8080/hessian";

        HessianProxyFactory factory = new HessianProxyFactory();
        factory.setOverloadEnabled(true);       //支持方法重载
        try {
            Basic basic = (Basic) factory.create(Basic.class, url);

            System.out.println(basic.sayHello("ttc"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
