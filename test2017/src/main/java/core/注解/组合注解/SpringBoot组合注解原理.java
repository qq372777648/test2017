//package core.注解.组合注解;
//
///** 
//* @author: liangzhenwei
//* @create：2018年1月4日 下午2:20:49 
//* @company:广州荔支网络技术有限公司
//*/
//public class SpringBoot组合注解原理 {
//	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
//		SpringBootApplication  a=TestSpringbootApplication.class.getAnnotation(SpringBootApplication.class);
//		System.out.println(a);
//		System.out.println(a.annotationType().getAnnotations().length);
//		System.out.println(a.annotationType().getAnnotation(SpringBootConfiguration.class));
//		System.out.println(a.annotationType().getDeclaredMethod("scanBasePackages").getAnnotation(AliasFor.class));
////		a.annotationType().
//		
//		SpringBootConfiguration a2=a.annotationType().getAnnotation(SpringBootConfiguration.class);
//		System.out.println(a2);
//	}
//
//}
//
//
//@SpringBootApplication
//@RestController
// class TestSpringbootApplication {
//	
//	 @Value("${book.author}")
//	 private String bookAuthor;
//	 @Value("${book.name}")
//	 private String bookName;
//
//	@RequestMapping("/")
//	String index() {
//
//		return "book name is:" + bookName + " and book author is:" + bookAuthor;
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(TestSpringbootApplication.class, args);
//	}
//}