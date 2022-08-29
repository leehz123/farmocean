
const joinBtn = document.getElementById('join_btn');
const idCheckBtn = document.getElementById('idCheckBtn');
const pwCheckBtn = document.getElementById('pwCheck');
const nickNameCheck = document.getElementById('post_member_nickName');
var selectBank = document.getElementById('post_member_bank');
const out = document.getElementById('out');

const xhttp = new XMLHttpRequest();
const xhttp2 = new XMLHttpRequest();
const xhttp3 = new XMLHttpRequest();
const xhttp4 = new XMLHttpRequest();

xhttp2.open('GET','/farmocean/member/list');
xhttp2.send();
const memberNickNames = new Array();
var idCheck = false;
idCheckBtn.addEventListener('click',(e)=>{
    var regType = /^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$/;
    if(xhttp2.readyState == 4){
        if(xhttp2.status == 200){            
            const member = JSON.parse(xhttp2.responseText);
            const memberIds = new Array();
            
            for(i = 0 ; i < member.length;++i){
                memberIds[i] = member[i].member_id; 
                memberNickNames[i] = member[i].member_nickName;
            }
            if(memberIds.includes(post_member_id.value) || 
            post_member_id.value == ''||
            post_member_id.value == null||
            regType.test(document.getElementById('post_member_id').value)==false){
                // alert('not available for use');
                id_out.innerText='This ID is not available';
                id_out.style.color='red';
                post_member_id.value = '';
                post_member_id.focus();
                idCheck = false;
            } else {
                // alert('available for use');
                id_out.innerText='This ID is  available';
                id_out.style.color='green';
                idCheck = true;
            }
        }
    }
});


joinBtn.addEventListener('click',(e)=>{

    if(memberNickNames.includes(post_member_nickName.value) || 
    post_member_nickName.value == ''||
    post_member_nickName.value == null){
        // alert('not available for use nickName');
        out.innerText = 'not available for use nickName';
        out.style.color = 'red';
        post_member_nickName.value = '';
        post_member_nickName.focus();
    } else {

    xhttp3.open('GET', '/farmocean/member/pwAvailable/'+post_member_pw.value);
    xhttp3.send();

    xhttp3.addEventListener('readystatechange', (e)=> {
        var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
        var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
        var regNickName = /^([a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{1,10}$/;

        const readyState = e.target.readyState;
        
        if(readyState == 4){
            const responseText = e.target.responseText;
            
            if(responseText==2){
                // alert('password not available for use');
                out.innerText = 'password not available for use';
                out.style.color = 'red';
                post_member_pw.value ='';
                post_member_pw_check.value='';
                post_member_pw.focus();

            } else if (idCheck==false){
                //  alert('ID not available for use');
                out.innerText = 'ID not available for use';
                out.style.color = 'red';
                post_member_id.value ='';
                post_member_id.focus();

            } else if(post_member_pw.value != post_member_pw_check.value){
                // alert('PW != PW CHECK');
                out.innerText = 'PW != PW CHECK';
                out.style.color = 'red';
            }else if(!regPhone.test(
                (post_member_phoneNum1.value+
                post_member_phoneNum2.value+
                post_member_phoneNum3.value))){
                    out.innerText = 'Invalid cell phone number';
                    out.style.color = 'red';
            } else if(!regEmail.test(post_member_email.value)){
                out.innerText = 'Invalid Email';
                out.style.color = 'red';
            } else if (!regNickName.test(post_member_nickName.value)){
                out.innerText = 'Invalid ';
                out.style.color = 'red';
            } else if(post_member_accountNum.value ==null|| 
                sample6_postcode.value+
                sample6_address.value+
                sample6_extraAddress.value+
                sample6_detailAddress.value==null){
                        out.innerText = 'Enter all information';
                        out.style.color = 'red'; 
            } else{
                const postMember = {
                    member_id : post_member_id.value,
                    member_pw : post_member_pw.value,
                    member_name : post_member_name.value,
                    member_nickName : post_member_nickName.value,
                    member_point : 3000,        
                    member_email : post_member_email.value,
                    member_phoneNum : post_member_phoneNum1.value+'-'+
                                        post_member_phoneNum2.value+'-'+
                                        post_member_phoneNum3.value,                                        
                    member_accountNum : '['+selectBank.options[selectBank.selectedIndex].value+']'+post_member_accountNum.value,
                    member_address : '['+sample6_postcode.value+']'+
                                        sample6_address.value+' '+
                                        sample6_extraAddress.value+' '+
                                        sample6_detailAddress.value,
                    member_account_status : 1,
                    member_type : 'S',
                    member_image : 'sample_img.jpg'
                }    
                console.log(selectBank.options[selectBank.selectedIndex.value]);
                xhttp.open('POST', '/farmocean/member/insert/member');
                xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
                console.log('JSON string : ' , JSON.stringify(postMember));
                xhttp.send(JSON.stringify(postMember));

                xhttp.addEventListener('readystatechange',(e)=>{
                    const readyState = e.target.readyState;
                    console.dir(e.target);
                    if(readyState == 4 ){
                        
                        const httpStatus = e.target.status;
                        const join_btn = document.getElementById('join_btn');
                        if(httpStatus == 200){
                            alert('success');
                            window.location.replace("/farmocean/member/login");
                            
                        } else{
                            out.innerText = 'SIGNUP FAILED!';
                            out.style.color = 'red';
                        }
                    }
                });
            }
                        
            }
        })
    } 
}
);
const a = document.getElementById('nickNameCheck');


function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}




	
	
	


	
	
	
