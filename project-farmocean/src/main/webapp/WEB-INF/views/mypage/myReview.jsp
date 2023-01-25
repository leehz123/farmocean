<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>���� �� �ı�</title>
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

		<h3>���� �� �ı�</h3>
		
		<table class="table table-hover" id="myReviewList" border='1'>
		
			<tr>
				<th>��ȣ</th>
				<th>�ı� �� ��ǰ</th>
				<th>�ı� ����</th>
				<th>�ı� �� ��¥</th>
				<th>�ı� ����</th>
			</tr>
			
		</table>
		
		<nav aria-label="Page navigation example" >
  			<ul class="pagination justify-content-center" >
    			<li class="page-item"><a class="page-link" href="javascript:history.back();">����</a></li>
    			<div id="pageing">
    				
    			</div>
    			<li class="page-item"><a class="page-link" href="javascript:history.forward()">����</a></li>
  			</ul>
		</nav>

		<div id="checkID" style="display:none">${id }</div>
		
<script src="/farmocean/resources/js/mypage/myReviewList.js?ver=<%=System.currentTimeMillis() %>"></script>
<%@ include file="/resources/jspf/body_footer.jspf" %>		
</body>
</html>