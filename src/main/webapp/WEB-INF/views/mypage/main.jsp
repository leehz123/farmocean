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
					<a id="follow" style="cursor:pointer" onClick="fnWinOpen(460, 600, '<c:url value="/mypage/followPage" />'); return false;" />�ȷο�</a>
				</div>
				<div>�ȷο�</div>
			</div>
			<div class="item">
				<div class="number">${ID.getMember_point() }</div>
				<div>����Ʈ</div>
			</div>
		</div>
		
		<hr>
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
		<div class="listContainer SMN_effect-56">
		
			<c:if test="${ID.getMember_type() eq 'S'}">
 
			<a id="choose" href= "<c:url value="/mypage/sellgoods/1" />" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="<c:url value="/mypage/sellgoods/1" />">
					��ǰ���<span class="right"></span>
				</div>
				<div class="right">></div>
			</a> 
			
		
			
			<a id="choose" href= "<c:url value="/product/product_detail_write" />" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="<c:url value="/product/product_detail_write" />">��ǰ���</div>
				<div class="right">></div>
			</a> 
			
			
 
			</c:if>
		
			<a id="choose" href= "<c:url value="/list/buylist" />" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="<c:url value="/list/buylist" />">������ ��ǰ</div>
				<div class="right">></div>
			</a> 
			
			
			
			<a id="choose" href= "<c:url value="/mypage/likegoods/1" />"  class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="<c:url value="/mypage/likegoods/1" />">���� ��ǰ</div>
				<div class="right">></div>
			</a>
			
	
			
			<a id="choose" href="#" class="item">
				<div class="icon">ii</div>
				<div class="text">�������</div>
				<div class="right">></div>
			</a> 
		</div>
		
		<hr>
		
		<div class="listContainer SMN_effect-56" > 
			<a id="choose" href= "<c:url value="/mypage/changeinfo" />" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="<c:url value="/mypage/changeinfo" />">ȸ����������</div>
				<div class="right">></div>
			</a> 
			
			<a id="choose" onClick="fnWinOpen(500, 600, '<c:url value="/mypage/changeimg" />'); return false;"  class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="<c:url value="/mypage/changeimg" />">�������̹�������</div>	
				<div class="right">></div>
			</a>
			
			<%
      		LoginMember a = (LoginMember)session.getAttribute("loginId");
      		if(a.getMember_pw()!="Qzd8ySNO2Yi7TH72d13hkth9EZfRifQ7DD1RttsD8Fo="){%>
      		
			<a id="choose" href= "<c:url value="/member/pwChange" />"  class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="<c:url value="/member/pwChange" />">��й�ȣ ����</div>
				<div class="right">></div>
			</a>         
      		<%}
      		%>
		</div>
		
		<hr>
		
		<div class="listContainer SMN_effect-56"  > 
			<a id="choose" href= "<c:url value="/mypage/myCommentList" />" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="<c:url value="/mypage/myCommentList" />">���� ���� ���</div>
				<div class="right">></div>
			</a> 
			<a id="choose" href= "<c:url value="/mypage/myReview" />" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="<c:url value="/mypage/myReview" />">���� ���� �ı�</div>
				<div class="right">></div>
			</a> 
			
		</div>
		
		<hr>
		
		<div class="infoContainer SMN_effect-56" >
			<a id="choose" href="<c:url value="/board/notice/1" />" class="item">
				<i class="fa-solid fa-bullhorn"></i>
				<div>��������</div>
			</a> 
			<a id="choose" style="cursor:pointer" onClick="fnWinOpen(1000, 420, '<c:url value="/mypage/mylist" />'); return false;" class="item">
				<i class="fa-solid fa-envelope"></i>
				<div>����</div>
			</a> 
			<a class="item">
				<i class="fa-solid fa-headset"></i>
				<div>������</div>
			</a>
		</div>
	</div>
	
	</c:forEach>

	<hr>
	
		<!-- 

		<h3>���� ������</h3> 
		 
		<h4>����</h4>
		
		<a href="<c:url value="/mypage/mylist" />">���� ���� ����</a> <br>
		<a href="<c:url value="/mypage/mysendlist" />">���� ���� ����</a> <br>
		<a onClick="window.open(this.href, '', 'width=1000, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   href="<c:url value="/mypage/mylist" />">����</a> <br>
		   
		
		   
		<a onClick="fnWinOpen(1000, 600, '<c:url value="/mypage/mylist" />'); return false;" >����</a> <br>   
		   
		-->
		<!-- 
		
		<h3>������</h3>
		   
		<a href="<c:url value="/mypage/changeimg" />">ȸ�� ������ �̹��� ����</a> <br>
		<a href="<c:url value="/mypage/changeinfo" />">ȸ�� ���� ����</a> <br>


		<hr>
		
		 -->
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
<script src="https://kit.fontawesome.com/2fa1db4389.js" crossorigin="anonymous"></script>
</body>

</html>