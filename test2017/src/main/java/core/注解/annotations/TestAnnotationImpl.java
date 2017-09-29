package core.注解.annotations;

import java.lang.annotation.Annotation;

public class TestAnnotationImpl {
	public static void main(String[] args) {
		AnnotationImpl aimpl=new  AnnotationImpl("testAnnotationImpl");
			System.out.println(aimpl instanceof Annotation);
	}

}
