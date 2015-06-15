package com.test.gzipfilterdemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class MyServletOutPutStream extends ServletOutputStream{
	
	private ByteArrayOutputStream bout;
	
	public MyServletOutPutStream(ByteArrayOutputStream bout) {
		// TODO Auto-generated constructor stub
		this.bout=bout;
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWriteListener(WriteListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
		this.bout.write(b);
	}

}
