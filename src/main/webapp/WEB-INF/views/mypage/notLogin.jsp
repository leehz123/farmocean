<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인시 이용가능합니다.</title>
</head>
<body>
		<script>
		var goLogin = function() {
			alert("로그인 시 이용 가능한 페이지입니다");
			location.replace('/farmocean/member/login');
		};
		
		goLogin();
		</script>
</body>
</html>