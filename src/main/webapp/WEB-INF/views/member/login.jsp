<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
<%@ include file="/resources/jspf/header.jspf"%>
</head>

<script>
	sessionStorage.clear();
</script>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>


	<h1>援щℓ�� 濡�洹몄��</h1>
	<form method="post">
		<table border="1" width="400px">
			<tr>
				<td>���대��</td>
				<td><input id="member_id" name="member_id"></td>
			</tr>
			<tr>
				<td>鍮�諛�踰���</td>
				<td><input type="password" id="member_pw" name="member_pw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<div id="out"></div> <br> <br>
					<button formaction="/farmocean/member/logincheck" type="submit"
						id="submit">濡�洹몄��</button> <br> <br>
					<button type="button" id="join_btn">����媛���</button> <br> <br>
					<button type="button" id="join_btn_seller">��留ㅼ�� ����媛���</button>
					<br> <br>
					<button type="button" id="id_search_btn">���대��李얘린</button> <br>
					<br>
					<button type="button" id="pw_search_btn">鍮�諛�踰��몄갼湲�</button> <br>
					<br>
					<div id="naver_id_login"></div>
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
  	var naver_id_login = new naver_id_login("tFcf6kO8bBQSvTpMwwWV", "http://localhost:8888/farmocean/member/naver_callback");
  	var state = naver_id_login.getUniqState();
  	naver_id_login.setButton("white", 2,40);
  	naver_id_login.setDomain("http://localhost:8888/farmocean/member/login");
  	naver_id_login.setState(state);
  	// naver_id_login.setPopup(true);
  	naver_id_login.init_naver_id_login();
  </script>
	<script>
	
	const joinBtn = document.getElementById('join_btn');
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