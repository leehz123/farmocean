const btn1 = document.getElementById('btn1');

const xhttp1 = new XMLHttpRequest();

const div1 = document.getElementById("div1");

const id = document.getElementById("sellMember_id");


  let follow = "follow";
  let following = "following";

btn1.addEventListener('click', (e) =>{
    //3. 새로운 xhttp 연결을 생성 open(method, url)
	
    const newFollow = {
			follow_id: 1,
			follower_id: 'kings',
			followee_id: id.textContent 
		};
    
    console.log('id : ' + id.textContent);
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
    	console.log('follwing');
    }
           
});


 xhttp1.addEventListener('readystatechange', (e) =>{

     const readyState1 = e.target.readyState;
     const responseText1 = e.target.responseText;

    if (readyState1 == 4 ){
    
    	if(div1.textContent == follow){
    	    btn1.innerText = "팔로우하기"; 	
    		div1.innerText= 'follow';
    	} else{
    		btn1.innerText = "팔로우중"; 	
    		div1.innerText= 'following';
  
    	}
	
    }
});



