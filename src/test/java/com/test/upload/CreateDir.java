package com.test.upload;

import java.io.File;
import java.util.UUID;

import org.junit.Test;

public class CreateDir {

	
	@Test
	public void createTest()
	{
		String path = "E:\\springmvc";
		File file = new File(path);
		
		if(file.exists())
		{
			
		}else
		{
			file.mkdirs();
		}
	}
	
	@Test
	public void creatuuid()
	{
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);
		String filename="你好";
		int hashcode = filename.hashCode();
		System.out.println(Integer.toBinaryString(hashcode));
		System.out.println(hashcode & 0xf);
	}
}
