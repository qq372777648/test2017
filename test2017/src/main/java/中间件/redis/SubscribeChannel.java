package 中间件.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPubSub;

/** 
* @author: liangzhenwei
* @create：2018年2月28日 下午6:05:17 
* @company:广州荔支网络技术有限公司
* @description
*/
public class SubscribeChannel extends JedisPubSub {
	 private static Logger logger = LoggerFactory.getLogger(SubscribeChannel.class);
	 
	 
	 /**
	  * 取得订阅的消息后的处理
	  */
	 @Override
	 public void onMessage(String channel, String message) {
		 System.out.println("onMessage: channel["+channel+"], message["+message+"]");
		 
	  logger.debug("onMessage: channel["+channel+"], message["+message+"]");
	  
	 }
	 /**
	  * 取得按表达式的方式订阅的消息后的处理
	  */
	 @Override
	 public void onPMessage(String pattern, String channel, String message) {
	  logger.debug("onPMessage: channel["+channel+"], message["+message+"]");
	  
	 }
	 /**
	  * 初始化订阅时候的处理   
	  */
	 @Override
	 public void onSubscribe(String channel, int subscribedChannels) {
	   logger.debug("onSubscribe: channel["+channel+"],"+
	     "subscribedChannels["+subscribedChannels+"]");
	  
	 }
	 /**
	  * 取消订阅时候的处理 
	  */
	 @Override
	 public void onUnsubscribe(String channel, int subscribedChannels) {
	  logger.debug("onUnsubscribe: channel["+channel+"], "+
	                                               "subscribedChannels["+subscribedChannels+"]");
	  
	 }
	 /**
	  * 取消按表达式的方式订阅时候的处理
	  */
	 @Override
	 public void onPUnsubscribe(String pattern, int subscribedChannels) {
	   logger.debug("onPUnsubscribe: pattern["+pattern+"],"+
	                                               "subscribedChannels["+subscribedChannels+"]");
	  
	 }
	 
	 /**
	  * 初始化按表达式的方式订阅时候的处理 
	  */
	 @Override
	 public void onPSubscribe(String pattern, int subscribedChannels) {
	  logger.debug("onPSubscribe: pattern["+pattern+"], "+
	                                               "subscribedChannels["+subscribedChannels+"]");
	  
	 }
	 
	 
	}