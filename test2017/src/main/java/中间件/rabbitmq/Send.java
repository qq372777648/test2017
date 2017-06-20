package 中间件.rabbitmq;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年6月20日 下午2:52:26 
* @version:1.0
* @description:
*/
public class Send {
	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args) throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException {
		ConnectionFactory factory = new ConnectionFactory();
//		factory.useSslProtocol();
		factory.setHost("139.162.80.167");
		   factory.setPort(5672);
		   factory.setUsername("lzw");
		   factory.setPassword("lzw123");
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		
		
		channel.close();
		connection.close();
	}

}
