package singleton;

/**
 * Created by Administrator on 2018/8/28.
 * 每个枚举实例都是static final类型的，也就表明只能被实例化一次。
 * 因为enum中的实例被保证只会被实例化一次，所以我们的INSTANCE也被保证实例化一次。
 * 枚举也提供了序列化机制
 */
public class EnumSingleTon {

    private enum EnumDBconn{
        INSTANCE;
        private DBConn dbConn;

        private EnumDBconn(){
            dbConn = new DBConn();
        }

        private DBConn getConn(){
            return dbConn;
        }
    }

    private static class DBConn{

    }

    public static DBConn newInsatnce(){
        return EnumDBconn.INSTANCE.getConn();
    }
}
