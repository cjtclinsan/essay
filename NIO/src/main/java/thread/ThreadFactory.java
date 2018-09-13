package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/8/24.
 */
public class ThreadFactory {
    SecurityManager s = System.getSecurityManager();
    ThreadGroup group = (s != null)?s.getThreadGroup():Thread.currentThread().getThreadGroup();

    private static ExecutorService service = Executors.newFixedThreadPool(10);


}
