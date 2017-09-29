package core.NIO.cs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

//public class TestServerSocketChannel {
//	public static void main(String[] args) throws IOException {
//		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//
//		serverSocketChannel.socket().bind(new InetSocketAddress(9999));
//		serverSocketChannel.configureBlocking(false);
//
//		while(true){
//		    SocketChannel socketChannel = serverSocketChannel.accept();
//
//		    if(socketChannel != null){
//		        //do something with socketChannel...
//		    }
//		}
//	}
//
//}



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

public class TestServerSocketChannel {

    private Selector selector;

    private ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

    public TestServerSocketChannel(int port) throws IOException {
        selector=Selector.open();
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();//创建nio通道
        serverSocketChannel.socket().bind(new InetSocketAddress(port));//创建基于nio通道的socket链接绑定
        serverSocketChannel.configureBlocking(false);//配置使通道不阻塞
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//将通道绑定到选择器
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector=selector;
    }

    public void listen() {

        try {
            for(;;) {

                int i=selector.select();//获取通道内关心事件的集合。
                if(i<1){
                    continue;
                }
                //Selector传回一组SelectionKeys
                Iterator<SelectionKey> iter=selector.selectedKeys().iterator();
                if(iter.hasNext()) {
                    SelectionKey selectionKey=iter.next();
                    //一个key被处理完成后，就都被从就绪关键字（ready keys）列表中除去
                    iter.remove();
                    process(selectionKey);
                }
                System.out.println(" loop " + new Date());
                System.out.println("\n\n\n");
            }
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void process(SelectionKey selectionKey) throws IOException {
        System.out.println("selectionKey.isAcceptable()"+selectionKey.isAcceptable());
        System.out.println("selectionKey.isReadable()"+selectionKey.isReadable());
        System.out.println("selectionKey.isWritable()"+selectionKey.isWritable());
        
        if(selectionKey.isAcceptable()) {//获得客户端链接，并注册到选择器中，观察的动作有读写。
            ServerSocketChannel server=(ServerSocketChannel)selectionKey.channel();
            SocketChannel socketChannel=server.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            System.out.println("accept:" + selectionKey.interestOps() + " " + selectionKey.readyOps());
        } else if(selectionKey.isReadable()) {//这个通道是一个可读的状态。进行读取操作
            SocketChannel channel=(SocketChannel)selectionKey.channel();
            int count=channel.read(byteBuffer);
            if(count > 0) {
                byteBuffer.flip();
                byte[] bbb=byteBuffer.array();
                System.out.println("i receive" + new String(bbb, 0, count));
                byteBuffer.clear();
            }
        } else if(selectionKey.isWritable()) {//这个通道是一个可以写的状态，进行写入的操作
            System.out.println("isWritable:" + selectionKey.interestOps());
            SocketChannel channel=(SocketChannel)selectionKey.channel();
            byteBuffer.clear();
            byteBuffer.put(new Date().toString().getBytes());
            byteBuffer.flip();
            channel.write(byteBuffer);
            channel.close();
            byteBuffer.clear();
        }
       
    }

    public static void main(String[] args) {
        int port=8888;
        try {
        	TestServerSocketChannel server=new TestServerSocketChannel(port);
            System.out.println("listening on " + port);
            server.listen();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}