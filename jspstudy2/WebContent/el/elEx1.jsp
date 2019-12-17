<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>EL(표현언어) 예제</title>
</head>
<body>
<%
	session.setAttribute("test","session test 속성");
	session.setAttribute("today", new Date());
%>
<form action="elEx2.jsp" method="post">
	이름 : <input type="text" name="name" value="홍길동">
	<input type="submit" value="전송">
</form>
</body>
</html>