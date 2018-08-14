package sample.memcached;

import com.whalin.MemCached.MemCachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2018/7/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMemcacheApplicationTests {
    @Autowired
    private MemCachedClient memCachedClient;

    @Test
    public void getContextLoads(){
        boolean i = memCachedClient.set("id", 1232, 1000);
        System.out.println(String.valueOf(i));
        System.out.println(memCachedClient.get("id"));
    }

}
