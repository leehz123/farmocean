<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>���� ���� ���</title>
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

		<h3>���� ���� ���</h3>
		
		<table id="myCommentList" border='1' style = "word-break: break-all">
		
			<tr>
				<th>��ȣ</th>
				<th>��� �� ��ǰ</th>
				<th>��� ����</th>
				<th>��� ����</th>
				<th>��� �� ��¥</th>
				<th>��� ����</th>
			</tr>
			
		</table>
		
		<div id="checkID" style="display:none">${id }</div>

<script src="/farmocean/resources/js/mypage/myCommentList.js?ver=<%=System.currentTimeMillis() %>"></script>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>