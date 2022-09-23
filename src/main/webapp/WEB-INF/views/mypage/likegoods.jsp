<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>찜한 상품 목록</title>
		<style>
			table { 
				width: 1280px;
			}
			td {
				padding: 20px;
				border: 1px solid #666666;
			}
			th {
				padding: 20px;
				border: 1px solid #666666;
			}
			tr:hover {
					background: rgb(77,77,77);
					color: #fff;
					cursor: pointer
			}
			
			th, td {
  				text-align: center;
			}
			#pageing > li {
            
            float: left;
 			
 			}
		</style>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h3>찜한 상품 목록</h3>
		
		<table id="likegoods" border='1' style = "word-break: break-all">
		
			<tr>
				<th>번호</th>
				<th>찜한 상품</th>
				<th>가격</th>
				<th>판매여부</th>
				<th>찜 취소</th>
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
		
		
<script src="/farmocean/resources/js/mypage/likegoods.js?ver=<%=System.currentTimeMillis() %>"></script>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>