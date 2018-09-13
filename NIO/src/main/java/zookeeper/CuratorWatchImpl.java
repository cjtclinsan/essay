package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

/**
 * Created by Administrator on 2018/8/16.
 */
public class CuratorWatchImpl implements CuratorWatcher {

    private volatile ChildListener listener;
    private CuratorFramework client;

    public CuratorWatchImpl(ChildListener listener, CuratorFramework framework) {
        this.listener = listener;
        this.client = framework;
    }

    public void unwatch(){
        this.listener = null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) throws Exception {
        if( listener != null ){
            listener.childChanged(watchedEvent.getPath(),client.getChildren().
                    usingWatcher(this).forPath(watchedEvent.getPath()));
        }
    }
}
