package javaload;

/**
 * Created by Administrator on 2018/8/22.
 */
public class Parent {
    public static int i = print("parent static:i");
    public int ii = print("parent:i");

    static {
        print("父类静态初始化");
    }

    {
        print("父类实例初始化");
    }

    private static int print(String s) {
        System.out.println("initial:" + s);
        return i;
    }

    public Parent(String str) {
        System.out.println("parent constructor:" + str);
    }
}
