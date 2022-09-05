
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
        var regNickName = /^(?=.*[a-z0-9°‘-ΖR])[a-z0-9°‘-ΖR]{2,16}$/;
        

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
            // ???? κ²??κ²°κ³Ό ?­λͺ©μ ?΄λ¦???? ?€??  μ½λλ₯? ??±?? λΆ?λΆ?.

            // κ°? μ£Όμ? ?ΈμΆ? κ·μΉ? ?°?Ό μ£Όμλ₯? μ‘°ν©??€.
            // ?΄? €?€? λ³??κ°? κ°μ΄ ?? κ²½μ°? κ³΅λ°±('')κ°μ κ°?μ§?λ―?λ‘?, ?΄λ₯? μ°Έκ³ ??¬ λΆκΈ° ??€.
            var addr = ''; // μ£Όμ λ³??
            var extraAddr = ''; // μ°Έκ³ ?­λͺ? λ³??

            //?¬?©?κ°? ? ?? μ£Όμ ????? ?°?Ό ?΄?Ή μ£Όμ κ°μ κ°?? Έ?¨?€.
            if (data.userSelectedType === 'R') { // ?¬?©?κ°? ?λ‘λͺ μ£Όμλ₯? ? ??? κ²½μ°
                addr = data.roadAddress;
            } else { // ?¬?©?κ°? μ§?λ²? μ£Όμλ₯? ? ??? κ²½μ°(J)
                addr = data.jibunAddress;
            }

            // ?¬?©?κ°? ? ?? μ£Όμκ°? ?λ‘λͺ ?????Ό? μ°Έκ³ ?­λͺ©μ μ‘°ν©??€.
            if(data.userSelectedType === 'R'){
                // λ²μ ?λͺμ΄ ?? κ²½μ° μΆκ????€. (λ²μ λ¦¬λ ? ?Έ)
                // λ²μ ?? κ²½μ° λ§μ??λ§? λ¬Έμκ°? "?/λ‘?/κ°?"λ‘? ???€.
                if(data.bname !== '' && /[?|λ‘?|κ°?]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // κ±΄λ¬Όλͺμ΄ ?κ³?, κ³΅λμ£Όν?Ό κ²½μ° μΆκ????€.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // ???  μ°Έκ³ ?­λͺ©μ΄ ?? κ²½μ°, κ΄νΈκΉμ?? μΆκ??? μ΅μ’ λ¬Έμ?΄? λ§λ ?€.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // μ‘°ν©? μ°Έκ³ ?­λͺ©μ ?΄?Ή ??? ?£??€.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // ?°?Έλ²νΈ??? μ£Όμ ? λ³΄λ?? ?΄?Ή ??? ?£??€.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // μ»€μλ₯? ??Έμ£Όμ ??λ‘? ?΄???€.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}




	
	
	


	
	
	
