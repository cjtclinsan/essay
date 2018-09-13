package zookeeper;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16.
 */
public interface ChildListener {
    void childChanged(String path, List<String> childern);
}
