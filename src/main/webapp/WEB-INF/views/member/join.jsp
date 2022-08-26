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
            		<table border="1">
            		<tr><td>���̵�</td><td><input type="text" id="post_member_id" placeholder="���̵� �Է����ּ���.">
            		<button id="idCheckBtn">�ߺ�Ȯ��</button></td></tr>
            		            		
           			<tr><td>��й�ȣ</td><td><input type="password" id="post_member_pw" placeholder="��й�ȣ (����, ���� ������ 6~12�ڸ� �̳�)"> <button id="pwCheck"></button></td></tr>
           			<tr><td>��й�ȣȮ��</td><td><input type="password" id="post_member_pw_check" placeholder="��й�ȣ�� �ѹ� �� �Է����ּ���."></td></tr>           			
           			<tr><td>�̸�</td><td><input type="text" id="post_member_name"  placeholder="�̸� �Է����ּ���."></td></tr>
           			<tr><td>�г���</td><td><input type="text" id="post_member_nickName"  placeholder="�г��� �Է����ּ���."></td></tr>
					<tr><td>�̸���</td><td><input type="text" id="post_member_email" placeholder="�̸����� �Է����ּ���." ></td></tr>
					<tr><td>��ȭ��ȣ</td><td><input type="text" id="post_member_phoneNum" placeholder="��ȭ��ȣ�� �Է����ּ���." ></td></tr>
					<tr><td>���¹�ȣ</td><td><input type="text" id="post_member_accountNum" placeholder="���¹�ȣ�� �Է����ּ���." ></td></tr>
					<tr><td>�ּ�</td><td><input type="text" id="post_member_address" placeholder="�ּҸ� �Է����ּ���." ></td></tr>
					</table>

            		<button id="join_btn">ȸ������</button>

        	</div> 
    	

	<script src="/farmocean/resources/js/member/join.js?ver=123"></script>
	


</body>
</html>

