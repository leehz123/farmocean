

const editContainer = document.getElementById('edit-container');
const container = document.getElementById('container');

const title = document.getElementById('title');
const content = document.getElementById('editor1'); //�ٵ� ck�����ʹ� �̰ɷ� value �� ������. �ϴ� �ؽ�Ʈ����� �ִ��� ������ üũ�ϴ� �����θ� ����.
//const contentValue = CKEDITOR.instances.editor1.getData();
const price = document.getElementById('price');
const stock = document.getElementById('stock');
const deadline = document.getElementById('deadline');
const cate = document.getElementById('cate');
const btnContainer = document.getElementById('btn-container');

const btnIns = document.getElementById('btn-ins');
const frmIns = document.getElementById('frm-ins');
const resetBtn = document.getElementById('reset-btn');




if(editContainer != null) {
    CKEDITOR.replace('editor1',
        {
            filebrowserUploadUrl: '/farmocean/prod_detail_write_img_upload/prod_img',
            width : 1000,  // �Է�â�� ����, ���̴� config.js ���� % �� ����
            height : 500,  // �Է�â�� ����
            startupFocus : false
        });

    
}


if(deadline != null) {
    //input datetime-local ���� ��¥ ���ķθ� ���� �����ϰ� �ϱ�
    deadline.value= new Date().toISOString().slice(0, -1);
    var now_utc = Date.now(); // ���� ��¥�� �и��ʷ�
    // getTimezoneOffset()�� ���� �ð����� ���̸� �� ������ ��ȯ
    var timeOff = new Date().getTimezoneOffset()*60000; // �д����� �и��ʷ� ��ȯ
    // new Date(today-timeOff).toISOString()�� '2022-09-05T23:17:38.134Z'�� ��ȯ
    var today = new Date(now_utc-timeOff).toISOString().substring(0, 16);
    deadline.setAttribute("min", today);
}




function formNullChk() {

    const textVal = CKEDITOR.instances['editor1'].getData();

    if(title != null || content != null || 
        price != null || stock != null ||
        deadline != null || cate != null) {
        console.log('Ÿ��Ʋ : ', title);
        console.log('���� : ', textVal);
        console.log('���� : ', price);
        console.log('��� : ', stock);
        console.log('������ : ', deadline); 
        console.log('ī�� : ', cate);

        if(title.value.length < 1 || textVal.length < 1 ||
            price.value.length < 1 || stock.value.length < 1 || 
            deadline.value.length < 1 || cate.value.length < 1) {
            console.log('Ÿ��Ʋ �� : ', title.value);
            console.log('���� �� : ',textVal); //if������ contentValue.length < 1 || �ϴ� ��
            console.log('���� �� : ',price.value);
            console.log('��� �� : ',stock.value);
            console.log('������ �� : ',deadline.value);
            console.log('ī�� �� : ',cate.value);

            return true;
        }
        return false;        
    }
};


if(btnIns != null) {
    btnIns.addEventListener('click', (e)=> {
        if(formNullChk()) {
            alert('��� �ִ� �׸��� ��� �Է����ּ���.');		
        } else {
            frmIns.submit();		
        }
        formNullChk();
    });
}

if(stock != null ) {
    stock.onkeydown = function(e) {
        if(!((e.keyCode > 95 && e.keyCode < 106)
            || (e.keyCode > 47 && e.keyCode < 58) 
            || e.keyCode == 8)) {
            return false;
        }
    }    
}
if(price != null ) {
    price.onkeydown = function(e) {
        if(!((e.keyCode > 95 && e.keyCode < 106)
            || (e.keyCode > 47 && e.keyCode < 58) 
            || e.keyCode == 8)) {
            return false;
        }
    }    
}



