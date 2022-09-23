
const checkID = document.getElementById('checkID');
const table = document.getElementById('myCommentList');
const xhttp = new XMLHttpRequest();

var list = function() {
    xhttp.open('GET', '/farmocean/memberUpdate/myCommentList?myID=' + checkID.innerText);
    xhttp.send();
};

xhttp.addEventListener('readystatechange', (e) => {
    const readyState = e.target.readyState;

    if (readyState == 4) {

        const httpStatus = e.target.status;

        if (httpStatus == 200) {

            const comment = JSON.parse(xhttp.responseText);
            console.log("comment:" + comment);

            const length = comment.length;
            console.log("length:" + length);

            if (length == 0) {
                const newRow = table.insertRow();
                newRow.innerText = '남긴 댓글이 없습니다.';
            }

            for ( i = 0; i < length; ++i ) {

                const newRow = table.insertRow();

                const newCell1 = newRow.insertCell(0);
                const newCell2 = newRow.insertCell(1);
                const newCell3 = newRow.insertCell(2);
                const newCell4 = newRow.insertCell(3);
                const newCell5 = newRow.insertCell(4);
                const newCell6 = newRow.insertCell(5);

                var comment_date = new Date(comment[i].comment_date);

                newCell1.innerText = i + 1;
                newCell2.innerHTML = `<a href='/farmocean/product/detail/${comment[i].prod_idx}'/>${comment[i].prod_name}</a>`;

                if (comment[i].comment_secret == 0) {
                    newCell3.innerText = "댓글";
                } else {
                    newCell3.innerText = "비밀글";
                }
                newCell4.innerText = comment[i].comment_content;
                newCell5.innerText = comment_date.toLocaleString();
                newCell6.innerHTML = `<a href='/farmocean/mypage/deleteComment?id=${comment[i].comment_idx}'/>??? ???????</a>`;
         
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