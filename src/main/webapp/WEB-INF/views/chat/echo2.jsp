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
	<input type="text" id="message" />
	<input type="button" id="send_btn" value="submit"/>
	<div id="messageArea"></div>
</body>

<script>
<% 
	LoginMember member = (LoginMember)session.getAttribute("loginId");
	String member_id = member.getMember_id(); 
%>
var member_id = <%=member_id %>;
</script>
<script src="/farmocean/resources/js/member/chatting.js"></script>
</html>