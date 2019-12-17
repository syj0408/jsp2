<%@ page language="java" contentType="text/html; charset=EUC-KR"
pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	alert("${msg}");
<c:choose>	
	<c:when test="${opener}">
		opener.location.href="${url}";
	</c:when>
	<c:otherwise>
		location.href="${url}";
	</c:otherwise>
</c:choose>
	<c:if test="${closer}">
		self.close();
	</c:if>
</script>

