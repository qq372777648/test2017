package core.注解.annotations;

import java.io.Serializable;
import java.lang.annotation.Annotation;

//
public class AnnotationImpl implements Db, Serializable {
	String str;

	public Class<? extends Annotation> annotationType() {
		return Db.class;
	}

	public String value() {
		return this.str;
	}

	public AnnotationImpl(String value) {
		this.str = value;
	}

}
