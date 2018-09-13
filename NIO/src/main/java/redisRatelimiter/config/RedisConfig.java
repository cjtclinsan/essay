package redisRatelimiter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2018/9/7.
 *
 */
@Configuration
@ComponentScan("redisRatelimiter.config")
@PropertySource("classpath:/application.properties")
public class RedisConfig {
    private @Value("${redis.host}") String redisHostName;
    private @Value("${redis.port}") int redisPort;
    private @Value("${redis.password}") String password;
    //@Autowired
    //JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        return new JedisPool(jedisPoolConfig, redisHostName,
                redisPort,0, password
                );
    }
}
