

const editContainer = document.getElementById('edit-container');
const container = document.getElementById('container');

const title = document.getElementById('title');
const content = document.getElementById('editor1'); //근데 ck에디터는 이걸로 value 못 가져옴. 일단 텍스트에리어가 있는지 없는지 체크하는 용으로만 놔둠.
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
            width : 1000,  // 입력창의 넓이, 넓이는 config.js 에서 % 로 제어
            height : 500,  // 입력창의 높이
            startupFocus : false
        });

    
}


if(deadline != null) {
    //input datetime-local 오늘 날짜 이후로만 선택 가능하게 하기
    deadline.value= new Date().toISOString().slice(0, -1);
    var now_utc = Date.now(); // 지금 날짜를 밀리초로
    // getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
    var timeOff = new Date().getTimezoneOffset()*60000; // 분단위를 밀리초로 변환
    // new Date(today-timeOff).toISOString()은 '2022-09-05T23:17:38.134Z'를 반환
    var today = new Date(now_utc-timeOff).toISOString().substring(0, 16);
    deadline.setAttribute("min", today);
}




function formNullChk() {

    const textVal = CKEDITOR.instances['editor1'].getData();

    if(title != null || content != null || 
        price != null || stock != null ||
        deadline != null || cate != null) {
        console.log('타이틀 : ', title);
        console.log('내용 : ', textVal);
        console.log('가격 : ', price);
        console.log('재고 : ', stock);
        console.log('마감일 : ', deadline); 
        console.log('카테 : ', cate);

        if(title.value.length < 1 || textVal.length < 1 ||
            price.value.length < 1 || stock.value.length < 1 || 
            deadline.value.length < 1 || cate.value.length < 1) {
            console.log('타이틀 값 : ', title.value);
            console.log('내용 값 : ',textVal); //if문에서 contentValue.length < 1 || 일단 뺌
            console.log('가격 값 : ',price.value);
            console.log('재고 값 : ',stock.value);
            console.log('마감일 값 : ',deadline.value);
            console.log('카테 값 : ',cate.value);

            return true;
        }
        return false;        
    }
};


if(btnIns != null) {
    btnIns.addEventListener('click', (e)=> {
        if(formNullChk()) {
            alert('비어 있는 항목을 모두 입력해주세요.');		
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



