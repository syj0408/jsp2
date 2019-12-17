<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>합계 구하기</title>
</head>
<body>
<form method="post">
	x:<input type="text" name="x" value="${param.x}" ><br>
	y:<input type="text" name="y" value="${param.y}" ><br>
	<input type="submit" value="더하기">
</form>
합계 : <c:out value="${param.x + param.y}" />
<h3>if 태그를 이용하여 출력하기.</h3>

<c:if test="${param.x + param.y > 0}">
	<h4>양수입니다.</h4>
</c:if>

<c:if test="${param.x + param.y < 0}">
	<h4>음수입니다.</h4>
</c:if>

<c:if test="${param.x + param.y == 0}">
	<h4>0입니다.</h4>
</c:if>
<br>
<h3>choose 태그를 이용하여 출력하기.</h3>
<c:choose>
	<c:when test="${param.x + param.y > 0}">
		<h4>양수입니다.</h4>
	</c:when>
	
	<c:when test="${param.x + param.y < 0}">
		<h4>음수입니다.</h4>
	</c:when>

	<c:otherwise>
		<h4>0입니다.</h4>
	</c:otherwise>
</c:choose>

</body>
</html>