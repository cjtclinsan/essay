package webservice;

import javax.jws.WebService;

/**
 * Created by Administrator on 2018/8/17.
 */
@WebService(endpointInterface = "webservice.IUserWebService",serviceName = "UserWebServiceImpl")
public class UserWebServiceImpl implements IUserWebService {
    @Override
    public String getWebService() {

        return "获取Webservice成功";
    }
}
