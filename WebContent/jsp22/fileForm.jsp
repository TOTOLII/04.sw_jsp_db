<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>  
<body>
	<form action="fileFormOk.jsp" method="post" enctype="multipart/form-data">
	<!-- enctype을 명시해줘야 파일이 정상적으로 첨부 된다. -->
		파일 : <input type="file" name="file"> <br/>
		<input type="submit" value="File Upload">
	</form>



</body>
</html>