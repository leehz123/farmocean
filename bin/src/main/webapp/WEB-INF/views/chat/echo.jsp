<%@page import="org.springframework.web.socket.WebSocketSession"%>
<%@page import="com.ezen.farmocean.member.dto.LoginMember"%>
<%@page import="java.net.http.WebSocket"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>

</head>

<body>
	<input type="text" id="message" onkeyup="enterkey()" />
	<input type="button" id="sendBtn" value="submit"/>
	<div id="messageArea"></div>
</body>


<script type="text/javascript">

	
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
</html>