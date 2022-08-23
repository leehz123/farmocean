<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
   form{
       text-align: center;
       margin:0 auto;
   }

   .border{
       margin:0 auto;
       width:400px;
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
            	<h1>회원가입</h1>
            	
            		아이디  <input type="text" id="post_buyer_id" placeholder="아이디를 입력해주세요."><br>
           			비밀번호 <input type="password" id="post_buyer_pw" placeholder="비밀번호를 입력해주세요."><br><br>
           			이름 <input type="text" id="post_buyer_name"  placeholder="이름 입력해주세요."> <br>
					이메일 <input type="text" id="post_buyer_email" placeholder="이메일을 입력해주세요." ><br>
					이미지 <input type="text" id="post_buyer_image" placeholder="파일을 업로드해주세요"  ><br>
            		<button id="join_btn">회원가입</button>

        	</div> 
    	

	<script src="/farmocean/resources/js/join.js"></script>
	


</body>
</html>

