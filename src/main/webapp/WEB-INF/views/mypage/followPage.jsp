<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<link rel="stylesheet" href="${path}/resources/css/mypage/followPage.css?ver=<%=System.currentTimeMillis() %>">
<title>팔로우 리스트</title>
		<style>
			table { 
				width: 100%;
			}
			
		</style>
</head>
<body>

		  	
		  	<!-- 
		<h3>팔로우 리스트</h3>
		  	<table class="table table-hover" id="mymessage" border='1'>
		
				<tr>
					<th>팔로우 아이디</th>
					<th>팔로우 취소버튼</th>
				</tr>
				
		<% int cnt = 1; %>
		<c:forEach items="${followee}" var="follow"  varStatus="status">
				<tr id="p<%=cnt%>">
					<td>
						<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   				href="<c:url value="/Sell/member/${follow.followee_id}" />">${followerNickname[status.index]}</a>
					</td>
					<td>
						<button id="btn<%= cnt%>" onclick = "unfollow('${follow.followee_id}', <%= cnt%>, '${sessionScope.loginId.member_id }')">팔로우 취소하기</button>
					</td>
				</tr>
			
		  	
			<% cnt++; %>
		</c:forEach>
			</table>
			 -->




	<table class="table" id="mymessage" border='1'>

		<tr>
			<th style="text-align:center;">팔로우 리스트</th>
		</tr>

		<%
		int cnt1 = 1;
		%>
		<c:forEach items="${followee}" var="follow" varStatus="status">
			<tr id="p<%=cnt1%>">

				<td>
									
					<c:if test="${imagelists[status.index] eq ''}">
 
  						<div class="grade">판매자</div>
 
					</c:if>
				
					<div class="person">
						<div class="box">
							<div class="image">
								<img id="preview"
									src="/farmocean/resources/image/mypage/${imagelists[status.index] }"
									width="50px" height="50px" alt="" />
							</div>
							<div class="online"></div>
						</div>

						<div class="information">
							<div class="content">
							
		   						<a class="username" onClick="fnWinOpen(500, 600, '<c:url value="/Sell/member/${follow.followee_id}" />'); return false;" />${followerNickname[status.index]}</a>
		   						
								<div class="green-tick">
									<svg width="20px" height="20px" version="1.1" id="Layer_1"
										xmlns="http://www.w3.org/2000/svg"
										xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
										viewBox="0 0 40 40" style="enable-background: new 0 0 40 40;"
										xml:space="preserve">
			<style type="text/css">
.st0 {
	fill: #0059FF;
	stroke: #00A2FF;
	stroke-miterlimit: 10;
}

.st1 {
	fill: #FFFFFF;
	stroke: #FFFFFF;
	stroke-miterlimit: 10;
}
</style>
			<path class="st0"
											d="M27.25,30.31L27.25,30.31c-1.94-1.78-5.12-0.54-5.28,2.07l0,0l0,0c-0.96-2.43-4.38-2.67-5.67-0.38l0,0l0,0
				c0.21-2.6-2.76-4.27-4.94-2.76l0,0l0,0c1.34-2.25-0.61-5.02-3.23-4.59l0,0l0,0c2.2-1.46,1.67-4.78-0.88-5.51l0,0h0
				c2.63-0.38,3.62-3.6,1.64-5.34l0,0l0,0c2.54,0.78,4.85-1.7,3.84-4.11l0,0l0,0c1.94,1.78,5.12,0.54,5.28-2.07l0,0l0,0
				c0.96,2.43,4.38,2.67,5.67,0.38l0,0l0,0c-0.21,2.6,2.76,4.27,4.94,2.76l0,0l0,0c-1.34,2.25,0.61,5.02,3.23,4.59l0,0l0,0
				c-2.2,1.46-1.67,4.78,0.88,5.51l0,0h0c-2.63,0.38-3.62,3.6-1.64,5.34l0,0l0,0C28.56,25.42,26.24,27.9,27.25,30.31L27.25,30.31z" />
			<polygon class="st1"
											points="25.64,17.12 24.83,16.25 17.54,22.88 18.36,23.75 " />
			<polygon class="st1"
											points="15.24,18.69 14.36,19.49 18.15,23.5 19.03,22.7 " />
			</svg>
								</div>
							</div>
							<div class="name">${follow.followee_id}</div>
						</div>

						<button class="btn first" id="btn<%= cnt1%>"
							onclick="unfollow('${follow.followee_id}', <%= cnt1%>, '${sessionScope.loginId.member_id }')">unfollow</button>

					</div>
				</td>

			</tr>
			<%
			cnt1++;
			%>
		</c:forEach>
	</table>




	<script type = "text/javascript" src="/farmocean/resources/js/mypage/followPage.js?"></script>
</body>
</html>