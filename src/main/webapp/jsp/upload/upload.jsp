<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/springmvc/upload.action" method="post" enctype="multipart/form-data">
		<input type="file" name="file1">
		<input type="submit" value="提交">
	</form>
</body>
</html>