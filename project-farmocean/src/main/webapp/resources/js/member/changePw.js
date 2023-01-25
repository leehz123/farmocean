const pw_out = document.getElementById('pw_out');
const pwField = document.getElementById('pw_field');
const pwCheckField = document.getElementById('member_pw_check');
const pwCheck2 = document.getElementById('member_pw');
const submitBtn = document.getElementById('submit');

const pwChangeXhttp = new XMLHttpRequest();
var pwCheck = false;

pwField.addEventListener('keyup', (e) => {

	pwChangeXhttp.open('GET','/farmocean/member/changePw/' + pwField.value);
	pwChangeXhttp.send();
	
});


pwChangeXhttp.addEventListener('readystatechange', (e) => {
    
    const readyState = e.target.readyState;
    
    if (readyState == 4) {
        
        const responseText = e.target.responseText;
        
        if(responseText==1){
            pw_out.innerText='������ �Ϸ�Ǿ����ϴ�';
            pw_out.style.color='green';
            pwCheck = true;
            
        } else if(responseText==2){
            pw_out.innerText='��й�ȣ�� Ʋ�Ƚ��ϴ�';
            pw_out.style.color='red';
            pwCheck = false;
        }   
    }
});
const pwXhttp = new XMLHttpRequest();

pwCheck2.addEventListener('keyup',(e)=>{
	pwXhttp.open('GET','/farmocean/memberUpdate/checkPassword/' + pwCheck2.value);
	pwXhttp.send();	
});

pwXhttp.addEventListener('readystatechange', (e) => {

    const readyState = e.target.readyState;

    if (readyState == 4) {
    	const httpStatus = e.target.status;  	

        const responseText = e.target.responseText;

        if (pwCheck2.value == '') {
            pw_out3.innerText = "��й�ȣ�� ����ֽ��ϴ�";
            pw_out3.style.color = "red";
            pwCheck = false;
        } else if (responseText == 2) {
            pw_out3.innerText = "8�� �̻� 15�� ����, ����, ����, Ư������ �ּ� 1���� �����Ǿ�� �մϴ�";
            pw_out3.style.color = "red";
            pwCheck = false;
        } else {
            pw_out3.innerText = "��� �����մϴ�";
            pw_out3.style.color = "green";
            pwCheck = true;
        }
    }
});

pwCheckField.addEventListener('keyup', (e) => {
    
   if(pwCheckField.value!=pwCheck2.value){
        pw_out2.innerText='��й�ȣ�� ��ġ���� �ʽ��ϴ�';
        pw_out2.style.color='red';
        pwCheck = false;
    } else {
        pw_out2.innerText='��й�ȣ�� ��ġ�մϴ�';
        pw_out2.style.color='green';
        pwCheck = true;
    }
    
});

const xhttp = new XMLHttpRequest();



submitBtn.addEventListener('click',(e)=>{
    if(pwCheck==false){
        alert('������ ��Ȯ�ϰ� �Է����ּ���');
        e.preventDefault();
    } else if(pwCheck==true){
        const postMember = {
            member_id : null,
            member_pw : pwCheck2.value,
            member_name :null,
            member_nickName : null,
            member_point : 0,        
            member_email : null,
            member_phoneNum : null,
            member_accountNum : null,
            member_address :null,
            member_account_status : 1,
            member_type : null,
            member_report : 0,
            member_image : null,
            member_join_date : null,
            member_modify_date : null
        }    

        xhttp.open('POST', '/farmocean/member/memberPwChange');
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
                    window.location.replace("/farmocean/mypage/main");
                } else {
                    alert('�޷�');
                }
            }
    });

    }
    
});











