package 中间件.kafka.consumerAck;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer; 




public class TestConsumerAck { 
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "139.162.80.167:9092");
//		props.put("zookeeper.connect", "139.162.80.167:2181");
	     props.put("group.id", "group-10001");
	     props.put("enable.auto.commit", "false");
	     props.put("auto.commit.interval.ms", "1000");
	     props.put("session.timeout.ms", "30000");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
	     consumer.subscribe(Arrays.asList("my-replicated-topic4"));
	     final int minBatchSize = 2;
	     List<ConsumerRecord<String, String>> buffer = new ArrayList<ConsumerRecord<String, String>>();
	     while (true) {
	         ConsumerRecords<String, String> records = consumer.poll(1);
	         for (ConsumerRecord<String, String> record : records) {
	        	 System.out.printf("topic= %s,offset = %d, key = %s, value = %s",record.topic(), record.offset(), record.key(), record.value());
	             buffer.add(record);
	         }
	         if (buffer.size() >= minBatchSize) {
//	             insertIntoDb(buffer);
	             consumer.commitSync();
	             System.out.println("cmt");
	             buffer.clear();
	         }
	     }
	}
}
