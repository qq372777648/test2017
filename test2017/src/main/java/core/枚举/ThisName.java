package core.枚举;

enum E {
	A,
	B;
}

public class ThisName{
	public static void main(String[] args) {
		
		System.out.println(E.A.name());
		System.out.println(E.B.name());
	}
}