
const xhttp1 = new XMLHttpRequest();

function unfollow(followee_id, cnt, follower_id) {
	
	console.log(followee_id);
	console.log(cnt);
	
	const follow = document.getElementById('p'+cnt);
	
	    const newFollow = {
			follow_id: 1,
			follower_id: follower_id,
			followee_id: followee_id 
		};
    
	
    	xhttp1.open('DELETE', `/farmocean/follow/following`);
        xhttp1.setRequestHeader('content-type', 'application/json;charset=UTF-8'); 
        xhttp1.send(JSON.stringify(newFollow)); 
    	console.log('following');
    	
    	follow.remove();
    	
}

 xhttp1.addEventListener('readystatechange', (e) =>{
     const readyState1 = e.target.readyState;
     const responseText1 = e.target.responseText;

    if (readyState1 == 4 ){
    
    
 
	
    }
});