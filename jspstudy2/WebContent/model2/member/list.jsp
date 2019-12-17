<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    <%--
    	1.로그아웃 상태: 로그인이 필요합니다. 메세지 출력
    				 loginForm.jsp로 페이지이동
    	2.로그인 상태
    		일반 사용자: 관리자만 가능한 거래입니다. 메세지 출력
    				 main.jsp로 페이지 이동
    		관리자
    	3.db에서 모든 회원 정보를 조회하여 List<Member> 객체로 리턴
    		List<Member> list = new MemberDao().list();
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 목록보기</title>
<link rel="stylesheet" href="../../css/main3.css">
</head>
<body>
<table><caption>회원 목록</caption>
<tr><th>아이디</th><th>이름</th><th>성별</th><th>전화</th><th>&nbsp;</th></tr>
<c:forEach var="m" items="${list}"><!-- 리스트 하나하나가 객체. -->
       <tr><td><a href="info.me?id=${m.id}">${m.id}</a></td> <!-- href="info.jsp?id=m.getId() info.jsp로 가서 id구분-->
       <td>${m.name}</td>
       <td>${m.gender==1?"남":"여" }</td>
       <td>${m.tel}</td>
       <td><a href="updateForm.me?id=${m.id}">[수정]</a>
<c:if test="${m.id != 'admin'}">
	<a href="delete.me?id=${m.id}">[강제탈퇴]</a>
  </c:if> </td></tr>
</c:forEach>
</table>
</body>
</html>
