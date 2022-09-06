<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/resources/css/mainpage/prod_reg/goodsManage.css" />
</head>
<body>

				<%@ include file="../includes/prod_reg/header.jsp" %>
				
				<div class="prod_content_wrap">
						<div class="prod_content_subject"><sapn>��ǰ ����</sapn></div>
						
						<div class="goods_table_wrap">
                    	<!-- ��ǰ ����Ʈ O -->
	                    <c:if test="${listcheck != 'empty'}">
	                    	<table class="goods_table">
	                    		<thead>
	                    			<tr>
										<td class="th_column_1">��ǰ ��ȣ</td>
	                    				<td class="th_column_2">��ǰ �̸�</td>
	                    				
	                    				<td class="th_column_4">��ǰ ����</td>
	                     				
	                    				<td class="th_column_5">���</td>
	                    				<td class="th_column_6">�Ǹ� ����</td>
	                    				<td class="th_column_7">�Ǹ� ����</td>
	                    			</tr>
	                    		</thead>	
	                    		<c:forEach items="${list}" var="list">
	                    		<tr>
	                    			<td><c:out value="${list.prod_idx}"></c:out></td>
	                    			<td><c:out value="${list.prod_name}"></c:out></td>
	     
	                    			<td><c:out value="${list.prod_price}"></c:out></td>
	                    			
	                    			<td><c:out value="${list.prod_stock}"></c:out></td>
	                    			<td><c:out value="${list.prod_sell}"></c:out></td>
	                    			<td><fmt:formatDate value="${list.prod_sell_deadline}" pattern="yyyy/MM/dd"/></td>
	                    		</tr>
	                    		</c:forEach>
	                    	</table>
	                    </c:if>
	                    <!-- ��ǰ ����Ʈ X -->
                		<c:if test="${listCheck == 'empty'}">
                			<div class="table_empty">
                				��ϵ� ��ǰ�� �����ϴ�.
                			</div>
                		</c:if> 
                	</div>
                	
                	<!-- �˻� ���� -->
                	<div class="search_wrap">
                		<form id="searchForm" action="${path }/mainpage/prod_reg/goodsManage" method="get">
                			<div class="search_input">
                    			<input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
                    			<input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum }"></c:out>'>
                    			<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
                    			<input type="hidden" name="type" value="G">
                    			<button class='btn search_btn'>�� ��</button>                				
                			</div>
                		</form>
                	</div>
                	
                	<!-- ������ �̸� �������̽� ���� -->
                	<div class="pageMaker_wrap">
                		<ul class="pageMaker">
                			
                			<!-- ���� ��ư -->
                			<c:if test="${pageMaker.prev }">
                				<li class="pageMaker_btn prev">
                					<a href="${pageMaker.pageStart -1}">����</a>
                				</li>
                			</c:if>
                			
                			<!-- ������ ��ȣ -->
                			<c:forEach begin="${pageMaker.pageStart }" end="${pageMaker.pageEnd }" var="num">
                				<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? 'active':''}">
                					<a href="${num}">${num}</a>
                				</li>	
                			</c:forEach>
                		
	                    	<!-- ���� ��ư -->
	                    	<c:if test="${pageMaker.next}">
	                    		<li class="pageMaker_btn next">
	                    			<a href="${pageMaker.pageEnd + 1 }">����</a>
	                    		</li>
	                    	</c:if>
	                    </ul>
                	</div>
                	
                	<form id="moveForm" action="${path }/admin/goodsManage" method="get" >
 						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
						<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
                	</form>
				</div>
				
				<%@ include file="../includes/prod_reg/footer.jsp" %>
				
	
	<script>
		$(document).ready(function() {
			let eResult = '<c:out value="${enroll_result}" />';
			
			checkResult(eResult);
			
			function checkResult(result) {
				if (result == '') {
					return;
				}
				
				alert("��ǰ'" + eResult + "'�� ����Ͽ����ϴ�.'")
			}
		})
		
		let searchForm = $('#searchForm');
		let moveForm = $('#moveForm');
		
		// ��ǰ �˻� ��ư ���� 
		$("#searchForm button").on("click", function(e){
			
			e.preventDefault();
			
			// �˻� Ű���� ��ȿ�� �˻�
			if(!searchForm.find("input[name='keyword']").val()){
				alert("Ű���带 �Է��Ͻʽÿ�");
				return false;
			}
			
			searchForm.find("input[name='pageNum']").val("1");
			
			searchForm.submit();
			
		});
		
		
		// ������ �̵� ��ư
		// ���� �̿ϼ�
		$(".pageMaker_btn a").on("click", function(e){
			
			e.preventDefault();
			
			moveForm.find("input[name='pageNum']").val($(this).attr("href"));
			
			moveForm.submit();
			
		});
	</script>

</body>
</html>