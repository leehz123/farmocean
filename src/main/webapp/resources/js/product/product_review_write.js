

const memberIdInput = document.getElementById('member-id-input');
const memberNicknameInput = document.getElementById('member-nickname-input');
const prodIdxInput = document.getElementById('prod-idx-input'); 
const submitBtn = document.getElementById('submit-btn');
const reviewContent = document.getElementById('review-contents');
const myForm = document.getElementById('myform');

window.onload = function() {


	if(memberIdInput.value.length >= 1) {
		const xhttp1 = new XMLHttpRequest();
		xhttp1.open('GET', '/farmocean/prod/get_member_nickname_by_member_id/' + memberIdInput.value);
		xhttp1.send();
		xhttp1.addEventListener('readystatechange', (e)=> {
			const readyState = e.target.readyState; 
			if(readyState == 4) {
				const memberNickname = e.target.responseText;
				memberNicknameInput.value = memberNickname;	
			}	
		}); 		
	}

	var path = window.location.pathname;
	// /farmocean/product/product_review_write/{prod_idx}
    prodIdx = path.replace('/farmocean/product/product_review_write/', '');
    prodIdxInput.value = prodIdx;
    
};



//���� ��ư�� ���� �ƴ��� (name�Ӽ� �̿�. ������ư�� name�Ӽ��� ����)
function isRadioBtnSelected() {
	var obj_length = document.getElementsByName("review_starnum").length;
	for (var i=0; i<obj_length; i++) {
		if (document.getElementsByName("review_starnum")[i].checked == true) {
			return true; // ���õ�
		}
	}
	return false; //���� �� ��
}



function formNullChk() {

	if(
		!isRadioBtnSelected() || 
		memberIdInput.value.length < 1 ||
		memberNicknameInput.value.length < 1 ||
		prodIdxInput.value.length < 1 ||
		reviewContent.value.length < 1
	) {
		return true; //�� �׸� ����
	}
	


	return false; //�� �׸� ����
};


submitBtn.addEventListener('click', (e)=> {
    
    if(formNullChk()) {
        alert('��� �ִ� �׸��� ��� �Է����ּ���.');		
    } else {
        // let additionalInput = document.createElement('input');
        // additionalInput.setAttribute('name', 'prod_idx');      //name
		// additionalInput.setAttribute('value', prodIdx);        //value			
		// frmIns.appendChild(additionalInput);                 //from			        
        myForm.submit();		
    }
    formNullChk();
});
