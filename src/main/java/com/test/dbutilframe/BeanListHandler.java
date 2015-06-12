package com.test.dbutilframe;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler implements ResultSetHandler{
	
	public Class<?> clazz;
	
	public BeanListHandler(Class<?> clazz)
	{
		this.clazz = clazz;
	}

	public Object handler(ResultSet rs) {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		try
		{
			while(rs.next())
			{
				Object bean = clazz.newInstance();
				//得到结果集元数据
				ResultSetMetaData rsmeta = rs.getMetaData();
				//得到结果集中有几列
				int columncount = rsmeta.getColumnCount();
				for(int i=0;i<columncount;i++)
				{
					String columnname = rsmeta.getColumnName(i+1);//得到列名
					Object columndata = rs.getObject(i+1);//得到列的数据
					Field f = clazz.getDeclaredField(columnname);//反射出类上列名对应的属性
					f.setAccessible(true);
					f.set(bean, columndata);
				}
				list.add(bean);
			}
			return list.size()>0?list:null;
		}catch(Exception e)
		{
			throw new RuntimeException(e); 
		}
	}

}
