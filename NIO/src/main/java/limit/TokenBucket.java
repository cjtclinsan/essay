package limit;

import com.google.common.base.Preconditions;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.LifecycleState;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/9/6.
 */
public class TokenBucket implements Lifecycle {

    // 默认桶大小个数 即最大瞬间流量是64M
    private static final int DEFAULT_BUCKET_SIZE = 1024 * 1024 * 64;

    //每个桶的单位是1字节
    private int everyTokenSize = 1;

    //瞬间最大流量
    private int maxFlowRate;

    //平均流量
    private int avgFlowRate;

    // 队列来缓存桶数量：最大的流量峰值就是 = everyTokenSize*DEFAULT_BUCKET_SIZE 64M = 1 * 1024 * 1024 * 64
    private ArrayBlockingQueue<Byte> tokenQueue = new ArrayBlockingQueue<Byte>(DEFAULT_BUCKET_SIZE);

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private volatile boolean isStart = false;

    private ReentrantLock lock = new ReentrantLock(true);

    private static  final byte A_CHAR = 'a';

    public TokenBucket() {

    }

    public TokenBucket(int maxFlowRate, int avgFlowRate) {
        this.maxFlowRate = maxFlowRate;
        this.avgFlowRate = avgFlowRate;
    }

    public TokenBucket(int everyTokenSize, int maxFlowRate, int avgFlowRate) {
        this.everyTokenSize = everyTokenSize;
        this.maxFlowRate = maxFlowRate;
        this.avgFlowRate = avgFlowRate;
    }

    public void addTokens(Integer tokenNum){
        //如果桶满了，就不再添加新令牌
        for( int i = 0; i < tokenNum; i++){
            tokenQueue.add(Byte.valueOf(A_CHAR));
        }
    }

    public TokenBucket build(){
        start();
        return this;
    }

    /**
     * 获取足够令牌数
     * @return
     */
    public boolean getToken(byte[] dataSize){
        Preconditions.checkNotNull(dataSize);
        Preconditions.checkArgument(isStart,"please invoke start method first !");
        int needTokenNum = dataSize.length/everyTokenSize + 1;   //传输内容对应的桶的个数
        try {
            final ReentrantLock lock = this.lock;
            lock.lock();

            boolean result = needTokenNum <= tokenQueue.size();  //是否有足够的桶数量
            if (!result) {
                return false;
            }
            int tokenCount = 0;
            for (int i = 0; i < needTokenNum; i++) {
                Byte poll = tokenQueue.poll();
                if (null != poll) {
                    tokenCount++;
                }
            }

            return tokenCount == needTokenNum;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void addLifecycleListener(LifecycleListener lifecycleListener) {

    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];
    }

    @Override
    public void removeLifecycleListener(LifecycleListener lifecycleListener) {

    }

    @Override
    public void init() throws LifecycleException {

    }

    @Override
    public void start() {
        //初始化桶队列大小
        if( maxFlowRate != 0 ){
            tokenQueue = new ArrayBlockingQueue<Byte>(maxFlowRate);
        }

        //初始化令牌生产者
        TokenProducer producer = new TokenProducer(avgFlowRate, null);
    }

    @Override
    public void stop() throws LifecycleException {

    }

    @Override
    public void destroy() throws LifecycleException {

    }

    @Override
    public LifecycleState getState() {
        return null;
    }

    @Override
    public String getStateName() {
        return null;
    }

    public static TokenBucket newBuilder(){
        return new TokenBucket();
    }

    public TokenBucket everyTokenSize(int everyTokenSize){
        this.everyTokenSize = everyTokenSize;
        return this;
    }

    public TokenBucket maxFlowRate(int maxFlowRate) {
        this.maxFlowRate = maxFlowRate;
        return this;
    }

    public TokenBucket avgFlowRate(int avgFlowRate) {
        this.avgFlowRate = avgFlowRate;
        return this;
    }

    private String StringCopy(String data, int copyNum){
        StringBuilder sBuilder = new StringBuilder(data.length() * copyNum);
        for( int i = 0; i < copyNum; i++ ){
            sBuilder.append(data);
        }
        return sBuilder.toString();
    }

    class TokenProducer implements Runnable{
        private int avgFlowRate;
        private TokenBucket tokenBucket;

        public TokenProducer(int avgFlowRate, TokenBucket tokenBucket) {
            this.avgFlowRate = avgFlowRate;
            this.tokenBucket = tokenBucket;
        }

        @Override
        public void run() {
            tokenBucket.addTokens(avgFlowRate);
        }
    }
}

