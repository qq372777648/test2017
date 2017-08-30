package _1µ¥ÀýÄ£Ê½;

public class test {
	public static void main(String[] args) {
		SingleTonObj s=SingleTonObj.getInstance();
		SingleTonObj ss=SingleTonObj.getInstance();
		SingleTonObj sss=SingleTonObj.getInstance();
		System.out.println(s);
		System.out.println(ss);
		System.out.println(sss);
	}

}
