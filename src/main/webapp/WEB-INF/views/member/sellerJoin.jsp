<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>판매자 회원가입</title>
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

	<button type="button" id="home_btn">로그인창으로</button>
	<br>
	<br>
	<button type="button" id="join_btn_buyer">구매자 회원가입</button>
	<br>
	<br>
	<button type="button" id="join_btn_seller">판매자 회원가입</button>
	<div class="border">
		<h1>구매자 회원가입</h1>
            		<table border="1">
            		<tr><td>아이디</td><td><input type="text" id="post_member_id" placeholder="아이디를 입력해주세요.">
            		<button id="idCheckBtn">중복확인</button></td></tr>
					<tr><td colspan="2">아이디 제약조건 : 영문+숫자 5~12자리만 사용가능합니다<div id="id_out">　</div></td></tr>            		            		
           			<tr><td>비밀번호</td><td><input type="password" id="post_member_pw" placeholder="비밀번호를 입력해주세요"></td></tr>
           			<tr><td colspan="2">비밀번호 제약조건 : ㅁㅁㅁㅁㅁㅁ<div id="pw_out">　</div></td></tr>
           			<tr><td>비밀번호확인</td><td><input type="password" id="post_member_pw_check" placeholder="비밀번호를 한번 더 입력해주세요."></td></tr>
           			<tr><td colspan="2"><div id="pw_out">　</div></td></tr>           			
           			<tr><td>이름</td><td><input type="text" id="post_member_name"  placeholder="이름 입력해주세요."></td></tr>
           			<tr><td>닉네임</td><td><input type="text" id="post_member_nickName"  placeholder="닉네임 입력해주세요."></td></tr>
           			<tr><td colspan="2">닉네임 제약조건 : 영문+숫자 5~12자리만 사용가능합니다<div id="nickname_out">　</div></td></tr>
					<tr><td>이메일</td><td><input type="text" id="post_member_email" placeholder="이메일을 입력해주세요." ></td></tr>
					<tr><td colspan="2">이메일 제약조건 : 영문+숫자 5~12자리만 사용가능합니다<div id="email_out">　</div></td></tr>
					<tr>
						<td>전화번호</td>
						<td>
							<input style="width:29%; text-align: center" type="text" id="post_member_phoneNum1" placeholder="전화번호를 입력해주세요." > -
							<input style="width:29%; text-align: center" type="text" id="post_member_phoneNum2" placeholder="전화번호를 입력해주세요." > -
							<input style="width:29%; text-align: center" type="text" id="post_member_phoneNum3" placeholder="전화번호를 입력해주세요." >
						</td>
					</tr>
			<tr>
				<td>계좌번호</td>
				<td><select id="post_member_bank" name="post_member_bank">
						<option value="신한은행">신한은행</option>
						<option value="우리은행">우리은행</option>
						<option value="국민은행">국민은행</option>
						<option value="농협">농협</option>
						<option value="우체국">우체국</option>
				</select> <input type="text" id="post_member_accountNum"
					placeholder="계좌번호를 입력해주세요."></td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td><input type="text" id="sample6_postcode" placeholder="우편번호"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button"
					onclick="sample6_execDaumPostcode()" value="우편번호 찾기"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" id="sample6_address" placeholder="주소"></td>
			</tr>
			<tr>
				<td>참고주소</td>
				<td><input type="text" id="sample6_extraAddress"
					placeholder="참고항목"></td>
			</tr>
			<tr>
				<td>추가주소</td>
				<td><input type="text" id="sample6_detailAddress"
					placeholder="추가주소"></td>
			</tr>

		</table>

		<tr>
			<td><div id="out"></div></td>
			<td><button id="join_btn">회원가입</button></td>
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

