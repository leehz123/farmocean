/**
 * 
 */

//등록버튼btn-ins 취소버튼resetBtn

const btnIns = document.getElementById('btn-ins');


document.getElementById('prod-sell-deadline').value= new Date().toISOString().slice(0, -1);


var now_utc = Date.now(); // 지금 날짜를 밀리초로
// getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
var timeOff = new Date().getTimezoneOffset()*60000; // 분단위를 밀리초로 변환
// new Date(today-timeOff).toISOString()은 '2022-09-05T23:17:38.134Z'를 반환
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
