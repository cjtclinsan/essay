package singleton;

/**
 * Created by Administrator on 2018/8/28.
 * 缺点：一旦我访问了该类的其他任何静态域，就会造成实例的初始化，
 * 而是事实我们至始至终可能都没使用到该实例
 */
public class EHan {
    private static EHan instance = new EHan();
    private EHan(){};
    public static EHan newInstance(){
        return instance;
    }
}
