package loadbalance;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Created by Administrator on 2018/8/16.
 * 负载均衡算法
 */
public class RoundRobin {

    private static Map<String, Integer> serviceWeightMap = new HashMap<String, Integer>();
    private static Map<String, Integer> serverMap = new HashMap<String, Integer>();
    private static Set<String> keySet = new HashSet<String>();
    private static ArrayList<String> keyList = new ArrayList<String>();
    static {
        serviceWeightMap.put("192.168.1.100", 1);
        serviceWeightMap.put("192.168.1.101", 1);
        //权重为4
        serviceWeightMap.put("192.168.1.102", 4);
        serviceWeightMap.put("192.168.1.103", 1);
        serviceWeightMap.put("192.168.1.104", 1);
        //权重为3
        serviceWeightMap.put("192.168.1.105", 3);
        serviceWeightMap.put("192.168.1.106", 1);
        //权重为2
        serviceWeightMap.put("192.168.1.107", 2);
        serviceWeightMap.put("192.168.1.108", 1);
        serviceWeightMap.put("192.168.1.109", 1);
        serviceWeightMap.put("192.168.1.110", 1);

        serverMap.putAll(serviceWeightMap);
        keySet = serverMap.keySet();
        keyList.addAll(keySet);
    }

    //轮询
    private static Integer pos = 0;
    public static String getServer(){
        String server = null;
        synchronized (pos){
            if( pos > keySet.size() ){
                pos = 0;
            }
            server = keyList.get(pos);
            pos++;
        }
        return  server;
    }

    //加权轮询
    public static String getServerWeight(){
        Iterator<String> iterator = keySet.iterator();
        List<String> serverList = new ArrayList<>();
        while ( iterator.hasNext() ){
            String server = iterator.next();
            int weight = serverMap.get(server);
            for( int i = 0; i < weight; i++ ){
                serverList.add(server);
            }
        }

        String server = null;
        synchronized (pos){
            if( pos > keySet.size() ){
                pos = 0;
            }
            server = serverList.get(pos);
            pos++;
        }
        return server;
    }

    //随机
    public static String getServerRandom(){
        Random random = new Random();
        int num = random.nextInt(keyList.size());

        return keyList.get(num);
    }

    //源地址散列
    public static String getServerIpHash() throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress().toString();
        int hash = Math.abs(ip.hashCode());
        int serverSize = keyList.size();
        int serverPos = hash % serverSize;
        return keyList.get(serverPos);
    }

    public static void main(String[] args) {
        try {
            System.out.println(getServerIpHash());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
