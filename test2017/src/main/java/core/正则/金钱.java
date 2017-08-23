package core.正则;

import java.util.regex.Pattern;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年8月9日 下午5:32:36 
* @version:1.0
* @description:
*/
public class 金钱 {
	public static void main(String[] args) {
		String pattern="^\\d+(\\.\\d{1,2})?$";
		System.out.println(Pattern.matches(pattern, "12.12"));
		
		//正小数
		String pattern2="^\\d+\\.\\d+$";
		System.out.println(Pattern.matches(pattern2, "12345678901.111"));
	}

}
