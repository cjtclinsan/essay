package socketclient;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by Administrator on 2018/7/17.
 */
public class SendAndReceiveUtil {
    public static String receiveData(SocketChannel channel) {
        ByteBuffer bb = ByteBuffer.allocate(1024);
        StringBuilder msg = new StringBuilder();
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        try {
            while( (channel.read(bb) ) > 0 ){
                bb.flip();
                msg.append(decoder.decode(bb).toString());
                bb.clear();
            }
            return msg.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static  void sendData(SocketChannel socketChannel, String msg) {
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
