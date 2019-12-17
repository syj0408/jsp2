<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- /WebContent/model1/board/list.jsp--%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시물 목록 보기</title>
<link rel="stylesheet" href="../../css/mainboard.css">
<script type="text/javascript">
	function listdo(page){
		document.sf.pageNum.value=page;
		document.sf.submit();
	}
</script>
</head>
<body>
<form action ="list.do" method="post" name="sf">
<input type="hidden" name="pageNum" value="1">
<table><tr><td style="border-width:0px;">
<select name="column">
	<option value="">선택하세요</option>
	<option value="subject">제목</option>
	<option value="name">작성자</option>
	<option value="content">내용</option>
	<option value="subject,name">제목,작성자</option>
	<option value="subject,content">제목,내용</option>
	<option value="name,content">작성자,내용</option></select>
<script>document.sf.column.value="${param.column}";</script>
<input type="text" name="find" value="${param.find}" style="width:50%">
<input type="submit" value="검색"></td>
</tr>
</table>
</form>
<table>
	<caption>게시판 목록</caption>
      <c:if test="${boardcnt == 0}">
      <tr>
         <td colspan="5">등록된 게시글이 없습니다.</td>
      </tr>
      </c:if>
      <c:if test="${boardcnt > 0}">
      <tr>
         <td colspan="5" style="text-align: right;">글개수:${boardcnt}</td>
      </tr>
      <tr>
         <th width="8%">번호</th>
         <th width="50%">제목</th>
         <th width="14%">작성자</th>
         <th width="17%">등록일</th>
         <th width="11%">조회수</th>
      </tr>
   	 <c:forEach var="b" items="${list}">
      <tr>  <td>${boardnum}</td> <!-- boardnum : 보여주기 식 번호 -->
         <c:set var="boardnum" value="${boardnum -1 }" />
         <td style="text-align: left">
         
      <%-- 첨부파일표시 --%> 
      <c:if test="${!empty b.file1}">
            <a href="file/${b.file1}" style="text-decoration: none;">@</a>
      </c:if>
      <c:if test="${empty b.file1}">&nbsp;&nbsp;&nbsp;</c:if>
      <%-- 답글 표시하기 --%>
      <c:if test="${b.getGrplevel() > 0}">
         	<c:forEach begin="1" end="${b.grplevel}">
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </c:forEach> └ 
      </c:if>
  
         <a href="info.do?num=${b.num}">${b.subject}</a>
         </td><td>${b.name}</td>
         <td>
         <c:set var="today" value="<%=new java.util.Date() %>" />
         <fmt:formatDate value="${today}" pattern="yyyyMMdd" var="today"/>
          <fmt:formatDate value="${b.regdate}" pattern="yyyyMMdd" var="wrday"/>
          <c:choose>
         	<c:when test="${today-wrday < 1}">
         		<fmt:formatDate value="${b.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
         	</c:when>
         	<c:otherwise>
         		<fmt:formatDate value="${b.regdate}" pattern="yy-MM-dd HH:mm"/>
         	</c:otherwise>
  		  </c:choose>
          </td>
         <td>${b.readcnt}</td></tr>
     </c:forEach>
      <tr><td colspan="5">
      <c:if test="${pageNum <= 1}">[이전]</c:if>
      <c:if test="${pageNum > 1 }">
      <a href="javascript:listdo(${pageNum - 1})">[이전]</a> 
	  </c:if>
    <c:forEach var = "a" begin="${startpage}" end="${endpage}" >
    	<c:if test="${a==pageNum}">[${a}]</c:if>
    	<c:if test="${a!=pageNum}">
    	<a href="javascript:listdo(${a})">[${a}]</a> 
    	</c:if>
   	</c:forEach>
<c:if test="${pageNum >= maxpage}">[다음]</c:if>
<c:if test="${pageNum < maxpage}">
	<a href="javascript:listdo(${pageNum + 1})">[다음]</a> 
</c:if>
</td></tr>
</c:if>
      <tr>
         <td colspan="5" style="text-align: right"><a href="writeForm.do">[글쓰기]</a></td>
      </tr>
</table>
</body>
</html>