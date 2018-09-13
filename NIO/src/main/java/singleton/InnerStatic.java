package singleton;

/**
 * Created by Administrator on 2018/8/28.
 * 一个类的静态属性只会在类第一次加载的时候初始化，所有没有并发的问题，由JVM来同步这个过程；
 *  而且静态的变量只初始化一次，所以仍然是单例的
 */
public class InnerStatic {
   private static class InnerStaticHolder{
       public static InnerStatic instance = new InnerStatic();
   }

   private InnerStatic(){}

   public static InnerStatic newInstance(){
       return InnerStaticHolder.instance;
   }
}
