package singleton;

/**
 * Created by Administrator on 2018/8/28.
 * 缺点：当一个线程访问这个方法时，其他所有县城会处于挂起等待的状态，
 *      造成无谓的等待
 */
public class LanHan {
    private static LanHan instance = null;
    private LanHan(){};

    public static synchronized LanHan newInstance(){
        if( null == instance ){
            instance = new LanHan();
        }
        return instance;
    }
}
