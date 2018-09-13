package listener;

/**
 * Created by Administrator on 2018/8/9.
 */
public enum SingleTon {
    datasource;

    private static class DBConnection{

    }

    private DBConnection dbC = null;

    private SingleTon(){
        dbC = new DBConnection();
    }

    public DBConnection getCon(){
        return dbC;
    }

    public static void main(String[] args) {
        DBConnection con1 = SingleTon.datasource.getCon();
        DBConnection con2 = SingleTon.datasource.getCon();

        System.out.println(con1+" "+con2);
    }
}
