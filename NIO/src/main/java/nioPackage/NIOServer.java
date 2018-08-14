package nioPackage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2018/8/13.
 */
public class NIOServer {

    private int port = 8000;
    private InetSocketAddress address = null;
    private Selector selector;

    public NIOServer(int port){
        try {
            this.port = port;
            address = new InetSocketAddress(this.port);

            ServerSocketChannel server = ServerSocketChannel.open();
            server.socket().bind(address);
            //服务端通道设置成非阻塞模式
            server.configureBlocking(false);
            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("服务器启动成功"+this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Selector开始轮询
    public void Listen(){
        try {
            while( true ) {
                int wait = this.selector.select();
                if( wait == 0 ){
                    continue;
                }
                Set<SelectionKey> keys = this.selector.selectedKeys();
                Iterator<SelectionKey> i = keys.iterator();
                while ( i.hasNext() ) {
                    SelectionKey key = i.next();
                    //针对每一个客户端进行操作
                    process(key);
                    i.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //处理客户端
    public void process(SelectionKey key) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        if( key.isAcceptable() ){
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();

            client.configureBlocking(false);
            //客户端一旦链接上来   读写
            //往这个selector上注册key   read  接下来可以读
            client.register(selector, SelectionKey.OP_READ);
        }else if( key.isReadable() ){
            SocketChannel client = (SocketChannel) key.channel();
            int len = client.read(buffer);
            if( len > 0 ){
                buffer.flip();    //把limit设置为当前的position值,把position设置为0,mark=-1
                String content = new String(buffer.array(),0,len);
                System.out.println(content);
                client.register(selector,SelectionKey.OP_WRITE);
            }
            buffer.clear();
        }else if(key.isWritable()){
            SocketChannel client = (SocketChannel) key.channel();
            client.write(ByteBuffer.wrap("Hello world".getBytes()));
            client.close();
        }
    }

    public static void main(String[] args) {
        new NIOServer(8000).Listen();
    }
}
