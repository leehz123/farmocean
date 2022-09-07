<%@page import="org.springframework.web.socket.WebSocketSession"%>
<%@page import="com.ezen.farmocean.member.dto.LoginMember"%>
<%@page import="java.net.http.WebSocket"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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


	<div id="messageArea" style="overflow: auto; width: 300px; height: 100px;"></div>
	<input type="text" id="message" onkeyup="enterkey()" />
	<input type="button" id="sendBtn" value="submit"/>
</body>

<script type="text/javascript">

const sendBtn = document.getElementById('sendBtn');
const message = document.getElementById('message');


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
<% 
	String getLoginNick;

	if(session.getAttribute("loginId")!=null){
		LoginMember loginId = (LoginMember)session.getAttribute("loginId"); 
		getLoginNick = loginId.getMember_nickName();	
	} else{
		getLoginNick = "undefined";		
	}
%>
// 메시지 전송
function sendMessage() {
	
	if('<%=getLoginNick %>'=='undefined'){
		alert('회원만 채팅에 참여할 수 있습니다');
	} else{
		sock.send(message.value);	
	}
    
}
// 서버로부터 메시지를 받았을 때
function onMessage(msg) {
    var data = msg.data;
    $("#messageArea").append(data + "<br/>");
    
    var divdiv = document.getElementById("messageArea"); 
    divdiv.scrollTop = divdiv.scrollHeight; 
}
// 서버와 연결을 끊었을 때
function onClose(evt) {
    messageArea.append("연결끊김");

}
</script>

</html>