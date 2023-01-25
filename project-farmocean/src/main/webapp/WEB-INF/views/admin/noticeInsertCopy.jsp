<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<%@ include file="/resources/jspf/csboard.jspf" %>
<script src="<c:url value="/resources/js/board/ajaxtest.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/admin/main.css" />" />
<title>공지글 가져오기</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
<%@ include file="/resources/jspf/admin/body_left.jspf" %>
		
		<h1>공지글 가져오기</h1>
	
			<input type="text" id="getUrl" value="https://www.esingsing.co.kr/bbs/board.php?bo_table=notice&wr_id=27" />
			<button id="btnConf" class="btn btn-primary">데이터 확인</button>
			<div id="result"></div>
			<input type="text" name="title" id="title" /><br>
			<textarea rows="20" cols="90" name="memo" id="memo"></textarea><br>
			<button id="btnIns" class="btn btn-primary">등록</button>
			
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>