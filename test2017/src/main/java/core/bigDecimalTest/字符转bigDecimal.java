package core.bigDecimalTest;

import java.math.BigDecimal;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年8月9日 下午5:30:37 
* @version:1.0
* @description:
*/
public class 字符转bigDecimal {
	public static void main(String[] args) {
		String StrBd="1048576.1054";
		//构造以字符串内容为值的BigDecimal类型的变量bd
		BigDecimal bd=new BigDecimal(StrBd);
		System.out.println(bd);
		//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
		bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		//转化为字符串输出
//		String OutString=bd.toString();
		System.out.println(bd);
		System.out.println("12.3".indexOf("."));
	}

}
