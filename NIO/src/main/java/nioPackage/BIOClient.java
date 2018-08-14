package nioPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2018/8/13.
 */
public class BIOClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8000);
            OutputStream out = socket.getOutputStream();
            out.write("hello server, i am client".getBytes());
            out.flush();

            InputStream in = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ( (len = in.read(buffer)) > 0){
                System.out.println(new String(buffer, 0, len));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
