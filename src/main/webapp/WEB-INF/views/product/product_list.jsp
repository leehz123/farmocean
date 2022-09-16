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


	<div id="list-paging-set">
	    <div id="product-list-container">
            <c:forEach items="${productList}" var="product">
                <div class="product">
                    <table>
                        <div><a class="prod-img-out" href="<c:url value="/product/detail/${product.prod_idx}"/>"></a></div>
                        <tr><td><a class="a-link" href="<c:url value="/product/detail/${product.prod_idx}"/>">${product.prod_name}</a></td></tr>
                        <tr><td>${product.prod_price}</td></tr>
                        <tr><td>
                          <div class="dropdown">
                            <button name="${product.member_id}" class="nickname-ajax dropbtn"></button>
                            <div class="dropdown-content">
                              <a href="">판매자 페이지</a>
                              <a href="/farmocean/mypage/sendMessages?id=${product.member_id}" onclick="window.open(this.href,'_blank', 'width=500, height=600, scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;">쪽지 보내기</a>
                              <a href="#">팔로우</a>
                              <a href="#">신고하기</a>
                            </div>
                          </div>
                        </td></tr>
                        <tr><td class="prod_sell" data-deadline="${product.prod_sell_deadline}"></td></tr>
                    </table> 
                </div>
            </c:forEach>	        
	    </div>
	    

	    <div id="paging-container">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                  <li class="page-item"><a class="page-link" href="#">이전</a></li>
                  
                <c:choose>
                    <c:when test="${searchCondition eq 'cate' }">
                      <c:forEach var="i" begin="1" end="${pageNum }">
                        <li class="page-item"><a class="page-link" href="<c:url value="/product/list/${productList[0].cate_idx}/${i }"/>">${i }</a></li>
                      </c:forEach>
                            
                    </c:when>
                    <c:when test="${searchCondition eq 'sellerId' }">
                      <c:forEach var="i" begin="1" end="${pageNum }">
                        <li class="page-item"><a class="page-link" href="<c:url value="/product/list/seller/${productList[0].member_id}/${i }"/>">${i }</a></li>
                      </c:forEach>    
                    </c:when>

                    <c:when test="${searchCondition eq 'sellerNickname' }">
                      <c:forEach var="i" begin="1" end="${pageNum }">
                        <li class="page-item"><a class="page-link" href="<c:url value="/product/list/seller_nick/${searchNick}/${i }"/>">${i }</a></li>
                      </c:forEach>    
                    </c:when>

                    <c:when test="${searchCondition eq 'prodName' }">
                      <c:forEach var="i" begin="1" end="${pageNum }">
                        <li class="page-item"><a class="page-link" href="<c:url value="/product/list/name/${searchName}/${i }"/>">${i }</a></li>
                      </c:forEach>    
                    </c:when>
                </c:choose>
                  
                  
                  
                  
                  
                  
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