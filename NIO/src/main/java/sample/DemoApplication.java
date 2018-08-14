package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import sample.servlet.MyServlet;

@SpringBootApplication
@ServletComponentScan
public class DemoApplication {
	@Bean
	public ServletRegistrationBean servletRegistrationBean(){
		return new ServletRegistrationBean(new MyServlet(), "/xs/*");
	}

//	/**
//	 * 修改DispatcherServlet默认配置
//	 *
//	 * @param dispatcherServlet
//	 * @return
//	 * @author SHANHY
//	 * @create  2016年1月6日
//	 */
//	@Bean
//	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//		registration.getUrlMappings().clear();
//		registration.addUrlMappings("*.do");
//		registration.addUrlMappings("*.json");
//		return registration;
//	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
