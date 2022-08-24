<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 회원가입</title>
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
	
    	
        	<div class="border">
            	<h1>판매자 회원가입</h1>
            	
            		아이디  <input type="text" id="post_seller_id" placeholder="아이디를 입력해주세요."><br><br>
           			비밀번호 <input type="password" id="post_seller_pw" placeholder="비밀번호를 입력해주세요."><br><br>
           			비밀번호확인 <input type="password" id="post_seller_pw_check" placeholder="비밀번호를 입력해주세요."><br><br>
           			이름 <input type="text" id="post_seller_name"  placeholder="이름 입력해주세요."><br><br>
					이메일 <input type="text" id="post_seller_email" placeholder="이메일을 입력해주세요." ><br><br>
					휴대폰번호 <input type="text" id="post_seller_phoneNum" placeholder="이메일을 입력해주세요." ><br><br>
					계좌번호 <input type="text" id="post_seller_accountNum" placeholder="이메일을 입력해주세요." ><br><br>

            		<button id="join_btn">판매자 회원가입</button>

        	</div> 
    	

	<script src="/farmocean/resources/js/sellerjoin.js"></script>
	


</body>
</html>

