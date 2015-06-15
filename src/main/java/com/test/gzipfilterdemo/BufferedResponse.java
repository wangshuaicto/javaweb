package com.test.gzipfilterdemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class BufferedResponse extends HttpServletResponseWrapper{
	
	HttpServletResponse res =null;
	private ByteArrayOutputStream bout = new ByteArrayOutputStream();
	private PrintWriter pw;

	public BufferedResponse(HttpServletResponse response) {
		super(response);
		// TODO Auto-generated constructor stub
		this.res = (HttpServletResponse) response;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return new MyServletOutPutStream(bout);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		pw = new PrintWriter(new OutputStreamWriter(bout, res.getCharacterEncoding()));
		return pw;
	}
	
	public byte[] getBuffer()
	{
		try
		{
			if(pw != null)
			{
				pw.close();
			}
			if(bout !=null)
			{
				return bout.toByteArray();
			}
			return null;
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
