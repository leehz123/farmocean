
const btn = document.getElementById('join_btn');

const xhttp = new XMLHttpRequest();


btn.addEventListener('click',(e)=>{
    const image = post_buyer_image.value;
    if(image == null||image==''){
        image="기본이미지";
    }
    const postBuyer = {
    	buy_id : post_buyer_id.value,
    	buy_pw : post_buyer_pw.value,
        buy_name : post_buyer_name.value,
        buy_email : post_buyer_email.value,
        buy_image : image,
        buy_point : 3000
    	}    

    xhttp.open('POST', '/farmocean/insert/buyer');
    xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    console.log('JSON string : ' , JSON.stringify(postBuyer));
    xhttp.send(JSON.stringify(postBuyer));
    
});

xhttp.addEventListener('readystatechange',(e)=>{
	const readyState = e.target.readyState;
	console.dir(e.target);
	if(readyState == 4 ){
        
		const httpStatus = e.target.status;
        const join_btn = document.getElementById('join_btn');
		if(httpStatus == 200){
            alert('성공하였습니다');
            window.location.href = "/farmocean/login";
            
		} else{
           alert('실패하였습니다');
        }
	}
});


	
	
	
