package webservice;

import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

/**
 * Created by Administrator on 2018/8/17.
 */
public class WebServiceClient {
    public static void main(String[] args) {
        JaxWsPortProxyFactoryBean bean = new JaxWsPortProxyFactoryBean();

        bean.setServiceInterface(IUserWebService.class);

        bean.setEndpointAddress("http://localhost:8080/userWebService");

        IUserWebService service = (IUserWebService) bean.createJaxWsService();
        String str = service.getWebService();
        System.out.println(str);
    }
}
