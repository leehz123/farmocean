
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="${path}/resources/css/product/product_detail.css">

<title>��ǰ �� ������(���⿡ ��ǰ �̸� ��)</title>
<%@ include file="/resources/jspf/header.jspf" %>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>   
<input id="input-prod-idx" type="hidden" value="${product.prod_idx }"></input>
�Ǹ��� ���̵� : <input id="input-seller-id" type="text" value="${product.member_id}">

<button id="test">test</button>

    <!-- http://localhost:8888/farmocean/product/detail/2525 -->
<!-- 
      
    <c:choose>
		<c:when test="${sessionScope.loginId eq null }">
		   �α��� �� �̿� �����մϴ�
		</c:when>
		<c:otherwise>
		   ID : [${sessionScope.loginId.member_id }] 
		   �̸� : [${sessionScope.loginId.member_name}]
		   ��� : [${sessionScope.loginId.member_pw}]
		</c:otherwise>
    </c:choose>
    <a href="#" id="login">�α�</a>
    <a href="#" id="logout">�ΰ���</a>

 -->
    <!-- <c:choose>
		<c:when test="${sessionScope.loginId eq null }">
		   �α��� �� ��� ���, �ı� ��� ����
		</c:when>
		<c:otherwise>
		   ID : [${sessionScope.loginId.member_id }] 
		   �̸� : [${sessionScope.loginId.member_name}]
		   ��� : [${sessionScope.loginId.member_pw}]
		</c:otherwise>
    </c:choose>
 -->

	
	<a id="test-a" href=""></a>
	
    <div id="prod-detail-container">
		
        <div id="prod-info1" class="prod-detail" >
            <!-- ��ǰ �̹���, �̸�, ����, �Ǹſ���, ��, ���� �ð�(ī��Ʈ�ٿ� ��� �� �� ���)...  -->
            <c:choose>
            	<c:when test="${prodImg.img_url eq null}">
       				<img id="prod-img" src="http://localhost:8888/farmocean/resources/upload/prod_img/34a828af-e0cc-4aa6-a807-769d253b56dc.jpg" alt="" />     		
            	</c:when>
            	<c:otherwise>
            		<img id="prod-img" src="${prodImg.img_url}" alt="" />
            	</c:otherwise>
            </c:choose>
            
            <!-- <table id="prod-info-simple">
                <tr><td id="prod-info1-name"></td></tr>
                <tr><td id="prod-info1-price"></td></tr>
                <tr><td id="prod-info1-sell-status"></td></tr>
                <tr><td id="prod-info1-deadline-timer"></td></tr>
                <tr><td><button id="prod-heart-btn" data-text="����">��</button>&nbsp;<button  onClick="fnWinOpen(290, 860, '<c:url value="/buy/prod/${product.prod_idx }" />');">��ǰ ����</button></td></tr>
            </table> -->
            
            <div id="prod-info1-simple">
                <div id="prod-info1-name">${product.prod_name }</div>
                <div id="prod-info1-price">${product.prod_price }��</div>
                <div id="prod-info1-sell-status"></div>
                <div id="prod-info1-deadline"></div>
                <div id="prod-info1-deadline-timer" data-deadline="${product.prod_sell_deadline }"></div>
                <button id="prod-heart-btn" data-text="����">��</button>
                <button  onClick="fnWinOpen(290, 860, '<c:url value="/buy/prod/${product.prod_idx }" />');">��ǰ ����</button>
            </div>
        </div>        
		
        <div id="prod-seller" class="prod-detail">
            <!-- ��ǰ �Ǹ��� ���� -->
           	<img id="seller-img" src="/farmocean/resources/image/mypage/${member.member_image}" alt="" />
           	<table id="seller-table">
           		<tr><td id="seller-nickname" class="seller-td">${member.member_nickName }</td></tr>
           		<tr><td id="seller-phone" class="seller-td">����ó : ${member.member_phoneNum }</td></tr>
           		<tr><td id="seller-account" class="seller-td">���� : ${member.member_accountNum }</td></tr>
                <tr><td><button id="seller-contact">����</button>&nbsp;&nbsp;<button id="seller-follow" data-text="�ȷο�">�ȷο�</button></td></tr>
            </table>
        </div>

        <div id="prod-detail-nav" class="prod-detail">
			<button id="prod-detail-nav-prod-info" class="nav-btn" onclick="onLinkClick(this);" data-scroll-to="prod-info2">������</button>
			<button id="prod-detail-nav-prod-review" class="nav-btn" onclick="onLinkClick(this);" data-scroll-to="prod-review">�ı�</button>
			<button id="prod-detail-nav-prod-comment" class="nav-btn" onclick="onLinkClick(this);" data-scroll-to="prod-comment">����</button>
        </div>

        <div id="prod-info2" class="prod-detail">
            <!-- ��ǰ �� ���� (.innerHTML�� prod_detail �� prod-content �ҷ����� ��)
            <br />+ ���� ���� ���� ���� ����, padding ���� -->
            ${product.prod_info }
        </div>

        <div id="prod-review" class="prod-detail"> <!--flex. column-->
            <!-- ��ǰ �ı�
            <br />�ƿ� Ʋ�� 5�� �������� �ű⿡ �ش��ϴ� ������ �ҷ�����
            <br>���信 ��ϵ� ������ ���� ��� visibility hidden����. ��ϵ� ���� ������ 2�� �̻��� ��� �������� ǥ��
            <br>������� ����� �����̳� ���� ���, ������ ���� hidden, visible. �� �������� +����������� ǥ��(�ٵ� �̰͵� �� �� ���� ������ hidden)
            <br>�׸��� �ı� �ۼ� �������� �����س��� (���� ���) -->
           	<div id="review-write-popup-btn-area"><button id="review-write-popup-btn">���� �ۼ�</button></div>
			<!-- 
            <div id="prod-review-picture-container"> 
                <div id="prod-review-picture1" class="prod-review-picture"></div>
                <div id="prod-review-picture-more" class="prod-review-picture"></div>
            </div>
			 -->            
            <div id="review-container"></div>
            
			<nav aria-label="Page navigation example">
				<ul class="pagination" id="review-pagination-out">
				</ul>
			</nav>
        </div>

        <div id="prod-comment" class="prod-detail"> 
            
            <c:choose>
                <c:when test="${sessionScope.loginId eq null }"></c:when>
                <c:otherwise>
                    <div id="prod-comment-input">
                        <textarea id="prod-comment-textarea"></textarea><button id="prod-comment-input-btn">�Է�</button>
 	                    <div id="comment-secret-div"><input id="comment-secret" type="checkbox"><label for="comment-secret">&nbsp;��б�</label></div>
                    </div>
                </c:otherwise>
            </c:choose>

			<div id="comment-container">
			</div>
            
			<nav aria-label="Page navigation example">
				<ul class="pagination" id="comment-pagination-out">
		
				</ul>
			</nav>
            <div id="no-comment"></div>
        </div>
    </div>

    <script>
        
    //var loginId = "<c:out value ='${sessionScope.loginId.member_id }'/>";
    var seller = "<c:out value ='${product.member_id }'/>";    

    document.getElementById('test').addEventListener('click', (e)=> {
        
    });

    //�Ǹ��� �ȷο� ��ư
    const followBtn = document.getElementById('seller-follow');
    followBtn.addEventListener('click', (e)=> {
        
        if(followBtn.getAttribute('data-text') == '�ȷο�') {
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
                        followBtn.setAttribute('data-text', '���ȷο�');
                    } else if(result.result == 2) {
                        alert("�̹� �ȷο� ���Դϴ�.");
                        followBtn.setAttribute('data-text', '���ȷο�');
                    } else if(result.result == 0) {
                        alert('�α����� �ʿ��մϴ�.');
                    }
                }
            });

        } else if(followBtn.getAttribute('data-text') == '���ȷο�') {
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
                        followBtn.setAttribute('data-text', '�ȷο�');
                    } else if(result.result == 2) {
                        alert("�̹� ���ȷο� ���Դϴ�.");
                        followBtn.setAttribute('data-text', '�ȷο�');
                    } else if(result.result == 0) {
                        alert('�α����� �ʿ��մϴ�.');
                    }
                }
            });        
        }
    });



    </script>



<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>

	<script charset="EUC-KR" src="${path}/resources/js/product/prod_detail.js"></script>

</html>




