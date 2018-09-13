package hessian;

/**
 * Created by Administrator on 2018/8/18.
 */
public class BasicImpl implements Basic {
    @Override
    public String sayHello(String name) {
        return "This is Hello words from HESSIAN Server. " + name;
    }
}
