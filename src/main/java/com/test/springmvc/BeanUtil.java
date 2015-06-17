package com.test.springmvc;

import java.lang.reflect.Method;

public class BeanUtil {
	public static Method[] findDeclaredMethods(Class<?> clazz)
	{
		return clazz.getDeclaredMethods();
	}
	
	public static <T> T instanceClass(Class<T> clazz)
	{
		if(!clazz.isInterface()) //不是接口
		{
			try {
				return  clazz.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
