package com.test.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NTest {
	
	public static void main(String[] args)
	{
		String basename="com.test.i18n.myproperties";
		Locale cn = Locale.CHINA;
		Locale en = Locale.US;
		ResourceBundle mycnresource = ResourceBundle.getBundle(basename, cn);
		ResourceBundle myenresource = ResourceBundle.getBundle(basename, en);
		
		String usernamecn =mycnresource.getString("username");
		String passwordcn = mycnresource.getString("password");
		
		String usernameen = myenresource.getString("username");
		String passworden = myenresource.getString("password");
		
		System.out.println(usernamecn+"---"+passwordcn);
		System.out.println(usernameen+"---"+passworden);
		
	}
	
	
}
