package 中间件.redis;

import redis.clients.jedis.Jedis;

/**
 * @author lzw
 * @date 2017年2月27日 上午9:31:11
 * @Description:
 * @version
 * V1.1 新增发布订阅
 *  V1.0
 */
public class JedisUtil {
//	jedis.set(key, value, nxxx, expx, time)
//    key 
//    value 
//    nxxx NX|XX, NX -- Only set the key if it does not already exist. XX 
//    -- Only set the key if it already exist.
//    expx EX|PX, expire time units: EX = seconds; PX = milliseconds
//    time expire time in the units of {@param #expx}
	/**
	 * Only! set the key if it does not already exist(only add)
	 */
	public final static String NX="NX";
	/**
	 * Only! set the key if it already exist (only update)
	 */
	public final static String XX="XX";
	
	/**
	 * second
	 */
	public final static String EX="EX";
	/**
	 * milliseconds
	 */
	public final static String PX="PX";
	
	
	
	public static class Key{
		
		/**
		 *  可插入可写(有效期：永久 )
		 * @param key
		 * @param value
		 * @return
		 */
		public static String set(String key, String value) {
			Jedis jedis = RedisFactory.getConnection();
			String command = jedis.set(key, value);
			jedis.close();
			return command;
		}
		public static String set(byte[] key, byte[] value) {
			Jedis jedis = RedisFactory.getConnection();
			String command = jedis.set(key, value);
			jedis.close();
			return command;
		}
		/**
		 * 可插入可写
		 * @param key
		 * @param value
		 * @param seconds（有效期s）
		 * @return
		 */
		public static String set(String key, String value,long seconds) {
			String res =null;
			Jedis jedis = RedisFactory.getConnection();
			if(jedis.exists(key))
				res=jedis.set(key, value, XX, EX, seconds) ;
			else
				res=jedis.set(key, value, NX, EX, seconds) ;
			jedis.close();
			return res;
		}
		public static String set(byte[] key, byte[] value,long seconds) {
			String res =null;
			Jedis jedis = RedisFactory.getConnection();
			if(jedis.exists(key))
				res=jedis.set(key, value, XX.getBytes(), EX.getBytes(), seconds) ;
			else
				res=jedis.set(key, value, NX.getBytes(), EX.getBytes(), seconds) ;
			jedis.close();
			return res;
		}
		
		
		public static String onlyUpdate(byte[] key, byte[] value,long seconds) {
			String res =null;
			Jedis jedis = RedisFactory.getConnection();
			res=jedis.set(key, value, XX.getBytes(), EX.getBytes(), seconds) ;
			jedis.close();
			return res;
		}
		public static String onlyUpdate(String key, String value,long seconds) {
			String res =null;
			Jedis jedis = RedisFactory.getConnection();
			res=jedis.set(key, value, XX, EX, seconds) ;
			jedis.close();
			return res;
		}
		
		
		
		public static String onlyAdd(byte[] key, byte[] value,long seconds) {
			String res =null;
			Jedis jedis = RedisFactory.getConnection();
			res=jedis.set(key, value, NX.getBytes(), EX.getBytes(), seconds) ;
			jedis.close();
			return res;
		}
		public static String onlyAdd(String key, String value,long seconds) {
			String res =null;
			Jedis jedis = RedisFactory.getConnection();
			res=jedis.set(key, value, NX, EX, seconds) ;
			jedis.close();
			return res;
		}
		
		
//		public static <T>  T get(T key) {
//			Jedis jedis = RedisFactory.getConnection();
//			
////			System.out.println(key.getClass());
////			System.out.println( key instanceof String);
////			System.out.println( key instanceof Byte);
////			System.out.println(key instanceof Byte[]);
////			System.out.println(key instanceof ByteArray);
////			System.out.println(key instanceof ByteArray);
////			byte [] test=new byte[1];
////			System.out.println(key.getClass()==test.getClass() );
//			
//			T value = (T)jedis.get(key);
//			jedis.close();
//			return value;
//		}
		
		public static  String get(String key) {
			Jedis jedis = RedisFactory.getConnection();
			String value = jedis.get(key);
			jedis.close();
			return value;
		}
		public static  byte[] get(byte[] key) {
			Jedis jedis = RedisFactory.getConnection();
			byte[] value = jedis.get(key);
			jedis.close();
			return value;
		}

		
	
		
		
		/**
		 * 
		 * @param key
		 * @return -1 永久   -2 失效   >0有效秒数
		 */
		public static long ttl(String key) {
			Jedis jedis = RedisFactory.getConnection();
			long result = jedis.ttl(key);
			jedis.close();
			return result;
		}
		public static long ttl(byte[] key) {
			Jedis jedis = RedisFactory.getConnection();
			long result = jedis.ttl(key);
			jedis.close();
			return result;
		}
		
		
		public static long del(String key) {
			Jedis jedis = RedisFactory.getConnection();
			long result = jedis.del(key);
			jedis.close();
			return result;
		}
		public static long del(byte[] key) {
			Jedis jedis = RedisFactory.getConnection();
			long result = jedis.del(key);
			jedis.close();
			return result;
		}
		
		
		public static long expire(byte[] key,int seconds) {
			Jedis jedis = RedisFactory.getConnection();
			long result = jedis.expire(key, seconds);
			jedis.close();
			return result;
		}
		public static long expire(String key,int seconds) {
			Jedis jedis = RedisFactory.getConnection();
			long result = jedis.expire(key, seconds);
			jedis.close();
			return result;
		}
		
		
	}
	


//	public static long addList(String key, String[] value) {
//		if (key == null || value == null)
//			return -1;
//		Jedis jedis = RedisFactory.getConnection();
//		long result = jedis.lpush(key, value);
//		jedis.close();
//		return result;
//	}
	
