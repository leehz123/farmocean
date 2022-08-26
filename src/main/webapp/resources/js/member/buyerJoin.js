
const btn = document.getElementById('join_btn');
const idCheckBtn = document.getElementById('idCheckBtn');
const pwCheckBtn = document.getElementById('pwCheck');
const nickNameCheck = document.getElementById('post_member_nickName');
const out = document.getElementById('out');

const xhttp = new XMLHttpRequest();
const xhttp2 = new XMLHttpRequest();
const xhttp3 = new XMLHttpRequest();
const xhttp4 = new XMLHttpRequest();

xhttp2.open('GET','/farmocean/member/list');
xhttp2.send();
const memberNickNames = new Array();
var idCheck = true;
idCheckBtn.addEventListener('click',(e)=>{
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
            post_member_id.value == null){
                // alert('not available for use');
                out.innerText = 'not available for use';
                out.style.color = 'red';
                idCheck = false;
                post_member_id.value = '';
                post_member_id.focus();
            } else {
                // alert('available for use');
                out.innerText = 'available for use';
                out.style.color = 'green';
                idCheck = true;
            }
        }
    }
});


btn.addEventListener('click',(e)=>{

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
            } else{
                const postMember = {
                    member_id : post_member_id.value,
                    member_pw : post_member_pw.value,
                    member_name : post_member_name.value,
                    member_nickName : post_member_nickName.value,
                    member_point : 3000,        
                    member_email : post_member_email.value,
                    member_phoneNum : post_member_phoneNum.value,
                    member_accountNum : 'null',
                    member_address : 'null',
                    member_account_status : 1,
                    member_type : 'B',
                    member_image : 'sample_img.jpg'
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




	
	
	
