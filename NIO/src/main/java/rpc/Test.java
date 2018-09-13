package rpc;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by Administrator on 2018/9/13.
 */
public class Test {
    public static void main(String[] args) {
        Hashtable table = new Hashtable();
        StringBuffer buffer = new StringBuffer();
        buffer.append("abc");
        table.put("1",buffer);
        buffer.append(",def");

        Enumeration it = table.elements();
        while (it.hasMoreElements()) {
            System.out.println(it.nextElement());
        }
    }
}
