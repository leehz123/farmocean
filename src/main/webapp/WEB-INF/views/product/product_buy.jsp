<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<script src="<c:url value="/resources/js/product/prod_buy.js" />"></script>
<title>${productTitle}</title>
</head>
<body>
	<style>
		*{
			margin: 0;
			padding: 0;
		}
	</style>
	<script type="text/javascript">
		window.resizeTo(570,435);
	</script>
	<c:choose>
    	<c:when test="${sessionScope.loginId eq null || productTitle eq null }">
    		<script type="text/javascript">
    			alert('�α��� ������ ���ų� �߸��� ��ǰ �Դϴ�.');
    			window.close();
    		</script>
    	</c:when>    
    	<c:otherwise>
    	<input type="hidden" id="productId" value="${productId }" />
    	<c:set var="prodimg1" value="${productImg[0].img_url}" />
    	<c:set var="prodimg2" value="${productImg[1].img_url}" />
    	
			<div class="card mb-3" style="max-width: 540px;margin:auto;" >
				<div class="row g-0">
					<div class="col-4">	
						<c:choose>						
							<c:when test="${prodimg1 eq null}">
								<img style="padding:16px 0px 8px 20px;width:150px;height:150px;" src="<c:url value="/resources/image/prod/no_img.jpg" />" class="img-fluid rounded-start" alt="${productTitle}">
							</c:when>
							<c:otherwise>
								<c:choose>
	                            	<c:when test="${fn:contains(prodimg1, 'http')}">
										<img style="padding:16px 0px 8px 20px;width:150px;height:150px;" src="${prodimg1 }" class="img-fluid rounded-start" alt="${productTitle}">
	                            	</c:when>
									<c:otherwise>
										<img style="padding:16px 0px 8px 20px;width:150px;height:150px;" src="<c:url value="${prodimg1 }"/> " class="img-fluid rounded-start" alt="${productTitle}">
									</c:otherwise>
	                            </c:choose>   
								
							</c:otherwise>
						</c:choose>
						<br>
						<c:choose>						
							<c:when test="${prodimg2 eq null}">
								<img style="padding:16px 0px 8px 20px;width:150px;height:150px;" src="<c:url value="/resources/image/prod/no_img.jpg" />" class="img-fluid rounded-start" alt="${productTitle}">
							</c:when>
							<c:otherwise>
								<c:choose>
	                            	<c:when test="${fn:contains(prodimg2, 'http')}">
										<img style="padding:16px 0px 8px 20px;width:150px;height:150px;" src="${prodimg2 }" class="img-fluid rounded-start" alt="${productTitle}">
	                            	</c:when>
									<c:otherwise>
										<img style="padding:16px 0px 8px 20px;width:150px;height:150px;" src="<c:url value="${prodimg2 }"/> " class="img-fluid rounded-start" alt="${productTitle}">
									</c:otherwise>
	                            </c:choose>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-8">
						<div class="card-body">
							<h5 class="card-title">${productTitle}</h5>
							<p class="card-text">
							<small class="text-muted">���� : ${productPrice} ��</small><br>
							<small class="text-muted">���� �ð� : ${productDeadline}</small>
							</p>
							<p class="card-text">
								����� ���� �Է�<br />
						
								<input type="text" onclick="execDaumPostcode()" id="postcode" placeholder="�����ȣ" readonly>
								<input type="button" onclick="execDaumPostcode()" value="�����ȣ ã��"><br>
								<input type="text" id="roadAddress" placeholder="���θ��ּ�" readonly>
								<input type="text" id="jibunAddress" placeholder="�����ּ�" readonly>
								<span id="guide" style="color:#999;display:none"></span>
								<input type="text" id="detailAddress" placeholder="���ּ�">
								<input type="text" id="extraAddress" placeholder="�����׸�">
							</p>
							<p class="card-text">
								<button onclick="fnBuyProd()" class="btn btn-primary">���� ��û</button>					
								<button onclick="window.close();"  class="btn btn-danger">â �ݱ�</button>
							</p>
						</div>
					</div>
				</div>
			</div>
    		<%
    			/*    			
   				<div class="card" style="width: 18rem;">
				<img src="${productImg.img_url }" class="card-img-top" alt="${productTitle}">
				<div class="card-body">
					<h5 class="card-title">${productTitle}</h5>
					<p class="card-text">[��������]</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">���� : ${productPrice} ��</li>
					<li class="list-group-item">���� �ð� <br /> ${productDeadline}</li>
					<li class="list-group-item">
						����� ���� �Է�<br />
						
						<input type="text" onclick="execDaumPostcode()" id="postcode" placeholder="�����ȣ" readonly>
						<input type="button" onclick="execDaumPostcode()" value="�����ȣ ã��"><br>
						<input type="text" id="roadAddress" placeholder="���θ��ּ�" readonly>
						<input type="text" id="jibunAddress" placeholder="�����ּ�" readonly>
						<span id="guide" style="color:#999;display:none"></span>
						<input type="text" id="detailAddress" placeholder="���ּ�">
						<input type="text" id="extraAddress" placeholder="�����׸�">
						
					</li>
				</ul>
				<div class="card-body">
					<button onclick="fnBuyProd()" class="btn btn-primary">���� ��û</button>					
					<button onclick="window.close();"  class="btn btn-danger">â �ݱ�</button>					
				</div>
			</div>
			*/
    		%>
   		</c:otherwise>	
    </c:choose>	
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    //�� ���������� ���θ� �ּ� ǥ�� ��Ŀ� ���� ���ɿ� ����, �������� �����͸� �����Ͽ� �ùٸ� �ּҸ� �����ϴ� ����� �����մϴ�.
	    function execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ�.
	
	                // ���θ� �ּ��� ���� ��Ģ�� ���� �ּҸ� ǥ���Ѵ�.
	                // �������� ������ ���� ���� ��쿣 ����('')���� �����Ƿ�, �̸� �����Ͽ� �б� �Ѵ�.
	                var roadAddr = data.roadAddress; // ���θ� �ּ� ����
	                var extraRoadAddr = ''; // ���� �׸� ����
	
	                // ���������� ���� ��� �߰��Ѵ�. (�������� ����)
	                // �������� ��� ������ ���ڰ� "��/��/��"�� ������.
	                if(data.bname !== '' && /[��|��|��]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // �ǹ����� �ְ�, ���������� ��� �߰��Ѵ�.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // ǥ���� �����׸��� ���� ���, ��ȣ���� �߰��� ���� ���ڿ��� �����.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	
	                // �����ȣ�� �ּ� ������ �ش� �ʵ忡 �ִ´�.
	                postcode.value = data.zonecode;
	                roadAddress.value = roadAddr;
	                jibunAddress.value = data.jibunAddress;
	                
	                // �����׸� ���ڿ��� ���� ��� �ش� �ʵ忡 �ִ´�.
	                if(roadAddr !== ''){
	                	extraAddress.value = extraRoadAddr;
	                } else {
	                	extraAddress.value = '';
	                }
	
	                var guideTextBox = document.getElementById("guide");
	                // ����ڰ� '���� ����'�� Ŭ���� ���, ���� �ּҶ�� ǥ�ø� ���ش�.
	                if(data.autoRoadAddress) {
	                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                    guideTextBox.innerHTML = '(���� ���θ� �ּ� : ' + expRoadAddr + ')';
	                    guideTextBox.style.display = 'block';
	
	                } else if(data.autoJibunAddress) {
	                    var expJibunAddr = data.autoJibunAddress;
	                    guideTextBox.innerHTML = '(���� ���� �ּ� : ' + expJibunAddr + ')';
	                    guideTextBox.style.display = 'block';
	                } else {
	                    guideTextBox.innerHTML = '';
	                    guideTextBox.style.display = 'none';
	                }
	            }
	        }).open();
	    }
	</script>
</body>
</html>