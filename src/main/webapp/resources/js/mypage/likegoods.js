
const table = document.getElementById('likegoods');
const checkPage = document.getElementById('checkPage');
const one = document.getElementById('one');
const two = document.getElementById('two');

one.addEventListener('click', (e) =>{
    checkPage.innerText = 1;
    delRow();
    list();
});

two.addEventListener('click', (e) =>{
    checkPage.innerText = 2;
    delRow();
    list();
});

const xhttp = new XMLHttpRequest();

var list = function() {
    xhttp.open('GET', '/farmocean/prodJson/bidsProdList/' + checkPage.innerText);
    xhttp.send();
};



xhttp.addEventListener('readystatechange', (e) => {
    const readyState = e.target.readyState;

    if (readyState == 4) {

        const httpStatus = e.target.status;

        if (httpStatus == 200) {

            const likegoods = JSON.parse(xhttp.responseText);

            console.log("likegoods:" + likegoods);

            const length = likegoods.prodList.length;

            console.log("length:" + length);

            if (length == 0) {
                const newRow = table.insertRow();
                newRow.innerText = '찜한 목록이 없습니다.';
            }

            //console.log("토탈: " + likegoods.totalPage);
            //console.log("길이: " + likegoods.prodList.length);

            //console.log("likegoods.prodList1: " + likegoods.prodList[0].prod_name);
            //console.log("likegoods.prodList2: " + likegoods.prodList[1].prod_name);
            //console.log("likegoods.prodList3: " + likegoods.prodList[2].prod_name);
            //console.log("likegoods.prodList4: " + likegoods.prodList[3].prod_name);
            //console.log("likegoods.prodList5: " + likegoods.prodList[4].prod_name);

            
            
            
            
            for ( i = 1; i < length; ++i ) {
            likegoods.prodList.forEach(element => {

                const newRow = table.insertRow();

                const newCell1 = newRow.insertCell(0);
                const newCell2 = newRow.insertCell(1);
                const newCell3 = newRow.insertCell(2);
                const newCell4 = newRow.insertCell(3);
                const newCell5 = newRow.insertCell(4);

                newCell1.innerText = i++;
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
                
                newCell5.innerHTML = `<a href='/farmocean/mypage/deleteLikegoods/${element.prod_idx}'/>찜 취소</a>`;

                    //console.log(element.prod_name);
                });
            }
            
            // for ( i = 0; i < length; ++i ) {

            //     const newRow = table.insertRow();

            //     const newCell1 = newRow.insertCell(0);
            //     const newCell2 = newRow.insertCell(1);
            //     const newCell3 = newRow.insertCell(2);
            //     const newCell4 = newRow.insertCell(3);
            //     const newCell5 = newRow.insertCell(4);

            //     newCell1.innerText = i + 1;
            //     newCell2.innerText = likegoods[i].prod_name;

            //     newCell2.innerHTML = `<a href='/farmocean/product/detail/${likegoods[i].prod_idx}'/>${likegoods[i].prod_name}</a>`;

            //     newCell3.innerText = likegoods[i].prod_price;

            //     const prod_sellArr = likegoods[i].prod_sell_deadline;

            //         var ts = prod_sellArr;
            //         var deadlineDate = new Date(ts);
            //         console.log("deadlineDate" + deadlineDate);
            //         var now = new Date();
            //         console.log("now" + now);
            //         if(deadlineDate <= now ) {
            //             newCell4.innerText = '판매종료';
            //         } else if(deadlineDate > now) {
            //             newCell4.innerText = '판매중';
            //         } 
                
            //     newCell5.innerHTML = `<a href='/farmocean/mypage/deleteLikegoods/${likegoods[i].prod_idx}'/>찜 취소</a>`;
            // }

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