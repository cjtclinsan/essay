package hashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/8/25.
 */
public class ConcurrentMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(100);
        map.put("1","1");
        System.out.println(map.size());

        int n = 150;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n+1);
    }
}
