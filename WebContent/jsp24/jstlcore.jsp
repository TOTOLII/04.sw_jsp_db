<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<c:set var="vatName" value="varValue"/>
	vatName : <c:out value="${vatName}"/>
	<br/>
	
	<c:remove var="vatName"/> <!-- 변수명 삭제 -->
	vatName : <c:out value="${vatName}"/>
	
	<hr/>
	
	<c:catch var="error"> <!-- 변수명 error -->
		<%=2/0%>
	</c:catch>
	<br/>
	
	<c:out value="${error}"/> <!-- 에러내용 출력 -->
	<hr/>

	<c:if test="${1+2==3}"> <!-- 1+2가 3과 같으면 -->
		1 + 2 = 3
	</c:if>
	
	<c:if test="${1+2!=3}"> <!-- 1+2가 3과 같지 않으면 -->
		1 + 2 != 3
	</c:if>
	<br/>
	
	<hr/>

	<c:forEach var="fEach" begin="0" end="30" step="3"> <!-- 변수명은 fEach 0 ~ 30까지 3씩 증가 -->
		${fEach}
	</c:forEach>


</body>
</html>