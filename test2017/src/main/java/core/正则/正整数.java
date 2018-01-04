package core.正则;

import java.util.regex.Pattern;

/** 
* @author: liangzhenwei
* @create：2017年11月29日 下午12:36:55 
* @company:广州荔支网络技术有限公司
*/
public class 正整数 {
	public static void main(String[] args) {
		String pattern2="^\\d+$";
		System.out.println(Pattern.matches(pattern2, "01234567890"));
	}

}
