package cxf.cxfServer;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年5月24日 下午4:46:25 
* @version:1.0
* @description:
*/
public class JaxWsServer {
	public static void main(String[] args) {
		JaxWsServerFactoryBean f=new JaxWsServerFactoryBean();
		f.setAddress("http://localhost:8081/ws/soap/hello");
		f.setServiceClass(HelloService.class);
		f.setServiceBean(new HelloServiceImpl());
		f.create();
		System.out.println("soap ws is published");
		
	}

}
