package proxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/8/30.
 */
public class CreateClassTest {
    public static void main(String[] args) throws IOException {
        byte[] classFile = ProxyGenerator.generateProxyClass("TestClass",
                new Class[]{TestInterface.class});

        File file = new File("E:/TestClass.class");

        FileOutputStream o = new FileOutputStream(file);

        o.write(classFile);
        o.flush();
        o.close();
    }
}
