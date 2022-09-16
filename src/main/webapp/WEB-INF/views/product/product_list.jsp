<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="${path}/resources/css/product/product_list.css">
<title>��ǰ ��� ������</title>
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
                              <a href="/farmocean/Sell/member/${product.member_id}">�Ǹ��� ������</a>
                              <a href="/farmocean/mypage/sendMessages?id=${product.member_id}" onclick="window.open(this.href,'_blank', 'width=500, height=600, scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;">���� ������</a>
                              <a href="" onclick="followAct(this); return false;" data-seller="${product.member_id}">�ȷο�</a>
                              <a href="" onclick="reportAct(this); return false;" data-seller="${product.member_id}">�Ǹ��� �Ű�</a>
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
                  <li class="page-item"><a class="page-link" href="#">����</a></li>
                  
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
                  
                  
                  
                  
                  
                  
                  <li class="page-item"><a class="page-link" href="#">����</a></li>
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

  for( var i = 0; i < imgOutA.length; i++ ){
      var out1 = imgOutA.item(i);
      out1.innerHTML = '<img class="prod-img" src="' + arr[i] + '" alt="">';
  }



  
  const followAct = function followAct(a) {
    var seller = a.getAttribute('data-seller');
    
    alert('�Լ��� ����');
    
    if(a.innerText == '�ȷο�') {
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
                  alert('�Ǹ��ڸ� �ȷο� �Ͽ����ϴ�.');
                  a.innerText = '���ȷο�';
              } else if(result.result == 2) {
                  alert("�̹� �ȷο� ���Դϴ�.");
                  a.innerText = '���ȷο�';
              } else if(result.result == 0) {
                  alert('�α����� �ʿ��մϴ�.');
              }
          }
      });

    } else if(a.innerText == '���ȷο�') {
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
                  alert('�Ǹ��ڸ� ���ȷο� �Ͽ����ϴ�.');
                  a.innerText = '�ȷο�';
              } else if(result.result == 2) {
                  alert("�̹� ���ȷο� ���Դϴ�.");
                  a.innerText = '�ȷο�';
              } else if(result.result == 0) {
                  alert('�α����� �ʿ��մϴ�.');
              }
          }
      });
    }
  }


//�Ű�      http://localhost:8888/farmocean/member/memberfaulty/{�Ű��Ϸ���ID}
//�Ű� ��� http://localhost:8888/farmocean/member/memberfaultycancel/{�Ű��Ϸ���ID}




const reportAct = function reportAct(a) {
  
  var seller = a.getAttribute('data-seller');
  
  if(a.innerText == '�Ǹ��� �Ű�') {
    if(confirm('���� �Ű��Ͻðڽ��ϱ�?')) {
      const xhttp17 = new XMLHttpRequest();
      xhttp17.open('GET', 'http://localhost:8888/farmocean/member/memberfaulty/' + seller);
      xhttp17.send();
      xhttp17.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;      
        if(readyState == 4) {
          
          const responseText = e.target.responseText;
          const result = JSON.parse(responseText);
          
          if(result.code == 1) {
            alert('�Ű�Ǿ����ϴ�.');
            a.innerText = '�Ű� ���';
          } else if (result.code == -5) {
            alert('�̹� �Ű�� �Ǹ����Դϴ�.');
            a.innerText = '�Ű� ���';
          } else if (result.code == 0) {
            alert('�α����� �ʿ��մϴ�.');
          }
        }
      });
                
    } else {
      return false;
    }

  } else if(a.innerText == '�Ű� ���') {

    const xhttp18 = new XMLHttpRequest();
    xhttp18.open('GET', 'http://localhost:8888/farmocean/member/memberfaultycancel/' + seller);
    xhttp18.send();
    xhttp18.addEventListener('readystatechange', (e)=> {
      const readyState = e.target.readyState;
      if(readyState == 4) {
        const responseText = e.target.responseText;
        const result = JSON.parse(responseText);

        if(result.code == 1) {
          alert('�Ű� ��ҵǾ����ϴ�.');
          a.innerText = '�Ǹ��� �Ű�';
        } else if (result.code == -5) {
          alert('�̹� �Ű� ��ҵǾ����ϴ�.');
          a.innerText = '�Ǹ��� �Ű�';
        } else if (result.code == 0) {
          alert('�α����� �ʿ��մϴ�.');
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