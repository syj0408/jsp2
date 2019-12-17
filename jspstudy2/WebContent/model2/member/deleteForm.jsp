<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/member/deleteForm.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원탈퇴 비밀번호 입력 화면</title>
<link rel="stylesheet" href="../../css/main3.css">
</head>
<body>
<form action="delete.me" method="post">
<input type="hidden" name="id" value='${param.id}'>
<table>
   <caption>회원 비밀번호 입력</caption>
   <tr><th>비밀번호</th>
       <td><input type="password" name="pass"></td></tr>
   <tr><td colspan="2">
     <input type="submit" value="탈퇴하기"></td></tr>       
</table></form></body></html>