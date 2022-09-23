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
 
  			<div class="grade">판매자</div>
 
			</c:if>
 
			<c:if test="${ID.getMember_type() eq 'B'}">
 
  			<div class="grade">구매자</div>
 
			</c:if>				
				
				<div class="name">${ID.getMember_id()}</div>
			</div>
			<div class="modify"></div>
		</div>
		<div class="summaryContainer">
			<div class="item">
				<div class="number">${ID.getMember_nickName()}</div>
				<div>닉네임</div>
			</div>
			<div class="item">
				<div class="number">
					<a onClick="window.open(this.href, '','width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   			href="<c:url value="/mypage/followPage" />">팔로우</a>
				</div>
				<div>팔로우</div>
			</div>
			<div class="item">
				<div class="number">${ID.getMember_point() }</div>
				<div>포인트</div>
			</div>
		</div>
		<!-- 
		<div class="shippingStatusContainer">
			<div class="title">주문/배송조회</div>
			<div class="status">

				<div class="item">
					<div>
						<div class="green number">6</div>
						<div class="text">장바구니</div>
					</div>
					<div class="icon">></div>
				</div>
				<div class="item">
					<div>
						<div class="number">0</div>
						<div class="text">결제완료</div>
					</div>
					<div class="icon">></div>
				</div>
				<div class="item">
					<div>
						<div class="green number">1</div>
						<div class="text">배송중</div>
					</div>
					<div class="icon">></div>
				</div>
				<div class="item">
					<div>
						<div class="green number">3</div>
						<div class="text">구매확정</div>
					</div>
				</div>

			</div>

		</div>
		 -->
		<div class="listContainer">
			<a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="location.href='http://localhost:8888/farmocean/mypage/sellgoods/1';">
					상품목록<span class="right"></span>
				</div>
				<div class="right">></div>
			</a> <a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="location.href='http://localhost:8888/farmocean/product/product_detail_write';">상품등록</div>
				<div class="right">></div>
			</a> <a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text">상품문의</div>
				<div class="right">></div>
			</a> <a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text">단골상점</div>
				<div class="right">></div>
			</a> <a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="location.href='http://localhost:8888/farmocean/mypage/likegoods/1';">찜한 상품</div>
				<div class="right">></div>
			</a>
		</div>
		<div class="listContainer">
			<a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text">
					<span>내지갑</span> <span class="smallLight"> <span>|</span> <span>보유
							적립금</span>
					</span>
				</div>
				<div class="right">
					<span class="blct">175 BLCT</span> >
				</div>
			</a> <a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="location.href='http://localhost:8888/farmocean/mypage/myCommentList';">내가 남긴 댓글</div>
				<div class="right">></div>
			</a> <a href="#" class="item">
				<div class="icon">ii</div>
				<div class="text" onclick="location.href='http://localhost:8888/farmocean/mypage/myReview';">내가 남긴 후기</div>
				<div class="right">></div>
			</a>
		</div>
		<div class="infoContainer">
			<a href="#" class="item">
				<div>icon</div>
				<div>공지사항</div>
			</a> <a href="#" class="item">
				<div>icos</div>
				<div>이용안내</div>
			</a> <a href="#" class="item">
				<div>icon</div>
				<div>고객센터</div>
			</a>
		</div>
	</div>
	
	</c:forEach>

	<hr>

		<h3>마이 페이지</h3> 
		 
		<h4>쪽지</h4>
		
		<a href="<c:url value="/mypage/mylist" />">내가 받은 쪽지</a> <br>
		<a href="<c:url value="/mypage/mysendlist" />">내가 보낸 쪽지</a> <br>
		<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   href="<c:url value="/mypage/sendMessage" />">쪽지 보내기</a> <br>
		   
		<hr>
		
		<h3>프로필</h3>
		   
		<a href="<c:url value="/mypage/changeimg" />">회원 프로필 이미지 변경</a> <br>
		<a href="<c:url value="/mypage/changeinfo" />">회원 정보 수정</a> <br>
		<%
      	LoginMember a = (LoginMember)session.getAttribute("loginId");
      	if(a.getMember_pw()!="Qzd8ySNO2Yi7TH72d13hkth9EZfRifQ7DD1RttsD8Fo="){%>
      	<a href="<c:url value="/member/pwChange" />">비밀번호 변경</a>         
      	<%}
      	%>

		<hr>
		<!--
		<h3>팔로우</h3>
		
		<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   href="<c:url value="/mypage/followPage" />">팔로우 리스트</a>
		
		<hr>
		-->
		<!-- 
		<h3>상품 판매 목록</h3>
		
		<a href="<c:url value="/mypage/sellgoods/1" />">상품 판매 목록</a>
		
		<hr>
		-->
		<!-- 
		<h3>찜한 상품 목록</h3>
		
		<a href="<c:url value="/mypage/likegoods/1" />">찜한 상품</a>
		
		<hr>
		 -->
		<!--
		<h3>상품 작성 페이지</h3>
		
		<a href="<c:url value="/product/product_detail_write" />">상품 작성 페이지</a>
		
		<hr>
		 -->
		 <!--
		<h3>내가 남긴 댓글</h3>
		
		<a href="<c:url value="/mypage/myCommentList" />">내가 남긴 댓글</a>
		
		<hr>
		
		<h3>내가 남긴 후기</h3>
		
		<a href="<c:url value="/mypage/myReview" />">내가 남긴 후기</a>
		-->
<%@ include file="/resources/jspf/body_footer.jspf" %>
<script type = "text/javascript" src="/farmocean/resources/js/mypage/main.js?"></script>
</body>

</html>