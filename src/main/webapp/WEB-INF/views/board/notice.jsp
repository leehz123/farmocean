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
	    <h2># ���� ���� </h2>
	    
	    <c:choose>
	    	<c:when test="${sessionScope.loginId eq null }">
	    		�α��� �� �̿� �����մϴ�
	    	</c:when>
	    	<c:otherwise>
	    		ID : [${sessionScope.loginId.member_id }] 
	    		�̸� : [${sessionScope.loginId.member_name}]
	    		<a class="btn btn-dark" href="<c:url value="/board/insert"/>" role="button">���</a>
	    		|| [ <a class="btn btn-dark" href="notice_insert" role="button">���� ��������</a>	    			    			    		
	    	</c:otherwise>
	    </c:choose>
	    || [ <button id="ajax-test" class="btn btn-dark">ajax-test</button>  ]
	  </blockquote>
	</figure>
	<table class="table table-dark table-hover">
	  <thead>
	    <tr>
	      <th scope="col">��ȣ</th>
	      <th scope="col">����</th>
	      <th scope="col">�̸�</th>
	      <th scope="col">��¥</th>	      	      
	      <th scope="col">��ȸ</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:forEach items="${boards }" var="board">
	    <tr>
	      <th scope="row"> �� (${board.board_idx })</th>
	      <th scope="row" class="board-title"><a href="<c:url value="/board/view/${board.board_idx }?page=${page }"/>">${board.board_title }</a></th>
	      <td>${board.board_writer }</td>
	      <td>${board.board_in_date } </td>     
	      <td>${board.board_count } </td>
	    </tr>	
	    </c:forEach>	       
	  </tbody>
	</table>
	<nav aria-label="...">
		<ul class="pagination">
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

</body>
</html>