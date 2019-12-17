<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>jstl을 이용한스 간단한  연산</title>
</head>
<body>
<form method="post" name="f">
	x:<input type="text" name="x" value="${param.x}" size="5">
<select name="op">
	<option value="1">+</option>
	<option value="2">-</option>
	<option value="3">*</option>
	<option value="4">/</option>
</select>
<script>
	var op = '${param.op}'
	if(op==' ')op="+";
	document.f.op.value= op
</script>
	y:<input type="text" name="y" value="${param.y}" size="5">
	<input type="submit" value="=">
</form>

<c:if test="${param.op==1}">
	<h3>${param.x}+${param.y}=${param.x+param.y}</h3>
</c:if>
<c:if test="${param.op==2}">
	<h3>${param.x}-${param.y}=${param.x-param.y}</h3>
</c:if>
<c:if test="${param.op==3}">
	<h3>${param.x}*${param.y}=${param.x*param.y}</h3>
</c:if>
<c:if test="${param.op==4}">
	<h3>${param.x}/${param.y}=${param.x/param.y}</h3>
</c:if>

</body>
</html>