package test;
/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年6月23日 上午11:33:45 
* @version:1.0
* @description:
*/
public class Test1 {
	public static void main(String[] args) {
		String strs[]="asdf.jpg".split("\\.");
		for (int i = 0; i < strs.length; i++) {
			System.out.println(strs[i]);
		}
		
		String s="123";
		System.out.println(s.substring(1,2));
	}

}
