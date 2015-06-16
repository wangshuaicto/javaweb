package com.test.annotationservlet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebInitParam {
	//参数名
	String paramName() default "";
	//参数值
	String paramValue() default "";
}
