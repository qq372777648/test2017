package net.lzw.jvframeworkTest.cxfServer;

import javax.jws.WebService;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年5月24日 下午4:43:14 
* @version:1.0
* @description:
*/
@WebService
public interface HelloService {
//	public ServiceResult say(String name);
	public String say(String name);

}
