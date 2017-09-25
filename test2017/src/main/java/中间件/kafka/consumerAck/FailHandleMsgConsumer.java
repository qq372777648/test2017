/*package 中间件.kafka.consumerAck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

public class FailHandleMsgConsumer {
	public static void main(String[] args) {
		String[] strs = { "139.162.80.167:2181", "group-3", "my-replicated-topic3", "10" };
		String zooKeeper = strs[0];
		String groupId = strs[1];
		String topic = strs[2];
		
		Properties props = new Properties();
		props.put("zookeeper.connect", zooKeeper);
		props.put("group.id", groupId);
		props.put("zookeeper.session.timeout.ms", "400");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");
		
		ConsumerConnector consumer=Consumer.createJavaConsumerConnector(new ConsumerConfig(props));
		
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(1));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
		
		ConsumerIterator<byte[], byte[]> it = streams.get(0).iterator();
		
		int count=0;
		while (it.hasNext()){
//			System.out.println(new String(it.next().key()));
			MessageAndMetadata<byte[], byte[]> kv=it.next();
			
//			if(++count>=5)
//				consumer.commitOffsets();
			System.out.println(">> key:"+ new String(kv.key())+" msg:"+new String(kv.message()));
		}
		
	}

}
*/