<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

</head>

<body>

	
	<h1>구매자 로그인</h1>
	<form method="post">
		<table border="1" width="400px">
			<tr>
				<td>아이디</td>
				<td><input id="member_id" name="member_id"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="member_pw" name="member_pw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<div id="out"></div>
					<br>
					<br>
					<button formaction="/farmocean/member/logincheck" type="submit" id="submit">로그인</button>
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
	const idSearchBtn = document.getElementById('id_search_btn');
	
	joinBtn.addEventListener('click',(e)=>{
	    window.location.href='/farmocean/member/join';
	});

	sellerJoinBtn.addEventListener('click',(e)=>{
	    window.location.href='/farmocean/member/sellerjoin';
	});
	
<<<<<<< HEAD
=======
	idSearchBtn.addEventListener('click',(e)=>{
	    window.location.href='/farmocean/member/searchId';
	});
>>>>>>> branch 'kyeongtae' of https://github.com/softdol/project-farmocean.git
	
	
	</script>
</body>
</html>