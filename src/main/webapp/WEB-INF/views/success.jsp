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
		<tr><td>ȸ�� ���</td><td id="logined_class"></td></tr>
	</table>
	
	<br><br>
	
	<button id="logout_btn">�α׾ƿ� ��ư</button>
	
	
	
	 
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
		        logined_class.innerText = '�Ϲ�ȸ��';
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
		        logined_point.innerText = '�Ǹ�ȸ���� ����Ʈ�� �����ϴ�';
		        logined_name.innerText = seller.sell_name;
		        logined_class.innerText = '�Ǹ�ȸ��';
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