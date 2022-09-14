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
<title>sample</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
<%@ include file="/resources/jspf/admin/body_left.jspf" %>
		
			구매자 ID : <input type="text" id="searchProdValue" value="softdol" /> <button id="btnSearchBuy" onclick="fhSearchBuyList()">검색</button>
			 || <button class="btn btn-primary" onClick="fnWinOpen(290, 860, '<c:url value="/buy/prod/2558" />');">상품구매(No.2558) 테스트 팝업</button>
			<hr />
			
			<div id="searchResult">
			</div>			
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>