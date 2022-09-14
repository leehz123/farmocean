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
<title>판매자 검색</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
<div class="container">
	<div class="row">
		<%@ include file="/resources/jspf/admin/body_left.jspf" %>
		
		<div class="col-md-9" id="adminBody">
			<select id="searchMember">
				<option value="I">ID</option>	
				<option value="N">닉네임</option>
			</select>
			<input type="text" id="searchMemberValue" /> <button id="btnSearchMember" onclick="searchMember()">검색</button>			
			<hr />			
			<div id="resultMember"></div>
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>