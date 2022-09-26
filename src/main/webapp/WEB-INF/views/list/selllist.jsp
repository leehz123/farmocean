<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<script src="<c:url value="/resources/js/board/ajaxadmin.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/follow/list.css" />" />
<title>sample</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
		
			
			<div id="searchResult">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">신청일 / 상품명</th>
							<th scope="col">구매자</th>
							<th scope="col">가격</th>
							<th scope="col">우편번호</th>
							<th scope="col">주소</th>
							<th scope="col">상태</th>
							<th scope="col">상태수정</th>
						</tr>
					</thead>
				
					<tbody id="tableAdd">						
						<c:forEach items="${sellList }" var="sellInfo" varStatus="status">
						<tr>
							<td> [${sellInfo.view_regdate }] <br>
								 ${sellInfo.prod_name }</td>
							<td> ${sellInfo.member_nickname }</td>
							<td> ${sellInfo.view_price }원</td>
							<td> ${sellInfo.post_code }</td>
							<td> ${sellInfo.view_address }</td>
							<td>
								<select name="state${status.index }" id="state${status.index }">
									<option value="0"<c:if test="${sellInfo.state eq 0}"> selected </c:if>>신청</option>
									<option value="1"<c:if test="${sellInfo.state eq 1}"> selected </c:if>>접수</option>
									<option value="2"<c:if test="${sellInfo.state eq 2}"> selected </c:if>>배송중</option>
									<option value="3"<c:if test="${sellInfo.state eq 3}"> selected </c:if>>배송확인</option>
									<option value="4"<c:if test="${sellInfo.state eq 4}"> selected </c:if>>반품</option>
									<option value="5"<c:if test="${sellInfo.state eq 5}"> selected </c:if>>취소</option>
									<option value="10"<c:if test="${sellInfo.state eq 10}"> selected </c:if>>판매완료</option>									
								</select>
							</td>
							<td> <button class="btn btn-danger" onclick="fnChgBuyInfo(${sellInfo.buy_idx},'state${status.index}' )">상태수정</button></td>
						</tr>
						</c:forEach>
								  		    
					</tbody>
				</table>			
			</div>
			<nav aria-label="...">
				<ul class="pagination justify-content-center">
					<!-- 
					<li class="page-item disabled">
					-->
					<li class="page-item">
						<c:choose>
						<c:when test="${page eq 1}">
							<a class="page-link" href="<c:url value="/list/selllist/1" />">Previous</a>
						</c:when>
						<c:otherwise>				
							<a class="page-link" href="<c:url value="/list/selllist/${page-1 }" />">Previous</a>
						</c:otherwise>	
						</c:choose>
					</li>
					<c:forEach var="i" begin="1" end="${pageLsit}">
						<c:choose>				
						<c:when test="${page eq i}">
						<li class="page-item active" aria-current="page">
						</c:when>	
						<c:otherwise>				
						<li class="page-item">
						</c:otherwise>
						</c:choose>
							<a class="page-link" href="<c:url value="/list/selllist/${i }" />" >${i }</a>
						</li>
					</c:forEach>			
			    	<li class="page-item">
						<c:choose>
						<c:when test="${page eq pageLsit}">
							<a class="page-link" href="<c:url value="/list/selllist/${page}" />" >Next</a>
						</c:when>
						<c:otherwise>				
							<a class="page-link" href="<c:url value="/list/selllist/${page+1 }" />" >Next</a>
						</c:otherwise>	
						</c:choose>
						</li>
		  		</ul>
			</nav>
  

<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>