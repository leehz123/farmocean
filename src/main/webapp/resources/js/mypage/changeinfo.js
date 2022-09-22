
// �г��� �ߺ�Ȯ�� �Ǵ� ���� Ȯ��------------------------------------------------------------------------

const nickNameField = document.getElementById('nickname'); // �г��� �ۼ��� ��
const nickNameChecker = document.getElementById('checkNickBtn'); // �г��� �ߺ� Ȯ�� ��ư
const out = document.getElementById('out'); // �г��� �ߺ� Ȯ�� ǥ��

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
                alert("�̹� �����ϴ� �г��� �Դϴ�.")
                out.innerText = "�̹� �����ϴ� �г��� �Դϴ�.";
                out.style.color = "red"

                nickNameField.focus();
            } else {
            
                var result = confirm("��� ������ �г��� �Դϴ�. ����Ͻðڽ��ϱ�?");
        
                if(result) {
                    out.innerText = "��� ������ �г��� �Դϴ�. �ߺ�Ȯ���� �Ϸ�Ǿ����ϴ�.";
                    out.style.color = "green"
            	
                    // readOnly�� �ٲ��ִ� ���
                    nickNameField.readOnly = true;
                }
                else {
                    out.innerText = "��� ������ �г��� �Դϴ�. �ߺ�Ȯ���� �����ּ���.";
                    out.style.color = "green"
                }
                	
            }
            
		}
	}
			
});

nickNameField.addEventListener('keyup',(e)=>{
    if (nickNameField.value == '') {
        out.innerText = "�г����� ����ֽ��ϴ�.";
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
                    out.innerText = "3�� �̻� 10�� ����, ���� �Ǵ� ���� �Ǵ� �ѱ۷� �����Ǿ�� �մϴ�.";
                    out.style.color = "red";
                
                    nickNameField.focus();
                } else {
                    out.innerText = "��� �����մϴ�. �ߺ�Ȯ���� �����ּ���.";
                    out.style.color = "green";
                    
                }
            
            }
        });
    }
})

// ��й�ȣ ���� Ȯ��---------------------------------------------------------------------------------

// const password = document.getElementById('password'); // ��й�ȣ �ۼ��� ��
// const out1 = document.getElementById('out1'); // ��й�ȣ �ߺ� Ȯ�� ǥ��

// const xhttp2 = new XMLHttpRequest();

// password.addEventListener('keyup', (e) => {

// 	xhttp2.open('GET','/farmocean/memberUpdate/checkPassword/' + password.value);
// 	xhttp2.send();
	
// });
	
// xhttp2.addEventListener('readystatechange', (e) => {

//     const readyState = e.target.readyState;

//     if (readyState == 4) {
//     	const httpStatus = e.target.status;
    	
//         console.log(httpStatus);    	

//         const responseText = e.target.responseText;

//         console.log(responseText);
//         console.log(password.value);

//         if (password.value == '') {
//             out1.innerText = "��й�ȣ�� ����ֽ��ϴ�";
//             out1.style.color = "red";

//             password.focus();
//         } else if (responseText == 2) {
//             out1.innerText = "8�� �̻� 15�� ����, ����, ����, Ư������ �ּ� 1���� �����Ǿ�� �մϴ�";
//             out1.style.color = "red";

//             password.focus();
//         } else {
//             out1.innerText = "��� �����մϴ�";
//             out1.style.color = "green";
//         }
//     }
// });


// �̸��� ���� Ȯ��---------------------------------------------------------------------------------

const email = document.getElementById('email'); // �̸��� �ۼ��� ��
const out2 = document.getElementById('out2'); // �̸��� �ߺ� Ȯ�� ǥ��

const xhttp3 = new XMLHttpRequest();

email.addEventListener('keyup',(e)=>{
    if (email.value == '') {
        out2.innerText = "�̸����� ����ֽ��ϴ�";
        out2.style.color = "red";

        email.focus();
    } else {
        
        xhttp3.open('GET','/farmocean/memberUpdate/checkEmail/' + email.value);
	    xhttp3.send();
        
        xhttp3.addEventListener('readystatechange', (e) => {
        
        const readyState = e.target.readyState;
        
            if (readyState == 4) {
                const responseText = e.target.responseText;

                console.log("responseText: " + responseText);
            
                if (responseText == 2) {
                    out2.innerText = "�̸����� ������ �߸��Ǿ����ϴ�.";
                    out2.style.color = "red";
                
                    email.focus();
                } else {
                    out2.innerText = "��� ������ �̸��� �Դϴ�.";
                    out2.style.color = "green";
                }
            
            }
        });
    }
})


// ��ȭ��ȣ ���� Ȯ��---------------------------------------------------------------------------------

// ��ȭ��ȣ
const phone = document.getElementById("phone");

const out3 = document.getElementById('out3'); // ��ȭ��ȣ �ߺ� Ȯ�� ǥ��

const xhttp4 = new XMLHttpRequest();

