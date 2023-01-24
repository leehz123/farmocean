
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

xhttp2.open('GET','/farmocean/member/list'); // ���̵� üũ ����Ʈ
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
                id_out.innerText='��� �Ұ����մϴ�';
                id_out.style.color='red';
                post_member_id.value = '';
                post_member_id.focus();
                idCheck = false;
            } else {
                // alert('available for use');
                id_out.innerText='��� �����մϴ�';
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
            pw_out.innerText = "��й�ȣ�� ����ֽ��ϴ�";
            pw_out.style.color = "red";
            pwCheck = false;
            pwField.focus();
        } else if (responseText == 2) {
            pw_out.innerText = "8�� �̻� 15�� ����, ����, ����, Ư������ �ּ� 1���� �����Ǿ�� �մϴ�";
            pw_out.style.color = "red";
            pwCheck = false;
            pwField.focus();
        } else {
            pw_out.innerText = "��� �����մϴ�";
            pw_out.style.color = "green";
            
            pwCheckField.addEventListener('keyup',(e)=>{
                if(pwField.value!=pwCheckField.value){
                    
                    pw_out.innerText = '��й�ȣ�� ��ġ���� �ʽ��ϴ�';
                    pw_out.style.color="red";
                } else{
                    pw_out.innerText = '��й�ȣ�� ��ġ�մϴ�!';
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
                nickname_out.innerText = "�̹� �����ϴ� �г��� �Դϴ�";
                nickname_out.style.color = "red"
                nickCheck = false;
                nickNameField.focus();
            } else if (nickNameField.value == '') {
                nickname_out.innerText = "�г����� ����ֽ��ϴ�";
                nickname_out.style.color = "red"
                nickCheck = false;
                nickNameField.focus();
            } else if (nickNameField.value == null) {
                nickname_out.innerText = "�г����� null�Դϴ�";
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
                			nickname_out.innerText = "2�� �̻� 16�� ����, ���� �Ǵ� ���� �Ǵ� �ѱ۷� �����Ǿ�� �մϴ�";
                			nickname_out.style.color = "red";
                            nickCheck = false;
                			nickNameField.focus();
                		} else {
                			nickname_out.innerText = "��� �����մϴ�";
                			nickname_out.style.color = "green";
                            nickCheck = true;
                		}
					
					}
				});
            }
            
		}
	}
			
});

const email = document.getElementById('email'); // �̸��� �ۼ��� ��
const out2 = document.getElementById('out2'); // �̸��� �ߺ� Ȯ�� ǥ��

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
            email_out.innerText = "�̸����� ����ֽ��ϴ�";
            email_out.style.color = "red";
            emailCheck = false;
            emailField.focus();
        } else if (responseText == 2) {
            email_out.innerText = "�̸����� ������ �߸��Ǿ����ϴ�";
            email_out.style.color = "red";
            emailCheck = false;
            emailField.focus();
        } else {
            email_out.innerText = "��� �����մϴ�";
            email_out.style.color = "green";
            emailCheck = true;
        }
    }
});


btn.addEventListener('click',(e)=>{
    if(idCheck==false){
        id_out.innerText='�ٽ� �Է����ּ���';
        id_out.style.color='red';
        post_member_id.value = '';
        post_member_id.focus();
        e.preventDefault();

    } else if (pwCheck == false){
        pw_out.innerText = "��й�ȣ�� �ٽ� �Է����ּ���";
        pw_out.style.color = "red";
        post_member_pw.value = '';
        post_member_pw_check.value = '';
        pwField.focus();
        e.preventDefault();
    } else if (nickCheck == false){
        nickname_out.innerText = "�г����� �ٽ� �Է����ּ���";
        nickname_out.style.color = "red";
        post_member_nickName.value = '';
        nickNameField.focus();
        e.preventDefault();
    } else if (emailCheck == false){
        email_out.innerText = "�̸����� �ٽ� �Է����ּ���";
        email_out.style.color = "red";
        post_member_email.value = '';
        
        emailField.focus();
        e.preventDefault();
        
    } else if(
        !(sample6_postcode.value+
        sample6_address.value+
        sample6_extraAddress.value+
        sample6_detailAddress.value)){
        alert('��� ������ �Է����ּ���');
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
            // Ŀ���� ���ּ� �ʵ�� �̵��Ѵ�.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}






	
	
	
