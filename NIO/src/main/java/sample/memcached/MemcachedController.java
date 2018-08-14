package sample.memcached;

import com.whalin.MemCached.MemCachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/7/2.
 */
@RestController
public class MemcachedController {
    @Autowired
    private MemCachedClient memCachedClient;

    @RequestMapping("/memcached")
    @ResponseBody
    public String getMemecachedInfo(){
        System.out.println(memCachedClient.get("id"));
        if( memCachedClient.get("id") != null ){
            return memCachedClient.get("id").toString();
        }else{
            boolean flag = memCachedClient.set("id", "存入数据");
            return flag+"";
        }
    }
}
