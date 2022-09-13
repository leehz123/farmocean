const btn1 = document.getElementById('btn1');


const xhttp1 = new XMLHttpRequest();

const div1 = document.getElementById("div1");

const id = document.getElementById("sellMember_id");


  let follow = "follow";
  let following = "following";

 xhttp1.addEventListener('readystatechange', (e) =>{
      console.log('2222');

     const readyState1 = e.target.readyState;
     const responseText1 = e.target.responseText;

    if (readyState1 == 4 ){
    
        		console.log('1111');
    
    		
    	if(div1.textContent == follow){    	
    	    btn1.innerText = "follow"; 
    		div1.innerText= "follow";
    		console.log('follow btn');
    		
    	} else{
    		btn1.innerText = "following";
    		div1.innerText= "following";
     		console.log('following btn');
  
    	}
	
    }
});

btn1.addEventListener('click', (e) =>{
	
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




