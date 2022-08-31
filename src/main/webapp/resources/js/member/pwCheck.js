const pwCheck = document.getElementById('post_member_pw_check');
const pw = document.getElementById('post_member_pw');
const pw_out = document.getElementById('pw_out');

pwCheck.addEventListener('keyup',(e)=>{
    if(pw.value!=pwCheck.value){
        
        pw_out.innerText = 'You can not available';
        pw_out.style.color="red";
    } else{
        pw_out.innerText = 'You can use it Password';
        pw_out.style.color="green";
    }
});