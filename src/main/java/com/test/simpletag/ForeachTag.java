package com.test.simpletag;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ForeachTag extends SimpleTagSupport{
	
	private Object items;

	private Collection collection;
	
	private String var;
	
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspFragment jspfragment = this.getJspBody();
		PageContext pagecontext = (PageContext) jspfragment.getJspContext();
		Iterator it = collection.iterator();
		while(it.hasNext())
		{
			Object data =it.next();
			pagecontext.setAttribute(var, data);
			jspfragment.invoke(null);
		}
	}

	
	public void setItems(Object items)
	{
		if(items instanceof Collection)
		{
			this.collection =  (Collection) items;
		}else if(items instanceof Map)
		{
			Map map = (Map) items;
			collection = map.entrySet();
		}else if(items.getClass().isArray())
		{
			List temp = new ArrayList();
			int len = Array.getLength(items);
			for(int i=0;i<len;i++)
			{
				temp.add(Array.get(items, i));
			}
			collection = temp;
		}
		this.items=items;
	}
	
	public void setVar(String var)
	{
		this.var=var;
	}
	
}
