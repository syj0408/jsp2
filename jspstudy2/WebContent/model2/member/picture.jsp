<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%--
	1.openr 화면에 결과 전달. => javascript
	2.현재 화면 close() => javascript
 --%>
<script type="text/javascript">
 	img = opener.document.getElementById("pic");
 	img.src = "picture/${fname}";
 	opener.document.f.picture.value="${fname}";
 	self.close();
</script>
