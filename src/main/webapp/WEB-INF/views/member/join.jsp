<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ȸ������</title>
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
            	<h1>ȸ������</h1>
            		<table>
            		<tr><td>���̵�</td><td><input type="text" id="post_member_id" placeholder="���̵� �Է����ּ���."></td>
            		<button id="idCheckBtn">�ߺ�Ȯ��</button></tr>            		
           			<tr><td>��й�ȣ <input type="password" id="post_member_pw" placeholder="��й�ȣ�� �Է����ּ���."></tr>
           			<tr><td>��й�ȣȮ�� <input type="password" id="post_member_pw_check" placeholder="��й�ȣ�� �Է����ּ���."></tr>           			
           			<tr><td>�̸� <input type="text" id="post_member_name"  placeholder="�̸� �Է����ּ���."></tr>
           			<tr><td>�г��� <input type="text" id="post_member_nickName"  placeholder="�г��� �Է����ּ���."></tr>
					<tr><td>�̸��� <input type="text" id="post_member_email" placeholder="�̸����� �Է����ּ���." ></tr>
					<tr><td>��ȭ��ȣ <input type="text" id="post_member_phoneNum" placeholder="��ȭ��ȣ�� �Է����ּ���." ></tr>
					<tr><td>���¹�ȣ <input type="text" id="post_member_accountNum" placeholder="���¹�ȣ�� �Է����ּ���." ></tr>
					<tr><td>�ּ� <input type="text" id="post_member_address" placeholder="�ּҸ� �Է����ּ���." ></tr>
					</table>

            		<button id="join_btn">ȸ������</button>

        	</div> 
    	

	<script src="/farmocean/resources/js/member/join.js?ver=123"></script>
	


</body>
</html>

