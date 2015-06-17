package com.test.springmvc;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingMap {
	/**
	 * 存储方法的访问路径
	 */
	private static Map<String,Class<?>> requestMap = new HashMap<String, Class<?>>();
	
	public static Class<?> getClassName(String path)
	{
		return requestMap.get(path);
	}
	
	public static void put(String path,Class<?> className)
	{
		requestMap.put(path, className);
	}
	
	public static Map<String,Class<?>> getRequestMap()
	{
		return requestMap;
	}
}
