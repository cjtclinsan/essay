package webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by Administrator on 2018/8/17.
 */
@WebService(serviceName = "MyService",targetNamespace = "http://www.baidu.com")
public class HelloService {
    @WebMethod(operationName = "AliassayHello")
    @WebResult(name = "myReturn")
    public String sayHello(@WebParam(name = "name") String name){
        return "hello:"+name;
    }
    public String sayGoodbye(String name){

        return  "goodbye: " + name;
    }
    @WebMethod(exclude = true)    //当前方法不被发布出去
    public String sayHello2(String name){
        return "hello2:"+name;
    }

    public static void main(String[] args) {
        Endpoint.publish("http://test.cm/", new HelloService());
        System.out.println("Server ready...");
    }
}
