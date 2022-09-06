<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
<style>
input{
	width:60%;
}

table{
text-align: center;
}
</style>
<%@ include file="/resources/jspf/header.jspf" %>
</head>

<body>
<%@ include file="/resources/jspf/body_header.jspf" %>


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

					</table>

            		<tr><td><div id="out"></div></td><td><button id="join_btn">회원가입</button></td></tr>
	
        	</div> 

    	
	
	<script src="/farmocean/resources/js/member/buyerJoin.js"></script>

	


</body>
</html>

