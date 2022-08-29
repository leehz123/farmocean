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
</head>
<body>
    <!-- http://localhost:8888/farmocean/product/detail/2261 -->
    <h3>��ǰ �������� �Դϴ�. ������ ����̶� ������ nav bar�� Ȩ������ �ΰ� ������ �ʳ�?</h3>


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

    <div id="prod-detail-container">

        <div id="prod-info1" class="prod-detail" >
            <!-- ��ǰ �̹���, �̸�, ����, �Ǹſ���, ��, ���� �ð�(ī��Ʈ�ٿ� ��� �� �� ���)...  -->
            <img id="prod-img" src="${prodImg.img_url}" alt="" />
            <div id="prod-info1-simple">
                <div id="prod-info1-name">${product.prod_name }</div>
                <div id="prod-info1-price">${product.prod_price }��</div>
                <div id="prod-info1-sell-status">${product.prod_sell }</div>
                <a href="#" id="prod-info1-heart">��</a>
                <div id="prod-info1-deadline-timer">���� �ð� 119�� 6�ð� 56�� 8��</div>
                <!-- out.div�� �ϸ� �� �� -->
            </div>
        </div>        
		
        <div id="prod-seller" class="prod-detail">
            <!-- ��ǰ �Ǹ��� ���� -->
           	<img id="seller-img" src="${member.member_image}" alt="" />
           	<table id="seller-table">
           		<tr><td id="seller-nickname" class="seller-td">${member.member_nickName }</td></tr>
           		<tr><td id="seller-phone" class="seller-td">����ó : ${member.member_phoneNum }</td></tr>
           		<tr><td id="seller-account" class="seller-td">���� : ${member.member_accountNum } ${member.member_name }</td></tr>
           	</table>
           	<a href="#" id="seller-contact">���� ������</a>
        </div>

        <div id="prod-detail-nav" class="prod-detail">
            <a href="#" id="prod-detail-nav-prod-info">�� ����</a>
            <a href="#" id="prod-detail-nav-prod-review">�ı�</a>
            <a href="#" id="prod-detail-nav-prod-comment">�ֹ�/����</a>
        </div>

        <div id="prod-info2" class="prod-detail">
            <!-- ��ǰ �� ���� (.innerHTML�� prod_detail �� prod-content �ҷ����� ��)
            <br />+ ���� ���� ���� ���� ����, padding ���� -->
            $ {product.prod_info }
        </div>

        <div id="prod-review" class="prod-detail"> <!--flex. column-->
            <!-- ��ǰ �ı�
            <br />�ƿ� Ʋ�� 5�� �������� �ű⿡ �ش��ϴ� ������ �ҷ�����
            <br>�ٵ� �ıⰡ ���� ��� �� Ʋ�� �� ���̰� �ؾ� ��.. �׸��� �ı� ������ŭ ���� ����..
            <br>���信 ��ϵ� ������ ���� ��� visibility hidden����. ��ϵ� ���� ������ 2�� �̻��� ��� �������� ǥ��
            <br>������ ���� DB�� �ش� ��ǰ �ıⰡ �� �� �ִ��� ������ ���������� �����
            <br>������� ����� �����̳� ���� ���, ������ ���� hidden, visible. �� �������� +����������� ǥ��(�ٵ� �̰͵� �� �� ���� ������ hidden)
            <br>��ȣ ����� a�±׷� createElement?
            <br>�׸��� �ı� �ۼ� �������� �����س��� (���� ���) -->
            <div id="prod-review-picture-container"> <!--flex. row-->
                <div id="prod-review-picture1" class="prod-review-picture"></div>
                <div id="prod-review-picture2" class="prod-review-picture"></div>
                <div id="prod-review-picture3" class="prod-review-picture"></div>
                <div id="prod-review-picture4" class="prod-review-picture"></div>
                <div id="prod-review-picture5" class="prod-review-picture"></div>
                <div id="prod-review-picture-more" class="prod-review-picture"></div>
            </div>
            
            <div id="prod-review1" class="review-container"> 
                <div class ="prod-review-user-profile"></div>
                <div class ="prod-review-user-nickname"></div>
                <div class ="prod-review-star"></div>
                <div class ="prod-review-date"></div>
                <div class ="prod-review-content"></div>
                <div class ="prod-review-picture-preview"></div>
                <div class ="prod-review-picture-number"></div>
            </div>
            
            <div id="prod-review2" class="review-container"></div>
            <div id="prod-review3" class="review-container"></div>
            <div id="prod-review4" class="review-container"></div>
            <div id="prod-review5" class="review-container"></div>
            <!-- <div> 1 2 3 4 5 6 7 8 9 ... </div> -->
        </div>

        <div id="prod-comment" class="prod-detail">
            <!-- ��ǰ ���
            <br /> ��б� ��� �� ��. ��� ���� �ϸ� ���â ���ڵ��ó�� ��Ÿ����? 
            <br> �׷��� ���div�� ���â ������ �մٰ� �Ʒ��� �������� �Ǵ� �� �ƴ�?  -->
            <div id="out">
                <c:choose>
                    <c:when test="${sessionScope.loginId eq null }"></c:when>
                    <c:otherwise>
                        <div id="prod-comment-input"><textarea id="prod-comment-textarea"></textarea><div id="prod-comment-input-a-div"><a href="#">�Է�</a></div></div>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
 
    </div>

</body>

<script src="${path}/resources/js/product/prod_detail.js"></script>
</html>