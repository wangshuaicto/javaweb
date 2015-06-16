package com.test.annotationservlet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebServlet {
	//servlet的访问URL
	String value();
	//servlet访问的url
	String[] urlPatterns() default {""};
	//servlet的描述
	String description() default "";
	//servlet显示名称
	String displayName() default "";
	//servlet名称
	String name() default "";
	//servlet的初始化参数
	WebInitParam[] initParams() default {};
}
