<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="${path}/resources/css/product/product_detail.css">
<script src="${path}/resources/js/product/product_detail.js"></script>
<title>��ǰ �� ������(���⿡ ��ǰ �̸� ��)</title>
</head>
<body>
    <!-- http://localhost:8888/farmocean/product/detail -->
    <h3>��ǰ �������� �Դϴ�.</h3>
   
    <div id="prod-detail-container">
       
        <div id="prod-info1" class="prod-detail" >
            <!-- ��ǰ �̹���, �̸�, ����, �Ǹſ���, ��, ���� �ð�...  -->
            <div id="prod-img"></div>
            <div id="prod-info1-simple">
                <div id="prod-info1-name">���� ������� �������� �󰡿��� ���� ���� �߼��ص帳�ϴ�.</div>
                <div id="prod-info1-price">50,000��</div>
                <div id="prod-info1-sell-status">�Ǹ���</div>
                <a href="#" id="prod-info1-heart">��</a>
                <div id="prod-info1-deadline-timer">���� �ð� 119�� 6�ð� 56�� 8��</div>
                <!-- out.div�� �ϸ� �� �� -->
            </div>
        </div>        

        <div id="prod-seller" class="prod-detail">
            ��ǰ �Ǹ��� ����
        </div>

        <div id="prod-info2" class="prod-detail">
            ��ǰ �� ����
        </div>

        <div id="prod-review" class="prod-detail">
            ��ǰ �ı�
        </div>

        <div id="prod-comment" class="prod-detail">
            ��ǰ ���
        </div>
 
    </div>

</body>
</html>