package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/8/27.
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        //refGetClass();

        // 获取并调用无参构造函数
        //refGetPublicConstructor();

        // 获取并调用私有的含参构造函数
        //refGetPrivateConstructor();

        // 获取并调用无参方法 fun
        //refGetMethodWithNoArg();

        // 获取并调用有参数方法 fun
        //refGetMethodWithArg();

        // 获取类的字段
        refGetField();

    }

    private static void refGetField() throws Exception {
        Class clazz = Class.forName("reflect.Person");

        //遍历
        Constructor[] cs = clazz.getDeclaredConstructors();
        for(Constructor c : cs){
            System.out.println(c);
        }

        //有参写法
        Constructor c = clazz.getDeclaredConstructor(String.class,int.class);
        c.setAccessible(true);
        Person p = (Person) c.newInstance("zhang wei",1);

        //无参写法
        //Person p = (Person) clazz.newInstance();

        Field[] f = clazz.getDeclaredFields();
        for (Field field : f) {
            System.out.println(field.getName()+"   "+field.getType()+"   "+field.get(p));
        }
    }

    private static void refGetMethodWithArg() throws Exception{
        Class clazz = Class.forName("reflect.Person");
        Constructor c = clazz.getDeclaredConstructor(String.class);

        c.setAccessible(true);

        Person p = (Person) c.newInstance("hello");

        Method method = clazz.getMethod("fun", String.class);
        method.invoke(p,"hello");
    }

    private static void refGetMethodWithNoArg() throws Exception{
        Class clazz = Class.forName("reflect.Person");
        Constructor c = clazz.getConstructor(null);
        Person p = (Person) c.newInstance(null);

        Method method = clazz.getMethod("fun", null);
        method.invoke(p,null);
    }

    private static void refGetPrivateConstructor() throws Exception {
        Class clazz = Class.forName("reflect.Person");
        Constructor c = clazz.getDeclaredConstructor(new Class[] {String.class});

        c.setAccessible(true);

        Person p = (Person) c.newInstance(new Object[]{"I'm a reflect name!"});

    }

    private static void refGetPublicConstructor() throws Exception {
        Class clazz = Class.forName("reflect.Person");

        Constructor c= clazz.getConstructor(null);

        Person p = (Person) c.newInstance(null);           //获取实例

    }

    private static void refGetClass() throws Exception {
        Class clazz = Class.forName("reflect.Person");

        Class clazz1 = new Person().getClass();

        Class clazz2 = Person.class;
    }
}
