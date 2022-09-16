<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<script src="<c:url value="/resources/js/board/ajaxadmin.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/admin/main.css" />" />
<title>sample</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

<%@ include file="/resources/jspf/admin/body_left.jspf" %>
		
			<h1>[�۾���]���� ��� ��� ���</h1>				
			<hr />			
			<form id="frmMainTopBanner" name="frmMainTopBanner" method="post" action="<c:url value="/admin/setMainTopBanner" />" enctype="multipart/form-data">
				<c:choose>
					<c:when test="${maintop.size() == 0}">
						<input type="hidden" name="mainTopIdx" id="mainTopIdx" value=""/>
						<input type="hidden" name="mainTopFileName" id="mainTopFileName" value=""/>
						����̸� : <input type="text" name="mainTopName" id="mainTopName" />
						��ʸ�ũ : <input type="text" name="mainTopLink" id="mainTopLink" value="<c:url value="/" />" />
						����̹��� : <input type="file" name="mainTopImg1" id="mainTopImg1" /> <br />
						<input type="hidden" name="mainTopIdx" id="mainTopIdx" value=""/>
						<input type="hidden" name="mainTopFileName" id="mainTopFileName" value=""/>
						����̸� : <input type="text" name="mainTopName" id="mainTopName" />
						��ʸ�ũ : <input type="text" name="mainTopLink" id="mainTopLink" value="<c:url value="/" />" />
						����̹��� : <input type="file" name="mainTopImg2" id="mainTopImg2" /> <br />
						<input type="hidden" name="mainTopIdx" id="mainTopIdx" value=""/>
						<input type="hidden" name="mainTopFileName" id="mainTopFileName" value=""/>
						����̸� : <input type="text" name="mainTopName" id="mainTopName" />
						��ʸ�ũ : <input type="text" name="mainTopLink" id="mainTopLink" value="<c:url value="/" />" />
						����̹��� : <input type="file" name="mainTopImg3" id="mainTopImg3" />
					</c:when>
					<c:otherwise>
						<c:forEach items="${maintop }" var="topBanner" varStatus="status">						
							<input type="hidden" name="mainTopIdx" id="mainTopIdx" value="${topBanner.idx }"/>
							<input type="hidden" name="mainTopFileName" id="mainTopFileName" value="${topBanner.filename }"/>
							����̸� : <input type="text" name="mainTopName" id="mainTopName" value="${topBanner.title }" />
							��ʸ�ũ : <input type="text" name="mainTopLink" id="mainTopLink" value="${topBanner.link }" />
							����̹��� : <input type="file" name="mainTopImg${status.count }" id="mainTopImg${status.count }" /> <br />
						</c:forEach>
					</c:otherwise>
				</c:choose>
				 <br /> <br />
				
				<button id="btnFrmSubmit">����</button>
				<input type="reset" />
			</form>
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>