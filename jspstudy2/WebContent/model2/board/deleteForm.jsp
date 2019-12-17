<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 삭제화면</title>
<link rel="stylesheet" href="../../css/mainboard.css">
</head>
<body>
<form action="delete.do" name="f" method = "post">
<input type="hidden" name="num" value="${param.num}">
<table><caption>게시글 삭제 화면</caption>
	<tr><th>게시글비밀번호</th><td><input type="password" name="pass"></td></tr>
	<tr><td colspan="2"><input type="submit" value="게시글 삭제"></td></tr>
</table>
</form>
</body>
</html>