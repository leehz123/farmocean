
// 쪽지 아이디 체크

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
                out.innerText = "보낼사람의 아이디를 적어주세요";
                out.style.color = "black"

                id.focus();
            } else if (!(memberId.includes(id.value))) {
                out.innerText = "아이디가 존재하지 않습니다";
                out.style.color = "red"

                id.focus();
            } else {
                out.innerText = "존재하는 아이디입니다";
                out.style.color = "green"
            }
        }
    }
});