	public static void  publish(String channel,String message){
		Jedis jedis = RedisFactory.getConnection();
		jedis.publish(channel, message);
		jedis.close();
	}
	
	
	
	

	
	

	
	
	
	public static void main(String[] args) {
		testConsumer();
		
//		Key.onlyAdd(SerializeUtil.serialize("aaa"), 
//				SerializeUtil.serialize(new RedisObj("sss",122, new RedisObj("yyyy", 222, null))), 300);
//		
//		Object value = SerializeUtil.unserialize(Key.get(SerializeUtil.serialize("aaa"))); 
//		System.out.println(((RedisObj)value).getA());

		

        
		
		
		
//		System.out.println(Key.get("name21"));
//		
//		
//		Key.set("niubi", "xxx",21);
//		System.out.println(Key.get("niubi"));
//		System.out.println(Key.ttl("niubi"));
		
//		Key.onlyUpdate("niubi", "isme", 25);
//		System.out.println(Key.get("niubi"));
//		System.out.println(Key.ttl("niubi"));
		
//		Key.set("nb2", "xxx",60);
//		Key.onlyAdd("nb2", "you", 30);
//		System.out.println(Key.get("nb2"));
//		System.out.println(Key.ttl("nb2"));
		
		
//		RedisFactory.getConnection().set("aaa", "sdf", NX, EX, 10);
//		System.out.println(Key.get("aaa"));
//		System.out.println(Key.ttl("aaa"));
//		
//		
//		Key.set("yongjiu", "sdfd");
//		System.out.println(Key.get("yongjiu"));
//		Key.set("yongjiu", "sdfd2");
//		System.out.println(Key.get("yongjiu"));
//		del("aaa");
//		RedisFactory.getConnection().set("sdfsdf", "sdf", XX, EX, 30);
//		System.out.println(getString("aaa"));
//		System.out.println(ttl("aaa"));
		
		
//		RedisFactory.getConnection().set(SerializeUtil.serialize("lzw2"),
//		SerializeUtil.serialize("非常酷2")
//		,NX.getBytes(), EX.getBytes(),
//		60
//		); 
		
//		System.out.println( SerializeUtil.unserialize(Key.get(SerializeUtil.serialize("lzw2"))));
//		System.out.println(Key.ttl(SerializeUtil.serialize("lzw2")));
		
//		RedisFactory.getConnection().set("sdfsdf", "sdf", NX, EX, 30);
//		System.out.println(Key.get("sdfsdf"));
//		System.out.println(Key.ttl("sdfsdf"));
		
		
		
//		RedisFactory.getConnection().expire(SerializeUtil.serialize("lzw"), 5);
//		del(SerializeUtil.serialize("lzw"));
//		
//		System.out.println( SerializeUtil.unserialize (RedisFactory.getConnection().get(SerializeUtil.serialize("lzw"))));
//
//		System.out.println(RedisFactory.getConnection().ttl(SerializeUtil.serialize("lzw")));
		
		
//		 jedis.expire("x", 5);
		
		
//		RedisFactory.getConnection().set(SerializeUtil.serialize("lzw"),
//	        		SerializeUtil.serialize("非常酷"),
//	        		SerializeUtil.serialize(JedisUtil.NX),
//	        		SerializeUtil.serialize(JedisUtil.EX),
//	        		60
//	        		); 
	}
	
	private static void testConsumer(){
//		new Thread(){
//			public void run() {
//				Jedis jedis = RedisFactory.getConnection();
//				for (int i = 0; i < 10; i++) {
//					
//					jedis.rpush("sheep meat pool", i+"");
//				}
//			};
//		}.start();
		
		Jedis jedis = RedisFactory.getConnection();
		System.out.println(jedis.blpop(3, "sheep meat pool").toString());
		

	}
	
	private static void testSubscribe(){
		final SubscribeChannel listener=new SubscribeChannel();
		
		new Thread(){
			public void run() {
				
				for (int i = 0; i < 10; i++) {
					System.out.println("发布");
					publish("comeon_subscribe_me",i+""+i);
					try {
						sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
//				listener.unsubscribe("comeon_subscribe_me");
				listener.unsubscribe();//取消一个
			};
		}.start();
		
		new Thread(){
			public void run() {
				
				Jedis jedis = RedisFactory.getConnection();
				jedis.subscribe(listener, "comeon_subscribe_me");
				System.out.println("cant excute2");
			};
		}.start();
		
		Jedis jedis = RedisFactory.getConnection();
		jedis.subscribe(listener, "comeon_subscribe_me");
		System.out.println("cant excute");
	}

}
