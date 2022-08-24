
const btn = document.getElementById('join_btn');

const xhttp = new XMLHttpRequest();


btn.addEventListener('click',(e)=>{
    
 
    const postseller = {
    	sell_id : post_seller_id.value,
    	sell_pw : post_seller_pw.value,
        sell_name : post_seller_name.value,
        sell_email : post_seller_email.value,
        sell_phoneNum : post_seller_phoneNum.value,
        sell_accountNum : post_seller_accountNum.value,
        sell_address : 'seoul',
        sell_image : 'default_image.jpg'
    	}    

    xhttp.open('POST', '/farmocean/insert/seller');
    xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    console.log('JSON string : ' , JSON.stringify(postseller));
    xhttp.send(JSON.stringify(postseller));
    
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

	
	
	
