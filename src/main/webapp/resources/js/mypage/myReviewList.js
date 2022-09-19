
const checkID = document.getElementById('checkID');
const table = document.getElementById('myReviewList');
const xhttp = new XMLHttpRequest();

var list = function() {
    xhttp.open('GET', '/farmocean/memberUpdate/myReviewList?myID=' + checkID.innerText);
    xhttp.send();
};

xhttp.addEventListener('readystatechange', (e) => {
    const readyState = e.target.readyState;

    if (readyState == 4) {

        const httpStatus = e.target.status;

        if (httpStatus == 200) {

            const review = JSON.parse(xhttp.responseText);
            console.log("review:" + review);

            const length = review.length;
            console.log("length:" + length);

            if (length == 0) {
                const newRow = table.insertRow();
                newRow.innerText = '내가남긴 후기가 없습니다.';
            }

            for ( i = 0; i < length; ++i ) {

                const newRow = table.insertRow();

                const newCell1 = newRow.insertCell(0);
                const newCell2 = newRow.insertCell(1);
                const newCell3 = newRow.insertCell(2);
                const newCell4 = newRow.insertCell(3);
                const newCell5 = newRow.insertCell(4);

                var review_date = new Date(review[i].review_date);

                newCell1.innerText = i + 1;
                newCell2.innerHTML = `<a href='/farmocean/product/detail/${review[i].prod_idx}'/>${review[i].prod_name}</a>`;
                newCell3.innerText = review[i].review_content;
                newCell4.innerText = review_date.toLocaleString();
                newCell5.innerHTML = `<a href='/farmocean/mypage/deleteReview?id=${review[i].review_idx}'/>후기 삭제하기</a>`;
         
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