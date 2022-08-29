const xhttp = new XMLHttpRequest();
const xhttp1 = new XMLHttpRequest();


const nickNameChecker = document.getElementById('nickNameCheck');
const nickNameField = document.getElementById('nickname');
const out = document.getElementById('out');
const subBtn = document.getElementById('subBtn');

nickNameChecker.addEventListener('click',(e)=>{
			
		xhttp.open('GET','/farmocean/memberUpdate/listAll');
		xhttp.send();
			
});

// 닉네임 제약조건
const pattern = new RegExp("^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$")

xhttp.addEventListener('readystatechange', (e) => {
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
            } 
//            else if (!pattern.test(nickNameField.value)) {
                //alert('2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성되어야 합니다??');

//                out.innerText = "2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성되어야 합니다";
//                out.style.color = "red";

//                nickNameField.value = '';
//                nickNameField.focus();
//            } 

            else {
                out.innerText = "사용 가능합니다";
                out.style.color = "green";
            }

        }
    }
});


xhttp1.addEventListener('readystatechange', (e) => {
    if (xhttp.readyState == 4) {
    
        if (xhttp.status == 200) {
        	 const member = JSON.parse(xhttp.responseText);
        	 
        	 if(member == 2) {
        	 	out.innerText = "2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성되어야 합니다";
                out.style.color = "red";
                
                nickNameField.value = '';
                nickNameField.focus();
        	 }else {
        	 	out.innerText = "사용 가능합니다";
                out.style.color = "green";
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