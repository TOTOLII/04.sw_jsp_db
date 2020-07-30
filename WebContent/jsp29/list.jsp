<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>히트</td>
		</tr>
		<c:forEach items="${list}" var="dto"> <!-- dto객체를 받아온 list를 사용하여 실행 -->
		<tr>
			<td>${dto.bId}</td>
			<td>${dto.bName}</td>
			<td>
				<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach> <!-- 답글 작성시 들여쓰기를 구현하기 위한 구문 --> <!-- bIndent만큼 들여씀 -->
				<a href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a></td> <!-- 제목에 하이퍼 링크를 달아 실제로 제목을 클릭하면 내용으로 넘어가는 구문 -->
				<!-- content_view에 bId를 같이 넣어 넘김 content_view에서는 넘어온 아이디로 데이터 베이스를 조회 후 해당되는 데이터를 가지고 온다. -->
			<td>${dto.bDate}</td>
			<td>${dto.bHit}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> <a href="write_view.do"> 글작성 </a> </td>
		</tr>
	</table>
</body>
</html>