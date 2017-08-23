package core.正则;

import java.util.regex.Pattern;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年8月9日 下午5:05:10 
* @version:1.0
* @description:
*/
public class 字母数字中文指定特殊符号 {
	public static void main(String[] args) {
		
		String pattern = "^[a-zA-Z0-9_·\\-\\(\\)\u4e00-\u9fa5]+$";
		System.out.println(Pattern.matches(pattern, "sdflj_1中午9)()_-疆人·买买提*"));
	}
}
