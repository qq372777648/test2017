package cxf.cxfServer;

import javax.jws.WebService;

/**
 * @author: lzw
 * @email:372777648@qq.com
 * @create：2017年5月24日 下午4:44:01
 * @version:1.0
 * @description:
 */

@WebService

public class HelloServiceImpl implements HelloService {

	// public ServiceResult say(String name) {
	// System.out.println(name+":hello world");
	// return new ServiceResult(1, name+":你好");
	// }

	public String say(String name) {
		System.out.println(name + ":hello world");
		return name + ":你好";
	}

}
