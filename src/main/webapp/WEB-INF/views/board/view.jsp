<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<%@ include file="/resources/jspf/csboard.jspf" %>
<title>���� ����</title>
</head>
<body>

<h1>[${board.board_title }]</h1><a class="btn btn-dark" href="../notice" role="button">��Ϻ���</a>
<hr />
[�Խ��� ���� : ${board.board_cate }]
[�ۼ��� : ${board.board_writer }]  
[��� ��] 
[��ȸ�� : ${board.board_count }] 
[�ۼ��� : ${board.board_in_date }]<br />
<hr />
<pre>
[����]
${board.board_memo }
	
</pre>

<hr />
��� ����
<hr />

<hr />
���ñ� ���
<hr />

<!-- 
<div class="view-padding">
	<div class="card" >
	  <div class="card-body">
	    <h4 class="card-title">���� : ${pizza.name }</h4><hr />
	    <h4 class="card-title">price : ${pizza.price }</h4><hr />
	    <h4 class="card-title">calories : ${pizza.calories } calories</h4><hr />
	    
    	<button id="btnReWrite" onclick="fnConfirm('����','mod?id=${pizza.id }');" type="button" class="btn btn-primary">����</button>
    	<button id="btnReWrite" onclick="fnConfirm('����','del?id=${pizza.id }');" type="button" class="btn btn-danger">����</button>
	    
	    <a class="btn btn-dark" href="notice" role="button">��Ϻ���</a>
	    </div>
	</div>
</div>
 -->
</body>
</html>