package nioPackageTwo;

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
 * Created by Administrator on 2018/8/14.
 *  步骤
 *  1，获取ServerSocketChannel
    2，绑定一个端口
    4，将两者进行一个register

    Selector
    3，得到Selector
    5，selector不断轮训客户端的请求  select  单线程
    6，handlerAccept()处理或者反馈客户端的链接

    Handler
    7，得到客户端的Handler
 */
public class NIONetServer {
    private int port = 20006;
    private InetSocketAddress address = null;
    private Selector selector;

    public NIONetServer(int port) {
        this.port = port;
        address = new InetSocketAddress(this.port);

        try {
            //1,获取ServerSocketChannel
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //2,绑定端口
            ssc.socket().bind(address);
            //将通道设置为非阻塞（关键）否则报异常
            ssc.configureBlocking(false);
            //获取selector
            selector = Selector.open();
            //将两者进行register注册
            ssc.register(selector, SelectionKey.OP_ACCEPT);   //这个时候为可接受状态

            System.out.println("服务器启动成功"+this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //selector开始轮询
    public void listen(){
        while(true){
            try {
                int wait = this.selector.select();   //操作系统底层的方法，增加线程数，启动线程，poll方法-->native的poll方法
                if( wait == 0 ){
                    continue;
                }

                Set<SelectionKey> keys = this.selector.selectedKeys();
                Iterator<SelectionKey> i = keys.iterator();
                while ( i.hasNext() ){
                    SelectionKey key = i.next();
                    //针对每个客户进行操作
                    process(key);
                    i.remove();
                    //select()返回的是上一次到本次选择，所增加的数目。
                    //也就是说上一次选择过后，Set中已经存在了某一个Selectionkey没有remove掉，
                    //那么下次selector.select()，选择中不会包含这个键，但是集合中仍然有这个事件会被处理
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void process(SelectionKey key) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //除此还有ShortBuffer,IntBuffer,LongBuffer,DoubleBuffer等，每一个Buffer类都有完全一样的操作，只是它们所处理的数据类型不一样。
        // 因为大多数标准I/O操作都是使用ByteBuffer，所以它除了具有一般缓冲区的操作之外还提供一些特有的操作，方便网络读写。
        if( key.isAcceptable() ){
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            //客户端一旦连接上来   读写
            //往这个selector上注册key  read
            client.register(selector, SelectionKey.OP_READ);
        }else if( key.isReadable() ){
            SocketChannel client = (SocketChannel) key.channel();
            int len = client.read(buffer);
            if( len > 0 ){        //读到了字节
                buffer.flip();    //把limit设置为当前的position值,把position设置为0,mark=-1
                String content = new String(buffer.array(), 0, len);
                System.out.println(content);
                client.register(selector, SelectionKey.OP_WRITE);
            }
            buffer.clear();
        }else if( key.isWritable() ){
            SocketChannel client = (SocketChannel) key.channel();
            client.write(ByteBuffer.wrap("Hello Server".getBytes()));
            client.close();
        }
    }

    public static void main(String[] args) {
        new NIONetServer(20006).listen();
    }
}
