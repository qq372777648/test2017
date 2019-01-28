package com.okcoin.websocket;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.ssl.SslContext;

public  class WebSocketBase {

	protected Logger log = Logger.getLogger(WebSocketBase.class);
	protected Timer timerTask = null;
	protected EventLoopGroup group = null;
	protected Bootstrap bootstrap = null;
	protected Channel channel = null;
	protected String url = null;
	protected ChannelFuture future = null;
	protected Set<String> subscribChannel = new HashSet<String>();

	public WebSocketBase(String url) {
		this.url = url;
	}

	public void start() {
		if (url == null) {
			log.info("WebSocketClient start error  url can not be null");
			return;
		}
		this.connect();
	}






	protected void connect() {
		try {
			final URI uri = new URI(url);
			group = new NioEventLoopGroup(1);
			bootstrap = new Bootstrap();
			final SslContext sslCtx = SslContext.newClientContext();
			final WebSocketClientHandler handler = new WebSocketClientHandler(
					WebSocketClientHandshakerFactory.newHandshaker(uri,
							WebSocketVersion.V13, null, false,
							new DefaultHttpHeaders(), Integer.MAX_VALUE));
			bootstrap.group(group).option(ChannelOption.TCP_NODELAY, true)
					.channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						protected void initChannel(SocketChannel ch) {
							ChannelPipeline p = ch.pipeline();
							if (sslCtx != null) {
								p.addLast(sslCtx.newHandler(ch.alloc(),
										uri.getHost(), uri.getPort()));
							}
							p.addLast(new HttpClientCodec(),
									new HttpObjectAggregator(8192), handler);
						}
					});

			future = bootstrap.connect(uri.getHost(), uri.getPort());
			future.addListener(new ChannelFutureListener() {
				public void operationComplete(final ChannelFuture future)
						throws Exception {
				}
			});
			channel = future.sync().channel();
			handler.handshakeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("WebSocketClient start error ", e);
			group.shutdownGracefully();
		}
	}

	public void sendMessage(String message) {
		channel.writeAndFlush(new TextWebSocketFrame(message));
	}

	public void sendPing() {
		String dataMsg = "{'event':'ping'}";
		this.sendMessage(dataMsg);
	}

//	public void reConnect() {
//		try {
//			this.group.shutdownGracefully();
//			this.group = null;
//			this.connect();
//			if (future.isSuccess()) {
//				this.setStatus(true);
//				this.sentPing();
//				Iterator<String> it = subscribChannel.iterator();
//				while (it.hasNext()) {
//					String channel = it.next();
//					this.addChannel(channel);
//				}
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}


}


