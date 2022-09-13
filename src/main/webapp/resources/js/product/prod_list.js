const loginBtn = document.getElementById('login');
const logoutBtn = document.getElementById('logout');

const xhttp1 = new XMLHttpRequest();
xhttp1.addEventListener('readystatechange', (e)=> {
    const readyState = e.target.readyState;
    const responseText = e.target.responseText;

    if(readyState == 4) {
        window.location.reload();
    }
});
loginBtn.addEventListener('click', (e)=> {
    xhttp1.open('GET', '/farmocean/prod/temp_login'); 
    xhttp1.send();
});


const xhttp2 = new XMLHttpRequest();
xhttp2.addEventListener('readystatechange', (e)=> {
    const readyState = e.target.readyState;
    const responseText = e.target.responseText;

    if(readyState == 4) { 
        window.location.reload();
    }
});
logoutBtn.addEventListener('click', (e)=> {
    xhttp2.open('GET', '/farmocean/prod/temp_logout'); 
    xhttp2.send();
});

const xhttp3 = new XMLHttpRequest();

xhttp3.addEventListener('readystatechange', (e)=> {
    const readyState = e.target.readyState;
    const responseText = e.target.responseText;

    if(readyState == 4) {
        
    }
});


//페이지네이션 클릭 시 액션
$('.pagination').children('li').on('click', function(e) {
    console.log($(this).text());
})

window.onload = function(){
	const nicknameAjax = document.getElementsByClassName('nickname-ajax');
	for(let i = 0; i < nicknameAjax.length; ++i) {
        const memberId = document.getElementsByClassName('nickname-ajax')[i].getAttribute('name'); 
        const xhttp4 = new XMLHttpRequest();
        xhttp4.open('GET', '/farmocean/prod/get_member_nickname_by_member_id/' + memberId);
        xhttp4.send();
        xhttp4.addEventListener('readystatechange', (e)=> {
            const readyState = e.target.readyState;
            if(readyState == 4) {
                const nickname = e.target.responseText;
                document.getElementsByClassName('nickname-ajax')[i].innerText = nickname;	        
            }

        });
        
        
	}
};



