package core.正则;

import java.util.regex.Pattern;

/**
 * @author: lzw
 * @email:372777648@qq.com
 * @create：2017年8月9日 下午5:03:01
 * @version:1.0
 * @description:
 */
public class 字母数字下划线 {
	public static void main(String[] args) {
		String pattern = "^\\w+$";
		System.out.println(Pattern.matches(pattern, "sdflj_1"));
	}

}
