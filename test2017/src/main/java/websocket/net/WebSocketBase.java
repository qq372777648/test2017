package websocket.net;

import java.net.URI;
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

	private Logger log = Logger.getLogger(WebSocketBase.class);
	private Timer timerTask = null;
	private MoniterTask moniter = null;
	private EventLoopGroup group = null;
	private Bootstrap bootstrap = null;
	private Channel channel = null;
	private ChannelFuture future = null;
	private String url ="wss://testnet.bitmex.com:80/realtime";// "wss://real.okcoin.cn:10440/websocket/okcoinapi";
	private WebSocketService service=new WebSocketService() {
		
		public void onReceive(String msg) {
			System.out.println(msg);
			
		}
	} ;


	public void start() {
		moniter = new MoniterTask(this);
		this.connect();
		timerTask = new Timer();
		timerTask.schedule(moniter, 1000, 5000);
	}


	public void addChannel(String channel) {
		String dataMsg = "{'event':'addChannel','channel':'" + channel+ "'}";
		this.sendMessage(dataMsg);
	}

	public void removeChannel(String channel) {
		String dataMsg = "{'event':'removeChannel','channel':'" + channel
				+ "'}";
		this.sendMessage(dataMsg);
	}

//	public void spotTrade(String apiKey, String secretKey, String symbol,
//			String price, String amount, String type) {
//		Map<String, String> signPreMap = new HashMap<String, String>();
//		signPreMap.put("api_key", apiKey);
//		signPreMap.put("symbol", symbol);
//		if (price != null) {
//			signPreMap.put("price", price);
//		}
//		if (amount != null) {
//			signPreMap.put("amount", amount);
//		}
//		signPreMap.put("type", type);
//		String preStr = MD5Util.createLinkString(signPreMap);
//		StringBuilder preBuilder = new StringBuilder(preStr);
//		preBuilder.append("&secret_key=").append(secretKey);
//		String signStr = MD5Util.getMD5String(preBuilder.toString());
//		String channel = "ok_spotcny_trade";
//		if (siteFlag == 1) {
//			channel = "ok_spotusd_trade";
//		}
//		StringBuilder tradeStr = new StringBuilder(
//				"{'event':'addChannel','channel':'" + channel
//						+ "','parameters':");
//		signPreMap.put("sign", signStr);
//		String params = MD5Util.getParams(signPreMap);
//		tradeStr.append(params).append("}");
//		log.info(tradeStr.toString());
//		this.sendMessage(tradeStr.toString());
//	}
	
	public static void main(String[] args) throws InterruptedException {
		WebSocketBase ws=new WebSocketBase();
		ws.start();
		Thread.sleep(2000);
		ws.sendMessage("help");
	}

	private void connect() {
		try {
			final URI uri = new URI(url);
			group = new NioEventLoopGroup(1);
			bootstrap = new Bootstrap();
			final SslContext sslCtx = SslContext.newClientContext();
			final WebSocketClientHandler handler = new WebSocketClientHandler(
					WebSocketClientHandshakerFactory.newHandshaker(uri,
							WebSocketVersion.V13, null, false,
							new DefaultHttpHeaders(), Integer.MAX_VALUE),
					service, moniter);
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
			group.shutdownGracefully();
		}
	}

	private void sendMessage(String message) {
		channel.writeAndFlush(new TextWebSocketFrame(message));
	}

	public void sentPing() {
		String dataMsg = "{'event':'ping'}";
		this.sendMessage(dataMsg);
	}


	public void setUrl(String url) {
		this.url = url;
	}

}

class MoniterTask extends TimerTask {

	private Logger log = Logger.getLogger(WebSocketBase.class);
	private long startTime = System.currentTimeMillis();
	private int checkTime = 5000;
	private WebSocketBase client = null;

	public void updateTime() {
		// log.info("startTime is update");
		startTime = System.currentTimeMillis();
	}

	public MoniterTask(WebSocketBase client) {
		this.client = client;
		// log.info("TimerTask is starting.... ");
	}

	public void run() {
//		if (System.currentTimeMillis() - startTime > checkTime) {
//			client.setStatus(false);
//			// log.info("Moniter reconnect....... ");
//			client.reConnect();
//		}
		client.sentPing();
		// log.info("Moniter ping data sent.... ");
	}

}
