package cglib;

/**
 * Created by Administrator on 2018/9/13.
 */
public class Test {
    public static void main(String[] args) {
        ShipProxy proxy = new ShipProxy();
        Ship ship = (Ship) proxy.getProxy(Ship.class);
        ship.travel();
    }

}
