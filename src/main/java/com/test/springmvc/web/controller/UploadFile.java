package com.test.springmvc.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.test.springmvc.Controller;
import com.test.springmvc.RequestMapping;
import com.test.springmvc.WebContext;

@Controller
public class UploadFile {
	
	@RequestMapping(value="springmvc/upload")
	public void upload()
	{
		ServletContext servletContext = WebContext.getServletContext();
		HttpServletRequest request = WebContext.getRequest();
		//文件保存路径
		String savePath = "E:\\springmvc\\upload";
		File savefile = new File(savePath);
		if(!savefile.exists()&&!savefile.isDirectory())
		{
			boolean issuss = savefile.mkdirs();
			if(issuss)
			{
				System.out.println("目录创建成功");
			}
		}
		//使用apache上传组件
		//创建一个disk工厂
		DiskFileItemFactory diskfactory = new DiskFileItemFactory();
		//创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(diskfactory);
		//解决上传中文乱码的问题
		upload.setHeaderEncoding("UTF-8");
		//判断提交上来的数据是否是上传表单提交过来的
		if(!upload.isMultipartContent(request))
		{
			//按照传统方式获取
			return;
		}
		
		try {
			List<FileItem> listfile = upload.parseRequest(request);
			
			for(FileItem item:listfile)
			{
				if(item.isFormField())
				{
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					System.out.println(name+" = "+value);
				}else
				{ //是文件
					String filename = item.getName();
					System.err.println("上传文件名："+filename);
					if(filename==null  || "".equals(filename.trim())){
						continue;
					}
					//处理获取到的上传路径，只保留文件名
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					//获取item中的上传文件流
					InputStream is = item.getInputStream();
					
					
					//创建一个文件输出流
					FileOutputStream fos = new FileOutputStream(savePath+"\\"+filename);
					byte[] buffer = new byte[1024];
					int len = 0;
					while((len=is.read(buffer))!=-1)
					{
						fos.write(buffer, 0, len);
					}
					fos.close();
					is.close();
					//删除处理文件上传时的临时文件
					item.delete();
					System.out.println("文件上传成功...");
					return ;
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("文件上传失败...");
		
	}
}
