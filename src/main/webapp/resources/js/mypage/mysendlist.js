
// ???? ???? ???? ???????

// ?α????? ?????
const myid = document.getElementById('realid');
const xhttp = new XMLHttpRequest();
const table = document.getElementById('mymessage');


var list = function() {
    xhttp.open('GET','/farmocean/memberUpdate/sendMessageList?myID=' + myid.innerText);
    xhttp.send();
};


xhttp.addEventListener('readystatechange', (e) => {
    
    const readyState = e.target.readyState;

    //console.log(readyState);

    if (readyState == 4) {

        const httpStatus = e.target.status;

        //console.log(httpStatus);

        if (httpStatus == 200) {

            const message = JSON.parse(xhttp.responseText);

            const length = message.length;

            if (length == 0) {
                const newRow = table.insertRow();
                const newCell1 = newRow.insertCell(0);
                newRow.innerText = '쪽지가 비어있습니다';
            }
                
                for (i = 0; i < message.length; ++i) {
                    
                    const newRow = table.insertRow();
                    
                    const newCell1 = newRow.insertCell(0);
                    const newCell2 = newRow.insertCell(1);
                    const newCell3 = newRow.insertCell(2);
                    const newCell4 = newRow.insertCell(3);
                    const newCell5 = newRow.insertCell(4);
                    const newCell6 = newRow.insertCell(5);

                    var sysdate = new Date(message[i].message_date);
                    var readsysdate = new Date(message[i].readMessage_date);
                    let id = message[i].message_id;
                    let check = message[i].message_check;

                    newCell1.innerText = i + 1;
                    newCell2.innerText = message[i].recipient_id;
                    newCell3.innerText = message[i].message_title;
                    newCell4.innerText = sysdate.toLocaleString();
                    
                    if (message[i].readMessage_date == null) {
                        newCell5.innerText = '';
                    } else {
                        newCell5.innerText = readsysdate.toLocaleString();
                    }

                    if (message[i].message_check == 0) {
                        //newCell6.innerText = '??????';
                        newCell6.innerHTML = `<a class="btn btn-outline-success" href='/farmocean/mypage/showMessageB?id=${id}&&check=${check}'/>안읽음</a>`;
                    } else {
                        //newCell6.innerText = '????';
                        newCell6.innerHTML = `<a class="btn btn-outline-primary" href='/farmocean/mypage/showMessageB?id=${id}&&check=${check}'/>읽음</a>`;
                    }
                }
                //console.log("?????: " + table.rows.length);               
            
        }

    }  
});

list();

function delRow() {

    //console.log("???????????: " + table.rows.length);

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

//newRow.addEventListener("click", function() {
//    alert("hello");
//})