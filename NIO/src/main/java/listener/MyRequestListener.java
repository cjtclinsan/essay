package listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/8/8.
 */
@WebListener
public class MyRequestListener implements ServletRequestListener {

    //用户请求结束、被销毁时触发
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        String ip = req.getRemoteAddr();
    }

    //用户请求到达、被初始化时触发
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        String ip = req.getRemoteAddr();
    }
}
