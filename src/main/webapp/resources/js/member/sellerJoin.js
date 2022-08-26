
const btn = document.getElementById('join_btn');
const idCheckBtn = document.getElementById('idCheckBtn');

const xhttp = new XMLHttpRequest();
const xhttp2 = new XMLHttpRequest();


xhttp2.open('GET','/farmocean/member/sellerlist');
xhttp2.send();
idCheckBtn.addEventListener('click',(e)=>{
    if(xhttp2.readyState == 4){
        if(xhttp2.status == 200){            
            const seller = JSON.parse(xhttp2.responseText);
            const sellMemberIds = new Array();
    
            for(i = 0 ; i < seller.length;++i){
                sellMemberIds[i] = seller[i].sell_id; 
            }
            if(sellMemberIds.includes(post_seller_id.value)){
                alert('중복된 아이디가 존재합니다');
                post_seller_id.value = '';
            } else {
                alert('아이디를 사용할 수 있습니다');
            }
        }
    }
});

btn.addEventListener('click',(e)=>{
    if(post_seller_pw.value!=post_seller_pw_check.value){
        alert('비밀번호가 일치하지 않습니다');
        post_seller_pw.value="";
        post_seller_pw_check.value="";
        return;
    }

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

    xhttp.open('POST', '/farmocean/member/insert/seller');
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
            window.location.replace("/farmocean/member/sellerlogin");
            
		} else{
           alert('failed');
        }
	}
});

	
	
	
