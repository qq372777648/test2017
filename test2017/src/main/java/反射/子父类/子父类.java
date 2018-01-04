package 反射.子父类; 
/** 
* @author: liangzhenwei
* @create：2017年11月9日 下午6:35:43 
*/
public class 子父类 {
	public static void main(String[] args) {
		System.out.println(Parent.class.isAssignableFrom(Son.class));
		System.out.println(Son.class.isAssignableFrom(Parent.class));
		System.out.println(Parent.class.isAssignableFrom(Parent.class));
		
		System.out.println(IFlyable.class.isAssignableFrom(Brid.class));
		System.out.println(Brid.class.isAssignableFrom(IFlyable.class));
	}

}


class Parent{
	
}

class Son extends Parent{
	
}

interface IFlyable{
	
}

class Brid implements IFlyable{
	
}
 