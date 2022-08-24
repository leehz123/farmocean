<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인 성공!!</h1>
	
	<% 
		String id = (String)session.getAttribute("logined");
	%>
	<table border="1">
		<tr><td>로그인 아이디 </td><td id="logined_id"></td></tr>
		<tr><td>잔여 포인트</td><td id="logined_point">원</td></tr> 
		<tr><td>로그인 이름</td><td id="logined_name"></td></tr> 
	</table>

	<button id="logout_btn">로그아웃 버튼</button>
	
	
	
	 
<script>
const xhttp = new XMLHttpRequest();
const logout_btn = document.getElementById('logout_btn');
const logined_id = document.getElementById('logined_id');
const logined_point = document.getElementById('logined_point');
const logined_name = document.getElementById('logined_name');
	
xhttp.open('GET', '/farmocean/list/'+'<%=session.getAttribute("logined") %>');

xhttp.send();

xhttp.onreadystatechange = function(e){
	if(xhttp.readyState == 4){
        if(xhttp.status == 200){
		const user = JSON.parse(xhttp.responseText);
        logined_id.innerText = user.buy_id;
        logined_point.innerText = user.buy_point;
        logined_name.innerText = user.buy_name;
        };
    }
};
logout_btn.addEventListener('click',(e)=>{
    <% session.invalidate();%>
    window.location.replace('/farmocean/login');
});
</script>

</body>


</html>