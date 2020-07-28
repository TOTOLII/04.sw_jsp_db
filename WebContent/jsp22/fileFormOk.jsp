<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 
	String path = request.getRealPath("filefolder"); //실제 경로
	
	int size= 1024 * 1024 * 10; //파일 크기 지정 여기서는 100M
	
	String file = ""; //이름이 중복될 때 뒤에 1,2,3,4 붙는것
	String oriFile = ""; //오리지날 파일
	
	try{
		MultipartRequest multi = new MultipartRequest(request, path, size, "EUC-KR", new DefaultFileRenamePolicy());
		//new DefaultFileRenamePolicy()는 파일이 중복될 때 뒤에 번호 붙이는 것임.
		Enumeration files = multi.getFileNames();
		String str = (String)files.nextElement();
		
		file = multi.getFilesystemName(str); // 중복되어 있을때 파일의 이름
		oriFile = multi.getOriginalFileName(str); //실제 파일 이름
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	파일 업로드 성공!
</body>
</html>