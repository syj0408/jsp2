<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>jstl core 태그 : 반복문</title>
</head>
<body>
<h3>반목 관련 태그 :forEach</h3>
<c:forEach var="test" begin="1" end="10">
	${test}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</c:forEach>

<h3>반목 관련 태그 :forEach</h3>
<c:forEach var="test" begin="1" end="10" step="2"> <!-- step="2" -> 2씩 건너뜀 -->
	${test}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</c:forEach>

<h3>forEach 태그를 이용하여 1부터 100까지의 합 구하기</h3>
<c:forEach var="i" begin="1" end="100">
	<c:set var="sum" value="${sum+i}" />
</c:forEach>
1부터 100까지의 합:${sum}<br>

<h3>forEach 태그를 이용하여 1부터 100까지의 짝수합 구하기</h3>
<c:forEach var="i" begin="0" end="100" step="2">
	<c:set var="even" value="${even+i}" />
</c:forEach>
1부터 100까지 짝수의 합:${even}<br>
<h3>forEach 태그를 이용하여 1부터 100까지의 짝수합 구하기</h3>
<c:set var="sum" value="${0}"/>
<c:forEach var="i" begin="0" end="100" >
	<c:if test="${i%2==0}">
	<c:set var="sum" value="${sum+i}" />
	</c:if>
</c:forEach>
1부터 100까지 짝수의 합:${sum}<br>

<h3>forEach 태그를 이용하여 1부터 100까지의 홀수합 구하기</h3>
<c:forEach var="i" begin="1" end="100" step="2">
	<c:set var="odd" value="${odd+i}" />
</c:forEach>
1부터 100까지 홀수의 합:${odd}<br>
<h3>forEach 태그를 이용하여 1부터 100까지의 홀수합 구하기</h3>
<c:set var="sum" value="${0}"/>
<c:forEach var="i" begin="1" end="100" >
	<c:if test="${i%2==1}">
	<c:set var="sum" value="${sum+i}" />
	</c:if>
</c:forEach>
1부터 100까지 홀수의 합:${sum}<br>

<h3>forEach 태그를 2~9단까지 구구단 출력하기</h3>
<c:forEach var="i" begin="2" end="9">
	<h4>${i}단</h4>
	<c:forEach var="j" begin="2" end="9">
		${i} * ${j} = ${i*j }<br>
		</c:forEach>
	<br>
</c:forEach>

<h3>forEach 태그를 이용하여 List 객체 출력하기</h3>
<%
	List<Integer> list = new ArrayList<Integer>();
	for(int i=1;i<=10;i++){
		list.add(i*10);
	}
	pageContext.setAttribute("list", list);
%>
<c:forEach var="i" items="${list}"> <!-- i => list가 가지고있는 element하나 씩.. "list"=list -->
	${i}&nbsp;&nbsp;&nbsp;
</c:forEach>
<c:forEach var="i" items="${list}" varStatus="stat"> <!-- varStatus="stat" = 첨자 -->
	<c:if test="${stat.index == 0 }" >
	 <h4>forEach태그에 varStatus 속성 연습</h4>
	 </c:if>
	 ${stat.count}:${i}<br> <!-- stat.index=1번값 10부터 stat.count=1부터   -->
</c:forEach>
<h3>forEach 태그를 이용하여 Map 객체 출력하기</h3>
<%
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("name","홍길동");
	map.put("today",new Date());
	map.put("age", 20);
	pageContext.setAttribute("maps", map); //이름이 map인 이름을 가진 객체를 생성 
%>
<c:forEach var="m" items="${maps}"  varStatus="stat"> <!-- m변수는 키와 객체가 쌍인  map.put("name","홍길동"); 이것부터 하나씩 가져옴-->
	${stat.count}:${m.key}=${m.value}<br> <!-- hash라 순서대로 출력되지 않음. -->
</c:forEach>
<h3>EL을 이용하여 Map 객체 출력하기</h3>
name=${maps.name}<br><!-- {속성명.변수이름} -->
today=${maps.today}<br>
age=${maps.age}<br>
<h3>forEach 태그를 이용하여 배열 객체 출력</h3>
<c:set var="arr" value="<%=new int[]{10,20,30,40,50} %>" />
<c:forEach var="a" items="${arr}" varStatus="stat">
	arr[${stat.index}]=${a}<br> 
</c:forEach>
<!-- EL -->
${arr[0]}<br>
${arr[2]}<br>
${list[3]}<br> <!-- list도 배열처럼 표시할 수 있다. -->
<h4>배열 객체의 두번째 요소부터 세번째 요소까지 출력하기</h4>
<c:forEach var="a" items="${arr}" varStatus="stat" begin="1" end="2" >
	arr[${stat.index}]=${a}<br>
</c:forEach>

<h4>배열 객체의 짝수 인덱스만 출력하기</h4>
<c:forEach var="a" items="${arr}" varStatus="stat" step="2">
	arr[${stat.index}]=${a}<br>
</c:forEach>

<h4>배열 객체의 홀수 인덱스만 출력하기</h4>
<c:forEach var="a" items="${arr}" varStatus="stat" step="2" begin="1" > 
	arr[${stat.index}]=${a}<br>
</c:forEach>
</body>
</html>