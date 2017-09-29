package core.运算;


//Java提供的位运算符有：左移( << )、右移( >> ) 、无符号右移( >>> ) 、
//位与( & ) 、位或( | )、位非( ~ )、位异或( ^ )，
//除了位非( ~ )是一元操作符外，其它的都是二元操作符。
public class 位运算 {

	public static void main(String[] args) {
		System.out.println(3&2);
		System.out.println(1|2);
		System.out.println(~5); //0000 0101-> 1111 1010->1000 0110-> -6
	}
}
