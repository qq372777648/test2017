package core.注解;

import core.注解.annotations.Db;

public class Test {
	public static void main(String[] args) {
		Table.class.getAnnotation(Db.class);
	}

}
