
// ���� ���̵� üũ

const id = document.getElementById("id");
const out = document.getElementById("out");
const xhttp = new XMLHttpRequest();

xhttp.open('GET','/farmocean/memberUpdate/listAll');
xhttp.send();

id.addEventListener('keyup',(e)=>{
    if (xhttp.readyState == 4) {
		if (xhttp.status == 200) {
			
			const member = JSON.parse(xhttp.responseText);
            const memberId = new Array();
            
            for (i = 0; i < member.length; ++i) {
            	memberId[i] = member[i].member_id;
            }

            if (id.value == "") {
                out.innerText = "��������� ���̵� �����ּ���";
                out.style.color = "black"

                id.focus();
            } else if (!(memberId.includes(id.value))) {
                out.innerText = "���̵� �������� �ʽ��ϴ�";
                out.style.color = "red"

                id.focus();
            } else {
                out.innerText = "�����ϴ� ���̵��Դϴ�";
                out.style.color = "green"
            }
        }
    }
});
