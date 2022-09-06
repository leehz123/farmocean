function sample6_execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ�.

					// �� �ּ��� ���� ��Ģ�� ���� �ּҸ� �����Ѵ�.
					// �������� ������ ���� ���� ��쿣 ����('')���� �����Ƿ�, �̸� �����Ͽ� �б� �Ѵ�.
			var addr = ''; // �ּ� ����
			var extraAddr = ''; // �߰� �ּ� 

			// ����ڰ� ������ �ּ� Ÿ�Կ� ���� �ش� �ּ� ���� �����´�.
			if (data.userSelectedType === 'R') {  // ����ڰ� ���θ� �ּҸ� �������� ���
				addr = data.roadAddress;
			} else { // ����ڰ� ���� �ּҸ� �������� ���(J)
				addr = data.jibunAddress;
			}

			 // ����ڰ� ������ �ּҰ� ���θ� Ÿ���϶� �����Ѵ�.
			if(data.userSelectedType === 'R'){
				// ���������� ���� ��� �߰��Ѵ�. (�������� ����)
				// �������� ��� ������ ���ڰ� "��/��/��"�� ������.
				if (data.bname !== '' && /[��|��|��]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}
			   // �ǹ����� �ְ�, ���������� ��� �߰��Ѵ�.
				if(data.buildingName !== '' && data.apartment === 'Y'){
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
			   // ǥ���� �����׸��� ���� ���, ��ȣ���� �߰��� ���� ���ڿ��� �����.
				if(extraAddr !== ''){
					extraAddr = ' (' + extraAddr + ')';
				}
				 // ���յ� �����׸��� �ش� �ʵ忡 �ִ´�.
				document.getElementById("sample6_extraAddress").value = extraAddr;
			
			} else {
				document.getElementById("sample6_extraAddress").value = '';
			}

			 // �����ȣ�� �ּ� ������ �ش� �ʵ忡 �ִ´�.
			document.getElementById('sample6_postcode').value = data.zonecode;
			document.getElementById("sample6_address").value = addr;
			document.getElementById("sample6_extraAddress").value = '';
			document.getElementById("sample6_detailAddress").value = '';
			// Ŀ���� ���ּ� �ʵ�� �̵��Ѵ�.
			document.getElementById("sample6_detailAddress").focus();
		}
	}).open();
}
