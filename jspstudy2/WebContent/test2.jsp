<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<%request.setCharacterEncoding("euc-kr"); %>
<body>
이름:${param.name}<br>
나이:${param.age}<br>
성별:${param.gender==1?"남자":"여자"}<br>
출생연도:${param.year}<br>
나이:만 ${2019-param.year}
</body>
</html>