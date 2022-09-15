const btn1 = document.getElementById('btn1');
const btn2 = document.getElementById('btn2');
const btn3 = document.getElementById('btn3');
const xhttp1 = new XMLHttpRequest();
const div1 = document.getElementById("div1");
const sellMember_id = document.getElementById("sellMember_id");
const login_id = document.getElementById("login_id");


  let follow = "follow";
  let following = "following";

console.log(sellMember_id);
console.log(login_id);
  
 xhttp1.addEventListener('readystatechange', (e) =>{
     const readyState1 = e.target.readyState;
     const responseText1 = e.target.responseText;

    if (readyState1 == 4 ){
    
    
    	if(div1.textContent == follow){    	
    	    btn1.innerText = 'following'; 
    		div1.innerText = 'following';
    		console.log('follow btn');
    		
    	} else{
    		btn1.innerText = 'follow';
    		div1.innerText = 'follow';
     		console.log('following btn');
  
    	}
	
    }
});

btn1.addEventListener('click', (e) =>{
	
    const newFollow = {
			follow_id: 1,
			follower_id: login_id.textContent,
			followee_id: sellMember_id.textContent 
		};
    
    console.log('id : ' + sellMember_id.textContent);
    console.log('div : ' + div1.textContent);
    
    
    if(div1.textContent == follow){
    
  		xhttp1.open('POST', `/farmocean/follow/follow`);
        xhttp1.setRequestHeader('content-type', 'application/json;charset=UTF-8'); 
        xhttp1.send(JSON.stringify(newFollow)); 
        console.log('follow');
     
        
    } else if (div1.textContent == following){
    
    	xhttp1.open('DELETE', `/farmocean/follow/following`);
        xhttp1.setRequestHeader('content-type', 'application/json;charset=UTF-8'); 
        xhttp1.send(JSON.stringify(newFollow)); 
    	console.log('following');
    }
});

btn2.addEventListener('click', (e) =>{
    window.open('/farmocean/mypage/sendMessage/'+sellMember_id.textContent);


});

btn3.addEventListener('click', (e) =>{
	window.open('/farmocean/product/list/seller/'+sellMember_id.textContent);
});


