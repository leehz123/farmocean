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
<title>공지 등록</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
<%@ include file="/resources/jspf/admin/body_left.jspf" %>
		
		<h1>[${board.board_title }][${linkName }]</h1>
		<a class="btn btn-dark" href="<c:url value="/admin/board/3/${page }" />" role="button">목록보기</a>
		<button id="btnReWrite" onclick="fnConfirmDel('${board.board_idx }');" type="button" class="btn btn-danger">삭제</button>
		<hr />
		[게시판 종류 : ${board.board_cate }]
		[작성자 : ${board.board_writer }]  
		[댓글 수] 
		[조회수 : ${board.board_count }] 
		[작성일 : ${board.board_in_date }]<br />
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