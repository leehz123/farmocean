const pw_out = document.getElementById('pw_out');
const pwField = document.getElementById('pw_field');
const pwCheckField = document.getElementById('member_pw_check');
const pwCheck2 = document.getElementById('member_pw');
const submit = document.getElementById('submit');

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
            pw_out.innerText='인증이 완료되었습니다';
            pw_out.style.color='green';
            pwCheck = true;
            
        } else if(responseText==2){
            pw_out.innerText='비밀번호가 틀렸습니다';
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
            pw_out3.innerText = "비밀번호가 비어있습니다";
            pw_out3.style.color = "red";
            pwCheck = false;
        } else if (responseText == 2) {
            pw_out3.innerText = "8자 이상 15자 이하, 숫자, 문자, 특수문자 최소 1개씩 구성되어야 합니다";
            pw_out3.style.color = "red";
            pwCheck = false;
        } else {
            pw_out3.innerText = "사용 가능합니다";
            pw_out3.style.color = "green";
            pwCheck = true;
        }
    }
});

pwCheckField.addEventListener('keyup', (e) => {
    
   if(pwCheckField.value!=pwCheck2.value){
        pw_out2.innerText='비밀번호가 일치하지 않습니다';
        pw_out2.style.color='red';
        pwCheck = false;
    } else {
        pw_out2.innerText='비밀번호가 일치합니다';
        pw_out2.style.color='green';
        pwCheck = true;
    }
    
});

submit.addEventListener("click", function () {
    var form = document.getElementById("form");
    form.action = "/farmocean/member/pwChange";
    form.mothod = "POST";

    if(pwCheck==true){
         form.submit();
    }
  });





