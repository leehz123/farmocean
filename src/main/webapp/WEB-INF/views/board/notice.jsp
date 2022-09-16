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
<%@ include file="/resources/jspf/body_header.jspf" %>
	<figure class="text-center">
	  <blockquote class="blockquote">
	    <h2># 공지 사항 </h2>
	    
	    <c:choose>
	    	<c:when test="${sessionScope.loginId.member_id eq 'softdol' }">
	    		<a class="btn btn-dark" href="<c:url value="/board/insert"/>" role="button">등록</a>
	    		|| [ <a class="btn btn-dark" href="<c:url value="/board/notice_insert"/>" role="button">공지 가져오기</a>
	    		|| [ <a class="btn btn-dark" href="<c:url value="/admin/main"/>" role="button">관리자 페이지</a>	    
	    	</c:when>
	    	<c:otherwise>	    		
	    					    			    		
	    	</c:otherwise>
	    </c:choose>	    
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
				<!--  <th scope="row" class="board-title"><a href="<c:url value="/board/view/${board.board_idx }?page=${page }"/>">${board.board_title }</a></th> -->
				<th scope="row" class="board-title"><a class="btn btn-outline-primary" data-bs-toggle="collapse" href="#collapseExample${status.count }" >${board.board_title }</a></th>
				<td>${board.board_writer }</td>
				<td>${board.board_in_date } </td>    
				
			</tr>
				
			<tr class="collapse" id="collapseExample${status.count }">
				<td></td>
				<td colspan="4">
					<div class="card card-body">
						${board.board_memo }
					</div>
				</td>
				
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

<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>