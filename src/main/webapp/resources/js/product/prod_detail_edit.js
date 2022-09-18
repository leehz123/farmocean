

const editContainer = document.getElementById('edit-container');
const container = document.getElementById('container');

const title = document.getElementById('prod-name');
const content = document.getElementById('editor1'); //�ٵ� ck�����ʹ� �̰ɷ� value �� ������. �ϴ� �ؽ�Ʈ����� �ִ��� ������ üũ�ϴ� �����θ� ����.
//const contentValue = CKEDITOR.instances.editor1.getData();
const price = document.getElementById('prod-price');
const stock = document.getElementById('prod-stock');
const deadline = document.getElementById('prod-sell-deadline');
const cate = document.getElementById('cate-idx');
const btnContainer = document.getElementById('btn-container');
const previewCont = document.getElementById('preview-cont');

const updateBtn = document.getElementById('update-btn'); 
const frmIns = document.getElementById('frm-ins');
const resetBtn = document.getElementById('reset-btn');


let thumbIdx = 0;

var fileNo = 0;
var filesArr = new Array();
var prodIdx = ''; 

let filePaths = new Array();



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





function date_to_str(format){
    // Sat Nov 26 2022 05:10:27 GMT+0900 -> yyyy-MM-ddThh:mm:ss or yyyy-MM-ddThh:mm:ss.SSS
    var year = format.getFullYear();
    var month = format.getMonth() + 1;
    if(month<10) month = '0' + month;
    var date = format.getDate();
    if(date<10) date = '0' + date;
    var hour = format.getHours();
    if(hour<10) hour = '0' + hour;
    var min = format.getMinutes();
    if(min<10) min = '0' + min;
    var sec = format.getSeconds();
    if(sec<10) sec = '0' + sec;
    return year + "-" + month + "-" + date + "T" + hour + ":" + min + ":" + sec;
}



if(updateBtn != null) {
    updateBtn.addEventListener('click', (e)=> {
	    
        if(formNullChk()) {
            alert('��� �ִ� �׸��� ��� �Է����ּ���.');		
        } else {
	        let additionalInput = document.createElement('input');
	        additionalInput.setAttribute('name', 'prod_idx');      //name
			additionalInput.setAttribute('value', prodIdx);        //value			
			frmIns.appendChild(additionalInput);                 //from			        
            frmIns.submit();		
        }
        formNullChk();
    });
}



window.addEventListener('load',() => {
	
	//���� â�� �ּҰ� �� �ڿ� ���� prod_idx ���ϱ�
    var path = window.location.pathname;
	// /farmocean/product/product_detail_edit/{prod_idx}
    prodIdx = path.replace('/farmocean/product/product_detail_edit/', '');
            
    const xhttp2 = new XMLHttpRequest();
    xhttp2.open('Get', '/farmocean/authentication_seller/' + prodIdx);
    xhttp2.send();
    xhttp2.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;
        if(readyState == 4) {
            const result = e.target.responseText;
            if(result != 1) {
                alert('�Ǹ��� ���θ� ������ �� �ֽ��ϴ�.');
                editContainer.remove();
                container.innerHTML = '�Ǹ��� ���θ� ������ �� �ֽ��ϴ�.';
            }
        }
    });



    const xhttp1 = new XMLHttpRequest();
    xhttp1.open('Get', '/farmocean/prod/get_product/' + prodIdx);
    xhttp1.send();
    xhttp1.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;

        if(readyState == 4) {
            const responseText = e.target.responseText;
            const product = JSON.parse(responseText);
            
            if(title != null || content != null || 
                price != null || stock != null ||
                deadline != null || cate != null) {
                    title.value = product.prod_name;
                    CKEDITOR.instances.editor1.setData(product.prod_info);
                    price.value = product.prod_price;
                    stock.value = product.prod_stock;
                    $("#cate").val(product.cate_idx).prop("selected", true);
                    deadline.value = date_to_str(new Date(product.prod_sell_deadline));
            }
        }
    });

    const xhttp15 = new XMLHttpRequest();
    xhttp15.open('GET', '/farmocean/prod/get_product_img/' + prodIdx);
    xhttp15.send();
    xhttp15.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;
        if(readyState == 4) {
            
            const responseText = e.target.responseText;
            const imgList = JSON.parse(responseText);
            for(let i = 0; i < imgList.length; ++i) {
                previewCont.innerHTML +=
                                            `<div class="img-cont" id="img-cont` + (fileNo) + `">
                                                <label for="radio` + (fileNo) + `">
                                                    <img class="img" id="img` + (fileNo) + `" data-fileNo="` + (fileNo) + `" src="`+ imgList[i].img_url +`" alt="" onclick="thumb(` + (fileNo) + `);"/>	
                                                </label>														
                                                <input type="radio" id="radio` + (fileNo) + `" class="thumb-radio" name="thumbnail" value="` + (fileNo) + `"/>
                                                <button class="img-delete" onclick="deleteFile(` + (fileNo) + `);">����</button>
                                            </div>`;
            }

        }
    });



    

});






