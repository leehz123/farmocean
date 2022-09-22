<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<%@ include file="/resources/jspf/csboard.jspf" %>
<script src="<c:url value="/resources/js/board/ajaxadmin.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/admin/main.css" />" />
<title>���� ���</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
<%@ include file="/resources/jspf/admin/body_left.jspf" %>
		
		<h1>[${board.board_title }][${linkName }]</h1>
		<a class="btn btn-dark" href="<c:url value="/admin/board/3/${page }" />" role="button">��Ϻ���</a>
		<button id="btnReWrite" onclick="fnConfirmDel('${board.board_idx }');" type="button" class="btn btn-danger">����</button>
		<hr />
		[�Խ��� ���� : ${board.board_cate }]
		[�ۼ��� : ${board.board_writer }]  
		[��� ��] 
		[��ȸ�� : ${board.board_count }] 
		[�ۼ��� : ${board.board_in_date }]<br />
		<hr />
		<div>
		<div>
		${board.board_memo }
		</div>
		
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>