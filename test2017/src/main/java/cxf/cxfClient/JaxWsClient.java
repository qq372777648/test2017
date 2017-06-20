package cxf.cxfClient;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import cxf.cxfServer.HelloService;
import cxf.cxfServer.HelloServiceImpl;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年5月24日 下午5:02:07 
* @version:1.0
* @description:
*/
public class JaxWsClient {
	public static void main(String[] args) {
//		//返回结果
////		JaxWsDynamicClientFactory f=JaxWsDynamicClientFactory.newInstance();//only use for jaxwsserver
//		DynamicClientFactory f=DynamicClientFactory.newInstance();//use for jaxwsserver and simple方式的
//		Client client =f.createClient("http://localhost:8081/ws/soap/hello?wsdl");
//		try {
//			System.out.println(client.invoke("say", "lzw")[0]);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//返回实现类（client HelloService.class 与 service 的一致  包括包名）
		JaxWsProxyFactoryBean f2=new JaxWsProxyFactoryBean();// for simple方式的
		f2.setAddress("http://139.162.80.167:8081/ws/soap/hello");
		f2.setServiceClass(net.lzw.jvframeworkTest.cxfServer.HelloService.class);
		net.lzw.jvframeworkTest.cxfServer.HelloService helloService =f2.create(net.lzw.jvframeworkTest.cxfServer.HelloService.class);
		System.out.println(helloService.say("klc"));
		
	}

}
