package 反射.泛型获取;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lzw
 * @email:372777648@qq.com
 * @create：2017年6月3日 下午3:48:48
 * @version:1.0
 * @description:
 */
public class List泛型获取 {
	List<String> stringList = new ArrayList<String>();
	List<Integer> integerList = new ArrayList<Integer>();

	public static void main(String... args) throws Exception {
		Field stringListField = List泛型获取.class.getDeclaredField("stringList");
		ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
		Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
		System.out.println(stringListClass);// class java.lang.String.

		Field integerListField = List泛型获取.class.getDeclaredField("integerList");
		ParameterizedType integerListType = (ParameterizedType) integerListField.getGenericType();
		Class<?> integerListClass = (Class<?>) integerListType.getActualTypeArguments()[0];
		System.out.println(integerListClass);// class java.lang.Integer.
	}

}
