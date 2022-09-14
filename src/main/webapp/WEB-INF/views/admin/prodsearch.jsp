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
<title>��ǰ��ȸ</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
<%@ include file="/resources/jspf/admin/body_left.jspf" %>
		
			<h1>�ǸŻ�ǰ ��ȸ(�Ǹ���, ��ǰ��ȣ, ��ǰ��)</h1>
			<hr />
			<select id="searchProd">
				<option value="M">�Ǹ���</option>	
				<option value="N">��ǰ��ȣ</option>
				<option value="P">��ǰ��</option>
			</select>
			<input type="text" id="searchProdValue" value="sample5" /> <button id="btnSearchProd" onclick="searchProd()">�˻�</button>
			<hr />
			<div id="resultProd">
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">��ǰ��ȣ</th>
			      <th scope="col">��ǰ��</th>
			      <th scope="col">�Ǹ���</th>
			      <th scope="col">�Ǹſ���</th>
			    </tr>
			  </thead>
			  <tbody id="tableAdd">
			  	<!-- ��� -->
			  	<!-- 
			    <tr>
			      <th scope="row">1</th>
			      <td>Mark</td>
			      <td>Otto</td>
			      <td>@mdo</td>
			    </tr>
			     -->
			    <!-- ��� -->			    
			  </tbody>
			</table>
					
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>