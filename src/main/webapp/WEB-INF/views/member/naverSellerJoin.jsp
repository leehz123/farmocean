<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ezen.farmocean.member.dto.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>네이버 판매자 회원가입</title>
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
		<h1 style="text-align: center; padding-bottom: 2%;">네이버 판매자 회원가입</h1>
		<table>

			<tr style="margin-top: 100px;">
				<td class="col1"><span class="starEm">*</span>아이디</td>
				<td class="col2"><input type="text" id="post_naver_id"
					value="<%=member12.getMember_id()%>" readonly="readonly">
			</tr>
			<tr>
				<td class="col1"><span class="starEm">*</span>이름</td>
				<td class="col2"><input type="text" id="post_naver_name"
					value="<%=member12.getMember_name()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td class="col1"><span class="starEm">*</span>닉네임</td>
				<td class="col2"><input type="text" id="post_naver_nickName"
					value="<%=member12.getMember_nickName()%>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td class="col1"><span class="starEm">*</span>이메일</td>
				<td class="col2"><input type="text" id="post_naver_email"
					value="<%=member12.getMember_id()%>" readonly="readonly">
			</tr>
			<!-- <tr>
        <td class="col1">이메일</td>
        <td class="col2">
            <input type="text" name="mailid">
            <span class="a">@</span>
            <input type="text" name="email">
            <select name="mailslc">
                <option value="self" selected>직접입력</option>
                <option value="naver">naver.com</option>
                <option value="gm">gmail.com</option>
                <option value="da">daum.com</option>
                <option value="yah">yahoo.com</option>
            </select>
            <input class='but2' type="button" value="이메일 중복확인" onclick="">
        </td>
    </tr> -->
			<tr>
				<td class="col1"><span class="starEm">*</span>전화번호</td>
				<td class="col2"><input style="width: 29%; text-align: center"
					type="text" id="post_naver_phoneNum1" maxlength="3"> - <input
					style="width: 29%; text-align: center" type="text"
					id="post_naver_phoneNum2" maxlength="4"> - <input
					style="width: 29%; text-align: center" type="text"
					id="post_naver_phoneNum3" maxlength="4"></td>
			</tr>
			<tr>
				<td class="col1">계좌번호</td>
				<td class="col2"><select id="post_member_bank"
					name="post_member_bank">
						<option value="신한은행">신한은행</option>
						<option value="우리은행">우리은행</option>
						<option value="국민은행">국민은행</option>
						<option value="농협">농협</option>
						<option value="우체국">우체국</option>
				</select> <input type="text" id="post_member_accountNum"
					placeholder="계좌번호를 입력해주세요."></td>
			</tr>
			<tr>
				<td class="col1"><span class="starEm">*</span>우편번호</td>
				<td class="col2"><input type="text" id="sample6_postcode"
					placeholder="우편번호">
					<button class="but1" onclick="sample6_execDaumPostcode()"
						value="우편번호 찾기">우편번호 찾기</button></td>
			</tr>

			<tr>
				<td class="col1"><span class="starEm">*</span>주소</td>
				<td class="col2"><input type="text" id="sample6_address"
					placeholder="주소"></td>
			</tr>
			<tr>
				<td class="col1"><span class="starEm">*</span>참고주소</td>
				<td class="col2"><input type="text" id="sample6_extraAddress"
					placeholder="참고항목"></td>
			</tr>
			<tr>
				<td class="col1"><span class="starEm">*</span>추가주소</td>
				<td class="col2"><input type="text" id="sample6_detailAddress"
					placeholder="추가주소"></td>
			</tr>
		</table>

		</div>
		<div id="out"></div>
		<div class="create">
			<button class="but3" id="join_btn">회원가입</button>
		</div>
		</div>
	</section>



	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/farmocean/resources/js/member/naverSellerJoin.js"></script>



	<%@ include file="/resources/jspf/body_footer.jspf"%>
</body>
</html>

