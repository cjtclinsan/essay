package listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Administrator on 2018/8/8.
 * @author cjtc
 */
@WebListener
public class ListenerTest implements HttpSessionListener {

    public static int TOTAL_ONLINE_USERS = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        if(null == servletContext.getAttribute("TOTAL_ONLINE_USERS")){
            TOTAL_ONLINE_USERS = 0;
        }else{
            TOTAL_ONLINE_USERS = (Integer)servletContext.getAttribute("TOTAL_ONLINE_USERS");
        }

        TOTAL_ONLINE_USERS++;
        servletContext.setAttribute("TOTAL_ONLINE_USERS",TOTAL_ONLINE_USERS);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        if(null == servletContext.getAttribute("TOTAL_ONLINE_USERS")){
            TOTAL_ONLINE_USERS = 0;
        }else{
            TOTAL_ONLINE_USERS = (Integer)servletContext.getAttribute("TOTAL_ONLINE_USERS");
        }

        if( TOTAL_ONLINE_USERS == 0 ){
            servletContext.setAttribute("TOTAL_ONLINE_USERS", 0);
        }else{
            TOTAL_ONLINE_USERS--;
            servletContext.setAttribute("TOTAL_ONLINE_USERS",TOTAL_ONLINE_USERS);
        }
    }
}
