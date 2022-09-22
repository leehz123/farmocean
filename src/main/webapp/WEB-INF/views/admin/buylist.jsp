<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<script src="<c:url value="/resources/js/board/ajaxadmin.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/admin/main.css" />" />
<title>sample</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
<%@ include file="/resources/jspf/admin/body_left.jspf" %>
		구매자 ID : <input type="text" id="member_id" name="member_id" value="" />
		<button id="btnSearch" onclick="searchBuyList(1)">검색</button>
		|| <button class="btn btn-primary" onClick="fnWinOpen(570, 375, '<c:url value="/buy/prod/2558" />');">상품구매(No.2558) 테스트 팝업</button>
		<%
			/*			
		<form action="<c:url value="/admin/buylist" />" method="post">
			구매자 ID : <input type="text" id="member_id" name="member_id" value="softdol" />
			<input type="submit" value="검색" /> 
			<button id="btnSearch" onclick="searchBuyList()">검색</button>
			 || <button class="btn btn-primary" onClick="fnWinOpen(290, 860, '<c:url value="/buy/prod/2558" />');">상품구매(No.2558) 테스트 팝업</button>
		</form>
		*/
		%>
			<hr />
			
			<div id="searchResult">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">신청일 / 상품명</th>
							<th scope="col">판매자</th>
							<th scope="col">가격</th>
							<th scope="col">우편번호</th>
							<th scope="col">주소</th>
							<th scope="col">상태</th>
							<th scope="col">완료처리</th>
						</tr>
					</thead>
				
					<tbody id="tableAdd">		
						<%
						/* 						
						<c:forEach items="${buyList }" var="buyInfo" varStatus="status">
						<tr>
							<td> [${buyInfo.view_regdate }] <br>
								 ${buyInfo.prod_name }</td>
							<td> ${buyInfo.member_nickname }(${buyInfo.sell_id })</td>
							<td> ${buyInfo.view_price }원</td>
							<td> ${buyInfo.post_code }</td>
							<td> ${buyInfo.view_address }</td>
							<td>
								<select name="state${status.index }" id="state${status.index }">
									<option value="0"<c:if test="${buyInfo.state eq 0}"> selected </c:if>>신청</option>
									<option value="1"<c:if test="${buyInfo.state eq 1}"> selected </c:if>>접수</option>
									<option value="2"<c:if test="${buyInfo.state eq 2}"> selected </c:if>>배송중</option>
									<option value="3"<c:if test="${buyInfo.state eq 3}"> selected </c:if>>배송확인</option>
									<option value="4"<c:if test="${buyInfo.state eq 4}"> selected </c:if>>반품</option>
									<option value="5"<c:if test="${buyInfo.state eq 5}"> selected </c:if>>취소</option>
									<option value="10"<c:if test="${buyInfo.state eq 10}"> selected </c:if>>판매완료</option>									
								</select>
							</td>
							<td> <button class="btn btn-danger" onclick="fnChgBuyInfo(${buyInfo.buy_idx},'state${status.index}' )">상태수정</button></td>
						</tr>
						</c:forEach>
						*/
						%>  		    
					</tbody>
				</table>			
			</div>		
			<nav aria-label="...">
				<ul class="pagination justify-content-center" id="pageNav">					
					
		  		</ul>
			</nav>	
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>