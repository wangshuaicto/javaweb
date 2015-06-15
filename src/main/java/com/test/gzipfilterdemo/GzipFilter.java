package com.test.gzipfilterdemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GzipFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		BufferedResponse bres = new BufferedResponse(res);
		chain.doFilter(req, bres);
		////拿出缓存中的数据，压缩后再打给浏览器
		byte[] out = bres.getBuffer();
		System.out.println("原始大小："+out.length);
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(bout);
		gout.write(out);
		gout.close();
		
		byte[] gzip = bout.toByteArray();
		System.out.println("压缩后大小："+gzip.length);
		
		res.setHeader("content-encoding", "gzip");
		res.setContentLength(gzip.length);
		res.getOutputStream().write(gzip);
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
