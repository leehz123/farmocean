const idSearchBtn = document.getElementById('post_search_btn');
const xhttp = new XMLHttpRequest();

idSearchBtn.addEventListener('click',(e)=>{
    const postMember = {    
        member_name : member_name.value,        
        member_email :member_email.value
    }
    xhttp.open('POST', '/farmocean/member/idSearch');
    xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    console.log('JSON string : ' , JSON.stringify(postMember));
    xhttp.send(JSON.stringify(postMember));

    xhttp.addEventListener('readystatechange',(e)=>{
        const readyState = e.target.readyState;
        if(readyState == 4){

            const member = JSON.parse(xhttp.responseText);
            alert(member.member_id);
        }
    })
});