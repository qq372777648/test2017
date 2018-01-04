package 动态代理.cglib.接口代理;

import java.lang.reflect.Method;
import java.util.Arrays;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/** 
* @author: liangzhenwei
* @create：2017年11月7日 上午11:50:14 
*/
public class RpcConsumerMethodInterceptor implements MethodInterceptor {

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println(method.getName());
		System.out.println(Arrays.asList(args));
		return method.getReturnType().newInstance();
	}



}
//
//private static final Logger logger = LoggerFactory.getLogger(RpcConsumerMethodInterceptor.class);
//private static final Logger accessLogger = LoggerFactory.getLogger("access_log");
//
//private static final SeqGenerator seqGenerator = new SeqGenerator(0, 1);
//
//TransformerFactory transformerFactory;
//
//ProxyTransport proxyTransport;
//
//long timeoutInMillis;
//
//int ver;
//
///**
// * 拦截和处理远程调用请求
// * 
// * @see net.sf.cglib.proxy.MethodInterceptor#intercept(java.lang.Object,
// *      java.lang.reflect.Method, java.lang.Object[],
// *      net.sf.cglib.proxy.MethodProxy)
// */
//@SuppressWarnings("deprecation")
//@Override
//public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//	Service serviceAnnotation = method.getAnnotation(Service.class);
//	if (serviceAnnotation == null) {
//		throw new RuntimeException();
//	}
//
//	ServiceProto.Request request = null;
//	ServiceProto.Response response = null;
//	SendCompleteNotifier sendCompleteNotifier = null;
//	String uuid = null;
//	long startTime = System.currentTimeMillis();
//	try {
//		ServiceProto.Request.Builder requestBuilder = request(serviceAnnotation, method, args);
//
//		// 如果是APP发起的请求，则app server会在线程中存入相关的日志信息
//		AppClientRequestLog appLog = LogMsgManager.getAppLogFromThreadLocal();
//
//		// 设置透传数据
//		setTransmitDatas(requestBuilder, appLog);
//		uuid = requestBuilder.getUuid();
//
//		// NOTE: 记录 RPC Client 调用日志
//		InvokerSendTransmissionParam invokerSendResult = saveRpcInvokerSendLog(requestBuilder);
//
//		// 更新 rpc id
//		if (invokerSendResult != null) {
//			requestBuilder.setRpcId(invokerSendResult.getDownstreamRpcId());
//		}
//
//		request = requestBuilder.build();
//		sendCompleteNotifier = createSendCompleteNotifier(request, appLog);
//
//		accessLogger.info(Joiner.on("`").join("socket_req", "uuid=" + uuid, "seq=" + request.getSeq(),
//				"domain=" + request.getDomain(), "op=" + request.getOp()));
//
//		if (Objects.equal(1, ver)) {
//			response = proxyTransport.send(request, sendCompleteNotifier, getTimeoutInMillis());
//		} else {
//			response = proxyTransport.send(new VerSpecifiedRequest(request, ver), sendCompleteNotifier,
//					getTimeoutInMillis());
//		}
//
//		// NOTE: 保存RPC客户端接收调用结果日志
//		saveRpcInvokerReceiveLog(response, sendCompleteNotifier);
//
//		return result(response, serviceAnnotation, method);
//	} catch (TimeoutException e) {
//		// NOTE: 保存RPC客户端接收调用超时日志
//		saveRpcInvokerTimeoutLog(request, sendCompleteNotifier);
//		throw e;
//	} finally {
//		// 如果RPC请求不是由外部请求触发，则释放透传日志对象
//		boolean isRpcTriggerByMySelf = isRpcTriggerByMySelfInsteadOfClientRequest();
//		if (isRpcTriggerByMySelf && StringUtils.isNotBlank(uuid)) {
//			TracerUtil.releaseTransmissionMsg(uuid);
//		}
//
//		accessLogger.info(Joiner.on("`").join("socket_res", "uuid=" + uuid, "seq=" + request.getSeq(),
//				"domain=" + request.getDomain(), "op=" + request.getOp(),
//				"t=" + (System.currentTimeMillis() - startTime)));
//	}
//}
//
///**
// * RPC调用是不是由自身触发的（而不是由外部请求触发）
// * 
// * @return true 是； false 不是；
// */
//private boolean isRpcTriggerByMySelfInsteadOfClientRequest() {
//	return LogMsgManager.getAppLogFromThreadLocal() == null;
//}
//
///**
// * 设置透传数据
// * 
// * @param requestBuilder
// *            请求builder
// * @param appLog
// *            app请求日志
// */
//private void setTransmitDatas(ServiceProto.Request.Builder requestBuilder, AppClientRequestLog appLog) {
//	if (appLog != null) {
//		requestBuilder.setUuid(appLog.getUuid());
//		requestBuilder.setUserIp(appLog.getUserIp());
//		requestBuilder.setUserPort(appLog.getUserPort());
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("AppClientRequestLog found from thread local. appLog: {}", appLog.toString());
//		}
//		return;
//	}
//
//	requestBuilder.setUuid(LogUtils.generateRpcUuid());
//	requestBuilder.setUserIp(LogUtils.DEFAULT_IP);
//	requestBuilder.setUserPort(LogUtils.DEFAULT_PORT);
//	if (logger.isDebugEnabled()) {
//		logger.debug("AppClientRequestLog not found from thread local. use default values.");
//	}
//	return;
//}
//
///**
// * 消息发送完成通知器
// */
//private SendCompleteNotifier createSendCompleteNotifier(final ServiceProto.Request request,
//		final AppClientRequestLog appLog) {
//
//	return new SendCompleteNotifier() {
//		/** 发送消息时，对方的IP和端口 */
//		String hostAddress;
//		/** 发送消息时，本机的IP和端口 */
//		String localAddress;
//		/** channel 信息 */
//		String channelName;
//
//		@Override
//		public String getHostAddress() {
//			return hostAddress;
//		}
//
//		@Override
//		public SendCompleteNotifier setHostAddress(String hostAddress) {
//			this.hostAddress = hostAddress;
//			return this;
//		}
//
//		@Override
//		public String getLocalAddress() {
//			return localAddress;
//		}
//
//		@Override
//		public SendCompleteNotifier setLocalAddress(String localAddress) {
//			this.localAddress = localAddress;
//			return this;
//		}
//
//		@Override
//		public String getChannelName() {
//			return channelName;
//		}
//
//		@Override
//		public SendCompleteNotifier setChannelName(String channelName) {
//			this.channelName = channelName;
//			return this;
//		}
//
//		@Override
//		public void whenSendComplete() {
//			// 更新发送时，本机ip和端口
//			TracerUtil.updateInvokerDownstreamLocalIp(request.getUuid(), getLocalAddress());
//
//			if (appLog != null) {
//				appLog.setRpcId(request.getRpcId());
//			}
//		}
//
//	};
//}
//
///**
// * 保存RPC客户端远程调用日志日志
// * 
// * @param request
// *            调用请求
// */
//private InvokerSendTransmissionParam saveRpcInvokerSendLog(ServiceProto.Request.Builder requestBuilder) {
//	InvokerSendParam sendParam = new InvokerSendParam();
//	sendParam.setSysName(LogUtils.SYS_NAME);
//	sendParam.setLocalIp(null); // local ip 在发送完成时,再在回调对象中更新
//	sendParam.setUserIp(Joiner.on(":").join(requestBuilder.getUserIp(), requestBuilder.getUserPort()));
//	sendParam.setReqSize(requestBuilder.getData().size());
//	sendParam.setUuid(requestBuilder.getUuid());
//	sendParam.setSeq(requestBuilder.getSeq());
//	sendParam.setDomain(requestBuilder.getDomain());
//	sendParam.setOp(requestBuilder.getOp());
//	return TracerUtil.invokerSend(sendParam);
//}
//
///**
// * 保存RPC客户端接收调用超时日志
// * 
// * @param response
// *            调用结果
// */
//private void saveRpcInvokerTimeoutLog(ServiceProto.Request request, SendCompleteNotifier sendCompleteNotifier) {
//	InvokerReceiveParam receiveParam = new InvokerReceiveParam();
//	receiveParam.setUuid(request.getUuid());
//	receiveParam.setSeq(request.getSeq());
//	receiveParam.setTimeout(true);
//	if (sendCompleteNotifier != null) {
//		receiveParam.setProxyIp(sendCompleteNotifier.getHostAddress());
//	}
//
//	TracerUtil.invokerReceive(receiveParam);
//}
//
///**
// * 保存RPC客户端接收调用结果日志
// * 
// * @param response
// *            调用结果
// */
//private void saveRpcInvokerReceiveLog(ServiceProto.Response response, SendCompleteNotifier sendCompleteNotifier) {
//	InvokerReceiveParam receiveParam = new InvokerReceiveParam();
//	receiveParam.setRcode(response.getRcode());
//	receiveParam.setResSize(response.getData().size());
//	receiveParam.setUuid(response.getUuid());
//	receiveParam.setSeq(response.getSeq());
//	receiveParam.setTimeout(false);
//	receiveParam.setProxyIp(sendCompleteNotifier.getHostAddress());
//	TracerUtil.invokerReceive(receiveParam);
//}
//
///**
// * 组装请求对象
// * 
// * @param serviceAnnotation
// *            服务注解
// * @param method
// *            远程调用方法
// * @param args
// *            方法使用的参数
// * @return 请求对象
// * @throws Exception
// */
//ServiceProto.Request.Builder request(Service serviceAnnotation, Method method, Object[] args) throws Exception {
//	ServiceProto.Request.Builder requestBuilder = ServiceProto.Request.newBuilder();
//	requestBuilder.setSeq(seqGenerator.genSeq()).setOp(serviceAnnotation.op())
//			.setDomain(serviceAnnotation.domain());
//
//	if (Objects.equal(1, args.length) && paramAnnotation(method, 0) == null
//			&& Objects.equal(serviceAnnotation.request(), args[0].getClass())) {
//		requestBuilder.setData(((MessageLite) args[0]).toByteString());
//	} else {
//		MessageLite.Builder requestBizDataBuilder = (Builder) serviceAnnotation.request()
//				.getDeclaredMethod("newBuilder").invoke(null);
//
//		for (int idx = 0; idx < method.getParameterTypes().length; ++idx) {
//			Attribute attrAnnotation = paramAnnotation(method, idx);
//			if (attrAnnotation == null) {
//				logger.warn("unannotated param, method: " + method.getName());
//				continue;
//			}
//
//			if (args[idx] == null) {
//				continue;
//			}
//
//			Object target = transformerFactory.getJavaTransformer(args[idx]).transform2Pb(args[idx]);
//			Method setter = requestBizDataBuilder.getClass().getDeclaredMethod(
//					target instanceof List ? SetterUtils.genListSetterName(attrAnnotation.name())
//							: SetterUtils.genSetterName(attrAnnotation.name()),
//					SetterUtils.getActrualClassIfWrapper(target.getClass()));
//			if (setter == null) {
//				throw new IllegalArgumentException("no setter for attributeName: " + attrAnnotation.name());
//			}
//			setter.invoke(requestBizDataBuilder, target);
//		}
//
//		requestBuilder.setData(requestBizDataBuilder.build().toByteString());
//	}
//	return requestBuilder;
//}
//
///**
// * 处理远程调用回复，包装成远程调用结果
// * 
// * @param response
// *            回复
// * @param serviceAnnotation
// *            服务注解
// * @param method
// *            远程调用的方法
// * @return 远程调用结果
// * @throws Exception
// */
//Object result(ServiceProto.Response response, Service serviceAnnotation, Method method) throws Exception {
//	try {
//		if (Void.class.isAssignableFrom(method.getReturnType())
//				|| Void.TYPE.isAssignableFrom(method.getReturnType())) {
//			return null;
//		}
//
//		if (!Objects.equal(0, response.getRcode())
//				|| Objects.equal(MessageLite.class, serviceAnnotation.response())
//				|| method.getAnnotation(Return.class) == null
//				|| Objects.equal(Void.class, method.getAnnotation(Return.class).resultType())) {
//			return new Result<Object>(response.getRcode(), null, response.getData().toByteArray());
//		}
//
//		Method parseFromMethod = serviceAnnotation.response().getDeclaredMethod("parseFrom", ByteString.class);
//		Object dcResult = parseFromMethod.invoke(null, response.getData());
//
//		if (serviceAnnotation.response().isAssignableFrom(method.getAnnotation(Return.class).resultType())) {
//			return new Result<Object>(0, dcResult, response.getData().toByteArray());
//		}
//
//		Return returnTypeAttributeAnnotation = method.getAnnotation(Return.class);
//		Class<?> returnTargetCls = returnTypeAttributeAnnotation.resultType();
//
//		if (List.class.isAssignableFrom(returnTargetCls)) {
//			Method getter = dcResult.getClass().getDeclaredMethod(
//					GetterUtils.genListGetterName(returnTypeAttributeAnnotation.name()));
//			Object pbResult = getter.invoke(dcResult);
//			return new Result<Object>(0, transformerFactory.getPbTransformer(pbResult).transform2Java(pbResult,
//					returnTypeAttributeAnnotation.genericType()), response.getData().toByteArray());
//		} else if (returnTargetCls.getAnnotation(Message.class) != null
//				&& Objects.equal(returnTargetCls.getAnnotation(Message.class).message(),
//						serviceAnnotation.response())) {
//			return new Result<Object>(0, transformerFactory.getPbTransformer(dcResult).transform2Java(dcResult,
//					returnTargetCls), response.getData().toByteArray());
//		} else {
//			Method getter = dcResult.getClass().getDeclaredMethod(
//					GetterUtils.genGetterName(returnTypeAttributeAnnotation.name()));
//			Object pbResult = getter.invoke(dcResult);
//			return new Result<Object>(0, transformerFactory.getPbTransformer(pbResult).transform2Java(pbResult,
//					returnTargetCls), response.getData().toByteArray());
//		}
//	} catch (Exception e) {
//		throw new RuntimeException(e);
//	}
//}
//
//Attribute paramAnnotation(Method method, int idx) {
//	for (Annotation annotation : method.getParameterAnnotations()[idx]) {
//		if (annotation instanceof Attribute) {
//			return (Attribute) annotation;
//		}
//	}
//	return null;
//}
//
//public TransformerFactory getTransformerFactory() {
//	return transformerFactory;
//}
//
//public void setTransformerFactory(TransformerFactory transformerFactory) {
//	this.transformerFactory = transformerFactory;
//}
//
//public ProxyTransport getProxyTransport() {
//	return proxyTransport;
//}
//
//public void setProxyTransport(ProxyTransport proxyTransport) {
//	this.proxyTransport = proxyTransport;
//}
//
//public long getTimeoutInMillis() {
//	return timeoutInMillis;
//}
//
//public void setTimeoutInMillis(long timeoutInMillis) {
//	this.timeoutInMillis = timeoutInMillis;
//}
//
//public int getVer() {
//	return ver;
//}
//
//public void setVer(int ver) {
//	this.ver = ver;
//}
