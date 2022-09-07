
<%@page import="org.springframework.web.socket.WebSocketSession"%>
<%@page import="com.ezen.farmocean.member.dto.LoginMember"%>
<%@page import="java.net.http.WebSocket"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<%@ include file="/resources/jspf/header.jspf"%>
</head>
<body>
	<%@ include file="/resources/jspf/body_header.jspf"%>
	<%@ include file="/WEB-INF/views/chat/echo.jsp"%>

	<h1>�α��� ����!!</h1>

	<%
	LoginMember member = (LoginMember) session.getAttribute("loginId");
	%>


	<table border="1">
		<tr>
			<td>�α��� ���̵�</td>
			<td id="logined_id">[<%=member.getMember_id()%>]�� ȯ���ؿ�.
			</td>
		</tr>
		<tr>
			<td>�α��� �̸�</td>
			<td id="logined_name">[<%=member.getMember_name()%>]
			</td>
		</tr>
		<tr>
			<td>�г���</td>
			<td id="logined_nickName">[<%=member.getMember_nickName()%>]
			</td>
		</tr>
		<tr>
			<td>ȸ�� ���</td>
			<td id="logined_class">[<%=member.getMember_type()%>]ȸ��
			</td>
		</tr>
	</table>

	<br>
	<br>

	<button id="logout_btn">�α׾ƿ� ��ư</button>
	<button id="chat_btn">ä�� ��ư</button>
	<button id="test_btn">test ��ư</button>
	


	<script>
		const logout = document.getElementById('logout_btn');
		const chat = document.getElementById('chat_btn');
		const test = document.getElementById('test_btn');
		
		logout.addEventListener('click',(e)=>{
			
			myWindow = window.open('https://nid.naver.com/nidlogin.logout', '���̹��˾�', 
			'width=1,left=4000px, height=1, top=4000px scrollbars=yes, resizable=no');
		
		setTimeout("myWindow.close()", 1000);
		setTimeout("window.location.replace('/farmocean/member/logout')", 1000);
		
		});
		
		
		
		
		chat.addEventListener('click',(e)=>{
	
		window.location.href='/farmocean/echo/chat';
		});
		
		const sendBtn = document.getElementById('sendBtn');
		const message = document.getElementById('message');
		const messageArea = document.getElementById('messageArea');

		sendBtn.addEventListener('click',(e)=>{
		    sendMessage();
		    message.value = '';
		});

		function enterkey() { 
			if (window.event.keyCode == 13) { 
				sendMessage();
			    message.value = '';
		    } 
		}



		let sock = new SockJS("http://localhost:8888/farmocean/echo");
		sock.onmessage = onMessage;
		sock.onclose = onClose;

		// �޽��� ����
		function sendMessage() {
		    sock.send(message.value);
		}
		// �����κ��� �޽����� �޾��� ��
		function onMessage(msg) {
		    var data = msg.data;
		    $("#messageArea").append(data + "<br/>");
		}
		// ������ ������ ������ ��
		function onClose(evt) {
		    messageArea.append("�������");

		}
	</script>
</body>


</html>