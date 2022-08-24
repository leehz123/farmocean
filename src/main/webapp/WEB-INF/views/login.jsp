<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
   form{
       text-align: center;
       margin:0 auto;
   }

   .border{
       margin:0 auto;
       width:800px;
       height:500px;
       border:1px solid #000;
       border-radius: 10%;
       text-align: center;
       margin:0 auto;
   }

	
   input{
       width:300px;
   }
	</style>
</head>

<body>
		<% session.invalidate(); %>
    		
        	<div class="border">
            	<h1>로그인</h1>
            	
            		ID <input type="text" id="login_buyer_id" placeholder="아이디를 입력해주세요."><br><br>
           			PW <input type="password" id="login_buyer_pw" placeholder="비밀번호를 입력해주세요."><br><br>
           			
            		<button id="login_btn">로그인</button><br><br>
            		<button id="join_btn">회원가입</button><br><br>
            		<button id="join_btn_seller">판매자 회원가입</button><br><br>
            		<button id="id_search_btn">아이디찾기</button><br><br>
            		<button id="pw_search_btn">비밀번호찾기</button><br><br>
        	</div> 
        	<br>
        	<div id="loginStatus"></div>
    	

	<script src="/farmocean/resources/js/login.js"></script>
	


</body>
</html>

