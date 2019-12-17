<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- /WebContent/model1/board/info.jsp     --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시물 상세 보기</title>
<link rel="stylesheet" href="../../css/mainboard.css">
</head>
<body>
	<table>
		<caption>게시물 상세 보기</caption>
		<tr>
			<th width="20%">글쓴이</th>
			<td width="80%" style="text-align: left">${b.name}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td style="text-align: left">${b.subject}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><table style="width: 100%; height: 250px;">
					<tr>
						<td
							style="border-width: 0px; vertical-align: top; text-align: left">
							<%-- //<c:out value="${b.content}"> 내용확인가능 --%>
							${b.content}
						</td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>&nbsp; <c:if test="${!empty b.file1}">
  							<a href="file/${b.file1}">${b.file1}</a>
						</c:if>
		</td></tr>
		<tr>
			<td colspan="2"><a href="replyForm.do?num=${b.num}">[답변]</a> 
			<a href="updateForm.do?num=${b.num}">[수정]</a> 
			<a href="deleteForm.do?num=${b.num}">[삭제]</a> 
			<a href="list.do">[목록]</a>
			</td>
		</tr>
	</table>
</body>
</html>