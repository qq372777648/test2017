package core.NIO.simpleCS;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/** 
* @author: liangzhenwei
* @create：2017年11月7日 下午2:18:56 
* 
*/
public class NioServer {
	private  Selector selector;  
    public NioServer(int port) throws IOException {  
        // 打开服务器套接字通道  
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();  
        // 服务器配置为非阻塞  
        serverSocketChannel.configureBlocking(false);  
        // 检索与此通道关联的服务器套接字  
        ServerSocket serverSocket = serverSocketChannel.socket();  
        // 进行服务的绑定  
        serverSocket.bind(new InetSocketAddress(port));  
        // 通过open()方法找到Selector  
        selector = Selector.open();  
        // 注册到selector，等待连接  
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);  
        System.out.println("Server Start----8888:");  
        
        while (true) {  
            // 选择一组键，并且相应的通道已经打开  
            selector.select();  
            // 返回此选择器的已选择键集。  
            Set<SelectionKey> selectionKeys = selector.selectedKeys();  
            Iterator<SelectionKey> iterator = selectionKeys.iterator();  
            while (iterator.hasNext()) {          
                SelectionKey selectionKey = iterator.next();  
                iterator.remove();  //移除set 中 SelectionKey
                handleKey(selectionKey);  //处理
            }  
        }  
        
    } 

}
 