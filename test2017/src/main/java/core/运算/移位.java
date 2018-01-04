package core.运算;

import java.util.Calendar;
import java.util.Date;

/** 
* @author: liangzhenwei
* @create：2017年12月28日 上午10:51:27 
* @company:广州荔支网络技术有限公司
*/

//>> 是带符号右移，若左操作数是正数，则高位补“0”，若左操作数是负数，则高位补“1”.
//
//<< 将左操作数向左边移动，并且在低位补0.
//
//>>> 是无符号右移，无论左操作数是正数还是负数，在高位都补“0”
public class 移位 {
	public static void main(String[] args) {
		int a=1;
		
		System.out.println(a<<2);
		System.out.println(-1<<2);
		//（默认int） 补码编码方式存储		11111111 11111111 11111111 11111111， 
		//		补码位移（符号不动,右补零）， 11111111 11111111 11111111 11111100
		//读取时将再取补（原码=补码的补码）           10000000 00000000 00000000 00000100 =-4
		
		System.out.println(a>>>2);
		
		System.out.println(0xff >>> 7);
		
		 
		 System.out.println((1 << 10) - 1);
		 // 00000000 00000000 00000000 00000001
		 // 00000000 00000000 00000100 00000000-1
		 // 00000000 00000000 00000011 11111111
		 System.out.println(0x03ff);
		 
		 int VERSION = 1;
		 long timeFlag=(new Date().getTime()-baseTime().getTime())/1000L;
		 System.out.printf("%010x\n",timeFlag);
		 long serverFlag=73;
		 long counterLimit = (1 << 12) - 1;
		 System.out.printf("%010x\n",counterLimit);
		 //1 30时间 10serverFlag  12 counterLimit  9serverFlag
		 System.out.printf("%010x\n",(((VERSION << 30 | (new Date().getTime()-baseTime().getTime())/1000L) << 10 | serverFlag) << 12 | counterLimit) << 9 | serverFlag);
	}
	
	private static Date baseTime() {
		Calendar c = Calendar.getInstance();
		// Calendar中月从0开始，所以1月是0
		c.set(2013, 0, 1, 0, 0, 0);
		return c.getTime();
	}

}
