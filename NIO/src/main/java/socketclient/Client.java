package socketclient;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Administrator on 2018/7/17.
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1",8888);

            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            Scanner sc = new Scanner(System.in);

            Thread readThread = new Thread(){
                @Override
                public void run(){
                    while (true){
                        String msg = null;

                        try {
                            msg = br.readLine();
                            System.out.println(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            Thread writeThread = new Thread(){
                @Override
                public void run(){
                    while (true){
                        try {
                            bw.write(sc.next()+"\n");
                            bw.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            readThread.start();
            writeThread.start();
        }catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
