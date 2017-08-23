package core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年7月13日 下午4:19:50 
* @version:1.0
* @description:
*/
public class BigDecimalTest {
	
	public static void main(String[] args) {
//		
//		 DecimalFormat df = new DecimalFormat("#.00");
//		
//		 System.out.println( df.format(2.985));
//		
//		BigDecimal b=new BigDecimal(1.825);
//		
//		 System.out.println( df.format(b));
		 
		 
		 
		 BigDecimal b2 =  new  BigDecimal(Double.toString(-1.235)); 
	       BigDecimal one =  new  BigDecimal("1"); 
	       System.out.println(b2.divide(one,2,BigDecimal.ROUND_HALF_UP).toString());

	        
//		
//		System.out.println(b.setScale(1).doubleValue());
		
	}

}
