<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>Main</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h3>마이 페이지</h3> 
		
		<h4>쪽지</h4>
		
		<a href="<c:url value="/mypage/mylist" />">내가 받은 쪽지</a> <br>
		<a href="<c:url value="/mypage/mysendlist" />">내가 보낸 쪽지</a> <br>
		<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   href="<c:url value="/mypage/sendMessage" />">쪽지 보내기</a> <br>
		   
		<hr>
		
		<h3>프로필</h3>
		   
		<a href="<c:url value="/mypage/changeimg" />">회원 프로필 이미지 변경</a> <br>
		<a href="<c:url value="/mypage/changeinfo" />">회원 정보 수정</a>
		
		<hr>
		
		<h3>팔로우</h3>
		
		<% int cnt = 1; %>
		<c:forEach items="${followee}" var="follow">
			<p id="p<%=cnt%>">
				<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   			href="<c:url value="/Sell/member/${follow.followee_id}" />">${follow.followee_id}</a>
		  		<button id="btn<%= cnt%>" onclick = "unfollow('${follow.followee_id}', <%= cnt%>, '${sessionScope.loginId.member_id }')">팔로우 취소하기</button>
		  	</p> 
			<% cnt++; %>
		</c:forEach>

		<hr>
		
		<h3>상품 판매 목록</h3>
		
		<a href="<c:url value="/mypage/sellgoods" />">상품 판매 목록</a>
		
		<hr>
		
		<h3>찜한 상품 목록</h3>
		
		<a href="<c:url value="/mypage/likegoods" />">찜한 상품 목록</a>
		
		<hr>
		
		<h3>상품 작성 페이지</h3>
		
		<a href="<c:url value="/product/product_detail_write" />">상품 작성 페이지</a>

<%@ include file="/resources/jspf/body_footer.jspf" %>
<script type = "text/javascript" src="/farmocean/resources/js/mypage/main.js?"></script>
</body>

</html>