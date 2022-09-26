const loginBtn = document.getElementById('login');
const logoutBtn = document.getElementById('logout');

const xhttp1 = new XMLHttpRequest();
xhttp1.addEventListener('readystatechange', (e)=> {
    const readyState = e.target.readyState;
    const responseText = e.target.responseText;

    if(readyState == 4) {
        window.location.reload();
    }
});


const xhttp3 = new XMLHttpRequest();

xhttp3.addEventListener('readystatechange', (e)=> {
    const readyState = e.target.readyState;
    const responseText = e.target.responseText;

    if(readyState == 4) {
        
    }
});


//���������̼� Ŭ�� �� �׼�
$('.pagination').children('li').on('click', function(e) {
    console.log($(this).text());
})

window.addEventListener('load',() => {
	
    if(document.getElementById('product-list-container').childElementCount < 1) {
        document.getElementById('product-list-container').innerHTML = `<h3>��ǰ�� �������� �ʽ��ϴ�.</h3>`;
    }
    
    const nicknameAjax = document.getElementsByClassName('nickname-ajax');
	for(let i = 0; i < nicknameAjax.length; ++i) {
        const memberId = document.getElementsByClassName('nickname-ajax')[i].getAttribute('name'); 
        const xhttp4 = new XMLHttpRequest();
        xhttp4.open('GET', '/farmocean/prod/get_member_nickname_by_member_id/' + memberId);
        xhttp4.send();
        xhttp4.addEventListener('readystatechange', (e)=> {
            const readyState = e.target.readyState;
            if(readyState == 4) {
                const nickname = e.target.responseText;
                document.getElementsByClassName('nickname-ajax')[i].innerText = nickname;	        
            }

        });
        
        
	}
});




const prod_sellArr = document.getElementsByClassName('prod_sell');
//jsp���Ͽ��� <tr><td class="prod_sell" data-deadline="${product.prod_sell_deadline}"></td></tr> 
/*
�Ǹſ��� �ؽ�Ʈ�� ������ td�±׿��� 
Ŀ���ҼӼ� data-deadline�� �������� prod_sell_deadline(Timestamp ���� ��¥)�� �����س���
prod_sell_deadline�� date�� ��ȯ �� ���� ��¥�� ��� date�� ���� ��
������<=���ó�¥ : �Ǹ����� 
������>���ó�¥ : �Ǹ���  

�±׿� ������ Ŀ���� �Ӽ����� .getAttribute('Ŀ���ҼӼ���');���� ������ �� ����
*/

for(var prod_sell of prod_sellArr) {
    var ts = prod_sell.getAttribute('data-deadline');
    var deadlineDate = new Date(ts);
    var now = new Date();
    // if(deadlineDate <= now ) {
    //     prod_sell.innerHTML = '<span style="color: rgb(133, 170, 255);">�Ǹ�����</span>';
    // } else if(deadlineDate > now) {
    //     prod_sell.innerHTML = '<span style="color: rgb(0, 76, 255);">�Ǹ���</span>';
    // } 
    if(deadlineDate <= now ) {
        prod_sell.innerHTML = '<span style="color: rgb(133, 170, 255);">'+deadlineDate.getFullYear() + '/' + (deadlineDate.getMonth()+1) + '/' +deadlineDate.getDate()+' ����'+'</span>';
    } else if(deadlineDate > now) {
        prod_sell.innerHTML = '<span style="color: rgb(0, 76, 255);">'+deadlineDate.getFullYear() + '/' + (deadlineDate.getMonth()+1) + '/' +deadlineDate.getDate()+' ����'+'</span>';
    } 
}





const prodPrice = document.getElementsByClassName('prod-price');

for(p of prodPrice) {
    var intPrice = p.getAttribute('data-price');
    var strPrice = intPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '��';
    p.innerText = strPrice;
}




