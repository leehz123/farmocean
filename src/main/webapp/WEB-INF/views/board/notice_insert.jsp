<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<script src="<c:url value="/resources/js/board/ajaxtest.js"/>"></script>
<title>������ ��������</title>
</head>
<body>

	<h1>������ ��������</h1>
	
	<input type="text" id="getUrl" value="https://www.esingsing.co.kr/bbs/board.php?bo_table=notice&wr_id=27" />
	<button id="btnConf" class="btn btn-primary">������ Ȯ��</button>
	<div id="result"></div>
	<input type="text" name="title" id="title" /><br>
	<textarea rows="20" cols="90" name="memo" id="memo"></textarea><br>
	<button id="btnIns" class="btn btn-primary">���</button>


</body>
</html>