package 中间件.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisFactory {
	private static JedisPool jedisPool = null;
	
	private static final String  HOST ;
	private static final int  PORT =6379;
	private static final String  PASSWORD ;
	
	
	private static final int MAX_WAIT = 1000;
	private static final int MAX_ACTIVE ;
	private static final int MAX_IDLE ;
	
	

	static {
		InputStream in = RedisFactory.class.getClassLoader().getResourceAsStream("config.properties");
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MAX_ACTIVE = Integer.parseInt(props.getProperty("redis.maxTotal", "200")) ;
		MAX_IDLE =Integer.parseInt(props.getProperty("redis.maxIdle", "100")) ;
		HOST=props.getProperty("redis.host", "");
		PASSWORD=props.getProperty("redis.password", "");
		
		
		JedisPoolConfig config = new JedisPoolConfig();
		// 最大空闲连接数, 应用自己评估，不要超过AliCloudDB for Redis每个实例最大的连接数
		config.setMaxIdle(MAX_IDLE);
		// 最大连接数, 应用自己评估，不要超过AliCloudDB for Redis每个实例最大的连接数
		config.setMaxTotal(MAX_ACTIVE);
		// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
		config.setMaxWaitMillis(MAX_WAIT);
		// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
		config.setTestOnBorrow(true);

		// config.setTestOnBorrow(false);
		// config.setTestOnReturn(false);

		
		jedisPool = new JedisPool(config, HOST, PORT, 3000, PASSWORD);

	}

	public static Jedis getConnection() {
		return jedisPool.getResource();
	}

	public static void close() {
		if (jedisPool != null) {
			jedisPool.close();
		}
	}
}
