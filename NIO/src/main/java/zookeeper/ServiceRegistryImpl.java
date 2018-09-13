package zookeeper;

import java.io.IOException;

/**
 * Created by Administrator on 2018/8/16.
 */
public class ServiceRegistryImpl implements ServiceRegistry {
    private ZookeeperClient client = null;

    private static final String REGISTRY_ROOT = "/registry";

    {
        client = new CuratorZookeeperClient("0.0.0.0:2181");
    }

    @Override
    public void registry(String serviceName, String serviceAddr) {
        String servicePath = REGISTRY_ROOT+"/"+serviceAddr;
        client.create(servicePath, false);

        String addressPath = REGISTRY_ROOT+"/"+serviceName;
        client.create(addressPath, false);
        System.out.println(servicePath+"    "+addressPath);
        System.out.println(serviceName+"注册成功");
    }

    public static void main(String[] args) throws IOException {
        ServiceRegistry registry = new ServiceRegistryImpl();
        registry.registry("pay-core", "192.168.11.111:20880");
        System.in.read();
    }
}
