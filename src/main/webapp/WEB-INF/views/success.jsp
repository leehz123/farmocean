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
		<tr><td>회원 등급</td><td id="logined_class"></td></tr>
	</table>
	
	<br><br>
	
	<button id="logout_btn">로그아웃 버튼</button>
	
	
	
	 
<script>
const xhttp = new XMLHttpRequest();
const xhttp2 = new XMLHttpRequest();
const logout_btn = document.getElementById('logout_btn');
const logined_id = document.getElementById('logined_id');
const logined_point = document.getElementById('logined_point');
const logined_name = document.getElementById('logined_name');
const logined_class = document.getElementById('logined_class');
	
xhttp.open('GET', '/farmocean/member/list/'+'<%=session.getAttribute("logined") %>');
xhttp.send();

xhttp2.open('GET', '/farmocean/member/sellerlist/'+'<%=session.getAttribute("logined") %>');
xhttp2.send();

xhttp.onreadystatechange = function(e){
	if(xhttp.readyState == 4){
        if(xhttp.status == 200){
	        if(xhttp.responseText != null){
				const buyer = JSON.parse(xhttp.responseText);
		        logined_id.innerText = buyer.buy_id;
		        logined_point.innerText = buyer.buy_point;
		        logined_name.innerText = buyer.buy_name;
		        logined_class.innerText = '일반회원';
	        }
        };
    }
};

xhttp2.onreadystatechange = function(e){
	if(xhttp2.readyState == 4){
        if(xhttp2.status == 200){
	        if(xhttp2.responseText != null){
				const seller = JSON.parse(xhttp2.responseText);
		        logined_id.innerText = seller.sell_id;
		        logined_point.innerText = '판매회원은 포인트가 없습니다';
		        logined_name.innerText = seller.sell_name;
		        logined_class.innerText = '판매회원';
	        }
        };
    }
};
logout_btn.addEventListener('click',(e)=>{
     
    window.location.replace('/farmocean/member/login');
});
</script>

</body>


</html>