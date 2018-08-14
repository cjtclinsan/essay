package sample.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/28.
 */
@Controller
public class PageController {
    @Value("${application.hello:Hello tc}")
    private String hello = "Hello tc";

    /**
     * 默认页<br/>
     * @RequestMapping("/") 和 @RequestMapping 是有区别的
     * 如果不写参数，则为全局默认页，加入输入404页面，也会自动访问到这个页面。
     * 如果加了参数“/”，则只认为是根页面。
     */
    @RequestMapping(value = {"/","/index"})
    public String index(Map<String,Object> model){
        // 直接返回字符串，框架默认会去 spring.view.prefix 目录下的 （index拼接spring.view.suffix）页面
        // 本例为 /WEB-INF/jsp/index.jsp
        model.put("time",new Date());
        model.put("message",this.hello);
        return "index";
    }

    /**
     * 响应到JSP页面page1
     */
    @RequestMapping("/page1")
    public ModelAndView page1(){
        ModelAndView model = new ModelAndView("page/page1");
        model.addObject("content", hello);
        return  model;
    }

    /**
     * 响应到JSP页面page1（可以直接使用Model封装内容，直接返回页面字符串）
     */
    @RequestMapping("/page2")
    public String page2(Model model){
        model.addAttribute("content", hello+"(第二种)");
        return  "page/page1";
    }
}
