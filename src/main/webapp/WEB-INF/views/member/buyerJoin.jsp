<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/resources/jspf/header.jspf"%>
<title>������ ȸ������</title>
<link rel="stylesheet"
	href="/farmocean/resources/js/member/joinDesign.css">
</head>
<body>
	<%@ include file="/resources/jspf/body_header.jspf"%>
	
	<section class="section1">
	<h1 style="text-align: center; padding-bottom: 2%; ">������ ȸ������</h1>
	<table>
		<tr style="margin-top: 100px;">
			<td class="col1"><span class="starEm">*</span>���̵�</td>
			<td class="col2"><input type="text" id="post_member_id"
				placeholder="���̵� �Է����ּ���." maxlength="12">
				<button class="but1" id="idCheckBtn">�ߺ�Ȯ��</button>
				<p>
					�ؾ��̵�� <span class="num">����+���� 5~12�ڸ�</span>�� ��밡���մϴ�
				</p>
				<div id="id_out"></div></td>
		</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>��й�ȣ</td>
			<td class="col2"><input type="password" id="post_member_pw"
				maxlength="15">
				<p>
					�غ�й�ȣ�� <span class="num">����, ����, Ư������(~!@#$%^&*)�� ���� 8 ~
						15�ڸ�</span>�� �Է��� �����մϴ�.
				</p>
				<div id="pw_out"></div></td>
		</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>��й�ȣ Ȯ��</td>
			<td class="col2"><input type="password"
				id="post_member_pw_check" maxlength="15">
				<p>�غ�й�ȣ�� �ѹ� �� �Է����ּ���</p></td>
		</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>�̸�</td>
			<td class="col2"><input type="text" id="post_member_name"
				placeholder="�̸��� �Է����ּ���." maxlength="5"></td>
		</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>�г���</td>
			<td class="col2"><input type="text" id="post_member_nickName"
				placeholder="�г��� �Է����ּ���.">
				<p>
					�شг����� <span class="num">����+���� 5~12�ڸ�</span>�� ��� �����մϴ�.
				</p>
				<div id="nickname_out"></div></td>
		</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>�̸���</td>
			<td class="col2"><input type="text" id="post_member_email"
				placeholder="�̸����� �Է����ּ���.">
				<p>
					�ؾ��̵�� <span class="num">����+���� 5~12�ڸ�</span>�� ��밡���մϴ�
				</p>
				<div id="email_out"></div></td>
		</tr>
		<!-- <tr>
        <td class="col1">�̸���</td>
        <td class="col2">
            <input type="text" name="mailid">
            <span class="a">@</span>
            <input type="text" name="email">
            <select name="mailslc">
                <option value="self" selected>�����Է�</option>
                <option value="naver">naver.com</option>
                <option value="gm">gmail.com</option>
                <option value="da">daum.com</option>
                <option value="yah">yahoo.com</option>
            </select>
            <input class='but2' type="button" value="�̸��� �ߺ�Ȯ��" onclick="">
        </td>
    </tr> -->
		<tr>
			<td class="col1"><span class="starEm">*</span>��ȭ��ȣ</td>
			<td class="col2"><input style="width: 29%; text-align: center"
				type="text" id="post_member_phoneNum1" maxlength="3"> - <input
				style="width: 29%; text-align: center" type="text"
				id="post_member_phoneNum2" maxlength="4"> - <input
				style="width: 29%; text-align: center" type="text"
				id="post_member_phoneNum3" maxlength="4"></td>
		</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>�����ȣ</td>
			<td class="col2"><input type="text" id="sample6_postcode"
				placeholder="�����ȣ">
				<button class="but1" onclick="sample6_execDaumPostcode()"
					value="�����ȣ ã��">�����ȣ ã��</button></td>
		</tr>

		<tr>
			<td class="col1"><span class="starEm">*</span>�ּ�</td>
			<td class="col2"><input type="text" id="sample6_address"
				placeholder="�ּ�"></td>
		</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>�����ּ�</td>
			<td class="col2"><input type="text" id="sample6_extraAddress"
				placeholder="�����׸�"></td>
		</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>�߰��ּ�</td>
			<td class="col2"><input type="text" id="sample6_detailAddress"
				placeholder="�߰��ּ�"></td>
		</tr>
	</table>

	</div>
	<div id="out"></div>
	<div class="create">
		<button class="but3" id="join_btn">ȸ������</button>
	</div>
	</div>
	</section>
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/farmocean/resources/js/member/buyerJoin.js"></script>



	<%@ include file="/resources/jspf/body_footer.jspf"%>
</body>
</html>

