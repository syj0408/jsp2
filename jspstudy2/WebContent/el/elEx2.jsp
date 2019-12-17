<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
  <%--
  	EL(표현언어) : 표현식을 대체 언어.
  				${값}
  				변수값은 표현 못함. => 반드시 속성이나 파라미터만 표현 가능
   --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>elEx1.jsp의 결과 화면</title>
</head>
<body>
<%
	request.setCharacterEncoding("euc-kr");
	String tel = "010-1111-2222";
	pageContext.setAttribute("tel", tel); //pageContext - page영역 담당 ,해당되는 page하나
	pageContext.setAttribute("test" , "pageContext 객체의 test 속성 값");
	request.setAttribute("test", "request 객체의 test 속성 값");
	application.setAttribute("test", "application 객체의 test 속성 값");

%>
<h3>JSP의 스크립트를 이용하여 파라미터와 속성값 출력</h3>
pageContext test 속성값: <%=pageContext.getAttribute("test") %><br>
session test 속성값 : <%=session.getAttribute("test") %><br>
today 속성값 : <%=session.getAttribute("today") %><br>
name 파라미터값 : <%=request.getParameter("name") %><br>
tel 변수값: <%=tel %><br>
tel 속성값: <%=pageContext.getAttribute("tel") %><br>
noAttr 속성값: <%=pageContext.getAttribute("noAttr")%><br>
noparam 파라미터 값: <%=request.getParameter("noparam") %><br> <!-- noparam = null 주의 !! -->


<h3>JSP의 EL(표현)을 이용하여 파라미터와 속성값 출력</h3>
pageContext test 속성값: ${test}<br>
session test 속성값 : ${sessionScope.test} <br> <!-- test가 두개니까 session에있는걸 한번에 가져오려고. -->
today 속성값 : ${today}<br> <!-- today session에 있음  page<request<session<application(접근순서) -->
name 파라미터값 :${param.name }<br>
tel 변수값: EL로 표현못함 <%=tel %><br> <!-- 반드시 속성에 등록되어있는것만ㄱ ㅏ져온다. -->
tel 속성값: ${tel}<br>
noAttr 속성값: ${noAttr} <br>
noparam 속성값 : ${param.noparam} <br> <!-- noparam = 빈문자열  주의 !! -->
<br>
<h3>영역을 설정하여 test 속성값 출력</h3>
\${test}=${test}<br>
\${pageScope.test}=${pageScope.test}<br>
\${requestScope.test}=${requestScope.test}<br>
\${sessionScope.test}=${sessionScope.test}<br>
\${applicationScope.test}=${applicationScope.test}<br>
\${requestScope.today}=${requestScope.today}<br>
</body>
</html>

<%--
	${test} : 영역담당 객체에 저장된 속성 중 이름이 test인 값을 리턴
		page 영역 : pageContext
		request 영역  : request
		session 영역  : session
		application 영역 : application
	1. pageContext 객체에 등록된 속성 중 이름이 test인 속성의 값을 리턴
	2. 1번에 해당하는 속성이 없는 경우
		request 객체에 등록된 속성중 이름이 test인 속성의 값을 리턴
	3. 2번에 해당하는 속성이 없는 경우
		session 객체에 등록된 속성중 이름이 test인 속성의 값을 리턴
	4. 3번에 해당하는 속성이 없는 경우
		application 객체에 등록된 속성 중 이름이 test인 속성의 값을 리턴
	5. 4번에 해당하는 속성이 없는경우
		null이 아니고 아무것도 출력하지 않음. 오류발생도 없음.
	- 직접 영역 담당 객체를 지정하여 출력하기
		pageContext 객체의 속성: ${pageScope.test}
		request 객체의 속성 : ${requestScope.test}
		session 객체의 속성 : ${sessionScope.test}
		application 객체의 속성 : ${applicationScope.test}
 --%>