<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script>

	myWindow = window.open('https://nid.naver.com/nidlogin.logout', '네이버팝업', 
	'width=1,left=4000px, height=1, top=4000px scrollbars=yes, resizable=no');
	
	setTimeout("myWindow.close()", 500);
	setTimeout("window.location.replace('/farmocean/member/login')", 500);
	
	

</script>
</body>
</html>