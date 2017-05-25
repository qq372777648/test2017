package 动态代理.jdk;
/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年5月25日 下午4:00:23 
* @version:1.0
* @description:
*/
public class People implements IPlay{

	public String play() {
		System.out.println("起来嗨");
		return "好累";
	}

}
