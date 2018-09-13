package redisRatelimiter.config;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/9/7.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    int limit() default 5;
    int timeout() default 1000;
}
