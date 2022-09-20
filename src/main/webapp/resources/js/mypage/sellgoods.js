
const table = document.getElementById('sellgoods');

const xhttp = new XMLHttpRequest();

var list = function() {
    xhttp.open('GET', '/farmocean/prodJson/prodInfo/1');
    xhttp.send();
};

xhttp.addEventListener('readystatechange', (e) => {
    const readyState = e.target.readyState;

    if (readyState == 4) {

        const httpStatus = e.target.status;

        if (httpStatus == 200) {

            const sellgoods = JSON.parse(xhttp.responseText);
            console.log("sellgoods:" + sellgoods);

            const length = sellgoods.prodList.length;
            console.log("length:" + length);

            if (length == 0) {
                const newRow = table.insertRow();
                newRow.innerText = '판매중인 목록이 없습니다.';
            }

            for ( i = 0; i < length; ++i ) {
                sellgoods.prodList.forEach(element => {

                console.log("element:" + element);

                const newRow = table.insertRow();

                const newCell1 = newRow.insertCell(0);
                const newCell2 = newRow.insertCell(1);
                const newCell3 = newRow.insertCell(2);
                const newCell4 = newRow.insertCell(3);
                const newCell5 = newRow.insertCell(4);
                const newCell6 = newRow.insertCell(5);
                const newCell7 = newRow.insertCell(6);

                newCell1.innerText = i + 1;
                newCell2.innerText = element.prod_name;

                newCell2.innerHTML = `<a href='/farmocean/product/detail/${element.prod_idx}'/>${element.prod_name}</a>`;

                newCell3.innerText = element.prod_price;

                const prod_sellArr = element.prod_sell_deadline;

                    var ts = prod_sellArr;
                    var deadlineDate = new Date(ts);
                    //console.log("deadlineDate" + deadlineDate);
                    var now = new Date();
                    //console.log("now" + now);
                    if(deadlineDate <= now ) {
                        newCell4.innerText = '판매종료';
                    } else if(deadlineDate > now) {
                        newCell4.innerText = '판매중';
                    } 
                    //console.log(element.prod_idx);
                    newCell5.innerHTML = `<a href='/farmocean/product/product_detail_edit/${element.prod_idx}'/>판매상품수정</a>`;

                    if (element.prod_delete == 0) {
                        newCell6.innerHTML = `<a href='/farmocean/mypage/hideSellgoods/${element.prod_idx}'/>숨김상태로 변경</a>`;
                    } else if (element.prod_delete == 1) {
                        newCell6.innerHTML = `<a href='/farmocean/mypage/hideSellgoods2/${element.prod_idx}'/>보이는상태로 변경</a>`;
                    } else {
                        newCell6.innerText = '삭제된 상품입니다';
                    }

                    //console.log(element.prod_idx);
                    newCell7.innerHTML = `<a href='/farmocean/mypage/deleteGoods?id=${element.prod_idx}'/>상품삭제</a>`;
                });
            }
        }

    }
})

list();

function delRow() {

    var number = Number(table.rows.length); 
    
    for (i = 2; i <= number; ++i) {
        table.deleteRow(table.rows.length-1); 
    }
}