const btn = document.getElementById('login_btn');
const joinBtn = document.getElementById('join_btn');
const sellerJoinBtn = document.getElementById('join_btn_seller');

const idSearchBtn = document.getElementById('id_search_btn');
const pwSearchBtn = document.getElementById('pw_search_btn');

const id = document.getElementById('login_buyer_id');
const pw = document.getElementById('login_buyer_pw');
const loginStatus = document.getElementById('loginStatus');
const xhttp = new XMLHttpRequest();
const xhttp2 = new XMLHttpRequest();
const xhttp3 = new XMLHttpRequest();

xhttp.open('GET', '/farmocean/member/list');
xhttp.send();

xhttp3.open('GET', '/farmocean/member/sellerlist');
xhttp3.send();

xhttp.onreadystatechange = function(e){
    if(xhttp.readyState == 4){
        if(xhttp.status == 200){
            
                const user = JSON.parse(xhttp.responseText);

                btn.addEventListener('click',(e)=>{
                const buyIdArr = new Array();

            for(i = 0 ; i < user.length;++i){
                buyIdArr[i] = user[i].buy_id; 
            }

            if(!buyIdArr.includes(id.value)){
                
                loginStatus.innerText = '일치하는 정보가 없습니다';
                loginStatus.style.color = 'red';
            }

            for(i = 0 ; i < user.length;++i){
              
                if(user[i].buy_id==id.value){
                    if(user[i].buy_pw == pw.value){
                     
                        // sessionStorage.setItem("logined",user[i].buy_id.toString());
                        xhttp2.open('GET', '/farmocean/member/dologin/'+user[i].buy_id.toString());            
                        xhttp2.send();
                        window.location.replace('/farmocean/member/success');
                        
                    } else{                       
                        loginStatus.innerText = '비밀번호가 옳바르지 않습니다';
                        loginStatus.style.color = 'red';
                    }
                }
            }
        });
        }
    }
   
};

xhttp3.onreadystatechange = function(e){
    if(xhttp3.readyState == 4){
        if(xhttp3.status == 200){
       
            const user2 = JSON.parse(xhttp3.responseText);

            btn.addEventListener('click',(e)=>{
            
            const sellIdArr = new Array();

            for(i = 0 ; i < user2.length;++i){
                sellIdArr[i] = user2[i].sell_id; 
            }

            if(!sellIdArr.includes(id.value)){
                loginStatus.innerText = '일치하는 정보가 없습니다.';
                loginStatus.style.color = 'red';
            }

            for(i = 0 ; i < user2.length;++i){
            
                if(user2[i].sell_id==id.value){
                    if(user2[i].sell_pw == pw.value){
                       // alert('판매자 로그인 성공');
                        // sessionStorage.setItem("logined",user[i].buy_id.toString());
                        xhttp2.open('GET', '/farmocean/member/sellerlogin/'+user2[i].sell_id.toString());            
                        xhttp2.send();
                        window.location.replace('/farmocean/member/success');
                        
                    } else{
                        loginStatus.innerText = '비밀번호가 옳바르지 않습니다';
                        loginStatus.style.color = 'red';
                    }
                }
            }
        });
        }
    }
};


joinBtn.addEventListener('click',(e)=>{
    window.location.replace('/farmocean/member/join');
});

sellerJoinBtn.addEventListener('click',(e)=>{
    window.location.replace('/farmocean/member/join/seller');
});






