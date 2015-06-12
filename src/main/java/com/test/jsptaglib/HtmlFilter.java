package com.test.jsptaglib;

public class HtmlFilter {
	
	public static String filter(String message)
	{
		if(message == null)
		{
			return null;
		}
		char[] content = new char[message.length()];
		message.getChars(0, message.length(), content, 0);
		StringBuffer result = new StringBuffer(message.length()+50);
		for(int i=0 ;i<content.length;i++)
		{
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default:
				result.append(content[i]);
			}
		}
		System.out.println("执行了Function.....");
		return result.toString();
	}

}
