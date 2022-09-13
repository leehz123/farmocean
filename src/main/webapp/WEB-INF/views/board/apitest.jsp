<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<script src="<c:url value="/resources/js/board/ajaxadmin.js"/>"></script>
<title>Insert</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
		
	<h1>[�̿Ϸ�]���� ���� ���</h1>
	<hr>
	<h1>[�۾���]
	<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   href="<c:url value="/Sell/member/sample63" />">�Ǹ��� �˾�</a></h1>
	<hr>	
	<h1>[�۾���]�Ű����ID(����¡)</h1>
	<div id="resultFaulty"></div>
	<hr>
	<h1>[�۾���]���� ��� ��� ���</h1>
	<form id="frmMainTopBanner" name="frmMainTopBanner" method="post" action="<c:url value="/admin/setMainTopBanner" />" enctype="multipart/form-data">
	<input type="hidden" name="mainTopIdx" id="" value=""/>
	<input type="hidden" name="mainTopFileName" id="" value=""/>
	����̸� : <input type="text" name="mainTopName" id="mainTopName" value="test1" />
	��ʸ�ũ : <input type="text" name="mainTopLink" id="mainTopLink" value="/" />
	����̹��� : <input type="file" name="mainTopImg" id="mainTopImg" /> <br />
	<input type="hidden" name="mainTopIdx" id="" value=""/>
	<input type="hidden" name="mainTopFileName" id="" value=""/>
	����̸� : <input type="text" name="mainTopName" id="mainTopName" value="�׽�Ʈ2" />
	��ʸ�ũ : <input type="text" name="mainTopLink" id="mainTopLink" value="<c:url value="/board/" />" />
	����̹��� : <input type="file" name="mainTopImg2" id="mainTopImg2" /> <br />
	<input type="hidden" name="mainTopIdx" id="" value=""/>
	<input type="hidden" name="mainTopFileName" id="" value=""/>
	����̸� : <input type="text" name="mainTopName" id="mainTopName" value="test3" />
	��ʸ�ũ : <input type="text" name="mainTopLink" id="mainTopLink" value="<c:url value="/board/notice" />" />
	����̹��� : <input type="file" name="mainTopImg3" id="mainTopImg3" /> <br />
	<button id="btnFrmSubmit">���</button>
	<input type="reset" />
	</form>
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
	<input type="text" id="searchProdValue" value="sample5" /> <button id="btnSearchProd">�˻�</button>
	<div id="resultProd">
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">��ǰ��ȣ</th>
	      <th scope="col">��ǰ��</th>
	      <th scope="col">�Ǹ���</th>
	      <th scope="col">�Ǹſ���</th>
	    </tr>
	  </thead>
	  <tbody id="tableAdd">
	  	<!-- ��� -->
	  	<!-- 
	    <tr>
	      <th scope="row">1</th>
	      <td>Mark</td>
	      <td>Otto</td>
	      <td>@mdo</td>
	    </tr>
	     -->
	    <!-- ��� -->			    
	  </tbody>
	</table>
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
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>