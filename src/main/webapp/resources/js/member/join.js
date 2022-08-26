
const btn = document.getElementById('join_btn');

const xhttp = new XMLHttpRequest();


btn.addEventListener('click',(e)=>{
    
 
    const postMember = {
    	member_id : post_member_id.value,
    	member_pw : post_member_pw.value,
        member_name : post_member_name.value,
        member_nickName : post_member_nickName.value,
        member_point : 3000,        
        member_email : post_member_email.value,
        member_phoneNum : post_member_phoneNum.value,
        member_accountNum : post_member_accountNum.value,
        member_address : post_member_address.value,
        member_account_status : 1,
        member_type : 'S',
        member_image : 'sample_img.jpg'
    	}    

    xhttp.open('POST', '/farmocean/member/insert/member');
    xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    console.log('JSON string : ' , JSON.stringify(postMember));
    xhttp.send(JSON.stringify(postMember));
    
});

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
           alert('failed');
        }
	}
});

	
	
	
