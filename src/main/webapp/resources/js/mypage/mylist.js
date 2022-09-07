
// ���� ���� ���� �ҷ�����

// �α����� ���̵�
const myid = document.getElementById('realid');
const xhttp = new XMLHttpRequest();
const table = document.getElementById('mymessage');


var list = function() {
    //console.log(myid.innerText);
    xhttp.open('GET','/farmocean/memberUpdate/myMessageList/' + myid.innerText);
    xhttp.send();
};


xhttp.addEventListener('readystatechange', (e) => {
    
    const readyState = e.target.readyState;

    console.log(readyState);

    if (readyState == 4) {

        const httpStatus = e.target.status;

        console.log(httpStatus);

        if (httpStatus == 200) {

            const message = JSON.parse(xhttp.responseText);

            const length = message.length;
            
                
                for (i = 0; i < message.length; ++i) {
                    
                    const newRow = table.insertRow();
                    
                    const newCell1 = newRow.insertCell(0);
                    const newCell2 = newRow.insertCell(1);
                    const newCell3 = newRow.insertCell(2);
                    const newCell4 = newRow.insertCell(3);
                    const newCell5 = newRow.insertCell(4);
                    const newCell6 = newRow.insertCell(5);
                    
                    newCell1.innerText = message[i].message_id;
                    newCell2.innerText = message[i].sender_id;
                    newCell3.innerText = message[i].message_title;
                    newCell4.innerText = message[i].message_contents;
                    newCell5.innerText = message[i].message_date;
                    newCell6.innerText = message[i].message_check;

                }
                console.log("����: " + table.rows.length);               
            
        }

    }  
});

list();

function delRow() {

    console.log("�����ٽ�: " + table.rows.length);

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


