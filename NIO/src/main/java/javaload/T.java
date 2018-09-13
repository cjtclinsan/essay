package javaload;

/**
 * Created by Administrator on 2018/8/22.
 */
public class T {
    public static  int k = 0;
    public static T t1 = new T("t1");
    public static T t2 = new T("t2");
    public static int i = print("i");

    public static int n = 99;
    public int j = print("j");

    {
        print("构造块");
    }

    static {
        print("静态块");
    }

    public T(String str) {
        System.out.println((++k) + ":" + str + "    i=" + i + "  n=" + n);
        ++n;
        ++i;
    }

    private static int print(String str) {
        System.out.println((++k) +":" + str + "   i=" + i + "   n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String[] args) {
        T t = new T("init");
    }
}
