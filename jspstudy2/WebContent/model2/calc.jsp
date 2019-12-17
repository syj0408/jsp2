<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>모델2 연습</title>
</head>
<body>
<form action="calc.me" method="post">
	<input type="text" name="num1" value="${param.num1}">
	<select name="op">
	<option>+</option>
	<option>-</option>
	<option>*</option>
	<option>/</option>
	</select>
	<script type="text/javascript">
		var op ='${param.op}'
		if(op =='')op="+";
		document.forms[0].op.value=op;
	</script>
	<input type="text" name="num2" value="${param.num2}">
	<input type="submit" value="=">
	<input type="text" name="result" value="${result}">
</form>
</body>
</html>