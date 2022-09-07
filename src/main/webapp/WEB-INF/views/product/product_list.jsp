<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="${path}/resources/css/product/product_list.css">
<title>상품 목록 페이지</title>
<%@ include file="/resources/jspf/header.jspf" %>

</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

      <c:choose>
        <c:when test="${sessionScope.loginId eq null }">
           로그인 후 이용 가능합니다
        </c:when>
        <c:otherwise>
           ID : [${sessionScope.loginId.member_id }] 
           이름 : [${sessionScope.loginId.member_name}]
           비번 : [${sessionScope.loginId.member_pw}]
        </c:otherwise>
    </c:choose>
    <a href="#" id="login">로긴</a>
    <a href="#" id="logout">로가웃</a>


	<div id="list-paging-set">
	    <div id="product-list-container">
            <c:forEach items="${productList}" var="product">
                <div class="product">
                    <table>
                        <div><a class="prod-img-out" href="<c:url value="/product/detail/${product.prod_idx}"/>"></a></div>
                        <tr><td><a href="<c:url value="/product/detail/${product.prod_idx}"/>">${product.prod_name}</a></td></tr>
                        <tr><td>${product.prod_price}</td></tr>
                        <tr><td><a href="">${product.member_id}</a></td></tr>
                        <tr><td>${product.prod_sell}</td></tr>
                    </table>
                </div>
            </c:forEach>	        
	    </div>
	    

	    <div id="paging-container">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                  <li class="page-item"><a class="page-link" href="#">이전</a></li>
                  <c:forEach var="i" begin="1" end="${cateNum }">
                    <li class="page-item"><a class="page-link" href="<c:url value="/product/list/1/${i }"/>">${i }</a></li>
                  </c:forEach>
                  <li class="page-item"><a class="page-link" href="#">다음</a></li>
                </ul>
              </nav>
        </div>            
	</div>

  <script>
    const imgOutA = document.getElementsByClassName('prod-img-out');
    var arr = new Array();
    
    <c:forEach items="${mainImgList}" var="img">
      // console.log('${img}');
      arr.push('${img}');    
    </c:forEach>

    // console.log(imgDivs.length);
    for( var i = 0; i < imgOutA.length; i++ ){
				var out1 = imgOutA.item(i);
				out1.innerHTML = '<img class="prod-img" src="' + arr[i] + '" alt="">';
			}

  </script>
  
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>

<script src="${path}/resources/js/product/prod_list.js"></script>
</html>