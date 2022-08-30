const nickNameChecker = document.getElementById('nickNameCheck'); // 닉네임 중복 확인 버튼
const nickNameField = document.getElementById('nickname');  // 닉네임 작성한 곳
const out = document.getElementById('out'); 					  // 닉네임 중복 확인
const subBtn = document.getElementById('subBtn'); 				  // 서브밋 버튼

const xhttp = new XMLHttpRequest();
const xhttp1 = new XMLHttpRequest();

xhttp.open('GET','/farmocean/memberUpdate/listAll');
xhttp.send();

nickNameChecker.addEventListener('click',(e)=>{
			
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

                nickNameField.value = '';
                nickNameField.focus();
            } else if (nickNameField.value == '') {
                out.innerText = "닉네임이 비어있습니다";
                out.style.color = "red"

                nickNameField.value = '';
                nickNameField.focus();
            } else if (nickNameField.value == null) {
                out.innerText = "닉네임이 null입니다";
                out.style.color = "red"

                nickNameField.value = '';
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
                		
                			nickNameField.value = '';
                			nickNameField.focus();
                		} else {
                			out.innerText = "사용 가능합니다";
                			out.style.color = "green";
                		}
					
					}
				});
            }
            
		}
	}
			
});


subBtn.addEventListener('click', (e) => {
    const check = out.innerText;

    if (check == '사용 가능합니다') {

    } else {
        alert('닉네임 중복확인을 완료 해주세요');
        // 이벤트 중단
        e.preventDefault();
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