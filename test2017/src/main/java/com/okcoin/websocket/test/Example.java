package com.okcoin.websocket.test;

import com.okcoin.websocket.WebSoketClient;

/**
 * WebSocket API使用事例
 * 
 * @author okcoin
 * 
 */
public class Example {
	public static void main(String[] args) {

//		// apiKey 为用户申请的apiKey
//		String apiKey = "XXXXX";
//
//		// secretKey为用户申请的secretKey
//		String secretKey = "XXXXX";
//
//		apiKey = "9a3776bc-2c5f-4a54-8848-bf0338a563da1";
//		secretKey = "02E6CD90FE25C4AD06A8EEF34A69F7922";

		// 国际站WebSocket地址 注意如果访问国内站 请将 real.okcoin.com 改为 real.okcoin.cn
		String url = "wss://www.bitmex.com:80/realtime";

		// 订阅消息处理类,用于处理WebSocket服务返回的消息

		// WebSocket客户端
		WebSoketClient client = new WebSoketClient(url);

		// 启动客户端
		client.start();

		// 添加订阅
		client.sendMessage("{\"op\": \"subscribe\", \"args\": \"trade:XBTUSD\"}");

	}
}
