<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>팔로우 리스트</title>
		<style>
			table { 
				width: 500px;
			}
			td {
				padding: 20px;
				border: 1px solid #666666;
			}
			th {
				padding: 20px;
				border: 1px solid #666666;
				background: rgb(77,77,77);
				color: #fff;
			}		
			th, td {
  				text-align: center;
			}
		</style>
</head>
<body>

		<h3>팔로우 리스트</h3>
		
		  	
		  	<table id="mymessage" border='1' style = "word-break: break-all">
		
				<tr>
					<th>팔로우 아이디</th>
					<th>팔로우 취소버튼</th>
				</tr>
		<% int cnt = 1; %>
		<c:forEach items="${followee}" var="follow">
				<tr id="p<%=cnt%>">
					<td>
						<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   				href="<c:url value="/Sell/member/${follow.followee_id}" />">${follow.followee_id}</a>
					</td>
					<td>
						<button id="btn<%= cnt%>" onclick = "unfollow('${follow.followee_id}', <%= cnt%>, '${sessionScope.loginId.member_id }')">팔로우 취소하기</button>
					</td>
				</tr>
			
		  	
			<% cnt++; %>
		</c:forEach>
			</table>

<script type = "text/javascript" src="/farmocean/resources/js/mypage/followPage.js?"></script>
</body>
</html>