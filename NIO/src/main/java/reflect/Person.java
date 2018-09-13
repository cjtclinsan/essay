package reflect;

/**
 * Created by Administrator on 2018/8/27.
 */
public class Person {
    public String name = "default name";
    public int sex = 1;
    public int[] array = new int[10];

    public Person() {
        System.out.println("无参构造：------"+name);
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    private Person(String name, int sex) {
        this.name = name;
        this.sex = sex;
        fun(name);
        System.out.println("有参构造：------"+name+":"+sex);
    }

    public void fun() {
        System.out.println("无参方法：------fun");
    }

    public void fun(String name) {
        System.out.println("有参方法fun：------参数：------"+name);
    }
}
