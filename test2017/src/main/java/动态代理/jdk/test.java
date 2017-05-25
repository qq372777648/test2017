package 动态代理.jdk;
/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年5月25日 下午4:01:22 
* @version:1.0
* @description:
*/
public class test {
	public static void main(String[] args) {
		IPlay people=new JDKDynamicProxy(new People()).getProxy();
		System.out.println(people.play());
	}

}
