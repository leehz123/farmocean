

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

var fileNo = 0;
var prodIdx = ''; 
let filesArr = new Array();
let filePaths = new Array();
let thumbnailPath = ''; //�õ� �̹��� �� ��ڷ� ���õ� �̹����� ���
let thumbIdx = null;
let deletedOldImgStr = new Array(); //(�����̹��� ��) ������ �̹��� ��� ���� (��Ʈ�ѷ��� ���� ��)
let imgAttachChange  = false;
let primaryFileNum = 0;







if(editContainer != null) {
    CKEDITOR.replace('editor1',
        {
            filebrowserUploadUrl: '/farmocean/prod_detail_write_img_upload/prod_img',
            width : 1260,  // �Է�â�� ����, ���̴� config.js ���� % �� ����
            height : 400,  // �Է�â�� ����
            startupFocus : false
        });

    
}



function formNullChk() {

    const textVal = CKEDITOR.instances['editor1'].getData();

    if(title.value.length < 1 || textVal.length < 1 ||
        price.value.length < 1 || stock.value.length < 1 || 
        deadline.value.length < 1 || cate.value.length < 1) {        
        return true;
    }
    return false;
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




window.addEventListener('load',() => {
	
	//���� â�� �ּҰ� �� �ڿ� ���� prod_idx ���ϱ�
    var path = window.location.pathname;

    prodIdx = path.replace('/farmocean/product/product_detail_edit/', '');
     
    //�Ǹ��� ���ΰ� "sample63"(������ ����)������ ���� ����
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
                    $("#cate-idx").val(product.cate_idx).prop("selected", true);
                    deadline.value = date_to_str(new Date(product.prod_sell_deadline));
            }
        }
    });




    $.ajax ( {
        type: 'GET',
        url: '/farmocean/prod/get_product_img/' + prodIdx,
        dataType: 'json',
        async: true,
        success: function( data ) {

            const imgList = data;

            let filePath = '';

            for(let i = 0; i < imgList.length; ++i) {
            	                     
                if(imgList[i].img_url.includes('http')) {
            		filePath = imgList[i].img_url;
            	} else {
            		filePath = '/farmocean' + imgList[i].img_url;
            	} 


                if(imgList[i].main_img == 1) {
                    thumbnailPath = filePath; //ù��°�� ������� ���� �̹����� ������ ��ǥ�̹���
                }


                previewCont.innerHTML +=
                                            `<div class="img-cont" id="old-img-cont` + i + `">
                                                <label for="radio` + i + `">
                                                    <img class="img old-img" id="old-img` + i + `" src="`+ filePath +`" alt="" onclick="oldThumb(` + i + `);"/>	
                                                </label>														
                                                <button class="img-delete" onclick="deleteOldPreview(` + i + `);">����</button>
                                                </div>`;
                                            }
                                            //������ �� <input type="radio" id="radio` + i + `" class="thumb-radio" name="thumbnail" value="` + i + `"/>
        //fileNo = previewCont.childElementCount;
        }
    });

    primaryFileNum = previewCont.childElementCount;
});








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
												<img class="img" id="new-img` + (fileNo) + `" data-fileNo="` + (fileNo) + `" src="`+ e.target.result +`" alt="" onclick="newThumb(` + (fileNo) + `);"/>	
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


//���� ���ε� ��ư ü���� �̺�Ʈ
const fileInput = document.getElementById('file-input');
$("#file-input").unbind("change").bind("change",function(){
    addFile(fileInput);
});


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






/* ���� �̹����� �̸����� ���� �� �̸����⸸ ���� + ������ ��� �迭�� �߰� */
function deleteOldPreview(num) {

    //ù��° �����̹����� ����� �̹����ϱ� ������ �� ��ں����� ������.
    if(num == 0) {
        thumbnailPath = '';
    }

    //console.log('delete file(num) : ' , num);
    
    deleteImgSrc = $("#old-img" + num).attr('src');
    
    if(!deleteImgSrc.includes('http')){
        deleteImgSrc = deleteImgSrc.replace('/farmocean', '');
    }
    deletedOldImgStr.push(deleteImgSrc);
    document.querySelector("#old-img-cont" + num).remove();
    //console.log('������ �̹��� �ּҵ� : ' + deletedOldImgStr);

    imgAttachChange = true; 
}



