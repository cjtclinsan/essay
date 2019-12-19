package com.tc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author taosh
 * @create 2019-12-16 16:47
 */
public class NIOserverDemo {
    private int port = 8080;
    //轮询器  Selector
    private Selector selector;
    //缓冲区 Buffer
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    //初始化
    public NIOserverDemo(int port) {

        try {
            this.port = port;
            //初始化（开门）
            ServerSocketChannel server = ServerSocketChannel.open();
            //告诉地址   ip:port
            server.bind(new InetSocketAddress(this.port));
            //BIO升级NIO   为了兼容BIO,模型默认采用阻塞式
            server.configureBlocking(false);

            selector = Selector.open();
            //接收任务
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen(){
        System.out.println("listen on "+this.port+".");
        //轮询
        while (true){
            try {
                //叫号
                selector.select();

                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                //不断迭代   同步体现在这里，每次只拿一个key，每次处理一种状态
                while (iter.hasNext()){
                    SelectionKey key = iter.next();
                    iter.remove();
                    //每个key代表一种状态
                    //每个号对应一个业务
                    //数据可读，数据可写，是否就绪等...
                    process(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //不同业务，不同操作，一次轮询调用一次process方法，同一时间，只能干一件事（同步）
    private void process(SelectionKey key) throws IOException {
        //针对每种状态进行反应
        if( key.isAcceptable() ){
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            //这个方法体现非阻塞
            SocketChannel channel = server.accept();
            channel.configureBlocking(false);
            //当数据准备就绪时，将状态改为可读
            key = channel.register(selector, SelectionKey.OP_READ);
        }else  if( key.isReadable() ){
            //key.channel  从多路复用器中拿到客户端的引用
            SocketChannel channel = (SocketChannel) key.channel();
            int len = channel.read(buffer);
            if( len > 0){
                buffer.flip();
                String content = new String(buffer.array(), 0, len);
                channel.register(selector, SelectionKey.OP_WRITE);
                //在key上携带一个附件
                key.attach(content);
                System.out.println("读取内容:"+content);
            }
        }else if( key.isWritable() ){
            SocketChannel channel = (SocketChannel) key.channel();

            String content = (String) key.attachment();

            channel.write(ByteBuffer.wrap(("输出:"+content).getBytes()));

            channel.close();
        }
    }

    public static void main(String[] args) {
        new NIOserverDemo(8080).listen();
    }
}
