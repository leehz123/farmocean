

const editContainer = document.getElementById('edit-container');
const container = document.getElementById('container');

const title = document.getElementById('prod-name');
const content = document.getElementById('editor1'); //근데 ck에디터는 이걸로 value 못 가져옴. 일단 텍스트에리어가 있는지 없는지 체크하는 용으로만 놔둠.
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
let thumbnailPath = ''; //올드 이미지 중 썸넬로 선택된 이미지의 경로
let thumbIdx = null;
let deletedOldImgStr = new Array(); //(기존이미지 중) 삭제될 이미지 경로 모음 (컨트롤러로 보낼 것)
let imgAttachChange  = false;
let primaryFileNum = 0;







if(editContainer != null) {
    CKEDITOR.replace('editor1',
        {
            filebrowserUploadUrl: '/farmocean/prod_detail_write_img_upload/prod_img',
            width : 1260,  // 입력창의 넓이, 넓이는 config.js 에서 % 로 제어
            height : 400,  // 입력창의 높이
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
	
	//현재 창의 주소값 맨 뒤에 붙은 prod_idx 구하기
    var path = window.location.pathname;

    prodIdx = path.replace('/farmocean/product/product_detail_edit/', '');
     
    //판매자 본인과 "sample63"(마스터 셀러)계정만 수정 가능
    const xhttp2 = new XMLHttpRequest();
    xhttp2.open('Get', '/farmocean/authentication_seller/' + prodIdx);
    xhttp2.send();
    xhttp2.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;
        if(readyState == 4) {
            const result = e.target.responseText;
            if(result != 1) {
                alert('판매자 본인만 수정할 수 있습니다.');
                editContainer.remove();
                container.innerHTML = '판매자 본인만 수정할 수 있습니다.';
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
                    thumbnailPath = filePath; //첫번째로 띄워지는 기존 이미지는 기존의 대표이미지
                }


                previewCont.innerHTML +=
                                            `<div class="img-cont" id="old-img-cont` + i + `">
                                                <label for="radio` + i + `">
                                                    <img class="img old-img" id="old-img` + i + `" src="`+ filePath +`" alt="" onclick="oldThumb(` + i + `);"/>	
                                                </label>														
                                                <button class="img-delete" onclick="deleteOldPreview(` + i + `);">삭제</button>
                                                </div>`;
                                            }
                                            //위에서 뺌 <input type="radio" id="radio` + i + `" class="thumb-radio" name="thumbnail" value="` + i + `"/>
        //fileNo = previewCont.childElementCount;
        }
    });

    primaryFileNum = previewCont.childElementCount;
});








/* 첨부파일 검증 */
function validation(obj){
    const fileTypes = ['image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif'];
    if (obj.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (obj.size > (7 * 1024 * 1024)) {
        alert("최대 파일 용량인 7MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("이미지 파일만 첨부할 수 있습니다.");
        return false;
    } else {
        return true;
    }
}



/* 첨부파일 추가 */
function addFile(obj){
    var maxFileCnt = 5;   // 첨부파일 최대 개수
    var attFileCnt = document.querySelectorAll('.img-cont').length;    // 기존 추가된 첨부파일 개수
    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    var curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수(파일 선택창에서 ctrl 로 파일 여러개 선택 가능)

    // 첨부파일 개수 확인
    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + " 개까지 첨부 가능합니다.");
    }

    for (var i = 0; i < Math.min(curFileCnt, remainFileCnt); i++) { //선택한 파일이 4개고 추가로 첨부 가능한 파일 개수가 1개면 1개만 업로드 됨
		
        const file = obj.files[i];

        // 첨부파일 검증
        if (validation(file)) {
            // 파일 배열에 담기
            var reader = new FileReader();
            reader.onload = function (e) {    
			
			previewCont.innerHTML +=
										`<div class="img-cont" id="img-cont` + (fileNo) + `">
											<label for="radio` + (fileNo) + `">
												<img class="img" id="new-img` + (fileNo) + `" data-fileNo="` + (fileNo) + `" src="`+ e.target.result +`" alt="" onclick="newThumb(` + (fileNo) + `);"/>	
											</label>														
											<input type="radio" id="radio` + (fileNo) + `" class="thumb-radio" name="thumbnail" value="` + (fileNo) + `"/>
											<button class="img-delete" onclick="deleteFile(` + (fileNo) + `);">삭제</button>
										</div>`;
			filesArr.push(file);
            };
            reader.readAsDataURL(file);

            fileNo++;
        } else {
            continue;
        }
    }
    // 초기화
    document.querySelector("input[type=file]").value = "";
}


//파일 업로드 버튼 체인지 이벤트
const fileInput = document.getElementById('file-input');
$("#file-input").unbind("change").bind("change",function(){
    addFile(fileInput);
});


