package listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Administrator on 2018/8/8.
 */
@WebListener
public class MyServletContextAttributeListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent scab) {
        String name = scab.getName();
        Object val = scab.getValue();
    }

    //删除属性时触发
    @Override
    public void attributeRemoved(ServletContextAttributeEvent scab) {

    }

    //替换属性值时触发
    @Override
    public void attributeReplaced(ServletContextAttributeEvent scab) {

    }
}
