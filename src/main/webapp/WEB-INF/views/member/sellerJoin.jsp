<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Ǹ��� ȸ������</title>
<style>
input {
	width: 60%;
}

table {
	text-align: center;
}
</style>
<%@ include file="/resources/jspf/header.jspf"%>
</head>

<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

	<button type="button" id="home_btn">�α���â����</button>
	<br>
	<br>
	<button type="button" id="join_btn_buyer">������ ȸ������</button>
	<br>
	<br>
	<button type="button" id="join_btn_seller">�Ǹ��� ȸ������</button>
	<div class="border">
		<h1>������ ȸ������</h1>
            		<table border="1">
            		<tr><td>���̵�</td><td><input type="text" id="post_member_id" placeholder="���̵� �Է����ּ���.">
            		<button id="idCheckBtn">�ߺ�Ȯ��</button></td></tr>
					<tr><td colspan="2">���̵� �������� : ����+���� 5~12�ڸ��� ��밡���մϴ�<div id="id_out">��</div></td></tr>            		            		
           			<tr><td>��й�ȣ</td><td><input type="password" id="post_member_pw" placeholder="��й�ȣ�� �Է����ּ���"></td></tr>
           			<tr><td colspan="2">��й�ȣ �������� : ������������<div id="pw_out">��</div></td></tr>
           			<tr><td>��й�ȣȮ��</td><td><input type="password" id="post_member_pw_check" placeholder="��й�ȣ�� �ѹ� �� �Է����ּ���."></td></tr>
           			<tr><td colspan="2"><div id="pw_out">��</div></td></tr>           			
           			<tr><td>�̸�</td><td><input type="text" id="post_member_name"  placeholder="�̸� �Է����ּ���."></td></tr>
           			<tr><td>�г���</td><td><input type="text" id="post_member_nickName"  placeholder="�г��� �Է����ּ���."></td></tr>
           			<tr><td colspan="2">�г��� �������� : ����+���� 5~12�ڸ��� ��밡���մϴ�<div id="nickname_out">��</div></td></tr>
					<tr><td>�̸���</td><td><input type="text" id="post_member_email" placeholder="�̸����� �Է����ּ���." ></td></tr>
					<tr><td colspan="2">�̸��� �������� : ����+���� 5~12�ڸ��� ��밡���մϴ�<div id="email_out">��</div></td></tr>
					<tr>
						<td>��ȭ��ȣ</td>
						<td>
							<input style="width:29%; text-align: center" type="text" id="post_member_phoneNum1" placeholder="��ȭ��ȣ�� �Է����ּ���." > -
							<input style="width:29%; text-align: center" type="text" id="post_member_phoneNum2" placeholder="��ȭ��ȣ�� �Է����ּ���." > -
							<input style="width:29%; text-align: center" type="text" id="post_member_phoneNum3" placeholder="��ȭ��ȣ�� �Է����ּ���." >
						</td>
					</tr>
			<tr>
				<td>���¹�ȣ</td>
				<td><select id="post_member_bank" name="post_member_bank">
						<option value="��������">��������</option>
						<option value="�츮����">�츮����</option>
						<option value="��������">��������</option>
						<option value="����">����</option>
						<option value="��ü��">��ü��</option>
				</select> <input type="text" id="post_member_accountNum"
					placeholder="���¹�ȣ�� �Է����ּ���."></td>
			</tr>
			<tr>
				<td>�����ȣ</td>
				<td><input type="text" id="sample6_postcode" placeholder="�����ȣ"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button"
					onclick="sample6_execDaumPostcode()" value="�����ȣ ã��"></td>
			</tr>
			<tr>
				<td>�ּ�</td>
				<td><input type="text" id="sample6_address" placeholder="�ּ�"></td>
			</tr>
			<tr>
				<td>�����ּ�</td>
				<td><input type="text" id="sample6_extraAddress"
					placeholder="�����׸�"></td>
			</tr>
			<tr>
				<td>�߰��ּ�</td>
				<td><input type="text" id="sample6_detailAddress"
					placeholder="�߰��ּ�"></td>
			</tr>

		</table>

		<tr>
			<td><div id="out"></div></td>
			<td><button id="join_btn">ȸ������</button></td>
		</tr>

	</div>


	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/farmocean/resources/js/member/pwCheck.js"></script>
	<script src="/farmocean/resources/js/member/sellerJoin.js"></script>
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

