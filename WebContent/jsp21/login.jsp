<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% if(session.getAttribute("ValidMem") != null){ %>
	<jsp:forward page="main.jsp"></jsp:forward>
<% } %> <!-- 이미 로그인 되어있다면 다른 페이지로 넘김 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="loginOk.jsp" method="post">
		아이디 : <input type="text" name="id" 
		        value="<% if(session.getAttribute("id") != null) out.println(session.getAttribute("id")); %>">
		       <!-- 세션 중 id 값이 비어있지 않으면, 값을 가지고 와서 출력 -->
		       <br/>
		비밀번호 : <input type="password" name="pw"> <br/>
		<input type="submit" value="로그인"> &nbsp;&nbsp; 
		<input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'">
		<!--  회원 가입의 버튼을 눌렀을 때 join.jsp로 넘김 -->
	</form>


</body>
</html>