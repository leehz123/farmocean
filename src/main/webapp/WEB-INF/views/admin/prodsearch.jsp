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
<title>상품조회</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
<%@ include file="/resources/jspf/admin/body_left.jspf" %>
		
			<h1>판매상품 조회(판매자, 상품번호, 상품명)</h1>
			<hr />
			<select id="searchProd">
				<option value="M">판매자</option>	
				<option value="N">상품번호</option>
				<option value="P">상품명</option>
			</select>
			<input type="text" id="searchProdValue" value="sample5" /> <button id="btnSearchProd" onclick="searchProd()">검색</button>
			<hr />
			<div id="resultProd">
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">상품번호</th>
			      <th scope="col">상품명</th>
			      <th scope="col">판매자</th>
			      <th scope="col">판매여부</th>
			    </tr>
			  </thead>
			  <tbody id="tableAdd">
			  	<!-- 양식 -->
			  	<!-- 
			    <tr>
			      <th scope="row">1</th>
			      <td>Mark</td>
			      <td>Otto</td>
			      <td>@mdo</td>
			    </tr>
			     -->
			    <!-- 양식 -->			    
			  </tbody>
			</table>
					
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>