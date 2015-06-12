package com.test.request.form;

import java.util.Random;

import org.apache.tomcat.util.codec.binary.Base64;

public class TokenProcesser {
	private static final TokenProcesser instance = new TokenProcesser();
	public static TokenProcesser getInstance()
	{
		return instance;
	}
	public String makeToken()
	{
		String md5 = (System.currentTimeMillis()+new Random().nextInt(999999999))+"";
		String token = Base64.encodeBase64String(md5.getBytes());
		return token;
	}
}
