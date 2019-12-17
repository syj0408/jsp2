<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>비밀번호찾기</title>
<link rel="stylesheet" href="css/main3.css">
<script type="text/javascript">
   function pwclose() {
	   self.close();
   }
</script>
</head>
<body>
<table>
  <tr><th>비밀번호</th>
    <td>**${pass}</td>
  </tr>
  <tr><td colspan="2">
     <input type="button" value="닫기" onclick="pwclose()">
  </td>
  </tr>
</table>
</body>
</html>