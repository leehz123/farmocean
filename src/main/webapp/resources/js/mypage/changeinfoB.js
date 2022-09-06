
// 닉네임 중복확인 또는 형식 확인------------------------------------------------------------------------

const nickNameField = document.getElementById('nickname'); // 닉네임 작성한 곳
const nickNameChecker = document.getElementById('nickNameCheck'); // 닉네임 중복 확인 버튼
const out = document.getElementById('out'); // 닉네임 중복 확인 표시

const xhttp = new XMLHttpRequest();
const xhttp1 = new XMLHttpRequest();

xhttp.open('GET','/farmocean/memberUpdate/listAll');
xhttp.send();

nickNameField.addEventListener('keyup',(e)=>{
			
	if (xhttp.readyState == 4) {
		if (xhttp.status == 200) {
			
			const member = JSON.parse(xhttp.responseText);
            const memberNickNames = new Array();
            
            for (i = 0; i < member.length; ++i) {
            	memberNickNames[i] = member[i].member_nickName;
            }
            
             if (memberNickNames.includes(nickNameField.value)) {
                out.innerText = "이미 존재하는 닉네임 입니다";
                out.style.color = "red"

                nickNameField.focus();
            } else if (nickNameField.value == '') {
                out.innerText = "닉네임이 비어있습니다";
                out.style.color = "red"

                nickNameField.focus();
            } else if (nickNameField.value == null) {
                out.innerText = "닉네임이 null입니다";
                out.style.color = "red"

                nickNameField.focus();
            } else {
            	
            	xhttp1.open('GET','/farmocean/memberUpdate/checkNickname/' + nickNameField.value);
				xhttp1.send();
				
				xhttp1.addEventListener('readystatechange', (e) => {
				
				const readyState = e.target.readyState;
				
					if (readyState == 4) {
                		const responseText = e.target.responseText;
                	
                		if (responseText == 2) {
                			out.innerText = "2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성되어야 합니다";
                			out.style.color = "red";
                		
                			nickNameField.focus();
                		} else {
                			out.innerText = "사용 가능합니다";
                			out.style.color = "green";
                			
                			// readOnly로 바꿔주는 방법
                			//nickNameField.readOnly = true;
                		}
					
					}
				});
            }
            
		}
	}
			
});

// 비밀번호 형식 확인---------------------------------------------------------------------------------

const password = document.getElementById('password'); // 비밀번호 작성한 곳
const out1 = document.getElementById('out1'); // 비밀번호 중복 확인 표시

const xhttp2 = new XMLHttpRequest();

password.addEventListener('keyup', (e) => {

	xhttp2.open('GET','/farmocean/memberUpdate/checkPassword/' + password.value);
	xhttp2.send();
	
});
	
xhttp2.addEventListener('readystatechange', (e) => {

    const readyState = e.target.readyState;

    if (readyState == 4) {
    	const httpStatus = e.target.status;
    	
        console.log(httpStatus);    	

        const responseText = e.target.responseText;

        console.log(responseText);
        console.log(password.value);

        if (password.value == '') {
            out1.innerText = "비밀번호가 비어있습니다";
            out1.style.color = "red";

            password.focus();
        } else if (responseText == 2) {
            out1.innerText = "8자 이상 15자 이하, 숫자, 문자, 특수문자 최소 1개씩 구성되어야 합니다";
            out1.style.color = "red";

            password.focus();
        } else {
            out1.innerText = "사용 가능합니다";
            out1.style.color = "green";
        }
    }
});


// 이메일 형식 확인---------------------------------------------------------------------------------

const email = document.getElementById('email'); // 이메일 작성한 곳
const out2 = document.getElementById('out2'); // 이메일 중복 확인 표시

const xhttp3 = new XMLHttpRequest();

email.addEventListener('keyup', (e) => {

	xhttp3.open('GET','/farmocean/memberUpdate/checkEmail/' + email.value);
	xhttp3.send();
	
});
	
xhttp3.addEventListener('readystatechange', (e) => {

    const readyState = e.target.readyState;

    if (readyState == 4) {
    	const httpStatus = e.target.status;
    	
        console.log(httpStatus);    	

        const responseText = e.target.responseText;

        console.log(responseText);
        console.log(email.value);

        if (email.value == '') {
            out2.innerText = "이메일이 비어있습니다";
            out2.style.color = "red";

            email.focus();
        } else if (responseText == 2) {
            out2.innerText = "이메일의 구성이 잘못되었습니다";
            out2.style.color = "red";

            email.focus();
        } else {
            out2.innerText = "사용 가능합니다";
            out2.style.color = "green";
        }
    }
});


// 서브밋 버튼--------------------------------------------------------------------------------------

const subBtn = document.getElementById('subBtn'); // 서브밋 버튼

const member_address = document.getElementById('member_address');
		
const postcode = document.getElementById('sample6_postcode');
const address = document.getElementById("sample6_address");
const extraAddress = document.getElementById("sample6_extraAddress");

const detailAddress = document.getElementById("sample6_detailAddress");

subBtn.addEventListener('click', (e) => {
    const check = out.innerText;
    const check1 = out1.innerText;
    const check2 = out2.innerText;

    if (!(check == '사용 가능합니다' || check == '')) {
            alert('닉네임 중복확인을 완료 해주세요');
            // 이벤트 중단
            e.preventDefault();
        } else if (!(check1 == '사용 가능합니다' || check1 == '')) {
            alert('비밀번호를 확인 해주세요');
            // 이벤트 중단
            e.preventDefault();
        } else if (!(check2 == '사용 가능합니다' || check2 == '')) {
            alert('이메일을 확인 해주세요');
            // 이벤트 중단
            e.preventDefault();
        } else {
    
            if (postcode.value == '' && address.value == '' && extraAddress.value  == '' && detailAddress.value  ==  '' ) {
    
            } else {
                if (extraAddress == '') {
                    member_address.value = '[' + postcode.value + ']' + address.value + detailAddress.value;
                } else {
                    member_address.value = '[' + postcode.value + ']' + address.value + ' ' + extraAddress.value + ' ' + detailAddress.value;
                }
            }
        }
});

function emulAcceptCharset(form) {
    if (form.canHaveHTML) {
        document.charset = form.acceptCharset;
    }

    return true;
}


function OpenWin() {
    var f = document.cplogn;

    shape = 'width=520,height=650,';
    shape += 'left=70,top=10,toolbar=no,location=no,directories=no,status=yes,';
    shape += 'menubar=yes,scrollbars=no,resizable=yes';

    var win = open('', 'MC', shape);

    f.target = 'MC';

    emulAcceptCharset(f);

    f.submit();

    if (win.focus) {
        win.focus();
    }
}