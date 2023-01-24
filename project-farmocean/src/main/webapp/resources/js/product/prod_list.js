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
	
    if(document.getElementById('product-list-container').childElementCount < 1) {
        document.getElementById('product-list-container').innerHTML = `<h3>상품이 존재하지 않습니다.</h3>`;
    }
    
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
//jsp파일에서 <tr><td class="prod_sell" data-deadline="${product.prod_sell_deadline}"></td></tr> 
/*
판매여부 텍스트를 내보낼 td태그에서 
커스텀속성 data-deadline에 마감일인 prod_sell_deadline(Timestamp 형식 날짜)을 저장해놓고
prod_sell_deadline을 date로 변환 후 오늘 날짜가 담긴 date와 비교할 것
마감일<=오늘날짜 : 판매종료 
마감일>오늘날짜 : 판매중  

태그에 지정된 커스텀 속성값은 .getAttribute('커스텀속성명');으로 가져올 수 있음
*/

for(var prod_sell of prod_sellArr) {
    var ts = prod_sell.getAttribute('data-deadline');
    var deadlineDate = new Date(ts);
    var now = new Date();
    // if(deadlineDate <= now ) {
    //     prod_sell.innerHTML = '<span style="color: rgb(133, 170, 255);">판매종료</span>';
    // } else if(deadlineDate > now) {
    //     prod_sell.innerHTML = '<span style="color: rgb(0, 76, 255);">판매중</span>';
    // } 
    if(deadlineDate <= now ) {
        prod_sell.innerHTML = '<span style="color: rgb(133, 170, 255);">'+deadlineDate.getFullYear() + '/' + (deadlineDate.getMonth()+1) + '/' +deadlineDate.getDate()+' 까지'+'</span>';
    } else if(deadlineDate > now) {
        prod_sell.innerHTML = '<span style="color: rgb(0, 76, 255);">'+deadlineDate.getFullYear() + '/' + (deadlineDate.getMonth()+1) + '/' +deadlineDate.getDate()+' 까지'+'</span>';
    } 
}





const prodPrice = document.getElementsByClassName('prod-price');

for(p of prodPrice) {
    var intPrice = p.getAttribute('data-price');
    var strPrice = intPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '원';
    p.innerText = strPrice;
}




