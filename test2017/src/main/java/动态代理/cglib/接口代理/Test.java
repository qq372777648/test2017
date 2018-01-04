package 动态代理.cglib.接口代理;

import java.lang.reflect.Method;
import java.util.Date;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import net.sf.cglib.proxy.Proxy;

/** 
* @author: liangzhenwei
* @create：2017年11月7日 上午11:48:01 
*/
public class Test {
	public static <T> T buildProxy(Class<T> cls, long timeoutInMillis) {
		RpcConsumerMethodInterceptor rpcConsumerMethodInterceptor = new RpcConsumerMethodInterceptor();

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		enhancer.setCallbackFilter(new CallbackFilter() {
	        public int accept(Method method) {
	                    return 1;
	        }
		});//过滤
		enhancer.setCallbacks(new Callback[] { NoOp.INSTANCE, rpcConsumerMethodInterceptor });

		return (T) enhancer.create();
	}
	
	public static void main(String[] args) {
		System.out.println(buildProxy(IA.class, 3000).doJob("lzw"));
		System.out.println(buildProxy(IA2.class, 3000).doJob("lzw",new Date()));
	}

}
 