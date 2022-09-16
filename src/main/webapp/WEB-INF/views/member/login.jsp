<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>Insert title here</title>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="/farmocean/resources/js/member/login.css">
<link
	href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/a81368914c.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<script>
   sessionStorage.clear();
</script>

<body>

 <img class="wave" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAeIAAABoCAMAAAAaawObAAAAA1BMVEX///+nxBvIAAAASElEQVR4nO3BAQ0AAADCoPdPbQ43oAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAODPAMQ4AAG8WSytAAAAAElFTkSuQmCC">
  <div class="container">
    <div class="img">
      <img src="/farmocean/resources/image/mainpage/logo_Img.png">
    </div>
	<div class="login-content">
		<form method="post">
		
			<h2 class="title">팜오션</h2>
			<div class="input-div one">
				<div class="i">
					<i class="fas fa-user"></i>
				</div>
				<div class="div">
					<h5>ID</h5>
					<input id="member_id" name="member_id">
				</div>
			</div>
			<div class="input-div pass">
				<div class="i">
					<i class="fas fa-lock"></i>
				</div>
				<div class="div">
					<h5>Password</h5>
					<input type="password" id="member_pw" name="member_pw">
				</div>
			</div>
			<br>
			<a href="/farmocean/member/searchId">Forgot Password?</a> 
			<input
				formaction="/farmocean/member/logincheck" type="submit" class="btn"
				value="Login">
			<div id="naver_id_login"></div>
		</form>
	</div>
	</div>
	</div>
	<button type="button" id="join_btn">회원가입</button>
	<br>
	<br>
	<button type="button" id="join_btn_seller">판매자 회원가입</button>
	<br>
	<br>
	<button type="button" id="id_search_btn">아이디 찾기</button>
	<br>
	<br>
	<button type="button" id="pw_search_btn">비밀번호 찾기</button>
	<br>
	<br>
	<div id="naver_id_login"></div>
	<script type="text/javascript">
     var naver_id_login = new naver_id_login("tFcf6kO8bBQSvTpMwwWV", "http://localhost:8888/farmocean/member/naver_callback");
     var state = naver_id_login.getUniqState();
     naver_id_login.setButton("white", 5,70);
     naver_id_login.setDomain("http://localhost:8888/farmocean/member/login");
     naver_id_login.setState(state);
     // naver_id_login.setPopup(true);
     naver_id_login.init_naver_id_login();
  </script>

	<script>
   
   const joinBtn = document.getElementById('join_btn');
   const chatBtn = document.getElementById('chat_btn');
   const sellerJoinBtn = document.getElementById('join_btn_seller');
   const idSearchBtn = document.getElementById('id_search_btn');
   const pwSearchBtn = document.getElementById('pw_search_btn');
   
   joinBtn.addEventListener('click',(e)=>{
       window.location.href='/farmocean/member/join';
   });

   sellerJoinBtn.addEventListener('click',(e)=>{
       window.location.href='/farmocean/member/sellerjoin';
   });
   
   idSearchBtn.addEventListener('click',(e)=>{
       window.location.href='/farmocean/member/searchId';
   });
   
   pwSearchBtn.addEventListener('click',(e)=>{
       window.location.href='/farmocean/member/searchPw';
   });
   
   
   </script>

</body>
</html>