/* ÷������ �߰� */
function addFile(obj){
    var maxFileCnt = 5;   // ÷������ �ִ� ����
    var attFileCnt = document.querySelectorAll('.img-cont').length;    // ���� �߰��� ÷������ ����
    var remainFileCnt = maxFileCnt - attFileCnt;    // �߰��� ÷�ΰ����� ����
    var curFileCnt = obj.files.length;  // ���� ���õ� ÷������ ����(���� ����â���� ctrl �� ���� ������ ���� ����)

    // ÷������ ���� Ȯ��
    if (curFileCnt > remainFileCnt) {
        alert("÷�������� �ִ� " + maxFileCnt + " ������ ÷�� �����մϴ�.");
    }

    for (var i = 0; i < Math.min(curFileCnt, remainFileCnt); i++) { //������ ������ 4���� �߰��� ÷�� ������ ���� ������ 1���� 1���� ���ε� ��
		
        const file = obj.files[i];
		var imgPreview;
        // ÷������ ����
        if (validation(file)) {
            // ���� �迭�� ���
            var reader = new FileReader();
            reader.onload = function (e) {    
			
			previewCont.innerHTML +=
										`<div class="img-cont" id="img-cont` + (fileNo) + `">
											<label for="radio` + (fileNo) + `">
												<img class="img" id="img` + (fileNo) + `" data-fileNo="` + (fileNo) + `" src="`+ e.target.result +`" alt="" onclick="thumb(` + (fileNo) + `);"/>	
											</label>														
											<input type="radio" id="radio` + (fileNo) + `" class="thumb-radio" name="thumbnail" value="` + (fileNo) + `"/>
											<button class="img-delete" onclick="deleteFile(` + (fileNo) + `);">����</button>
										</div>`;
			filesArr.push(file);
            };
            reader.readAsDataURL(file);
			
            // ��� �߰�
            // let htmlData = '';
            // htmlData += '<div id="file' + fileNo + '" class="filebox">';
            // console.log('���ϸ�div id : file', fileNo);
            // htmlData += '   <p class="name">' + file.name + '</p>';
            // htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');">����</a>';
            // htmlData += '</div>';
            // $('#file-list').append(htmlData);
            fileNo++;
        } else {
            continue;
        }
    }
    // �ʱ�ȭ
    document.querySelector("input[type=file]").value = "";
}


/* ÷������ ���� */
function validation(obj){
    const fileTypes = ['image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif'];
    if (obj.name.length > 100) {
        alert("���ϸ��� 100�� �̻��� ������ ���ܵǾ����ϴ�.");
        return false;
    } else if (obj.size > (7 * 1024 * 1024)) {
        alert("�ִ� ���� �뷮�� 7MB�� �ʰ��� ������ ���ܵǾ����ϴ�.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("Ȯ���ڰ� ���� ������ ���ܵǾ����ϴ�.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("�̹��� ���ϸ� ÷���� �� �ֽ��ϴ�.");
        return false;
    } else {
        return true;
    }
}


/* ÷������ ���� */
function deleteFile(num) {
	console.log('delete file(num) : ' , num);
    document.querySelector("#img-cont" + num).remove();
	filesArr[num-1].is_delete = true;

}






// �̹��� Ŭ�� �� Ŭ���� �̹����� �׵θ� ����
function thumb(num) {
	const imgList = document.getElementsByClassName('img');
	for(let i = 0; i < imgList.length; ++i) {
		imgList[i].style.border = 'none';
	}
	document.getElementById('img' + num).style.border = '3px solid rgb(96, 152, 255)';
    thumbIdx = num-1;
}
