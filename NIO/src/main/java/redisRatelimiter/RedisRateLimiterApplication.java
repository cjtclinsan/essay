package redisRatelimiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redisRatelimiter.config.RateLimiter;
import redisRatelimiter.web.RedisRateLimiterPlus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/9/7.
 */
@SpringBootApplication
public class RedisRateLimiterApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisRateLimiterApplication.class, args);
    }

    @Configuration
    static class WebMvcConfigurer extends WebMvcConfigurerAdapter{
        private JedisPool jedisPool;

        public void addIntercetors(InterceptorRegistry registry){
            registry.addInterceptor(new HandlerInterceptorAdapter() {
                @Override
                public boolean preHandle(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler) throws Exception {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    Method method = handlerMethod.getMethod();
                    RateLimiter rateLimiter = method.getAnnotation(RateLimiter.class);

                    if( null != rateLimiter ){
                        int limit = rateLimiter.limit();
                        int timeout = rateLimiter.timeout();

                        Jedis jedis = jedisPool.getResource();
                        String token = RedisRateLimiterPlus.acquireTokenFromBucket(
                                jedis, method.getName(), limit, timeout);

                        if( null == token ){
                            response.sendError(500);
                            return false;
                        }
                        jedis.close();
                    }
                    return true;
                }
            }).addPathPatterns("/*");

        }
    }
}
