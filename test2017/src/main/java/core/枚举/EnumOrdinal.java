package core.枚举;

enum Mobile {
	Samsung(400), Nokia(250), Motorola(325);

	int price;

	Mobile(int p) {
		price = p;
	}

	int showPrice() {
		return price;
	}
}

public class EnumOrdinal {

	public static void main(String args[]) {

		System.out.println("CellPhone List:");
		for (Mobile m : Mobile.values()) {
			System.out.println(m + " costs " + m.showPrice() + " dollars"+" 枚举中排序："+m.ordinal());
		}

		Mobile ret = Mobile.Motorola;
		System.out.println("The ordinal is = " + ret.ordinal());
		System.out.println("MobileName = " + ret.name());
	}
}