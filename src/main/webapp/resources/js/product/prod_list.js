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

window.addEventListener('load',() => {
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
});




const prod_sellArr = document.getElementsByClassName('prod_sell');
for(var prod_sell of prod_sellArr) {
    var ts = prod_sell.getAttribute('data-deadline');
    var deadlineDate = new Date(ts);
    var now = new Date();
    if(deadlineDate <= now ) {
        prod_sell.innerHTML = '<span style="color: rgb(133, 170, 255);">판매종료</span>';
    } else if(deadlineDate > now) {
        prod_sell.innerHTML = '<span style="color: rgb(0, 76, 255);">판매중</span>';
    } else {
        prod_sell.innerHTML = '<span style="color: yellow;">크기 비교 안 됨?</span>';
    }
}

