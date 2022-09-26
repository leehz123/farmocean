
// 내가 받은 쪽지 불러오기

// 로그인한 아이디
const myid = document.getElementById('realid');
const table = document.getElementById('mymessage');
const xhttp = new XMLHttpRequest();


var list = function() {
    xhttp.open('GET','/farmocean/memberUpdate/myMessageList?myID=' + myid.innerText);
    xhttp.send();
    
    //xhttp.open('Post','/farmocean/memberUpdate/myMessageList');
    //xhttp.setRequestHeader('Content-type', 'application/json;charset=UTF-8')
    //xhttp.send(JSON.stringify(realMyid));
};


xhttp.addEventListener('readystatechange', (e) => {

    //console.log("myid.innerText: " + myid.innerText);
    
    const readyState = e.target.readyState;

    console.log(readyState);

    if (readyState == 4) {

        const httpStatus = e.target.status;

        //console.log(httpStatus);

        if (httpStatus == 200) {

            const message = JSON.parse(xhttp.responseText);

            const length = message.length;
            //console.log("메세지 길이: " + message.length);

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
                    newCell2.innerText = message[i].sender_id;
                    newCell3.innerHTML = `<a href='/farmocean/mypage/showMessage?id=${id}&&check=${check}'/>${message[i].message_title}</a>`;
                    newCell4.innerText = sysdate.toLocaleString();

                    if (message[i].readMessage_date == null) {
                        newCell5.innerText = '';
                    } else {
                        newCell5.innerText = readsysdate.toLocaleString();
                    }

                    if (message[i].message_check == 0) {
                        //newCell6.innerText = '안읽음';
                        newCell6.innerHTML = `<a class="btn btn-outline-success" href='/farmocean/mypage/showMessage?id=${id}&&check=${check}'/>안읽음</a>`;
                    } else {
                        //newCell6.innerText = '읽음';
                        newCell6.innerHTML = `<a class="btn btn-outline-primary" href='/farmocean/mypage/showMessage?id=${id}&&check=${check}'/>읽음</a>`;
                    }

                }
                //console.log("갯수: " + table.rows.length);               
            
        }

    }  
});

list();

function delRow() {

    //console.log("갯수다시: " + table.rows.length);

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


