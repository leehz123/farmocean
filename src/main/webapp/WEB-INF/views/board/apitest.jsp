<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script>
	const loot_depth = '/farmocean';
</script>
<link rel="stylesheet" href="<c:url value="/resources/css/mainpage/main.css" />" />
<script src="<c:url value="/resources/js/board/ajaxadmin.js"/>"></script>
<title>Insert</title>
</head>
<body>
<div class="wrapper">
	<div class="wrap">
		<div class="top_gnb_area">
			<ul class="list">
				<!-- �α��� X -->
				<c:if test = "${member == null }">
					<li>
						<a href="${path }/member/login">�α���</a>
					</li>
					<li>
						<a href="${path }/member/join">ȸ������</a>
					</li>					
				</c:if>
				<!-- �α��� O (member ���� ����) -->
				<c:if test = "${member != null }">
					<!-- �Ǹ��� ���� (�α��� ���� ���� �ٲ� ��.) -->
					<c:if test = "${member.admin == 1 }">
						<li><a href="${path }/mainpage/seller/main">�Ǹ��� ������</a></li> <!-- �Ǹ��ڷ� �α��� �� ��ǰ ��� ������ ���� ���� -->
					</c:if>
					<li>
						<a id="gnb_logout_btn">�α׾ƿ�</a>
					</li>
					<li>
						����������
					</li>
					<li>
						��ٱ���
					</li>
				</c:if>
				<li>
					������
				</li>
				<!-- �Ǹ��ڷ� �α��� �ؾ� �������� ��ø� �α���X�� �� ���Ƿ� ���� (��ǰ ��� ������) -->
				<li><a href="${path }/mainpage/prod_reg/main">�Ǹ��� ������</a></li>
			</ul>
		</div>
		<div class="top_area">
			<div class="logo_area">
				<h1>logo area</h1>
			</div>
			<div class="search_area">
				<div class="search_wrap">
               		<form id="searchForm" action="${path }/mainpage/search" method="get">
               			<div class="search_input">
               				<select name="type">
               					<option value="T">��ǰ �̸�</option>
               					<option value="A">�Ǹ��� ID</option>
               				</select>
               				<input type="text" name="keyword">
                   			<button class='btn search_btn'>�� ��</button>                				
               			</div>
               		</form>
               	</div>
			</div>
			<div class="login_area">
				<div class="login_btn"><a href="/member/login">�α���</a></div>
				<span><a href="${path }/member/join">ȸ������</a></span>
			</div>
			<div class="clearfix"></div>				
		</div>
		<div class="navi_bar_area">
			<div class="navi_bar_area">
				<div class="dropdown">
					<button class="dropbtn">�ķ��۹�
						<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-content">
						<c:forEach items="${cates2 }" var="cate">
							<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
						</c:forEach>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropbtn">Ư���۹�
						<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-content">
						<c:forEach items="${cates7 }" var="cate">
							<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
						</c:forEach>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropbtn">���Ϸ�
						<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-content">
						<c:forEach items="${cates1 }" var="cate">
							<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
						</c:forEach>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropbtn">ä�ҷ�
						<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-content">
						<c:forEach items="${cates6 }" var="cate">
							<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
						</c:forEach>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropbtn">���깰
						<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-content">
						<c:forEach items="${cates5 }" var="cate">
							<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
						</c:forEach>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropbtn">�Ѹ���?
						<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-content">
						<c:forEach items="${cates3 }" var="cate">
						<!-- ī�װ� ��ȸ �������� ��� �����ϸ� �� -->
							<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
						</c:forEach>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropbtn">�ǰ���?
						<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-content">
						<c:forEach items="${cates4 }" var="cate">
							<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		
		<div class="content_area">
		
			<h1>[�̿Ϸ�]���� ���� ���</h1>
			<hr>
			<h1>[�۾���]�Ű����ID(����¡)</h1>
			<div id="resultFaulty"></div>
			<hr>
			<h1>�Ǹ��� �˻�(ID, �г���)</h1>
			<select id="searchMember">
				<option value="I">ID</option>	
				<option value="N">�г���</option>
			</select>
			<input type="text" id="searchMemberValue" /> <button id="btnSearchMember">�˻�</button>
			<div id="resultMember"></div>
			<hr>
			<h1>�ǸŻ�ǰ ��ȸ(�Ǹ���, ��ǰ��ȣ, ��ǰ��)</h1>
			<select id="searchProd">
				<option value="M">�Ǹ���</option>	
				<option value="N">��ǰ��ȣ</option>
				<option value="P">��ǰ��</option>
			</select>
			<input type="text" id="searchProdValue" /> <button id="btnSearchProd">�˻�</button>
			<div id="resultProd">
			</div>
			<hr>
			<h1>���� �ּ� �˻�</h1>
			<input type="text" id="sample4_postcode" placeholder="�����ȣ">
			<input type="button" onclick="sample4_execDaumPostcode()" value="�����ȣ ã��"><br>
			<input type="text" id="sample4_roadAddress" placeholder="���θ��ּ�">
			<input type="text" id="sample4_jibunAddress" placeholder="�����ּ�">
			<span id="guide" style="color:#999;display:none"></span>
			<input type="text" id="sample4_detailAddress" placeholder="���ּ�">
			<input type="text" id="sample4_extraAddress" placeholder="�����׸�">
			
			<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<script>
			    //�� ���������� ���θ� �ּ� ǥ�� ��Ŀ� ���� ���ɿ� ����, �������� �����͸� �����Ͽ� �ùٸ� �ּҸ� �����ϴ� ����� �����մϴ�.
			    function sample4_execDaumPostcode() {
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
			                document.getElementById('sample4_postcode').value = data.zonecode;
			                document.getElementById("sample4_roadAddress").value = roadAddr;
			                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
			                
			                // �����׸� ���ڿ��� ���� ��� �ش� �ʵ忡 �ִ´�.
			                if(roadAddr !== ''){
			                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
			                } else {
			                    document.getElementById("sample4_extraAddress").value = '';
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
			
		</div>
        
        <div class="footer">
            <div class="footer_container">
                <div class="footer_left">
                    <img src=""> <!-- �ΰ� �̹��� �ƹ��ų� ���� ���߿� -->
                </div>
                <div class="footer_right">
                    [11921] ��� ������ �ǿ���� 44 ����������ǰ�������
                    <br>
                    ��ȭ : ooo-oo-ooooo
                    <br>
                    �ѽ� : oooo-oooo
                    <br>
                    E-mail : oooo@ezen.or.kr
                    <br>
                    <br>
                    COPYRIGHT @2012 by Korea Agro-Fisheries & Food Trade Corporation ALL RIGHTS RESERVED.
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
	</div>
</div>
</body>
</html>