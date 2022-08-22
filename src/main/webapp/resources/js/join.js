
const btn = document.getElementById('join_btn');

const xhttp = new XMLHttpRequest();


btn.addEventListener('click',(e)=>{
    
    const postUser = {
    	id : post_user_id.value,
    	pw : post_user_pw.value,
        name : post_user_name.value,
        email : post_user_email.value,
    	}    

    xhttp.open('POST', '/farmocean/insert/user');
    xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    console.log('JSON string : ' , JSON.stringify(postUser));
    xhttp.send(JSON.stringify(postUser));
    
});

xhttp.addEventListener('readystatechange',(e)=>{
	const readyState = e.target.readyState;
	console.dir(e.target);
	if(readyState == 4 ){
        
		const httpStatus = e.target.status;

		if(httpStatus == 200){
			alert('회원가입이 완료되었습니다');
		} else{
            alert('실패하였습니다');
        }
	}
});
	
	
	
