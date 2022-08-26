<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session ="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<%@ include file="/resources/jspf/csboard.jspf" %>
<title>sample</title>
</head>
<body>
	<figure class="text-center">
	  <blockquote class="blockquote">
	    <h2># 공지 사항 </h2>
	    
	    <c:choose>
	    	<c:when test="${sessionScope.loginId eq null }">
	    		로그인 후 이용 가능합니다
	    	</c:when>
	    	<c:otherwise>
	    		ID : [${sessionScope.loginId.member_id }] 
	    		이름 : [${sessionScope.loginId.member_name}]
	    		<a class="btn btn-dark" href="insert" role="button">등록</a>
	    		|| [ <a class="btn btn-dark" href="notice_insert" role="button">공지 가져오기</a>	    			    			    		
	    	</c:otherwise>
	    </c:choose>
	    || [ <button id="ajax-test" class="btn btn-dark">ajax-test</button>  ]
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