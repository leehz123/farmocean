<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>

<body>
	<button id = "buyerLogin">구매자로그인</button> 
	<button id = "sellerLogin">판매자로그인</button> 
	
	<h1>판매자 로그인</h1>
	<form method="post">
		<table border="1" width="400px">
			<tr>
				<td>아이디</td>
				<td><input id="sell_pw" name="sell_id"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="sell_pw" name="sell_pw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<br>
					<br>
					<button formaction="/farmocean/member/sellerlogincheck" type="submit" id="submit">로그인</button>
					<br>
					<br>
					<button type="button" id="join_btn">회원가입</button>
					<br>
					<br>
					<button type="button" id="join_btn_seller">판매자 회원가입</button>
					<br>
					<br>
					<button type="button" id="id_search_btn">아이디찾기</button>
					<br>
					<br>
					<button type="button" id="pw_search_btn">비밀번호찾기</button>
					<br>
					<br> 
				</td>
			</tr>
		</table>
	</form>
	
	
	<script>
	const joinBtn = document.getElementById('join_btn');
	const sellerJoinBtn = document.getElementById('join_btn_seller');
	const buyerLoginBtn = document.getElementById('buyerLogin');
	const sellerLoginBtn = document.getElementById('sellerLogin');
	
	joinBtn.addEventListener('click',(e)=>{
	    window.location.replace('/farmocean/member/join');
	});

	sellerJoinBtn.addEventListener('click',(e)=>{
	    window.location.replace('/farmocean/member/join/seller');
	});
	
	buyerLoginBtn.addEventListener('click',(e)=>{
	    window.location.replace('/farmocean/member/buyerlogin');
	});
	
	sellerLoginBtn.addEventListener('click',(e)=>{
	    window.location.replace('/farmocean/member/sellerlogin');
	});
	</script>
</body>
</html>