<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
	1.로그인 후에 보여지는 페이지
	  => 로그인이 안된 경우 => loginFrom.jsp 페이지 이동하기.
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원관리</title>
</head>
<body>
	<h3>${sessionScope.login}로 로그인 되었습니다.</h3>
	<h3> <a href="logout.me">로그아웃</a> </h3>
	<h3> <a href="info.me?id=${sessionScope.login}">회원정보보기</a> </h3>
	<%-- 관리자 로그인 --%>
	<c:if test="${sessionScope.login == 'admin' }">
	<h3> <a href="list.me">회원 목록보기</a> </h3>
	</c:if>
</body>
</html>