
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
        var regNickName = /^(?=.*[a-z0-9°¡-ÆR])[a-z0-9°¡-ÆR]{2,16}$/;
        

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
            // ?Œ?—…?—?„œ ê²??ƒ‰ê²°ê³¼ ?•­ëª©ì„ ?´ë¦??–ˆ?„?•Œ ?‹¤?–‰?•  ì½”ë“œë¥? ?‘?„±?•˜?Š” ë¶?ë¶?.

            // ê°? ì£¼ì†Œ?˜ ?…¸ì¶? ê·œì¹™?— ?”°?¼ ì£¼ì†Œë¥? ì¡°í•©?•œ?‹¤.
            // ?‚´? ¤?˜¤?Š” ë³??ˆ˜ê°? ê°’ì´ ?—†?Š” ê²½ìš°?—” ê³µë°±('')ê°’ì„ ê°?ì§?ë¯?ë¡?, ?´ë¥? ì°¸ê³ ?•˜?—¬ ë¶„ê¸° ?•œ?‹¤.
            var addr = ''; // ì£¼ì†Œ ë³??ˆ˜
            var extraAddr = ''; // ì°¸ê³ ?•­ëª? ë³??ˆ˜

            //?‚¬?š©?ê°? ?„ ?ƒ?•œ ì£¼ì†Œ ????…?— ?”°?¼ ?•´?‹¹ ì£¼ì†Œ ê°’ì„ ê°?? ¸?˜¨?‹¤.
            if (data.userSelectedType === 'R') { // ?‚¬?š©?ê°? ?„ë¡œëª… ì£¼ì†Œë¥? ?„ ?ƒ?–ˆ?„ ê²½ìš°
                addr = data.roadAddress;
            } else { // ?‚¬?š©?ê°? ì§?ë²? ì£¼ì†Œë¥? ?„ ?ƒ?–ˆ?„ ê²½ìš°(J)
                addr = data.jibunAddress;
            }

            // ?‚¬?š©?ê°? ?„ ?ƒ?•œ ì£¼ì†Œê°? ?„ë¡œëª… ????…?¼?•Œ ì°¸ê³ ?•­ëª©ì„ ì¡°í•©?•œ?‹¤.
            if(data.userSelectedType === 'R'){
                // ë²•ì •?™ëª…ì´ ?ˆ?„ ê²½ìš° ì¶”ê???•œ?‹¤. (ë²•ì •ë¦¬ëŠ” ? œ?™¸)
                // ë²•ì •?™?˜ ê²½ìš° ë§ˆì??ë§? ë¬¸ìê°? "?™/ë¡?/ê°?"ë¡? ??‚œ?‹¤.
                if(data.bname !== '' && /[?™|ë¡?|ê°?]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // ê±´ë¬¼ëª…ì´ ?ˆê³?, ê³µë™ì£¼íƒ?¼ ê²½ìš° ì¶”ê???•œ?‹¤.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // ?‘œ?‹œ?•  ì°¸ê³ ?•­ëª©ì´ ?ˆ?„ ê²½ìš°, ê´„í˜¸ê¹Œì?? ì¶”ê???•œ ìµœì¢… ë¬¸ì?—´?„ ë§Œë“ ?‹¤.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // ì¡°í•©?œ ì°¸ê³ ?•­ëª©ì„ ?•´?‹¹ ?•„?“œ?— ?„£?Š”?‹¤.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // ?š°?¸ë²ˆí˜¸??? ì£¼ì†Œ ? •ë³´ë?? ?•´?‹¹ ?•„?“œ?— ?„£?Š”?‹¤.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // ì»¤ì„œë¥? ?ƒ?„¸ì£¼ì†Œ ?•„?“œë¡? ?´?™?•œ?‹¤.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}




	
	
	


	
	
	
