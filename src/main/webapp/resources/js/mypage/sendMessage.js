
// ���� ���̵� üũ

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
        alert("������ �ۼ����ּ���");
        title.focus();
        e.preventDefault();
    } else if (content.value == "") {
        alert("������ �ۼ����ּ���");
        content.focus();
        e.preventDefault();
    } else if (id.value == "") {
        alert("��������� ���̵� �ۼ����ּ���");
        id.focus();
        e.preventDefault();
    } else if (!(memberId.includes(id.value))) {
        alert("���̵� �������� �ʽ��ϴ�");
        id.value = '';
        id.focus();
        e.preventDefault();
    } else {
        alert("���������� �߼��߽��ϴ�");     
    }				
});