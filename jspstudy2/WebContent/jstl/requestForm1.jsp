<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>request 객체 예제</title>
</head>
<body>
<h2>request 내장객체: 클라이언트의 요청 정보를 저장</h2>
<form action="request1.jsp" method="post">
<!-- name들이 파라미터값(입력된값)으로 간다.  -->
<input type="hidden" name="test" value="test">
이름: <input type="text" name="name"><br>
나이: <input type="text" name="age"><br>
성별: <input type="radio" name="gender" value="1">남
	 <input type="radio" name="gender" value="2">여<br>
취미: <input type="checkbox" name="hobby" value="요리">요리
	<input type="checkbox" name="hobby" value="여행">여행
	<input type="checkbox" name="hobby" value="야구">야구
	<input type="checkbox" name="hobby" value="축구">축구
	<input type="checkbox" name="hobby" value="독서">독서<br>
출생년도: <select name="year">
	<c:forEach begin="1990" end="2000" var="i">
		<option>${i}</option>
	</c:forEach></select><br>
<input type="submit" value="전송">
</form>
</body>
</html>