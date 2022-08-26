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
            	<h1>회원가입</h1>
            		<table>
            		<tr><td>아이디</td><td><input type="text" id="post_member_id" placeholder="아이디를 입력해주세요."></td>
            		<button id="idCheckBtn">중복확인</button></tr>            		
           			<tr><td>비밀번호 <input type="password" id="post_member_pw" placeholder="비밀번호를 입력해주세요."></tr>
           			<tr><td>비밀번호확인 <input type="password" id="post_member_pw_check" placeholder="비밀번호를 입력해주세요."></tr>           			
           			<tr><td>이름 <input type="text" id="post_member_name"  placeholder="이름 입력해주세요."></tr>
           			<tr><td>닉네임 <input type="text" id="post_member_nickName"  placeholder="닉네임 입력해주세요."></tr>
					<tr><td>이메일 <input type="text" id="post_member_email" placeholder="이메일을 입력해주세요." ></tr>
					<tr><td>전화번호 <input type="text" id="post_member_phoneNum" placeholder="전화번호를 입력해주세요." ></tr>
					<tr><td>계좌번호 <input type="text" id="post_member_accountNum" placeholder="계좌번호를 입력해주세요." ></tr>
					<tr><td>주소 <input type="text" id="post_member_address" placeholder="주소를 입력해주세요." ></tr>
					</table>

            		<button id="join_btn">회원가입</button>

        	</div> 
    	

	<script src="/farmocean/resources/js/member/join.js?ver=123"></script>
	


</body>
</html>

