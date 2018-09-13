package listener;

/**
 * Created by Administrator on 2018/8/9.
 */
public class LvShi implements ZiRanRen {
    @Override
    public void quanli() {
        new Mayun().quanli();
        new Mayun().eat();
        new Mayun().drink();
    }
}
