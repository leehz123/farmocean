
const btn = document.getElementById('join_btn');
const idCheckBtn = document.getElementById('idCheckBtn');

const nickNameField = document.getElementById('post_member_nickName');
const idField = document.getElementById('post_member_id');
const pwField = document.getElementById('post_member_pw');
const pwCheckField = document.getElementById('post_member_pw_check');
const emailField = document.getElementById('post_member_email');


const out = document.getElementById('out');
const id_out = document.getElementById('id_out');
const pw_out = document.getElementById('pw_out');
const nickname_out = document.getElementById('nickname_out');
const email_out = document.getElementById('email_out');

const xhttp = new XMLHttpRequest();
const xhttp2 = new XMLHttpRequest();
const xhttp3 = new XMLHttpRequest();
const xhttp4 = new XMLHttpRequest();
const xhttp5 = new XMLHttpRequest();

xhttp2.open('GET','/farmocean/member/list'); // 아이디 체크 리스트
xhttp2.send();

const memberNickNames = new Array();
var idCheck = false;
var pwCheck = false;
var nickCheck = false;
var emailCheck = false;
idCheckBtn.addEventListener('click',(e)=>{
    var regType = /^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$/;
    if(xhttp2.readyState == 4){
        if(xhttp2.status == 200){            
            const member = JSON.parse(xhttp2.responseText);
            const memberIds = new Array();
            
            for(i = 0 ; i < member.length;++i){
                memberIds[i] = member[i].member_id; 

            }
            if(memberIds.includes(post_member_id.value) || 
            post_member_id.value == ''||
            post_member_id.value == null||
            regType.test(document.getElementById('post_member_id').value)==false){
                // alert('not available for use');
                id_out.innerText='사용 불가능합니다';
                id_out.style.color='red';
                post_member_id.value = '';
                post_member_id.focus();
                idCheck = false;
            } else {
                // alert('available for use');
                id_out.innerText='사용 가능합니다';
                id_out.style.color='green';
                idCheck = true;
            }
            
            
        }
    }
});

const pwXhttp = new XMLHttpRequest();

pwField.addEventListener('keyup', (e) => {

	pwXhttp.open('GET','/farmocean/memberUpdate/checkPassword/' + pwField.value);
	pwXhttp.send();
	
});
	
pwXhttp.addEventListener('readystatechange', (e) => {

    const readyState = e.target.readyState;

    if (readyState == 4) {
    	const httpStatus = e.target.status;
    	
        console.log(httpStatus);    	

        const responseText = e.target.responseText;

        console.log(responseText);
        console.log(pwField.value);

        if (pwField.value == '') {
            pw_out.innerText = "비밀번호가 비어있습니다";
            pw_out.style.color = "red";
            pwCheck = false;
            pwField.focus();
        } else if (responseText == 2) {
            pw_out.innerText = "8자 이상 15자 이하, 숫자, 문자, 특수문자 최소 1개씩 구성되어야 합니다";
            pw_out.style.color = "red";
            pwCheck = false;
            pwField.focus();
        } else {
            pw_out.innerText = "사용 가능합니다";
            pw_out.style.color = "green";
            
            pwCheckField.addEventListener('keyup',(e)=>{
                if(pwField.value!=pwCheckField.value){
                    
                    pw_out.innerText = '비밀번호가 일치하지 않습니다';
                    pw_out.style.color="red";
                } else{
                    pw_out.innerText = '비밀번호가 일치합니다!';
                    pw_out.style.color="green";
                    pwCheck = true;
                }
            });
        }
    }
});

nickXhttpList = new XMLHttpRequest();
nickXhttp = new XMLHttpRequest();

nickXhttpList.open('GET','/farmocean/memberUpdate/listAll');
nickXhttpList.send();

