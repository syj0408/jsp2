<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title><decorator:title /></title>
<decorator:head />
<link rel="stylesheet" href="${path}/css/main2.css">
<script type="text/javascript" src="http://cdn.ckeditor.com/4.5.7/full/ckeditor.js">

</script>
</head>
<body>
<table>
	<tr><th colspan="3" align="right">
	디지털 컨버전스 과정 2회차 프로그램 연습 
	<div class="login" style="float: right; font-size:15px;">
	<c:if test="${empty sessionScope.login}" >
		<a href="loginForm.me">로그인&nbsp;</a>
		<a href="joinForm.me">회원가입</a>
	</c:if>
	<c:if test="${!empty sessionScope.login}">
	${sessionScope.login}님이 로그인하셨습니다.&nbsp;
		<a href="logout.me">로그아웃</a>
	</c:if>
	</div>
	</th></tr>
	<tr><td width="15%" style="vertical-align:top"> <a href="${path}/model2/member/main.me">회원관리</a><br>
						 <a href="${path}/model2/board/list.do">게시판</a><br>
	</td><td colspan="2" style="text-align: left;">
	<decorator:body />
	</td></tr>
	<tr><td colspan="3">구디아카데미 Since 2016</td></tr>
</table>
</body>
</html>
