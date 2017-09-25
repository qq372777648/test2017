package 中间件.kafka.consumerAck;

//public class Producter {
//	public static void main(String[] args) {
//		Properties props = new Properties();
//		props.put("bootstrap.servers", "139.162.80.167:9092");
////		props.put("metadata.broker.list","139.162.80.167:9092,139.162.80.167:9093,139.162.80.167:9094");
//		props.put("acks", "all");
//		 props.put("retries", 0);
//		 props.put("batch.size", 16384);
//		 props.put("linger.ms", 1);
//		 props.put("buffer.memory", 33554432);
//		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//		 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//		Producer<String, String> producer = new KafkaProducer<String, String>(props);
//		for(int i = 0; i < 5; i++){
//			System.out.println(i);
//		    producer.send(new ProducerRecord<String, String>("my-replicated-topic3", "key"+i, "msg"+i));
//		}
//		producer.close();
//	}
//
//}
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
  
//测试通过
public class MyProducter {
    public static void main(String[] args) {
        long events = 3;
        Random rnd = new Random();
  
        Properties props = new Properties();
//		props.put("bootstrap.servers", "139.162.80.167:9092");
		props.put("metadata.broker.list","139.162.80.167:9092,139.162.80.167:9093,139.162.80.167:9094");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
//        props.put("partitioner.class", "example.producer.SimplePartitioner");
        props.put("request.required.acks", "1");
  
        ProducerConfig config = new ProducerConfig(props);
  
        Producer<String, String> producer = new Producer<String, String>(config);
  
        for (long nEvents = 0; nEvents < events; nEvents++) { 
               long runtime = new Date().getTime();  
               String ip = "192.168.91." + nEvents; 
               String msg = "msgg"+ ip; 
               KeyedMessage<String, String> data = new KeyedMessage<String, String>("my-replicated-topic3", ip, msg);
               producer.send(data);
               System.out.println(msg);
        }
        producer.close();
    }
}