/* 첨부파일 검증 */
function validation(obj){
    const fileTypes = ['image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif'];
    if (obj.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (obj.size > (7 * 1024 * 1024)) {
        alert("최대 파일 용량인 7MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("이미지 파일만 첨부할 수 있습니다.");
        return false;
    } else {
        return true;
    }
}






/* 기존 이미지는 미리보기 삭제 시 미리보기만 삭제 + 삭제된 경로 배열에 추가 */
function deleteOldPreview(num) {

    //첫번째 기존이미지는 썸네일 이미지니까 삭제될 때 썸넬변수도 비워줘야.
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
    //console.log('삭제될 이미지 주소들 : ' + deletedOldImgStr);

    imgAttachChange = true; 
}



/* 첨부파일 삭제 */
function deleteFile(num) {
	console.log('delete file(num) : ' , num);
    document.querySelector("#img-cont" + num).remove();
	filesArr[num-1].is_delete = true;
}





// 이미지 클릭 시 클릭된 이미지만 테두리 적용 (기존이미지 ver)
function oldThumb(num) {

    thumbIdx = null;

    //new 든 old든 다 테두리 없애준 다음에 
	const imgList = document.getElementsByClassName('img');
	for(let i = 0; i < imgList.length; ++i) {
		imgList[i].style.border = 'none';
	}
    //선택된 old이미지에 테두리 적용
	document.getElementById('old-img' + num).style.border = '3px solid rgb(96, 152, 255)';
    
    //대표이미지 주소 변수에 저장
    thumbnailPath = $("#old-img" + num).attr('src');

    imgAttachChange = true; 
}


// 이미지 클릭 시 클릭된 이미지만 테두리 적용 (새로 추가된 이미지 ver)
function newThumb(num) {
    
    thumbnailPath = '';

	const imgList = document.getElementsByClassName('img');
	for(let i = 0; i < imgList.length; ++i) {
		imgList[i].style.border = 'none';
	}
	document.getElementById('new-img' + num).style.border = '3px solid rgb(96, 152, 255)';    
    
    // filesArr 썸네일 이미지 인덱스 저장
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
                alert("상품이 수정되었습니다.");
                location.href = '/farmocean/product/detail/' + data.prod_idx;
            } else if(data.result == -1){   
                alert("상품 수정에 실패했습니다. 다시 시도해주세요.");
            }
        },
        error: function (xhr, desc, err) {
            alert('에러가 발생 하였습니다.');
            return;
        }
    });	
}


// 최종 등록 버튼 눌렀을 때 이벤트
if(updateBtn != null) {
    updateBtn.addEventListener('click', (e)=> {
	    e.preventDefault();

        if(formNullChk()) {
            alert('비어 있는 항목을 모두 입력해주세요.');
        } else if(previewCont.childElementCount < 1) {
            alert('상품 이미지를 첨부해주세요.');
        } else {



            //새로 추가된 이미지가 있을 경우
            if(filesArr.length != 0) {

                imgAttachChange = true;
                
                var form = $('fake-form')[0];        
                var formData = new FormData(form);
            
                //새로 추가된 파일 중에 썸네일 이미지 등록 돼 있으면
                if(thumbIdx != null) {
                    // 썸네일로 선택된 이미지가 맨 앞에 들어갈 것
                    formData.append('attach_file', filesArr[thumbIdx]);
                }            
            
                for (var i = 0; i < filesArr.length; i++) {
                    if(i == thumbIdx) { //썸네일인 이미지는 이미 첫번째로 넣어놨으니까 제외
                        continue;
                    } 
            
                    if (!filesArr[i].is_delete) { // 삭제되지 않은 파일만 폼데이터에 담기
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
                            alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
                        } else {
            
                            //업로드 경로 확인용
                            //for(let i = 0; i < data.result.length; ++i) {console.log(i + '번째로 업로드된 이미지 : ' + data.result[i]);}            
    
                                // 기존 이미지 중에 썸네일로 선택된 게 없다면
                                if(thumbnailPath == '') {
                                    if(thumbIdx == null) {
                                        //올드 이미지 먼저 추가 
                                        $('.old-img').each(function(){
                                            if($(this).attr('src').includes('http')) {
                                                filePaths.push($(this).attr('src')); 
                                            } else {
                                                filePaths.push($(this).attr('src').substring(10)); 
                                            }
                                        });                                                         

                                        //뉴이미지 나중에 추가
                                        for(imgUrl of data.result) {
                                            filePaths.push(imgUrl); 
                                        }
                                    } else {
                                        filePaths = data.result;//뉴 이미지경로 먼저 삽입 (첫번째가 썸넬이니까 따로 구분할 필요 없음)    
                                        $('.old-img').each(function(){
                                            if($(this).attr('src').includes('http')) {
                                                filePaths.push($(this).attr('src')); 
                                            } else {
                                                filePaths.push($(this).attr('src').substring(10)); 
                                            }
                                        });                                                                                                 
                                    }

                                
                                //기존 이미지 중에 썸네일로 선택된 게 있다면
                                } else {
                                    
                                    //올드 이미지들
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
        
                                    //뉴 이미지 나중에 추가
                                    for(imgUrl of data.result) {
                                        filePaths.push(imgUrl); 
                                    }
                                }
                                
                            
                            const filePathsStr = filePaths.join('#');
                            
                            submitForm(filePathsStr);
            
                        }
            
                    },
                    error: function (xhr, status, error) {
                        alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
                    return false;
                    }
                });
            
            
            
            } else {


                // 기존 이미지 중에 썸네일로 선택된 게 없다면
                if(thumbnailPath == '') { 
                    
                    $('.old-img').each(function(){
                                                    if($(this).attr('src').includes('http')) {
                                                        filePaths.push($(this).attr('src')); 
                                                    } else {
                                                        filePaths.push($(this).attr('src').substring(10)); 
                                                    }
                                                });                                                         
                
                //기존 이미지 중에 썸네일로 선택된 게 있다면
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