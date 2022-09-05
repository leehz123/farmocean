const pwCheck = document.getElementById('post_member_pw_check');
const pw = document.getElementById('post_member_pw');
const pw_out = document.getElementById('pw_out');

pwCheck.addEventListener('keyup',(e)=>{
    if(pw.value!=pwCheck.value){
        
        pw_out.innerText.replace('비밀번호가 일치하지 않습니다');
        pw_out.style.color="red";
    } else{
        pw_out.innerText = '비밀번호가 일치합니다';
        pw_out.style.color="green";
    }
});