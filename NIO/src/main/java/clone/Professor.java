package clone;

/**
 * Created by Administrator on 2018/8/27.
 */
public class Professor implements Cloneable{
    String name;
    int age;

    public Professor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Object clone() {
        Object o=null;
        try {
            o=super.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        return o;
    }
}