/* ÷������ ���� */
function deleteFile(num) {
	console.log('delete file(num) : ' , num);
    document.querySelector("#img-cont" + num).remove();
	filesArr[num-1].is_delete = true;
}





// �̹��� Ŭ�� �� Ŭ���� �̹����� �׵θ� ���� (�����̹��� ver)
function oldThumb(num) {

    thumbIdx = null;

    //new �� old�� �� �׵θ� ������ ������ 
	const imgList = document.getElementsByClassName('img');
	for(let i = 0; i < imgList.length; ++i) {
		imgList[i].style.border = 'none';
	}
    //���õ� old�̹����� �׵θ� ����
	document.getElementById('old-img' + num).style.border = '3px solid rgb(96, 152, 255)';
    
    //��ǥ�̹��� �ּ� ������ ����
    thumbnailPath = $("#old-img" + num).attr('src');

    imgAttachChange = true; 
}


// �̹��� Ŭ�� �� Ŭ���� �̹����� �׵θ� ���� (���� �߰��� �̹��� ver)
function newThumb(num) {
    
    thumbnailPath = '';

	const imgList = document.getElementsByClassName('img');
	for(let i = 0; i < imgList.length; ++i) {
		imgList[i].style.border = 'none';
	}
	document.getElementById('new-img' + num).style.border = '3px solid rgb(96, 152, 255)';    
    
    // filesArr ����� �̹��� �ε��� ����
    thumbIdx = num-1;
    imgAttachChange = true; 
}









function submitForm(filePathsStr) {
   
    if(imgAttachChange == false && primaryFileNum == previewCont.childElementCount) {
        filePathsStr = 'noImgChange';
    }
   
    var formData = {
        filePathsStr : filePathsStr,
        deletedOldImgStr : deletedOldImgStr.join('#'), 
        prod_idx: prodIdx,
        prod_name: title.value,
        prod_info: CKEDITOR.instances['editor1'].getData(),
        cate_idx: cate.value,
        prod_price: price.value,
        prod_sell_deadline: deadline.value,
        prod_stock: stock.value
    };

    $.ajax({
        method: 'PUT',
        url: '/farmocean/update_prod',
        data: JSON.stringify(formData),
        async: true,
        cache: false,
        processData: false,
        contentType: 'application/json; charset=UTF-8',
        success: function (data) {
            if(data.result == 1) {
                alert("��ǰ�� �����Ǿ����ϴ�.");
                location.href = '/farmocean/product/detail/' + data.prod_idx;
            } else if(data.result == -1){   
                alert("��ǰ ������ �����߽��ϴ�. �ٽ� �õ����ּ���.");
            }
        },
        error: function (xhr, desc, err) {
            alert('������ �߻� �Ͽ����ϴ�.');
            return;
        }
    });	
}


