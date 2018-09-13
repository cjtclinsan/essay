package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Administrator on 2018/8/8.
 */
@WebListener
public class MyServetContextListener implements ServletContextListener {
    //web应用关闭时调用该方法
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        String appName = application.getInitParameter("userName");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        String appName = application.getInitParameter("userName");
    }
}
