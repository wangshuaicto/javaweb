package com.test.response;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageInputStreamImpl;
import javax.imageio.stream.ImageOutputStreamImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo12
 */
public class ServletDemo12 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo12() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("refresh","5"); //设置浏览器每隔5秒刷新
		//内存中创建图片
		BufferedImage bufferimage = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
		//得到画笔
		Graphics2D graphics = (Graphics2D) bufferimage.getGraphics();
		graphics.setBackground(Color.CYAN);
		graphics.fillRect(0, 0, 80, 20);
		graphics.setColor(Color.BLUE);
		graphics.drawString(makenum(), 0, 20);
		//设置浏览器的打开方式
		response.setHeader("content-type", "image/jpeg");
		
		//设置浏览器不进行缓存
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		//将图片写入到浏览器
		ImageIO.write(bufferimage, "jpg", response.getOutputStream());
	}
	
	/**生成随机图片**/
	private String makenum()
	{
		Random random = new Random();
		String num = random.nextInt(999999)+"";
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<6-num.length();i++)
		{
			sb.append("0");
		}
		num = sb.toString()+num;
		return num;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
