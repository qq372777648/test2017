package 概率模拟测试;
/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年7月18日 上午10:25:12 
* @version:1.0
* @description:
*/
public class 抢手决斗胜率计算 {
	protected static double A=0.6;
	protected static double B=0.5;
	
	protected static double awin=0;
	protected static double bwin=0;
	protected static double alldie=0;
	
	protected static final double safe=(1-A)*(1-B);
	protected static final double roundA=A*(1-B);
	protected static final double roundB=B*(1-A);
	
	public static void main(String[] args) {
		System.out.println("A胜率   ：  B胜率 ： 双亡率   ：双安率");
		for (int i = 1; i < 10; i++) {
			kill(i);
//			System.out.println(roundSafe(2));
		}
	}
	
	public static void kill(int round){
		double preSafe=roundSafe(round);
//		System.out.println(preSafe);
		awin=preSafe*roundA+awin;
		bwin=preSafe*roundB+bwin;
		alldie=1-awin-bwin-preSafe*safe;
		System.out.println(awin+"  :  "+bwin+"  :  "+alldie+"  :  "+((double)preSafe*safe));
	}
	
	public static double roundSafe(int i){
		if(--i>0){
//			System.out.print("!safe");
			return safe*roundSafe(i);
		}
//		System.out.print("*safe");
		return 1;
	}
	

}
