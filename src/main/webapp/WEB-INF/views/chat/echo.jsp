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
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
</head>


<body>

	<div id="chat">	
		<div id="messageArea"
			style="border-style: solid; overflow-y: auto; width: 300px; height: 300px;"></div>
			<br>
			
		<input type="text" id="message" onkeyup="enterkey()" />
		<input type="button" id="sendBtn" value="submit" />
	</div>
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


function NotReload(){
    if( (event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode == 116) ) {
        event.keyCode = 0;
        event.cancelBubble = true;
        event.returnValue = false;
    } 
}
document.onkeydown = NotReload;



let sock = new SockJS("http://3.39.84.37/farmocean/echo");
sock.onmessage = onMessage;
sock.onclose = onClose;
<%String getLoginNick;

if (session.getAttribute("loginId") != null) {
	LoginMember loginId = (LoginMember) session.getAttribute("loginId");
	getLoginNick = loginId.getMember_nickName();
} else {
	getLoginNick = "undefined";
}%>
// �޽��� ����
function sendMessage() {
	
	if('<%=getLoginNick%>'=='undefined'){
		alert('ȸ���� ä�ÿ� ������ �� �ֽ��ϴ�');
	} else{
		sock.send(message.value);	
	}
    
}
// �����κ��� �޽����� �޾��� ��
function onMessage(msg) {
    var data = msg.data;
    $("#messageArea").append(data + "<br/>");
    
    var divdiv = document.getElementById("messageArea"); 
    divdiv.scrollTop = divdiv.scrollHeight; 
}
// ������ ������ ������ ��
function onClose(evt) {
	if('<%=getLoginNick%>'=='undefined'){
		messageArea.append("�α��� ȸ���� ä�ÿ� ������ �� �ֽ��ϴ�");
	} else{
    	messageArea.append("���� ����");
	}

}
</script>

</html>