package com.test.annotationservlet;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ScanClassUtil {
	
	/**
	 * 从包package中获取所有class
	 * @param pack
	 * @return
	 */
	public static Set<Class<?>> getClasses(String pack)
	{
		//第一个class类集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		//是否循环迭代
		boolean recursive = true;
		//获取包的名字并进行替换
		String packageName = pack;
		String packageDirName = packageName.replace(".", "/");
		//定义一个枚举的集合 并进行循环来处理目录下的things
		Enumeration<URL> dirs;
		try
		{
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			while (dirs.hasMoreElements()) {
				URL url = (URL) dirs.nextElement();
				//得到协议的名称
				String protocal = url.getProtocol();
				//如果是以文件的形式保存在服务器上
				if("file".equals(protocal))
				{
					System.err.println("file类型扫描...");
					//获取包的物理路径
					String filepath = URLDecoder.decode(url.getFile(), "utf-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filepath, recursive, classes);
				}else if("jar".equals(protocal))
				{
					//如果是jar包 定义一个jarfile
					System.err.println("jar类型的扫描...");
					JarFile jar;
					try
					{
						jar = ((JarURLConnection)url.openConnection()).getJarFile();
						//从此jar包中得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						//进行循环迭代
						while(entries.hasMoreElements())
						{
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							//-------------------未完成续写
							if(name.charAt(0)=='/')
							{
								//如果name是以/开始则截取后面的字符串
								name = name.substring(1);
							}
							if(name.startsWith(packageDirName))
							{
								//如果前半部分和定义的包名相同
								int idx = name.lastIndexOf('/');
								if(idx != -1)
								{
									packageName = name.substring(0,idx).replace('/', '.');
								}
								if((idx!=-1)|| recursive)
								{
									//如果是class而不是目录
									if(name.endsWith(".class") && !entry.isDirectory())
									{
										//去掉后面的.class获取真正的类名
										String className = name.substring(packageName.length()+1,name.length()-6);
										try
										{
											//添加到classes
											classes.add(Class.forName(packageName+"."+className));
										}catch(Exception e)
										{
											
										}
									}
								}
							}
						}
						
					}catch(Exception e)
					{
						
					}
				}
			}
		}catch(Exception e)
		{
			
		}
		return classes;
	}
	
	public static void  findAndAddClassesInPackageByFile(String packageName,String packagePath,final boolean recursive,Set<Class<?>> classes)
	{
		//获取此包的目录
		File dir = new File(packagePath);
		//如果不存在也不是目录直接返回
		if(!dir.exists()||!dir.isDirectory())
		{
			return;
		}
		//如果存在就获取包下的所有文件
		File[] dirfiles = dir.listFiles(new FileFilter() {
			
			public boolean accept(File file) {
				// TODO Auto-generated method stub
				return (recursive&&file.isDirectory())||(file.getName().endsWith(".class"));
			}
		});
		
		//循环所有文件
		for(File file:dirfiles)
		{
			//如果是目录则继续扫描
			if(file.isDirectory())
			{
				findAndAddClassesInPackageByFile(packageName+"."+file.getName(), file.getAbsolutePath(), recursive, classes);
			}else
			{
				//如果是java类文件，去掉后面的.class 只留下类名
				String className = file.getName().substring(0,file.getName().length()-6);
				//添加到集合中去
				try {
					classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName+"."+className));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}
