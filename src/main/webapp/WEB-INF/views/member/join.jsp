<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ȸ������</title>
<style>

	</style>
</head>

<body>
			<button type="button" id="home_btn">�α���â����</button><br><br>
    		<button type="button" id="join_btn_buyer">������ ȸ������</button><br><br>
			<button type="button" id="join_btn_seller">�Ǹ��� ȸ������</button>
        	<div class="border">
            	<h1>������ ȸ������</h1>
            		<table border="1">
            		<tr><td>���̵�</td><td><input type="text" id="post_member_id" placeholder="���̵� �Է����ּ���.">
            		<button id="idCheckBtn">�ߺ�Ȯ��</button></td></tr>
            		            		
           			<tr><td>��й�ȣ</td><td><input type="password" id="post_member_pw" placeholder="��й�ȣ (����, ���� ������ 6~12�ڸ� �̳�)"></td></tr>
           			<tr><td>��й�ȣȮ��</td><td><input type="password" id="post_member_pw_check" placeholder="��й�ȣ�� �ѹ� �� �Է����ּ���."></td></tr>           			
           			<tr><td>�̸�</td><td><input type="text" id="post_member_name"  placeholder="�̸� �Է����ּ���."></td></tr>
           			<tr><td>�г���</td><td><input type="text" id="post_member_nickName"  placeholder="�г��� �Է����ּ���."></td></tr>
					<tr><td>�̸���</td><td><input type="text" id="post_member_email" placeholder="�̸����� �Է����ּ���." ></td></tr>
					<tr><td>��ȭ��ȣ</td><td><input type="text" id="post_member_phoneNum" placeholder="��ȭ��ȣ�� �Է����ּ���." ></td></tr>

					</table>

            		<tr><td><div id="out"></div></td><td><button id="join_btn">ȸ������</button></td></tr>

        	</div> 
    	

	<script src="/farmocean/resources/js/member/buyerJoin.js?ver=123"></script>
	<script>
		const homeBtn = document.getElementById('home_btn');
		const buyerJoinBtn = document.getElementById('join_btn_buyer');
		const sellerJoinBtn = document.getElementById('join_btn_seller');
	
		homeBtn.addEventListener('click',(e)=>{
		    window.location.replace('/farmocean/member/login');
		});
		
		buyerJoinBtn.addEventListener('click',(e)=>{
		    window.location.replace('/farmocean/member/join');
		});
	
		sellerJoinBtn.addEventListener('click',(e)=>{
		    window.location.replace('/farmocean/member/sellerjoin');
		});
	</script>
	


</body>
</html>

