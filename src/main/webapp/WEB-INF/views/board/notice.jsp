<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>sample</title>
</head>
<body>

	<figure class="text-center">
	  <blockquote class="blockquote">
	    <h2># 공지 사항</h2>
	    <a class="btn btn-dark" href="insert" role="button">등록</a>	    	
	  </blockquote>
	</figure>
	<table class="table table-dark table-hover">
	  <thead>
	    <tr>
	      <th scope="col">번호</th>
	      <th scope="col">제목</th>
	      <th scope="col">이름</th>
	      <th scope="col">날짜</th>	      	      
	      <th scope="col">조회</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:forEach items="${boards }" var="board">
	    <tr>
	      <th scope="row"> ☆ (${board.board_idx })</th>
	      <th scope="row" class="board-title"><a href="view/${board.board_idx }">${board.board_title }</a></th>
	      <td>${board.board_writer }</td>
	      <td>${board.board_in_date } </td>     
	      <td>${board.board_count } </td>
	    </tr>	
	    </c:forEach>    
	  </tbody>
	</table>
	
</body>
</html>