<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>내가 쓴 후기</title>
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

		<h3>내가 쓴 후기</h3>
		
		<table class="table table-hover" id="myReviewList" border='1'>
		
			<tr>
				<th>번호</th>
				<th>후기 쓴 상품</th>
				<th>후기 내용</th>
				<th>후기 쓴 날짜</th>
				<th>후기 삭제</th>
			</tr>
			
		</table>
		
		<nav aria-label="Page navigation example" >
  			<ul class="pagination justify-content-center" >
    			<li class="page-item"><a class="page-link" href="javascript:history.back();">이전</a></li>
    			<div id="pageing">
    				
    			</div>
    			<li class="page-item"><a class="page-link" href="javascript:history.forward()">다음</a></li>
  			</ul>
		</nav>

		<div id="checkID" style="display:none">${id }</div>
		
<script src="/farmocean/resources/js/mypage/myReviewList.js?ver=<%=System.currentTimeMillis() %>"></script>
<%@ include file="/resources/jspf/body_footer.jspf" %>		
</body>
</html>