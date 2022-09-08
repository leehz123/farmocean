

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



//라디오 버튼이 선택 됐는지 (name속성 이용. 라디오버튼은 name속성이 공통)
function isRadioBtnSelected() {
	var obj_length = document.getElementsByName("review_starnum").length;
	for (var i=0; i<obj_length; i++) {
		if (document.getElementsByName("review_starnum")[i].checked == true) {
			return true; // 선택됨
		}
	}
	return false; //선택 안 됨
}



function formNullChk() {

	if(
		!isRadioBtnSelected() || 
		memberIdInput.value.length < 1 ||
		memberNicknameInput.value.length < 1 ||
		prodIdxInput.value.length < 1 ||
		reviewContent.value.length < 1
	) {
		return true; //빈 항목 있음
	}
	


	return false; //빈 항목 없음
};


submitBtn.addEventListener('click', (e)=> {
    
    if(formNullChk()) {
        alert('비어 있는 항목을 모두 입력해주세요.');		
    } else {
        // let additionalInput = document.createElement('input');
        // additionalInput.setAttribute('name', 'prod_idx');      //name
		// additionalInput.setAttribute('value', prodIdx);        //value			
		// frmIns.appendChild(additionalInput);                 //from			        
        myForm.submit();		
    }
    formNullChk();
});
