const loginBtn = document.getElementById('login');
const logoutBtn = document.getElementById('logout');

const xhttp1 = new XMLHttpRequest();
xhttp1.addEventListener('readystatechange', (e)=> {
    const readyState = e.target.readyState;
    const responseText = e.target.responseText;

    if(readyState == 4) {
        //const s = JSON.parse(responseText); ��Ʈ�ѷ����� return (LoginMember)session.getAttribute("loginId"); �س��� ������ �� �޾Ƶ� �� �̷��� ���� �ʾƵ� ��
        window.location.reload();
    }
});
loginBtn.addEventListener('click', (e)=> {
    xhttp1.open('GET', '/farmocean/prod/temp_login'); 
    xhttp1.send();
});


const xhttp2 = new XMLHttpRequest();
xhttp2.addEventListener('readystatechange', (e)=> {
    const readyState = e.target.readyState;
    const responseText = e.target.responseText;

    if(readyState == 4) { 
        //const s = JSON.parse(responseText); ��Ʈ�ѷ����� return (LoginMember)session.getAttribute("loginId"); �س��� ������ �ٷ� ���ǿ� ����� �̷��� ���� �ʾƵ� ��
        window.location.reload();
    }
});
logoutBtn.addEventListener('click', (e)=> {
    xhttp2.open('GET', '/farmocean/prod/temp_logout'); 
    xhttp2.send();
});
