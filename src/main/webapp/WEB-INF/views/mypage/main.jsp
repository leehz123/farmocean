<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<link rel="stylesheet" href="${path}/resources/css/mypage/main.css?ver=<%=System.currentTimeMillis() %>">
<title>Main</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

	<c:forEach items="${myMembers }" var="ID">
					 
	<div class="wrap">
		<div class="greenContainer">
			<div>
			
			<c:if test="${ID.getMember_type() eq 'S'}">
 
  			<div class="grade">�Ǹ���</div>
 
			</c:if>
 
			<c:if test="${ID.getMember_type() eq 'B'}">
 
  			<div class="grade">������</div>
 
			</c:if>				
				
				<div class="name">${ID.getMember_id()}</div>
			</div>
			<div class="modify"></div>
		</div>
		<div class="summaryContainer">
			<div class="item">
				<div class="number">${ID.getMember_nickName()}</div>
				<div>�г���</div>
			</div>
			<div class="item">
				<div class="number">
					<a onClick="window.open(this.href, '','width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   			href="<c:url value="/mypage/followPage" />">�ȷο�</a>
				</div>
				<div>�ȷο�</div>
			</div>
			<div class="item">
				<div class="number">${ID.getMember_point() }</div>
				<div>����Ʈ</div>
			</div>
		</div>
		<!-- 
		<div class="shippingStatusContainer">
			<div class="title">�ֹ�/�����ȸ</div>
			<div class="status">

				<div class="item">
					<div>
						<div class="green number">6</div>
						<div class="text">��ٱ���</div>
					</div>
					<div class="icon">></div>
				</div>
				<div class="item">
					<div>
						<div class="number">0</div>
						<div class="text">�����Ϸ�</div>
					</div>
					<div class="icon">></div>
				</div>
				<div class="item">
					<div>
						<div class="green number">1</div>
						<div class="text">�����</div>
					</div>
					<div class="icon">></div>
				</div>
				<div class="item">
					<div>
						<div class="green number">3</div>
						<div class="text">����Ȯ��</div>
					</div>
				</div>

			</div>

		</div>
		 -->
		<div class="listContainer">
			<a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="location.href='http://localhost:8888/farmocean/mypage/sellgoods/1';">
					��ǰ���<span class="right"></span>
				</div>
				<div class="right">></div>
			</a> <a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="location.href='http://localhost:8888/farmocean/product/product_detail_write';">��ǰ���</div>
				<div class="right">></div>
			</a> <a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text">��ǰ����</div>
				<div class="right">></div>
			</a> <a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text">�ܰ����</div>
				<div class="right">></div>
			</a> <a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="location.href='http://localhost:8888/farmocean/mypage/likegoods/1';">���� ��ǰ</div>
				<div class="right">></div>
			</a>
		</div>
		<div class="listContainer">
			<a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text">
					<span>������</span> <span class="smallLight"> <span>|</span> <span>����
							������</span>
					</span>
				</div>
				<div class="right">
					<span class="blct">175 BLCT</span> >
				</div>
			</a> <a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="location.href='http://localhost:8888/farmocean/mypage/myCommentList';">���� ���� ���</div>
				<div class="right">></div>
			</a> <a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="location.href='http://localhost:8888/farmocean/mypage/myReview';">���� ���� �ı�</div>
				<div class="right">></div>
			</a>
		</div>
		<div class="infoContainer">
			<a href="#" class="item">
				<div>icon</div>
				<div>��������</div>
			</a> <a href="#" class="item">
				<div>icos</div>
				<div>�̿�ȳ�</div>
			</a> <a href="#" class="item">
				<div>icon</div>
				<div>������</div>
			</a>
		</div>
	</div>
	
	</c:forEach>

	<hr>

		<h3>���� ������</h3> 
		 
		<h4>����</h4>
		
		<a href="<c:url value="/mypage/mylist" />">���� ���� ����</a> <br>
		<a href="<c:url value="/mypage/mysendlist" />">���� ���� ����</a> <br>
		<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   href="<c:url value="/mypage/sendMessage" />">���� ������</a> <br>
		   
		<hr>
		
		<h3>������</h3>
		   
		<a href="<c:url value="/mypage/changeimg" />">ȸ�� ������ �̹��� ����</a> <br>
		<a href="<c:url value="/mypage/changeinfo" />">ȸ�� ���� ����</a> <br>
		<%
      	LoginMember a = (LoginMember)session.getAttribute("loginId");
      	if(a.getMember_pw()!="Qzd8ySNO2Yi7TH72d13hkth9EZfRifQ7DD1RttsD8Fo="){%>
      	<a href="<c:url value="/member/pwChange" />">��й�ȣ ����</a>         
      	<%}
      	%>

		<hr>
		<!--
		<h3>�ȷο�</h3>
		
		<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   href="<c:url value="/mypage/followPage" />">�ȷο� ����Ʈ</a>
		
		<hr>
		-->
		<!-- 
		<h3>��ǰ �Ǹ� ���</h3>
		
		<a href="<c:url value="/mypage/sellgoods/1" />">��ǰ �Ǹ� ���</a>
		
		<hr>
		-->
		<!-- 
		<h3>���� ��ǰ ���</h3>
		
		<a href="<c:url value="/mypage/likegoods/1" />">���� ��ǰ</a>
		
		<hr>
		 -->
		<!--
		<h3>��ǰ �ۼ� ������</h3>
		
		<a href="<c:url value="/product/product_detail_write" />">��ǰ �ۼ� ������</a>
		
		<hr>
		 -->
		 <!--
		<h3>���� ���� ���</h3>
		
		<a href="<c:url value="/mypage/myCommentList" />">���� ���� ���</a>
		
		<hr>
		
		<h3>���� ���� �ı�</h3>
		
		<a href="<c:url value="/mypage/myReview" />">���� ���� �ı�</a>
		-->
<%@ include file="/resources/jspf/body_footer.jspf" %>
<script type = "text/javascript" src="/farmocean/resources/js/mypage/main.js?"></script>
</body>

</html>