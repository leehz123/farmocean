
const table = document.getElementById('likegoods');

const xhttp = new XMLHttpRequest();

var list = function() {
    xhttp.open('GET', '/farmocean/prodJson/bidsProdList');
    xhttp.send();
};

xhttp.addEventListener('readystatechange', (e) => {
    const readyState = e.target.readyState;

    if (readyState == 4) {

        const httpStatus = e.target.status;

        if (httpStatus == 200) {

            const likegoods = JSON.parse(xhttp.responseText);

            const length = likegoods.length;

            if (length == 0) {
                const newRow = table.insertRow();
                const newCell1 = newRow.insertCell(0);
                newRow.innerText = '찜한 목록이 없습니다.';
            }

            for ( i = 0; i < length; ++i ) {

                const newRow = table.insertRow();

                const newCell1 = newRow.insertCell(0);
                const newCell2 = newRow.insertCell(1);
                const newCell3 = newRow.insertCell(2);
                const newCell4 = newRow.insertCell(3);
                const newCell5 = newRow.insertCell(4);

                newCell1.innerText = i + 1;
                newCell2.innerText = likegoods[i].prod_name;

                newCell2.innerHTML = `<a href='/farmocean/product/detail/${likegoods[i].prod_idx}'/>${likegoods[i].prod_name}</a>`;

                newCell3.innerText = likegoods[i].prod_price;

                const prod_sellArr = likegoods[i].prod_sell_deadline;

                    var ts = prod_sellArr;
                    var deadlineDate = new Date(ts);
                    console.log("deadlineDate" + deadlineDate);
                    var now = new Date();
                    console.log("now" + now);
                    if(deadlineDate <= now ) {
                        newCell4.innerText = '판매종료';
                    } else if(deadlineDate > now) {
                        newCell4.innerText = '판매중';
                    } 
                
                newCell5.innerHTML = `<a href='/farmocean/mypage/deleteLikegoods/${likegoods[i].prod_idx}'/>찜 취소</a>`;
            }

        }

    }
})

list();

function delRow() {

    var number = Number(table.rows.length); 
    
    for (i = 2; i <= number; ++i) {
        //if (table.rows.length <= 1) return;
        table.deleteRow(table.rows.length-1); 
    }
}


playAlert = setInterval(function() {
    delRow();
    list();
}, 10000);

