<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>상품 판매 목록</title>
		<style>
			table { 
				width: 100%;
			}
			th {
				max-width: 200px;
			}
			td {
				max-width: 200px;
			}
			#pageing > li {
            
            float: left;
 			
 			}
		</style>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h3>내가 올린 상품</h3>
		
				<table class="table table-hover" id="sellgoods" border='1'>
		
			<tr>
				<th>번호</th>
				<th>판매 상품</th>
				<th>가격</th>
				<th>판매여부</th>
				<th>판매상품수정</th>
				<th>숨김여부</th>
				<th>상품삭제</th>
			</tr>
			
		</table>
		
		<nav aria-label="Page navigation example">
  			<ul class="pagination">
    			<li class="page-item"><a class="page-link" href="javascript:history.back();">이전</a></li>
    			<div id="pageing">
    				
    			</div>
    			<li class="page-item"><a class="page-link" href="javascript:history.forward()">다음</a></li>
  			</ul>
		</nav>
		

			<div id="iPage" style="display:none;">${iPages }</div>


<script src="/farmocean/resources/js/mypage/sellgoods.js?ver=<%=System.currentTimeMillis() %>"></script>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>