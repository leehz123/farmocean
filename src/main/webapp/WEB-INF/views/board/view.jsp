<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<%@ include file="/resources/jspf/csboard.jspf" %>
<title>공지 사항</title>
</head>
<body>

<h1>[${board.board_title }]</h1><a class="btn btn-dark" href="../notice" role="button">목록보기</a>
<hr />
[게시판 종류 : ${board.board_cate }]
[작성자 : ${board.board_writer }]  
[댓글 수] 
[조회수 : ${board.board_count }] 
[작성일 : ${board.board_in_date }]<br />
<hr />
<pre>
[내용]
${board.board_memo }
	
</pre>

<hr />
댓글 영역
<hr />

<hr />
개시글 목록
<hr />

<!-- 
<div class="view-padding">
	<div class="card" >
	  <div class="card-body">
	    <h4 class="card-title">제목 : ${pizza.name }</h4><hr />
	    <h4 class="card-title">price : ${pizza.price }</h4><hr />
	    <h4 class="card-title">calories : ${pizza.calories } calories</h4><hr />
	    
    	<button id="btnReWrite" onclick="fnConfirm('수정','mod?id=${pizza.id }');" type="button" class="btn btn-primary">수정</button>
    	<button id="btnReWrite" onclick="fnConfirm('삭제','del?id=${pizza.id }');" type="button" class="btn btn-danger">삭제</button>
	    
	    <a class="btn btn-dark" href="notice" role="button">목록보기</a>
	    </div>
	</div>
</div>
 -->
</body>
</html>