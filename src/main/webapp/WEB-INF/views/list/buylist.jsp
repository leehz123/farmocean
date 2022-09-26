<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<script src="<c:url value="/resources/js/follow/list.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/admin/main.css" />" />
<title>sample</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
	
		<h3>������ ��ǰ</h3>
			<div id="searchResult">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">��û�� / ��ǰ��</th>
							<th scope="col">�Ǹ���</th>
							<th scope="col">����</th>
							<th scope="col" width="80px">�����ȣ</th>
							<th scope="col">�ּ�</th>
							<th scope="col">����</th>
							<th scope="col">�Ϸ�ó��</th>
						</tr>
					</thead>
				
					<tbody id="tableAdd">		
						
												
						<c:forEach items="${buyList }" var="buyInfo" varStatus="status">
						<tr>
							<td> [${buyInfo.view_regdate }] <br>
								 ${buyInfo.prod_name }</td>
							<td> ${buyInfo.member_nickname }(${buyInfo.sell_id })</td>
							<td> ${buyInfo.view_price }��</td>
							<td> ${buyInfo.post_code }</td>
							<td> ${buyInfo.view_address }</td>
							<td>
							
								<c:if test="${buyInfo.state eq 0}">��û</c:if>
								<c:if test="${buyInfo.state eq 1}">����</c:if>
								<c:if test="${buyInfo.state eq 2}">�����</c:if>
								<c:if test="${buyInfo.state eq 3}">���Ȯ��</c:if>
								<c:if test="${buyInfo.state eq 4}">��ǰ</c:if>
								<c:if test="${buyInfo.state eq 5}">���</c:if>
								<c:if test="${buyInfo.state eq 10}">�ǸſϷ�</c:if>

							</td>
							<td> <button class="btn btn-danger" onclick="fnChgBuyInfo(${buyInfo.buy_idx},3)">���Ȯ��</button></td>
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
							<a class="page-link" href="<c:url value="/list/buylist/1" />">Previous</a>
						</c:when>
						<c:otherwise>				
							<a class="page-link" href="<c:url value="/list/buylist/${page-1 }" />">Previous</a>
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
							<a class="page-link" href="<c:url value="/list/buylist/${i }" />" >${i }</a>
						</li>
					</c:forEach>
						<li class="page-item">
						<c:choose>
						<c:when test="${page eq pageLsit}">
							<a class="page-link" href="<c:url value="/list/buylist/${page}" />" >Next</a>
						</c:when>
						<c:otherwise>				
							<a class="page-link" href="<c:url value="/list/buylist/${page+1 }" />" >Next</a>
						</c:otherwise>	
						</c:choose>
						</li>
		  		</ul>
			</nav>

<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>