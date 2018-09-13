package listener;

/**
 * Created by Administrator on 2018/8/10.
 */
public class Adapter implements Ps2 {
    private Usb usb;

    public Adapter(Usb usb) {
        this.usb = usb;
    }

    @Override
    public void isps2() {
        usb.isUsb();
    }
}
