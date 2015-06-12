package com.test.i18n;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;

public class MessageFormatTest {
	
	public static void main(String[] args)
	{
		String pattern = "there is a {0} in the {1},{2,date,short}! it costs {3,number,currency},at {4,time,short}";
		MessageFormat mf = new MessageFormat(pattern,Locale.CHINA);
		Object[] data = new Object[]{"sb","office",new Date(),123,new Date()};
		String result = mf.format(data);
		System.out.println(result);
	}

}
