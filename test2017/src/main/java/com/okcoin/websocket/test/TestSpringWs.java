package com.okcoin.websocket.test;

import java.lang.reflect.Type;
import java.util.Scanner;

import org.springframework.messaging.Message;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

/** 
* @author: liangzhenwei
* @create：2018年9月30日 下午5:55:23 
* @company:广州荔支网络技术有限公司
* @description
*/
public class TestSpringWs {
	public static void main(String[] args) {
		WebSocketClient client = new StandardWebSocketClient();
		 
		WebSocketStompClient stompClient = new WebSocketStompClient(client);
//		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		 
		StompSessionHandler sessionHandler = new StompSessionHandler(){

			public Type getPayloadType(StompHeaders arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public void handleFrame(StompHeaders headers, Object payload) {
//				    logger.info("Received : " + msg.getText()+ " from : " + msg.getFrom());

				System.out.println(1);
			}

			public void afterConnected(StompSession arg0, StompHeaders arg1) {
				// TODO Auto-generated method stub
				System.out.println(1);
				
			}

			public void handleException(StompSession arg0, StompCommand arg1, StompHeaders arg2, byte[] arg3,
					Throwable arg4) {
				// TODO Auto-generated method stub
				System.out.println(1);
			}

			public void handleTransportError(StompSession arg0, Throwable arg1) {
				// TODO Auto-generated method stub
				System.out.println(1);
			}
			
		};
		stompClient.connect("wss://www.bitmex.com/realtime", sessionHandler);
		 
		new Scanner(System.in).nextLine();
	}

}
