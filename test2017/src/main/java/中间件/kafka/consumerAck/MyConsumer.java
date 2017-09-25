package 中间件.kafka.consumerAck;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import 中间件.kafka.Config;

//配置解释
//http://colobu.com/2015/03/11/kafka-configuration-parameters/
public class MyConsumer {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.KAFKA_MASTER);
		// props.put(ConsumerConfig.CLIENT_ID_CONFIG,
		// config.getClientIdConsumer());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-5");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);//auto.commit.enable	true	如果true,consumer定期地往zookeeper写入每个分区的offset
	     props.put("auto.commit.interval.ms", "1000");//auto.commit.interval.ms	10000	往zookeeper上写offset的频率
	     props.put("session.timeout.ms", "30000");
//		props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, config.getHeartbeatIntervalMs());
//		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, config.getAutoOffsetReset());//如果offset出了返回，则 smallest: 自动设置reset到最小的offset. largest : 自动设置offset到最大的offset. 其它值不允许，会抛出异常.
//		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, config.getMaxPollRecords());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		
		
		props.put("rebalance.retries.max",10);//rebalance.retries.max	4	rebalance时的最大尝试次数
		
		
		KafkaConsumer<String, String> consumer=new KafkaConsumer<String, String>(props);
		consumer.subscribe(Collections.singletonList("my-replicated-topic3"));
		
		
		System.out.println("开始1");
		long begin = System.currentTimeMillis();
		while (true) {
			try {
				ConsumerRecords<String, String> records = consumer.poll(1);
				for (ConsumerRecord<String, String> record : records) {
						System.out.println(record.key()+"====" +record.value());
						try {
								consumer.commitSync(Collections.singletonMap(
										new TopicPartition(record.topic(), record.partition()),
										new OffsetAndMetadata(record.offset() + 0)));//+0失败？
								Thread.sleep(500);
						} catch (Exception ex) {
							break;
						}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
