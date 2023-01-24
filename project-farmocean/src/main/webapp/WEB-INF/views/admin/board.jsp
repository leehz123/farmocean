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
		
		<figure class="text-center">
		  <blockquote class="blockquote">
		    <h2># 공지 사항 </h2>
		  </blockquote>
		</figure>
		
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col"> </th>
					<th scope="col">제목</th>
					<th scope="col">이름</th>
					<th scope="col">날짜</th>				
				</tr>
			</thead>
			<tbody>	
				<c:forEach items="${boards }" var="board" varStatus="status">
				<tr>
					<th scope="row" style="width:50px;text-align: center;"> ☆ </th>
					<th scope="row" class="board-title"><a href="<c:url value="/admin/boardView/${board.board_idx }?page=${page }"/>">${board.board_title }</a></th>					
					<td>${board.board_writer }</td>
					<td>${board.board_in_date } </td>    
					
				</tr>				
				</c:forEach>	       
			</tbody>
		</table>
	
		<nav aria-label="...">
			<ul class="pagination justify-content-center">
				<!-- 
				<li class="page-item disabled">
				-->
				<li class="page-item">
					<a class="page-link" href="<c:url value="/board/notice/1"/>">Previous</a>
				</li>
				<c:forEach var="i" begin="1" end="${pageLsit}">
					<c:choose>				
					<c:when test="${page eq i}">
					<li class="page-item active" aria-current="page">
					</c:when>	
					<c:otherwise>				
					<li class="page-item">
					</c:otherwise>
					</c:choose>
						<a class="page-link" href="<c:url value="/board/notice/${i }"/>">${i }</a>
					</li>
				</c:forEach>			
		    	<li class="page-item">
		      		<a class="page-link" href="<c:url value="/board/notice/${pageLsit}"/>">Next</a>
		    	</li>
	  		</ul>
		</nav>

			
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>