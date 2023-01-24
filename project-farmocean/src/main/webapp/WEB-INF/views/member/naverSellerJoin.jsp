<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ezen.farmocean.member.dto.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���̹� �Ǹ��� ȸ������</title>
<link rel="stylesheet"
	href="/farmocean/resources/js/member/joinDesign.css">
<%@ include file="/resources/jspf/header.jspf"%>
</head>
<body>
	<%@ include file="/resources/jspf/body_header.jspf"%>
	<%
	Member member12 = (Member) session.getAttribute("naverId");
	%>
	<section class="section1">
		<h1 style="text-align: center; padding-bottom: 2%;">���̹� �Ǹ��� ȸ������</h1>
		<table>

			<tr style="margin-top: 100px;">
				<td class="col1"><span class="starEm">*</span>���̵�</td>
				<td class="col2"><input type="text" id="post_naver_id"
					value="<%=member12.getMember_id()%>" readonly="readonly">
			</tr>
			<tr>
				<td class="col1"><span class="starEm">*</span>�̸�</td>
				<td class="col2"><input type="text" id="post_naver_name"
					value="<%=member12.getMember_name()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td class="col1"><span class="starEm">*</span>�г���</td>
				<td class="col2"><input type="text" id="post_naver_nickName"
					value="<%=member12.getMember_nickName()%>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td class="col1"><span class="starEm">*</span>�̸���</td>
				<td class="col2"><input type="text" id="post_naver_email"
					value="<%=member12.getMember_id()%>" readonly="readonly">
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
					type="text" id="post_naver_phoneNum1" maxlength="3"> - <input
					style="width: 29%; text-align: center" type="text"
					id="post_naver_phoneNum2" maxlength="4"> - <input
					style="width: 29%; text-align: center" type="text"
					id="post_naver_phoneNum3" maxlength="4"></td>
			</tr>
			<tr>
				<td class="col1">���¹�ȣ</td>
				<td class="col2"><select id="post_member_bank"
					name="post_member_bank">
						<option value="��������">��������</option>
						<option value="�츮����">�츮����</option>
						<option value="��������">��������</option>
						<option value="����">����</option>
						<option value="��ü��">��ü��</option>
				</select> <input type="text" id="post_member_accountNum"
					placeholder="���¹�ȣ�� �Է����ּ���."></td>
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
	<script src="/farmocean/resources/js/member/naverSellerJoin.js"></script>



	<%@ include file="/resources/jspf/body_footer.jspf"%>
</body>
</html>

