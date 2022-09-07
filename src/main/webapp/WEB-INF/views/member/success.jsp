
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

	<h1>로그인 성공!!</h1>

	<%
	LoginMember member = (LoginMember) session.getAttribute("loginId");
	%>


	<table border="1">
		<tr>
			<td>로그인 아이디</td>
			<td id="logined_id">[<%=member.getMember_id()%>]님 환영해요.
			</td>
		</tr>
		<tr>
			<td>로그인 이름</td>
			<td id="logined_name">[<%=member.getMember_name()%>]
			</td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td id="logined_nickName">[<%=member.getMember_nickName()%>]
			</td>
		</tr>
		<tr>
			<td>회원 등급</td>
			<td id="logined_class">[<%=member.getMember_type()%>]회원
			</td>
		</tr>
	</table>

	<br>
	<br>

	<button id="logout_btn">로그아웃 버튼</button>
	<button id="chat_btn">채팅 버튼</button>
	<button id="test_btn">test 버튼</button>
	


	<script>
		const logout = document.getElementById('logout_btn');
		const chat = document.getElementById('chat_btn');
		const test = document.getElementById('test_btn');
		
		logout.addEventListener('click',(e)=>{
			
			myWindow = window.open('https://nid.naver.com/nidlogin.logout', '네이버팝업', 
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

		// 메시지 전송
		function sendMessage() {
		    sock.send(message.value);
		}
		// 서버로부터 메시지를 받았을 때
		function onMessage(msg) {
		    var data = msg.data;
		    $("#messageArea").append(data + "<br/>");
		}
		// 서버와 연결을 끊었을 때
		function onClose(evt) {
		    messageArea.append("연결끊김");

		}
	</script>
</body>


</html>