/**
 * 
 */

//��Ϲ�ưbtn-ins ��ҹ�ưresetBtn

const btnIns = document.getElementById('btn-ins');


document.getElementById('prod-sell-deadline').value= new Date().toISOString().slice(0, -1);


var now_utc = Date.now(); // ���� ��¥�� �и��ʷ�
// getTimezoneOffset()�� ���� �ð����� ���̸� �� ������ ��ȯ
var timeOff = new Date().getTimezoneOffset()*60000; // �д����� �и��ʷ� ��ȯ
// new Date(today-timeOff).toISOString()�� '2022-09-05T23:17:38.134Z'�� ��ȯ
var today = new Date(now_utc-timeOff).toISOString().substring(0, 16);

document.getElementById("prod-sell-deadline").setAttribute("min", today);



// const xhttp1 = new XMLHttpRequest();
// xhttp1.addEventListener('readystatechange', (e)=> {
//     const readystatechange = e.target.readystatechange();

//     if(readystatechange == 4) {
//         const responseText = e.target.responseText;


//     }
// });


// btnIns.addEventListener('click', (e)=> {
//     xhttp1.open('POST', '');
// });
