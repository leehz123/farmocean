const btn = document.getElementById('login_btn');

const id = document.getElementById('login_buyer_id');
const pw = document.getElementById('login_buyer_pw');
const xhttp = new XMLHttpRequest();

xhttp.onreadystatechange = function(e){
	if(xhttp.readyState == 4){
        if(xhttp.status == 200){
		const user = JSON.parse(xhttp.responseText);

        btn.addEventListener('click',(e)=>{
            const idArr = new Array();
            for(i = 0 ; i < user.length;++i){
                idArr[i] = user[i].buy_id;
            }

            if(!idArr.includes(id.value)){
                alert('일치하는 정보가 없습니다.');
            }
            for(i = 0 ; i < user.length;++i){
                console.log(user[i].buy_id);

                if(user[i].buy_id==id.value){
                    if(user[i].buy_pw == pw.value){
                        alert('로그인 성공');
                        sessionStorage.setItem("logined", user[i].buy_id );
                        window.location.href = "/farmocean/";
                    } else{
                        alert('비밀번호가 일치하지 않습니다');
                    }
                }
            }
        });
        }
    }
};
xhttp.open('GET', '/farmocean/list');
xhttp.send();







