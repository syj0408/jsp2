<%@page import="model.Board"%>
<%@page import="model.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 수정 화면</title>
<link rel="stylesheet" href="../../css/mainboard.css">
<script>
    function file_delete(){
       document.f.file2.value="";
       file_desc.style.display="none"; //파일자체가 사라지진 않는다. 안보여지는것 뿐.
       }
</script>
</head>
<body>
<form action="update.do" method="post" enctype="multipart/form-data" name="f"> <!--  multipart/form-data => useBean사용불가. -->
   <input type="hidden" name="num" value = "${b.num}">
   <input type="hidden" name="file2" value = "${b.file1}"> <!-- 기존에 있는 내용을 없앤다. -->
   <table><caption>게시판 수정 화면</caption>
   <tr><td>글쓴이</td><td><input type="text" name="name" value="${b.name}"></td></tr>
   <tr><td>비밀번호</td><td><input type="password" name="pass" ></td></tr>
   <tr><td>제목</td><td><input type="text" name="subject" value="${b.subject}"></td></tr>
   <tr><td>내용</td><td><textarea rows="15" name="content" id="content1">${b.content}</textarea></td></tr>
   <script>CKEDITOR.replace("content1",{ 	filebrowserImageUploadUrl : "imgupload.do"});</script>
		<%-- filebrowserImageUploadUrl : 이미지 업로드 설정 
		imgupload.do : 업로드를 위한 url 지?..--%>	
   <tr><td>첨부파일</td><td style="text-align:left">
   <c:if test="${!empty b.file1}">
      <div id="file_desc">${b.file1}
      <a href="javascript:file_delete()">[첨부파일 삭제]</a></div>
  </c:if>
   <input type="file" name="file1"></td></tr>
   <tr><td colspan="2"><a href="javascript:document.f.submit()">[게시물 수정]</a></td></tr>
</table>
</form>
</body>
</html>