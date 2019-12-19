package com.tc.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author taosh
 * @create 2019-12-18 16:53
 */
public class AIOServer {

    final private int port;

    public AIOServer(int port) {
        this.port = port;
        listen();
    }

    private void listen() {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            //开门营业
            AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);

            //工作线程，用来监听回调（事件响应时的回调）
            final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(threadGroup);

            server.bind(new InetSocketAddress(port));
            System.out.println("服务已启动，监听端口:"+port);

            //准备接收数据
            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

                //实现completed方法来实现回调，由操作系统来触发
                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    System.out.println("IO操作成功，开始获取数据");

                    try {
                        buffer.clear();
                        result.read(buffer).get();
                        buffer.flip();
                        result.write(buffer);
                        buffer.flip();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("IO操作失败:"+exc.getMessage());
                }
            });

            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AIOServer(8090);
    }
}