phone.addEventListener('keyup',(e)=>{
          
        xhttp4.open('GET','/farmocean/memberUpdate/checkPhone/' + phone.value);
	    xhttp4.send();
        
        xhttp4.addEventListener('readystatechange', (e) => {
        
        const readyState = e.target.readyState;
        
            if (readyState == 4) {
                const responseText = e.target.responseText;

                console.log("responseText: " + responseText);
            
                if (phone.value == '') {
                    out3.innerText = "��ȭ��ȣ�� ����ֽ��ϴ�";
                    out3.style.color = "red";
            
                    phone.focus();
                } else if (responseText == 2) {
                    out3.innerText = "��ȭ��ȣ�� ������ �߸��Ǿ����ϴ�.";
                    out3.style.color = "red";

                    phone.focus();
                
                } else {
                    out3.innerText = "��� ������ ��ȭ��ȣ �Դϴ�.";
                    out3.style.color = "green";
                }
            
            }
        });
    
})

// ���¹�ȣ ���� Ȯ��---------------------------------------------------------------------------------

const bankNumber = document.getElementById("bankNumber");
const out4 = document.getElementById('out4'); // ���¹�ȣ ���ڸ� Ȯ�� ǥ��

const xhttp5 = new XMLHttpRequest();

bankNumber.addEventListener('keyup', (e) => {

	xhttp5.open('GET','/farmocean/memberUpdate/checkNumber/' + bankNumber.value);
	xhttp5.send();
	
});
	
xhttp5.addEventListener('readystatechange', (e) => {

    const readyState = e.target.readyState;

    if (readyState == 4) {
    	const httpStatus = e.target.status;
    	
        console.log(httpStatus);    	

        const responseText = e.target.responseText;

        console.log(responseText);
        console.log(phone.value);

        if (bankNumber.value == '') {
            out4.innerText = "���¹�ȣ�� ����ֽ��ϴ�.";
            out4.style.color = "red";
    
            bankNumber.focus();
        } else if (responseText == 2) {
            out4.innerText = "���¹�ȣ�� ���ڸ� �Է°����մϴ�.";
            out4.style.color = "red";

            bankNumber.focus();
        } else {
            out4.innerText = "��� �����մϴ�.";
            out4.style.color = "green";
        }
    }
});


// ����� ��ư--------------------------------------------------------------------------------------

const subBtn = document.getElementById('subBtn'); // ����� ��ư

const member_address = document.getElementById('member_address'); // ���� �ּ�
		
// ������ �ּ�
const postcode = document.getElementById('sample6_postcode');
const address = document.getElementById("sample6_address");
const extraAddress = document.getElementById("sample6_extraAddress");
const detailAddress = document.getElementById("sample6_detailAddress");

// ���� �̸�
const bankName = document.getElementById("bankName");
// ���� ���� ����
const nowBank = document.getElementById("member_accountNum");

const member_name = document.getElementById("member_name");

subBtn.addEventListener('click', (e) => {
    const check = out.innerHTML; //�г��� �ߺ� Ȯ�� ǥ��
    const check2 = out2.innerHTML; // �̸��� �ߺ� Ȯ�� ǥ��
    const check3 = out3.innerHTML; // ��ȭ��ȣ �ߺ� Ȯ�� ǥ��
    const check4 = out4.innerHTML; // ���¹�ȣ ���ڸ� Ȯ�� ǥ��

    console.log("check: " + check);
    console.log("check2: " + check2);
    console.log("check3: " + check3);
    console.log("check4: " + check4);


    if (!(check == '��� ������ �г��� �Դϴ�. �ߺ�Ȯ���� �Ϸ�Ǿ����ϴ�.' || check == '&nbsp;')) {
            alert('�г��� �ߺ�Ȯ���� �Ϸ� ���ּ���');
            // �̺�Ʈ �ߴ�
            e.preventDefault();
        } else if (!(check2 == '��� ������ �̸��� �Դϴ�.' || check2 == '&nbsp;')) {
            alert('�̸����� Ȯ�� ���ּ���');
            // �̺�Ʈ �ߴ�
            e.preventDefault();
        } else if (!(check3 == '��� ������ ��ȭ��ȣ �Դϴ�.' || check3 == '&nbsp;')) {
            alert('��ȭ��ȣ�� Ȯ�� ���ּ���');
            // �̺�Ʈ �ߴ�
            e.preventDefault();
        } else if (!(check4 == '��� �����մϴ�.' || check4 == '&nbsp;')) {
            alert('���¹�ȣ�� Ȯ�� ���ּ���');
            // �̺�Ʈ �ߴ�
            e.preventDefault();
        }else {
    
            if (postcode.value == '' || address.value == '' || detailAddress.value  ==  '' ) {
    
            } else {
                if (extraAddress == '') {
                    member_address.value = '[' + postcode.value + ']' + address.value + detailAddress.value;
                } else {
                    member_address.value = '[' + postcode.value + ']' + address.value + ' ' + extraAddress.value + ' ' + detailAddress.value;
                }
            }

            if (bankNumber.value != '') {
                nowBank.value = '[' + bankName.value + ']' + member_name.value + ':' + bankNumber.value;
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