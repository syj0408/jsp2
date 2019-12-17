<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>core 태그: 조건문 예제</title>
</head>
<body>
<h3>조건문 관련 태그 : if,choose 태그</h3>

<% if(5 < 10){%>
		<h4>5는 10보다 작다</h4>
<%} %>
<c:if test="${5 <10}">
	<h4>5는 10보다 작다</h4> <!-- true일때 출력 -->
</c:if>

<c:if test="${6+3 != 10}">
	<h4>6+3은 10이 아니다.</h4>
</c:if>
<c:if test="${6+3 != 9}">
	<h4>6+3은 9이 아니다.</h4> 
</c:if>

<c:choose>
	<c:when test="${5+10==25}">
		<h4>5+10=25</h4>
	</c:when>
	
	<c:when test="${5+10==15}">
		<h4>5+10=15</h4>
	</c:when>
	
	<c:when test="${5+10==510}">
		<h4>5+10=510</h4>
	</c:when>
	
	<c:otherwise>
		<h4>5+10=모름</h4>
	</c:otherwise>
</c:choose>

</body>
</html>