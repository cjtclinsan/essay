package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16.
 */
public class CuratorZookeeperClient implements ZookeeperClient {
    private final CuratorFramework client;

    public CuratorZookeeperClient(String host) {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString("0.0.0.0:2181").retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000))
                .connectionTimeoutMs(5000);

        client = builder.build();

        client.start();
    }

    @Override
    public void create(String path, boolean eph) {
        int i = path.lastIndexOf("/");
        System.out.println(path+"     i:"+i);
        if( i > 0 ){
            create(path.substring(0,i),eph);
        }


        try {
            if( eph ) {
                client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
            }else{
                client.create().withMode(CreateMode.PERSISTENT).forPath(path);
            }
        } catch (KeeperException.NodeExistsException e) {
            e.printStackTrace();
        }catch (Exception e){
            throw new  RuntimeException(e);
        }
    }

    @Override
    public void detele(String path) {
        try {
            client.delete().forPath(path);
        } catch (KeeperException.NodeExistsException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<String> addChildern(String path) {
        try {
            return client.getChildren().forPath(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> addChileListenter(String path, ChildListener listener) {

        try {
            return client.getChildren()
                    .usingWatcher(new CuratorWatchImpl(listener,client))
                    .forPath(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isConnect() {
        return client.getZookeeperClient().isConnected();
    }

    @Override
    public void close() {
        client.close();
    }
}
