<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 답변 글쓰기</title>
<link rel="stylesheet" href="../../css/mainboard.css">
</head>
<body>
<form action="reply.do" method="post" name="f">
	<input type="hidden" name="num" value="${b.num}"> <!-- hidden값들은 원글에 대한것들. -->
	<input type="hidden" name="grp" value="${b.grp}">
	<input type="hidden" name="grplevel" value="${b.grplevel}">
	<input type="hidden" name="grpstep" value="${b.grpstep}">
	<table><caption>게시판 답글 등록</caption>
	<tr><td>글쓴이</td><td><input type="text" name="name"></td></tr>
	<tr><td>비밀번호</td><td><input type="password" name="pass"></td></tr>
	<tr><td>제목</td><td><input type="text" name="subject" value="RE:${b.subject}"></td></tr>
	<tr><td>내용</td><td><textarea name="content" rows="15"></textarea></td></tr>
	<tr><td colspan="2"><a href = "javascript:document.f.submit()">[답변 등록]</a></td></tr> <!-- javascript:document.f.submit() => reply.jsp로 이동 -->
</table>
</form>
</body>
</html>