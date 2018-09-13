package redisRatelimiter.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redisRatelimiter.config.RateLimiter;

/**
 * Created by Administrator on 2018/9/7.
 */
@RestController
public class TestController {

    @RateLimiter(limit = 2, timeout = 5000)
    @GetMapping("/test1")
    public String test1(){
        return "test1";
    }

    @RateLimiter(limit = 5, timeout = 5000)
    @GetMapping("/test2")
    public String test2(){
        return "test2";
    }
}
