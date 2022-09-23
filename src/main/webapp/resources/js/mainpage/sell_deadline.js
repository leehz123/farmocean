// const prod_sellArr = document.getElementsByClassName('prod_sell');
//jsp���Ͽ��� <tr><td class="prod_sell" data-deadline="${product.prod_sell_deadline}"></td></tr> 
/*
�Ǹſ��� �ؽ�Ʈ�� ������ td�±׿��� 
Ŀ���ҼӼ� data-deadline�� �������� prod_sell_deadline(Timestamp ���� ��¥)�� �����س���
prod_sell_deadline�� date�� ��ȯ �� ���� ��¥�� ��� date�� ���� ��
������<=���ó�¥ : �Ǹ����� 
������>���ó�¥ : �Ǹ���  

�±׿� ������ Ŀ���� �Ӽ����� .getAttribute('Ŀ���ҼӼ���');���� ������ �� ����
*/

window.addEventListener('load', () => {
    const prod_sellArr = document.getElementsByClassName('ls_prod_sell');
    for(var prod_sell of prod_sellArr) {
        var ts = prod_sell.getAttribute('data-deadline');
        var deadlineDate = new Date(ts);
        var now = new Date();
        if(deadlineDate <= now ) {
            prod_sell.innerHTML = '<span style="color: rgb(133, 170, 255);">'+deadlineDate+'</span>';
        } else if(deadlineDate > now) {
            prod_sell.innerHTML = '<span style="color: rgb(0, 76, 255);">'+deadlineDate+'</span>';
        } 
    }

});