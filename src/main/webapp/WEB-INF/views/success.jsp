<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>�α��� ����!!</h1>
	
	<% 
		String id = (String)session.getAttribute("logined");
	%>
	<table border="1">
		<tr><td>�α��� ���̵� </td><td id="logined_id"></td></tr>
		<tr><td>�ܿ� ����Ʈ</td><td id="logined_point">��</td></tr> 
		<tr><td>�α��� �̸�</td><td id="logined_name"></td></tr> 
	</table>

	<button id="logout_btn">�α׾ƿ� ��ư</button>
	
	
	
	 
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