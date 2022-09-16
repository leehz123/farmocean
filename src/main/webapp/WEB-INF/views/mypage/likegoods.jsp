<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>���� ��ǰ ���</title>
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
		</style>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h3>���� ��ǰ ���</h3>
		
		<table id="likegoods" border='1' style = "word-break: break-all">
		
			<tr>
				<th>��ȣ</th>
				<th>���� ��ǰ</th>
				<th>����</th>
				<th>�Ǹſ���</th>
				<th>�� ���</th>
			</tr>
			
		</table>
		
		<nav aria-label="Page navigation example">
  			<ul class="pagination">
    			<li class="page-item"><a class="page-link" href="#">����</a></li>
    			<li id="one" class="page-item"><a class="page-link" href="#">1</a></li>
    			<li id="two" class="page-item"><a class="page-link" href="#">2</a></li>
    			<li class="page-item"><a class="page-link" href="#">3</a></li>
    			<li class="page-item"><a class="page-link" href="#">����</a></li>
  			</ul>
		</nav>
		
		<div id="checkPage" style="display:none">1</div>
		
		
<script src="/farmocean/resources/js/mypage/likegoods.js?ver=<%=System.currentTimeMillis() %>"></script>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>