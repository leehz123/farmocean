// const prod_sellArr = document.getElementsByClassName('prod_sell');
//jsp파일에서 <tr><td class="prod_sell" data-deadline="${product.prod_sell_deadline}"></td></tr> 
/*
판매여부 텍스트를 내보낼 td태그에서 
커스텀속성 data-deadline에 마감일인 prod_sell_deadline(Timestamp 형식 날짜)을 저장해놓고
prod_sell_deadline을 date로 변환 후 오늘 날짜가 담긴 date와 비교할 것
마감일<=오늘날짜 : 판매종료 
마감일>오늘날짜 : 판매중  

태그에 지정된 커스텀 속성값은 .getAttribute('커스텀속성명');으로 가져올 수 있음
*/

window.addEventListener('load', () => {
    const prod_sellArr = document.getElementsByClassName('ls_prod_sell');
    for(var prod_sell of prod_sellArr) {
        var ts = prod_sell.getAttribute('data-deadline');
        var deadlineDate = new Date(ts);
        var now = new Date();
        if(deadlineDate <= now ) {
            prod_sell.innerHTML = '<span style="color: rgb(133, 170, 255);">'+deadlineDate+'</span>';
        } else if(deadlineDate > now) {
            prod_sell.innerHTML = '<span style="color: rgb(0, 76, 255);">'+deadlineDate+'</span>';
        } 
    }

});