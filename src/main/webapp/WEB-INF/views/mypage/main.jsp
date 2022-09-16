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

		<h3>���� ������</h3> 
		
		<h4>����</h4>
		
		<a href="<c:url value="/mypage/mylist" />">���� ���� ����</a> <br>
		<a href="<c:url value="/mypage/mysendlist" />">���� ���� ����</a> <br>
		<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   href="<c:url value="/mypage/sendMessage" />">���� ������</a> <br>
		   
		<hr>
		
		<h3>������</h3>
		   
		<a href="<c:url value="/mypage/changeimg" />">ȸ�� ������ �̹��� ����</a> <br>
		<a href="<c:url value="/mypage/changeinfo" />">ȸ�� ���� ����</a>
		
		<hr>
		
		<h3>�ȷο�</h3>
		
		<% int cnt = 1; %>
		<c:forEach items="${followee}" var="follow">
			<p id="p<%=cnt%>">
				<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   			href="<c:url value="/Sell/member/${follow.followee_id}" />">${follow.followee_id}</a>
		  		<button id="btn<%= cnt%>" onclick = "unfollow('${follow.followee_id}', <%= cnt%>, '${sessionScope.loginId.member_id }')">�ȷο� ����ϱ�</button>
		  	</p> 
			<% cnt++; %>
		</c:forEach>

		<hr>
		
		<h3>��ǰ �Ǹ� ���</h3>
		
		<a href="<c:url value="/mypage/sellgoods" />">��ǰ �Ǹ� ���</a>
		
		<hr>
		
		<h3>���� ��ǰ ���</h3>
		
		<a href="<c:url value="/mypage/likegoods" />">���� ��ǰ ���</a>
		
		<hr>
		
		<h3>��ǰ �ۼ� ������</h3>
		
		<a href="<c:url value="/product/product_detail_write" />">��ǰ �ۼ� ������</a>

<%@ include file="/resources/jspf/body_footer.jspf" %>
<script type = "text/javascript" src="/farmocean/resources/js/mypage/main.js?"></script>
</body>

</html>