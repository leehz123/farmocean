<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix= "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<link rel="stylesheet" href="${path}/resources/css/product/product_list.css">
<title>상품 목록 페이지</title>
<%@ include file="/resources/jspf/header.jspf" %>

</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
  
  <div id="sort-area"><div id="sort-text">${sort}</div></div>  
	<div id="list-paging-set">
    <div id="product-list-container">
      <c:forEach items="${productList}" var="product">
          <div class="product">
              <a class="prod-img-out" href="<c:url value="/product/detail/${product.prod_idx}"/>"></a>
              <div class="prod-info prod-name"><a class="a-link" href="<c:url value="/product/detail/${product.prod_idx}"/>">${product.prod_name}</a></div>
              <div class="prod-info prod-price" data-price="${product.prod_price}"></div>                        
              <div class="prod-info dropdown">
                <button id="prod-seller" name="${product.member_id}" class="nickname-ajax dropbtn prod-seller"></button>
                <div class="dropdown-content">
                  <a href="/farmocean/Sell/member/${product.member_id}">판매자 페이지</a>
                  <a href="/farmocean/mypage/sendMessages?id=${product.member_id}" onclick="window.open(this.href,'_blank', 'width=500, height=600, scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;">쪽지 보내기</a>
                  <a href="" onclick="followAct(this); return false;" data-seller="${product.member_id}">팔로우</a>
                  <a href="" onclick="reportAct(this); return false;" data-seller="${product.member_id}">판매자 신고</a>
                </div>
              </div>
              <div class="prod-info prod_sell" data-deadline="${product.prod_sell_deadline}"></div>
          </div>
      </c:forEach>	        
    </div>
    

	    <div id="paging-container">
        <nav aria-label="Page navigation example">
            <ul class="pagination">
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
            </ul>
          </nav>
    </div>            
	</div>

	<script>

		const imgOutA = document.getElementsByClassName('prod-img-out');
		var arr = new Array();
		
		<c:forEach items="${mainImgList}" var="img">
		  arr.push('${img}');    
		</c:forEach>
		
		for( var i = 0; i < imgOutA.length; i++ ){
		    var out1 = imgOutA.item(i);
		    if(arr[i].includes('http')) {
		  	  out1.innerHTML = '<img class="prod-img" src="' + arr[i] + '" alt="">';      	    	  
		    } else {
		  	  out1.innerHTML = '<img class="prod-img" src="/farmocean' + arr[i] + '" alt="">';
		    }
		}
		
		
		
		
		const followAct = function followAct(a) {
			var seller = a.getAttribute('data-seller');
			
			if(a.innerText == '팔로우') {
				  
				const xhttp15 = new XMLHttpRequest();
				xhttp15.open('POST', '/farmocean/follow');
				var follow = {
				    followee_id : seller
				}
				xhttp15.setRequestHeader('Content-Type', 'application/json;characterset=UTF-8');
				xhttp15.send(JSON.stringify(follow));
				
				xhttp15.addEventListener('readystatechange', (e)=> {
				    const readyState = e.target.readyState;
				    if(readyState == 4) {
				        const responseText = e.target.responseText;
				        const result = JSON.parse(responseText);
				        if(result.result == 1) {
				            alert('판매자를 팔로우 하였습니다.');
				            a.innerText = '언팔로우';
				        } else if(result.result == 2) {
				            alert("이미 팔로우 중입니다.");
				            a.innerText = '언팔로우';
				        } else if(result.result == 0) {
				            alert('로그인이 필요합니다.');
			        	}
			    	}
				});
			
			} else if(a.innerText == '언팔로우') {
				const xhttp16 = new XMLHttpRequest();
				xhttp16.open('DELETE', '/farmocean/unfollow');
				var follow = {
				    followee_id : seller
				}
				xhttp16.setRequestHeader('Content-Type', 'application/json;characterset=UTF-8');
				xhttp16.send(JSON.stringify(follow));
				xhttp16.addEventListener('readystatechange', (e)=> {
				    const readyState = e.target.readyState;
				    if(readyState == 4) {
				        const responseText = e.target.responseText;
				        const result = JSON.parse(responseText);
				        if(result.result == 1) {
				            alert('판매자를 언팔로우 하였습니다.');
				            a.innerText = '팔로우';
				        } else if(result.result == 2) {
				            alert("이미 언팔로우 중입니다.");
				            a.innerText = '팔로우';
				        } else if(result.result == 0) {
				            alert('로그인이 필요합니다.');
				        }
				    }
				});
			}
		}
	
	
	
	
	
	
		const reportAct = function reportAct(a) {
			
			var seller = a.getAttribute('data-seller');
			var loginId = "<c:out value ='${sessionScope.loginId.member_id }'/>";    
			
			if(loginId == null || loginId == undefined || loginId == '') {
			  alert('로그인이 필요합니다.');
			  return false;
			}
			
			
			if(a.innerText == '판매자 신고') {
				
				if(confirm('정말 신고하시겠습니까?')) {
					const xhttp17 = new XMLHttpRequest();
					xhttp17.open('GET', 'http://localhost:8888/farmocean/member/memberfaulty/' + seller);
					xhttp17.send();
					xhttp17.addEventListener('readystatechange', (e)=> {
						const readyState = e.target.readyState;      
						if(readyState == 4) {
						  
						  const responseText = e.target.responseText;
						  const result = JSON.parse(responseText);
						  
						  if(result.code == 1) {
						    alert('신고되었습니다.');
						    a.innerText = '신고 취소';
						  } else if (result.code == -5) {
						    alert('이미 신고된 판매자입니다.');
						    a.innerText = '신고 취소';
						  } else if (result.code == 0) {
						    alert('로그인이 필요합니다.');
						  }
						}
			  		});
				            
				} else {
			  		return false;
				}

			} else if(a.innerText == '신고 취소') {
			
				const xhttp18 = new XMLHttpRequest();
				xhttp18.open('GET', 'http://localhost:8888/farmocean/member/memberfaultycancel/' + seller);
				xhttp18.send();
				xhttp18.addEventListener('readystatechange', (e)=> {
				  const readyState = e.target.readyState;
				  if(readyState == 4) {
						const responseText = e.target.responseText;
						const result = JSON.parse(responseText);
						
						if(result.code == 1) {
						  alert('신고 취소되었습니다.');
						  a.innerText = '판매자 신고';
						} else if (result.code == -5) {
						  alert('이미 신고 취소되었습니다.');
						  a.innerText = '판매자 신고';
						} else if (result.code == 0) {
						  alert('로그인이 필요합니다.');
						}
					}
				});
			} 
		}

	</script>
  
	<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>

<script src="${path}/resources/js/product/prod_list.js"></script>
</html>