nickNameField.addEventListener('keyup',(e)=>{
			
	if (nickXhttpList.readyState == 4) {
		if (nickXhttpList.status == 200) {
			
			const member = JSON.parse(nickXhttpList.responseText);
            const memberNickNames = new Array();
            
            for (i = 0; i < member.length; ++i) {
            	memberNickNames[i] = member[i].member_nickName;
            }
            
             if (memberNickNames.includes(nickNameField.value)) {
                nickname_out.innerText = "이미 존재하는 닉네임 입니다";
                nickname_out.style.color = "red"
                nickCheck = false;
                nickNameField.focus();
            } else if (nickNameField.value == '') {
                nickname_out.innerText = "닉네임이 비어있습니다";
                nickname_out.style.color = "red"
                nickCheck = false;
                nickNameField.focus();
            } else if (nickNameField.value == null) {
                nickname_out.innerText = "닉네임이 null입니다";
                nickname_out.style.color = "red"
                nickCheck = false;
                nickNameField.focus();
            } else {
            	
            	nickXhttp.open('GET','/farmocean/memberUpdate/checkNickname/' + nickNameField.value);
				nickXhttp.send();
				
				nickXhttp.addEventListener('readystatechange', (e) => {
				
				const readyState = e.target.readyState;
				
					if (readyState == 4) {
                		const responseText = e.target.responseText;
                	
                		if (responseText == 2) {
                			nickname_out.innerText = "2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성되어야 합니다";
                			nickname_out.style.color = "red";
                            nickCheck = false;
                			nickNameField.focus();
                		} else {
                			nickname_out.innerText = "사용 가능합니다";
                			nickname_out.style.color = "green";
                            nickCheck = true;
                		}
					
					}
				});
            }
            
		}
	}
			
});

const email = document.getElementById('email'); // 이메일 작성한 곳
const out2 = document.getElementById('out2'); // 이메일 중복 확인 표시

const emailXhttp = new XMLHttpRequest();

emailField.addEventListener('keyup', (e) => {
	emailXhttp.open('GET','/farmocean/memberUpdate/checkEmail/' + emailField.value);
	emailXhttp.send();

	
});
	
emailXhttp.addEventListener('readystatechange', (e) => {

    const readyState = e.target.readyState;

    if (readyState == 4) {
    	const httpStatus = e.target.status;
    	
        console.log(httpStatus);    	

        const responseText = e.target.responseText;

        console.log(responseText);
        console.log(emailField.value);

        if (emailField.value == '') {
            email_out.innerText = "이메일이 비어있습니다";
            email_out.style.color = "red";
            emailCheck = false;
            emailField.focus();
        } else if (responseText == 2) {
            email_out.innerText = "이메일의 구성이 잘못되었습니다";
            email_out.style.color = "red";
            emailCheck = false;
            emailField.focus();
        } else {
            email_out.innerText = "사용 가능합니다";
            email_out.style.color = "green";
            emailCheck = true;
        }
    }
});


btn.addEventListener('click',(e)=>{
    if(idCheck==false){
        id_out.innerText='다시 입력해주세요';
        id_out.style.color='red';
        post_member_id.value = '';
        post_member_id.focus();
        e.preventDefault();

    } else if (pwCheck == false){
        pw_out.innerText = "비밀번호를 다시 입력해주세요";
        pw_out.style.color = "red";
        post_member_pw.value = '';
        post_member_pw_check.value = '';
        pwField.focus();
        e.preventDefault();
    } else if (nickCheck == false){
        nickname_out.innerText = "닉네임을 다시 입력해주세요";
        nickname_out.style.color = "red";
        post_member_nickName.value = '';
        nickNameField.focus();
        e.preventDefault();
    } else if (emailCheck == false){
        email_out.innerText = "이메일을 다시 입력해주세요";
        email_out.style.color = "red";
        post_member_email.value = '';
        
        emailField.focus();
        e.preventDefault();
        
    } else if(
        !(sample6_postcode.value+
        sample6_address.value+
        sample6_extraAddress.value+
        sample6_detailAddress.value)){
        alert('모든 정보를 입력해주세요');
        e.preventDefault(); 
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
            member_accountNum : 'null',
            member_address : '['+sample6_postcode.value+']'+
                                sample6_address.value+' '+
                                sample6_extraAddress.value+' '+
                                sample6_detailAddress.value,
            member_account_status : 1,
            member_type : 'B',
            member_report : 0,
            member_image : 'profile_basic_image.jpg',
            member_join_date : null,
            member_modify_date : null
        }    


        xhttp.open('POST', '/farmocean/member/insert/member');
        xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        console.log('JSON string : ' , JSON.stringify(postMember));
        xhttp.send(JSON.stringify(postMember));

        xhttp.addEventListener('readystatechange',(e)=>{
            const readyState = e.target.readyState;
            console.dir(e.target);
            if(readyState == 4 ){
                
                const httpStatus = e.target.status;
               
                if(httpStatus == 200){
                    alert('success');
                    window.location.replace("/farmocean/member/login");
                    
                } else {
                    out.innerText = 'SIGNUP FAILED!';
                    out.style.color = 'red';
                }
            }
        });
    }
    }
               
                        
);
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 추가 주소 

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') {  // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

             // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
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






	
	
	
