
// �г��� �ߺ�Ȯ�� �Ǵ� ���� Ȯ��------------------------------------------------------------------------

const nickNameField = document.getElementById('nickname'); // �г��� �ۼ��� ��
const nickNameChecker = document.getElementById('nickNameCheck'); // �г��� �ߺ� Ȯ�� ��ư
const out = document.getElementById('out'); // �г��� �ߺ� Ȯ�� ǥ��

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
                out.innerText = "�̹� �����ϴ� �г��� �Դϴ�";
                out.style.color = "red"

                nickNameField.focus();
            } else if (nickNameField.value == '') {
                out.innerText = "�г����� ����ֽ��ϴ�";
                out.style.color = "red"

                nickNameField.focus();
            } else if (nickNameField.value == null) {
                out.innerText = "�г����� null�Դϴ�";
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
                			out.innerText = "2�� �̻� 16�� ����, ���� �Ǵ� ���� �Ǵ� �ѱ۷� �����Ǿ�� �մϴ�";
                			out.style.color = "red";
                		
                			nickNameField.focus();
                		} else {
                			out.innerText = "��� �����մϴ�";
                			out.style.color = "green";
                			
                			// readOnly�� �ٲ��ִ� ���
                			//nickNameField.readOnly = true;
                		}
					
					}
				});
            }
            
		}
	}
			
});

// ��й�ȣ ���� Ȯ��---------------------------------------------------------------------------------

const password = document.getElementById('password'); // ��й�ȣ �ۼ��� ��
const out1 = document.getElementById('out1'); // ��й�ȣ �ߺ� Ȯ�� ǥ��

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
            out1.innerText = "��й�ȣ�� ����ֽ��ϴ�";
            out1.style.color = "red";

            password.focus();
        } else if (responseText == 2) {
            out1.innerText = "8�� �̻� 15�� ����, ����, ����, Ư������ �ּ� 1���� �����Ǿ�� �մϴ�";
            out1.style.color = "red";

            password.focus();
        } else {
            out1.innerText = "��� �����մϴ�";
            out1.style.color = "green";
        }
    }
});


// �̸��� ���� Ȯ��---------------------------------------------------------------------------------

const email = document.getElementById('email'); // �̸��� �ۼ��� ��
const out2 = document.getElementById('out2'); // �̸��� �ߺ� Ȯ�� ǥ��

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
            out2.innerText = "�̸����� ����ֽ��ϴ�";
            out2.style.color = "red";

            email.focus();
        } else if (responseText == 2) {
            out2.innerText = "�̸����� ������ �߸��Ǿ����ϴ�";
            out2.style.color = "red";

            email.focus();
        } else {
            out2.innerText = "��� �����մϴ�";
            out2.style.color = "green";
        }
    }
});


// ����� ��ư--------------------------------------------------------------------------------------

const subBtn = document.getElementById('subBtn'); // ����� ��ư

const member_address = document.getElementById('member_address');
		
const postcode = document.getElementById('sample6_postcode');
const address = document.getElementById("sample6_address");
const extraAddress = document.getElementById("sample6_extraAddress");

const detailAddress = document.getElementById("sample6_detailAddress");

subBtn.addEventListener('click', (e) => {
    const check = out.innerText;
    const check1 = out1.innerText;
    const check2 = out2.innerText;

    if (!(check == '��� �����մϴ�' || check == '')) {
            alert('�г��� �ߺ�Ȯ���� �Ϸ� ���ּ���');
            // �̺�Ʈ �ߴ�
            e.preventDefault();
        } else if (!(check1 == '��� �����մϴ�' || check1 == '')) {
            alert('��й�ȣ�� Ȯ�� ���ּ���');
            // �̺�Ʈ �ߴ�
            e.preventDefault();
        } else if (!(check2 == '��� �����մϴ�' || check2 == '')) {
            alert('�̸����� Ȯ�� ���ּ���');
            // �̺�Ʈ �ߴ�
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