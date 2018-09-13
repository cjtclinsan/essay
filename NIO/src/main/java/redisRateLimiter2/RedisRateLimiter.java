package redisRateLimiter2;

import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/9/7.
 */
public class RedisRateLimiter {
    private JedisPool jedisPool;
    private TimeUnit timeUnit;
    private int permitsPerUnit;


    private static final int PERIOD_SECOND_TTL = 10;
    private static final int PERIOD_MINUTE_TTL = 2 * 60 + 10;
    private static final int PERIOD_HOUR_TTL = 2 * 3600 + 10;
    private static final int PERIOD_DAY_TTL = 2 * 3600 * 24 + 10;

    private static final int MICROSECONDS_IN_MINUTE = 60 * 1000000;
    private static final int MICROSECONDS_IN_HOUR = 3600 * 1000000;
    private static final int MICROSECONDS_IN_DAY = 24 * 3600 * 1000000;

    public RedisRateLimiter(JedisPool jedisPool, TimeUnit timeUnit, int permitsPerUnit) {
        this.jedisPool = jedisPool;
        this.timeUnit = timeUnit;
        this.permitsPerUnit = permitsPerUnit;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public int getPermitsPerUnit() {
        return permitsPerUnit;
    }

    public void setPermitsPerUnit(int permitsPerUnit) {
        this.permitsPerUnit = permitsPerUnit;
    }
}
