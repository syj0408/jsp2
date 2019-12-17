<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>JSTL core 태그 예제 : taglib 지시어 표시해야 한다.</title>
</head>
<body>
<h3>속성 설정 태그 : set,remove,out 태그</h3>
<%session.setAttribute("test", "Hello JSTL"); %> <!-- el 버젼 -->
<c:set var="test" value="${'Hello JSTL'}" scope="session"/> <!-- JSTL 버젼 -->
<!--  이름이 test인 곳에 'hello jstl'를 입력 / session.setA~ 윗줄과 같음. scope가 생략되면 pageContext.setA~ 로 기본지정.-->
test 속성 : ${sessionScope.test} <br>
test 속성 : <c:out value="${test}" /><br>
<c:remove var="test"/>
test 속성: ${test}<br>
</body>
</html>