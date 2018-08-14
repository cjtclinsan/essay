package sample.memcached;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/7/2.
 */
@Configuration
public class MemcacheConfiguration {
    @Value("${memcache.servers}")
    private String[] servers;
    @Value("${memcache.initConn}")
    private int initConn;
    @Value("${memcache.minConn}")
    private int minConn;
    @Value("${memcache.maxConn}")
    private int maxConn;
    @Value("${memcache.maintSleep}")
    private int maintSleep;
    @Value("${memcache.nagle}")
    private boolean nagle;
    @Value("${memcache.socketTO}")
    private int socketTO;

    @Bean
    public SockIOPool sockIOPool(){
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(servers);
        pool.setInitConn(initConn);
        pool.setMinConn(minConn);
        pool.setMaxConn(maxConn);
        pool.setMaintSleep(maintSleep);
        pool.setNagle(nagle);
        pool.setSocketTO(socketTO);
        pool.initialize();
        return pool;
    }

    @Bean
    public MemCachedClient memCachedClient(){
        return new MemCachedClient();
    }
}
