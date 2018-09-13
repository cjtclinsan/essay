package singleton;

/**
 * Created by Administrator on 2018/8/28.
 * 缺点：若无volatile，由于指令重排序，
 * 可能会导致返回一个还未完全实例化的实例；
 */
public class DoubleCheck {
    private static volatile DoubleCheck instance = null;   //防止指令重排序

    private DoubleCheck() {}

    public static DoubleCheck newInstance(){
        if( null == instance ){
            synchronized (DoubleCheck.class){
                if( null == instance ){
                    instance = new DoubleCheck();
                }
            }
        }
        return instance;
    }

}
