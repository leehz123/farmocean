<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>

<body>
	
	id : <input id="post_user_id" type="text"/><br>
	pw : <input id="post_user_pw" type="password" ><br>
	name : 	<input id="post_user_name" type="text"> <br>
	email : <input id="post_user_email" type="text" ><br>
	
	<button id="join_btn">회원가입</button>
	
	<div id="out"></div>
	<div id="out2"></div>
	<div id="out3"></div>

	<script src="/farmocean/resources/js/join.js"></script>
	


</body>
</html>