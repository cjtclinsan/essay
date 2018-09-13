package listener;

/**
 * Created by Administrator on 2018/8/10.
 */
public class Facader {
    private SubMethod1 sm1 = new SubMethod1();
    private SubMethod2 sm2 = new SubMethod2();
    private SubMethod3 sm3 = new SubMethod3();

    public void faceMethod1(){
        sm1.method1();
        sm2.method2();
    }
}
