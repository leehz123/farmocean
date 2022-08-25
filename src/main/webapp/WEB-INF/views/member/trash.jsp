<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>�α���</title>
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
            	<h1>�α���</h1>
            	
            		ID <input type="text" id="login_buyer_id" placeholder="���̵� �Է����ּ���."><br><br>
           			PW <input type="password" id="login_buyer_pw" placeholder="��й�ȣ�� �Է����ּ���."><br><br>
           			
            		<button id="login_btn">�α���</button><br><br>
            		<button id="join_btn">ȸ������</button><br><br>
            		<button id="join_btn_seller">�Ǹ��� ȸ������</button><br><br>
            		<button id="id_search_btn">���̵�ã��</button><br><br>
            		<button id="pw_search_btn">��й�ȣã��</button><br><br>
        	</div> 
        	<br>
        	<div id="loginStatus"></div>
    	

	<script src="/farmocean/resources/js/login.js"></script>
	


</body>
</html>

