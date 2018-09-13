package webservice;

import javax.xml.ws.Endpoint;

/**
 * Created by Administrator on 2018/8/17.
 */
public class WebServiceApp {
    public static void main(String[] args) {
        IUserWebService userWebService = new UserWebServiceImpl();
        String address = "http://localhost:8080/userWebService";

        Endpoint.publish(address, userWebService);
        System.out.println("web service 已启动");
    }
}
