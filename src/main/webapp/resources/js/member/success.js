
const logout_btn = document.getElementById('logout_btn');
const logined_id = document.getElementById('logined_id');
const logined_point = document.getElementById('logined_point');
const logined_name = document.getElementById('logined_name');
	
logout_btn.addEventListener('click',(e)=>{
    session.invalidate();
    window.location.replace('/farmocean/login');
});

xhttp.onreadystatechange = function(e){
	if(xhttp.readyState == 4){
        if(xhttp.status == 200){
		const user = JSON.parse(xhttp.responseText);
        logined_id.innerText = user.buy_id;
        logined_point.innerText = user.buy_point;
        logined_name.innerText = user.buy_name;
        };
    }
};








