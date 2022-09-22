<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/resources/jspf/header.jspf"%>
<title>구매자 회원가입</title>
<link rel="stylesheet"
	href="/farmocean/resources/js/member/joinDesign.css">
</head>
<body>
	<%@ include file="/resources/jspf/body_header.jspf"%>
	
	<section class="section1">
	<h1 style="text-align: center; padding-bottom: 2%; ">구매자 회원가입</h1>
	<table>
		<tr style="margin-top: 100px;">
			<td class="col1"><span class="starEm">*</span>아이디</td>
			<td class="col2"><input type="text" id="post_member_id"
				placeholder="아이디를 입력해주세요." maxlength="12">
				<button class="but1" id="idCheckBtn">중복확인</button>
				<p>
					※아이디는 <span class="num">영문+숫자 5~12자리</span>만 사용가능합니다
				</p>
				<div id="id_out"></div></td>
		</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>비밀번호</td>
			<td class="col2"><input type="password" id="post_member_pw"
				maxlength="15">
				<p>
					※비밀번호는 <span class="num">문자, 숫자, 특수문자(~!@#$%^&*)의 조합 8 ~
						15자리</span>로 입력이 가능합니다.
				</p>
				<div id="pw_out"></div></td>
		</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>비밀번호 확인</td>
			<td class="col2"><input type="password"
				id="post_member_pw_check" maxlength="15">
				<p>※비밀번호를 한번 더 입력해주세요</p></td>
		</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>이름</td>
			<td class="col2"><input type="text" id="post_member_name"
				placeholder="이름을 입력해주세요." maxlength="5"></td>
		</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>닉네임</td>
			<td class="col2"><input type="text" id="post_member_nickName"
				placeholder="닉네임 입력해주세요.">
				<p>
					※닉네임은 <span class="num">영문+숫자 5~12자리</span>로 사용 가능합니다.
				</p>
				<div id="nickname_out"></div></td>
		</tr>
		<tr>
			<td class="col1"><span class="starEm">*</span>이메일</td>
			<td class="col2"><input type="text" id="post_member_email"
				placeholder="이메일을 입력해주세요.">
				<p>
					※아이디는 <span class="num">영문+숫자 5~12자리</span>만 사용가능합니다
				</p>
				<div id="email_out"></div></td>
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
				type="text" id="post_member_phoneNum1" maxlength="3"> - <input
				style="width: 29%; text-align: center" type="text"
				id="post_member_phoneNum2" maxlength="4"> - <input
				style="width: 29%; text-align: center" type="text"
				id="post_member_phoneNum3" maxlength="4"></td>
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
	<script src="/farmocean/resources/js/member/buyerJoin.js"></script>



	<%@ include file="/resources/jspf/body_footer.jspf"%>
</body>
</html>

