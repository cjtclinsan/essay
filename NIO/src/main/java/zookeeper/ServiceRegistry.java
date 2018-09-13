package zookeeper;

/**
 * Created by Administrator on 2018/8/16.
 */
public interface ServiceRegistry {
    void registry(String serviceName, String serviceAddr);
}
