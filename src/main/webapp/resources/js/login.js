const btn = document.getElementById('login_btn');

const id = document.getElementById('login_buyer_id');
const pw = document.getElementById('login_buyer_pw');
const xhttp = new XMLHttpRequest();

xhttp.onreadystatechange = function(e){
	if(xhttp.readyState == 4){
        if(xhttp.status == 200){
		const user = JSON.parse(xhttp.responseText);

        btn.addEventListener('click',(e)=>{
            const idArr = new Array();
            for(i = 0 ; i < user.length;++i){
                idArr[i] = user[i].buy_id;
            }

            if(!idArr.includes(id.value)){
                alert('��ġ�ϴ� ������ �����ϴ�.');
            }
            for(i = 0 ; i < user.length;++i){
                console.log(user[i].buy_id);

                if(user[i].buy_id==id.value){
                    if(user[i].buy_pw == pw.value){
                        alert('�α��� ����');
                        sessionStorage.setItem("logined", user[i].buy_id );
                        window.location.href = "/farmocean/";
                    } else{
                        alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�');
                    }
                }
            }
        });
        }
    }
};
xhttp.open('GET', '/farmocean/list');
xhttp.send();







