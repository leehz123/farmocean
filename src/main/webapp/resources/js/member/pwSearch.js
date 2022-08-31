const pwSearchBtn = document.getElementById('post_search_btn');
const out = document.getElementById('out');
const xhttp = new XMLHttpRequest();

pwSearchBtn.addEventListener('click',(e)=>{
    const postMember = {    
        member_id : member_id.value,        
        member_email :member_email.value
    }
    xhttp.open('POST', '/farmocean/member/pwsearch');
    xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    console.log('JSON string : ' , JSON.stringify(postMember));
    xhttp.send(JSON.stringify(postMember));

    xhttp.addEventListener('readystatechange',(e)=>{
        const readyState = e.target.readyState;
        if(readyState == 4){

            if(xhttp.responseText=='undefined'){
                out.innerText = '조회된 아이디가 없습니다';
                out.style.color='red';
            } else{
			    out.innerText = '조회하신 아이디의 비밀번호는[ '+xhttp.responseText+' ] 입니다';
                out.style.color='green';
            }
            
        }
    })
});