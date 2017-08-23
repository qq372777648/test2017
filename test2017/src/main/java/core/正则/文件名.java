package core.正则;

import java.util.regex.Pattern;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年8月14日 下午4:45:47 
* @version:1.0
* @description:
*/
public class 文件名 {
	public static void main(String[] args) {
		System.out.println(Pattern.matches("^[a-zA-Z0-9_\\-\\(\\)\u4e00-\u9fa5]+.\\w+$", "-1aB_收款订单模板.xlsx"));
	}

}
