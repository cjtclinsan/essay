package zookeeper;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16.
 */
public interface ZookeeperClient {
    void create(String path, boolean eph);

    void detele(String path);

    List<String> addChildern(String path);

    List<String> addChileListenter(String path, ChildListener listener);

    boolean isConnect();

    void close();
}
