

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

const btnIns = document.getElementById('btn-ins');
const frmIns = document.getElementById('frm-ins');
const resetBtn = document.getElementById('reset-btn');

let thumbIdx = 0;

var fileNo = 0;
var filesArr = new Array();
let filePaths = new Array();

//btnIns.addEventListener('click', (e)=> {


    //e.preventDefault(); 
    


    // �������� ���
    // var form = $('form[id=frm-ins]')[0];        
    // var formData = new FormData(form);

//    var formData = $('#frm-ins').serialize(); �̰� �� �ٸ� ���ڵ� ������ ��.. ���ڰ� ���� ���µ� RequestBody �� �޾��� �� %EC%BD%30�̷������� ������ ��

    //formData.append('filePaths', filePaths); �̷��� �߰��ϸ� form ���� input�� ���ִ� �ѱ� �����͵��� ��Ʈ�ѷ����� ������ ��Ÿ�� � ����� �ᵵ �ذ��� �� �ż� �׳� input hidden�� �ְ� ��
    
    // var formData = {
    //                 member_id: document.getElementById('member-id').value,	
    //                 prod_idx: document.getElementById('prod-idx').value,
    //                 file_paths: document.getElementById('file-paths').value,
    //                 review_starnum: whichRadioBtnSelected(),
    //                 review_content: document.getElementById('review-content').value
    //                 };

    // $.ajax({
    //     method: 'POST',
    //     url: '/farmocean/insert_prod',
    //     enctype: 'application/x-www-form-urlencoded',
    //     data: formData,
    //     processData: false,        
    //     contentType: false,
    //     caches: false,
    //     success: function (data) {
    //         if(data.result == 1) {
    //             alert("��ǰ �Խñ��� ��ϵǾ����ϴ�.");
    //             //location.href = '';
    //         } else {
    //             alert("��ǰ �Խñ� ��Ͽ� �����߽��ϴ�. �ٽ� �õ����ּ���.");
    //         }
    //     },
    //     error: function (xhr, desc, err) {
    //         alert('������ �߻� �Ͽ����ϴ�.');
    //         return;
    //     }
    // });	

//});



if(editContainer != null) {
    CKEDITOR.replace('editor1',
        {
            filebrowserUploadUrl: '/farmocean/prod_detail_write_img_upload/prod_img',
            width : 1260,  // �Է�â�� ����, ���̴� config.js ���� % �� ����
            height : 400,  // �Է�â�� ����
            startupFocus : false
        });

    
}


// if(deadline != null) {
//     //input datetime-local ���� ��¥ ���ķθ� ���� �����ϰ� �ϱ�
//     deadline.value= new Date().toISOString().slice(0, -1);
//     var now_utc = Date.now(); // ���� ��¥�� �и��ʷ�
//     // getTimezoneOffset()�� ���� �ð����� ���̸� �� ������ ��ȯ
//     var timeOff = new Date().getTimezoneOffset()*60000; // �д����� �и��ʷ� ��ȯ
//     // new Date(today-timeOff).toISOString()�� '2022-09-05T23:17:38.134Z'�� ��ȯ
//     var today = new Date(now_utc-timeOff).toISOString().substring(0, 16);
//     deadline.setAttribute("min", today);
// }




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



// function submitEvent() {
//     if(formNullChk()) {
//         alert('��� �ִ� �׸��� ��� �Է����ּ���.');		
//     } else {
//         $.ajax({
//             url: $('#frm-ins').attr('action'),
//             type: 'POST',
//             data : $('#frm-ins').serialize(),
//             success: function(data){
//                 if(data.result == 1) {
//                     alert('��ǰ�� ��ϵǾ����ϴ�.');
//                     location.href = '/farmocean/product/detail/' + data.prod_idx;
//                 } else if(data.result == -1) {
//                     alert('��ǰ ��Ͽ� �����߽��ϴ�.');
//                     return false;
//                 } 
//             }
//         });
//     }
// }



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


// �Ǹ������� ���� ���ķθ� ������ �� �ְ� �ϴ� ��
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











const prodRegister = function prodRegister() {
    
	// �������� ���
	//var form = $('form[id=form1]')[0];        
	//var formData = new FormData(form);

	//formData.append('filePaths', filePaths); �̷��� �߰��ϸ� form ���� input�� ���ִ� �ѱ� �����͵��� ��Ʈ�ѷ����� ������ ��Ÿ�� � ����� �ᵵ �ذ��� �� �ż� �׳� input hidden�� �ְ� ��
	
	var formData = {
					file_paths: document.getElementById('file-paths').value,
					prod_name: document.getElementById('prod-name').value,
                    prod_info: CKEDITOR.instances['editor1'].getData(),
                    cate_idx: document.getElementById('cate-idx').value,
                    prod_price: document.getElementById('prod-price').value,
                    prod_sell_deadline: document.getElementById('prod-sell-deadline').value,
                    prod_stock: document.getElementById('prod-stock').value
					};

	$.ajax({
        method: 'POST',
        url: '/farmocean/insert_prod',
        data: JSON.stringify(formData),
        async: true,
        cache: false,
        processData: false,
	    contentType: 'application/json; charset=UTF-8',
        success: function (resultData) {
            if(resultData.result == 1) {
            	alert("��ǰ�� ��ϵǾ����ϴ�.");
                location.href = '/farmocean/product/detail/' + resultData.prod_idx;
        	} else if(resultData.result == -1) {
        		alert("��ǰ ��Ͽ� �����߽��ϴ�. �ٽ� �õ����ּ���.");
        	}
        },
        error: function (xhr, desc, err) {
            alert("������ ������ ó���� �����ǰ��ֽ��ϴ�. ��� �� �ٽ� �õ����ּ���.");
            return;
        }
    });	
}








if(btnIns != undefined) {
    btnIns.addEventListener('click', (e)=> {

        e.preventDefault();
    
        var form = $('fake-form')[0];        
        var formData = new FormData(form);
    
        // ����Ϸ� ���õ� �̹����� �� �տ� �� ��
        formData.append('attach_file', filesArr[thumbIdx]);
    
        for (var i = 0; i < filesArr.length; i++) {
            if(i == thumbIdx) { //������� �̹����� �̹� ù��°�� �־�����ϱ� ����
                continue;
            } 
    
            if (!filesArr[i].is_delete) { // �������� ���� ���ϸ� �������Ϳ� ���
                formData.append('attach_file', filesArr[i]);
            }
        }
                
        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            url: '/farmocean/prod/upload_prod_image',
            //dataType: 'json', 
            data : formData,
            async :true,
            cache: false,
            processData: false,
            contentType: false,
            success: function (data) {
    
                if(data.result == null){
                    alert("������ ������ ó���� �����ǰ��ֽ��ϴ�. ��� �� �ٽ� �õ����ּ���");
                } else {
    
                    //���ε� ��� Ȯ�ο�
                    for(let i = 0; i < data.result.length; ++i) {
                        console.log(i + '��°�� ���ε�� �̹��� : ' + data.result[i]);    		
                        }
        
    
                    filePaths = data.result;
                    //alert('�̹��� ���ε� �Ϸ�');				                    
                    document.getElementById('file-paths').value = filePaths.join('#');
    
                    prodRegister();
    
                }
    
            },
            error: function (xhr, status, error) {
                alert("���������� �����ǰ��ֽ��ϴ�. ��� �� �ٽ� �õ����ֽñ� �ٶ��ϴ�.");
            return false;
            }
        });
    
    });
}