package 中间件.rabbitmq;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * @author: lzw
 * @email:372777648@qq.com
 * @create：2017年6月20日 下午3:00:10
 * @version:1.0
 * @description:
 */
public class Recv2Succ {
	private final static String QUEUE_NAME = "hello2";

	public static void main(String[] argv)
			throws java.io.IOException, java.lang.InterruptedException, TimeoutException, KeyManagementException, NoSuchAlgorithmException {

		ConnectionFactory factory = new ConnectionFactory();
//		factory.useSslProtocol();
		factory.setHost("139.162.80.167");
		   factory.setPort(AMQP.PROTOCOL.PORT);
		   factory.setUsername("lzw");
		   factory.setPassword("lzw123");
		Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					
				}
				// 消息处理完成确认
				channel.basicAck(envelope.getDeliveryTag(), false);
//				//失敗回饋
//				channel.basicNack(envelope.getDeliveryTag(), false, true);
				
				//不回馈，connection一直持有，则该任务一直不能分给其他消费者
			}
		};
		
		boolean autoAck = false;
		channel.basicConsume(QUEUE_NAME, autoAck, consumer);
		
		

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	}

}
