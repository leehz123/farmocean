
// 쪽지 아이디 체크

const id = document.getElementById("id");
const out = document.getElementById("out");
const xhttp = new XMLHttpRequest();

xhttp.open('GET','/farmocean/memberUpdate/listAll');
xhttp.send();

const title = document.getElementById("title");
const content = document.getElementById("content");
const submitBtn = document.getElementById("submitBtn");

submitBtn.addEventListener('click', (e) => {

    const member = JSON.parse(xhttp.responseText);
    const memberId = new Array();
    
    for (i = 0; i < member.length; ++i) {
        memberId[i] = member[i].member_id;
    }
    
    if (title.value == "") {
        alert("제목을 작성해주세요");
        title.focus();
        e.preventDefault();
    } else if (content.value == "") {
        alert("내용을 작성해주세요");
        content.focus();
        e.preventDefault();
    } else if (id.value == "") {
        alert("보낼사람의 아이디를 작성해주세요");
        id.focus();
        e.preventDefault();
    } else if (!(memberId.includes(id.value))) {
        alert("아이디가 존재하지 않습니다");
        id.value = '';
        id.focus();
        e.preventDefault();
    } else {
        alert("성공적으로 발송했습니다");     
    }				
});