// ���� ��� ��ư ������ �� �̺�Ʈ
if(updateBtn != null) {
    updateBtn.addEventListener('click', (e)=> {
	    e.preventDefault();

        if(formNullChk()) {
            alert('��� �ִ� �׸��� ��� �Է����ּ���.');
        } else if(previewCont.childElementCount < 1) {
            alert('��ǰ �̹����� ÷�����ּ���.');
        } else {



            //���� �߰��� �̹����� ���� ���
            if(filesArr.length != 0) {

                imgAttachChange = true;
                
                var form = $('fake-form')[0];        
                var formData = new FormData(form);
            
                //���� �߰��� ���� �߿� ����� �̹��� ��� �� ������
                if(thumbIdx != null) {
                    // ����Ϸ� ���õ� �̹����� �� �տ� �� ��
                    formData.append('attach_file', filesArr[thumbIdx]);
                }            
            
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
                            //for(let i = 0; i < data.result.length; ++i) {console.log(i + '��°�� ���ε�� �̹��� : ' + data.result[i]);}            
    
                                // ���� �̹��� �߿� ����Ϸ� ���õ� �� ���ٸ�
                                if(thumbnailPath == '') {
                                    if(thumbIdx == null) {
                                        //�õ� �̹��� ���� �߰� 
                                        $('.old-img').each(function(){
                                            if($(this).attr('src').includes('http')) {
                                                filePaths.push($(this).attr('src')); 
                                            } else {
                                                filePaths.push($(this).attr('src').substring(10)); 
                                            }
                                        });                                                         

                                        //���̹��� ���߿� �߰�
                                        for(imgUrl of data.result) {
                                            filePaths.push(imgUrl); 
                                        }
                                    } else {
                                        filePaths = data.result;//�� �̹������ ���� ���� (ù��°�� ����̴ϱ� ���� ������ �ʿ� ����)    
                                        $('.old-img').each(function(){
                                            if($(this).attr('src').includes('http')) {
                                                filePaths.push($(this).attr('src')); 
                                            } else {
                                                filePaths.push($(this).attr('src').substring(10)); 
                                            }
                                        });                                                                                                 
                                    }

                                
                                //���� �̹��� �߿� ����Ϸ� ���õ� �� �ִٸ�
                                } else {
                                    
                                    //�õ� �̹�����
                                    if(!thumbnailPath.includes('http')) {
                                        filePaths.push(thumbnailPath.replace('/farmocean', ''));
                                    } else {
                                        filePaths.push(thumbnailPath);
                                    }

                                    $('.old-img').each(function(){ 
                                                                    if(!$(this).attr('src').includes(thumbnailPath)) {
                                                                        if($(this).attr('src').includes('http')) {
                                                                            filePaths.push($(this).attr('src')); 
                                                                        } else {
                                                                            filePaths.push($(this).attr('src').substring(10)); 
                                                                        }                                                                            
                                                                    }

                                                                });
        
                                    //�� �̹��� ���߿� �߰�
                                    for(imgUrl of data.result) {
                                        filePaths.push(imgUrl); 
                                    }
                                }
                                
                            
                            const filePathsStr = filePaths.join('#');
                            
                            submitForm(filePathsStr);
            
                        }
            
                    },
                    error: function (xhr, status, error) {
                        alert("���������� �����ǰ��ֽ��ϴ�. ��� �� �ٽ� �õ����ֽñ� �ٶ��ϴ�.");
                    return false;
                    }
                });
            
            
            
            } else {


                // ���� �̹��� �߿� ����Ϸ� ���õ� �� ���ٸ�
                if(thumbnailPath == '') { 
                    
                    $('.old-img').each(function(){
                                                    if($(this).attr('src').includes('http')) {
                                                        filePaths.push($(this).attr('src')); 
                                                    } else {
                                                        filePaths.push($(this).attr('src').substring(10)); 
                                                    }
                                                });                                                         
                
                //���� �̹��� �߿� ����Ϸ� ���õ� �� �ִٸ�
                } else {
                    if(!thumbnailPath.includes('http')) {
                        filePaths.push(thumbnailPath.replace('/farmocean', ''));
                    } else {
                        filePaths.push(thumbnailPath);
                    }
                    $('.old-img').each(function(){ 
                                                    if(!$(this).attr('src').includes(thumbnailPath)) {
                                                        if($(this).attr('src').includes('http')) {
                                                            filePaths.push($(this).attr('src')); 
                                                        } else {
                                                            filePaths.push($(this).attr('src').substring(10)); 
                                                        }                                                                            
                                                    }
                                                });
                }

                const filePathsStr = filePaths.join('#');                
                
                submitForm(filePathsStr);
            }

        }

    });

}