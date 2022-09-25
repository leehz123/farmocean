

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

const btnIns = document.getElementById('btn-ins');
const frmIns = document.getElementById('frm-ins');
const resetBtn = document.getElementById('reset-btn');

let thumbIdx = 0;

var fileNo = 0;
var filesArr = new Array();
let filePaths = new Array();

//btnIns.addEventListener('click', (e)=> {


    //e.preventDefault(); 
    


    // 폼데이터 담기
    // var form = $('form[id=frm-ins]')[0];        
    // var formData = new FormData(form);

//    var formData = $('#frm-ins').serialize(); 이건 또 다른 인코딩 에러를 냄.. 문자가 가긴 가는데 RequestBody 로 받았을 때 %EC%BD%30이런식으로 깨져서 감

    //formData.append('filePaths', filePaths); 이렇게 추가하면 form 안의 input에 들어가있는 한글 데이터들이 컨트롤러에서 깨져서 나타남 어떤 방법을 써도 해결이 안 돼서 그냥 input hidden에 넣고 저
    
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
    //             alert("상품 게시글이 등록되었습니다.");
    //             //location.href = '';
    //         } else {
    //             alert("상품 게시글 등록에 실패했습니다. 다시 시도해주세요.");
    //         }
    //     },
    //     error: function (xhr, desc, err) {
    //         alert('에러가 발생 하였습니다.');
    //         return;
    //     }
    // });	

//});



if(editContainer != null) {
    CKEDITOR.replace('editor1',
        {
            filebrowserUploadUrl: '/farmocean/prod_detail_write_img_upload/prod_img',
            width : 1260,  // 입력창의 넓이, 넓이는 config.js 에서 % 로 제어
            height : 400,  // 입력창의 높이
            startupFocus : false
        });

    
}


// if(deadline != null) {
//     //input datetime-local 오늘 날짜 이후로만 선택 가능하게 하기
//     deadline.value= new Date().toISOString().slice(0, -1);
//     var now_utc = Date.now(); // 지금 날짜를 밀리초로
//     // getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
//     var timeOff = new Date().getTimezoneOffset()*60000; // 분단위를 밀리초로 변환
//     // new Date(today-timeOff).toISOString()은 '2022-09-05T23:17:38.134Z'를 반환
//     var today = new Date(now_utc-timeOff).toISOString().substring(0, 16);
//     deadline.setAttribute("min", today);
// }




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



// function submitEvent() {
//     if(formNullChk()) {
//         alert('비어 있는 항목을 모두 입력해주세요.');		
//     } else {
//         $.ajax({
//             url: $('#frm-ins').attr('action'),
//             type: 'POST',
//             data : $('#frm-ins').serialize(),
//             success: function(data){
//                 if(data.result == 1) {
//                     alert('상품이 등록되었습니다.');
//                     location.href = '/farmocean/product/detail/' + data.prod_idx;
//                 } else if(data.result == -1) {
//                     alert('상품 등록에 실패했습니다.');
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


// 판매종료일 오늘 이후로만 선택할 수 있게 하는 거
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
												<img class="img" id="img` + (fileNo) + `" data-fileNo="` + (fileNo) + `" src="`+ e.target.result +`" alt="" onclick="thumb(` + (fileNo) + `);"/>	
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


/* 첨부파일 삭제 */
function deleteFile(num) {
	console.log('delete file(num) : ' , num);
    document.querySelector("#img-cont" + num).remove();
	filesArr[num-1].is_delete = true;

}



// 이미지 클릭 시 클릭된 이미지만 테두리 적용
function thumb(num) {
	const imgList = document.getElementsByClassName('img');
	for(let i = 0; i < imgList.length; ++i) {
		imgList[i].style.border = 'none';
	}
	document.getElementById('img' + num).style.border = '3px solid rgb(96, 152, 255)';
    thumbIdx = num-1;
}











const prodRegister = function prodRegister() {
    
	// 폼데이터 담기
	//var form = $('form[id=form1]')[0];        
	//var formData = new FormData(form);

	//formData.append('filePaths', filePaths); 이렇게 추가하면 form 안의 input에 들어가있는 한글 데이터들이 컨트롤러에서 깨져서 나타남 어떤 방법을 써도 해결이 안 돼서 그냥 input hidden에 넣고 저
	
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
            	alert("상품이 등록되었습니다.");
                location.href = '/farmocean/product/detail/' + resultData.prod_idx;
        	} else if(resultData.result == -1) {
        		alert("상품 등록에 실패했습니다. 다시 시도해주세요.");
        	}
        },
        error: function (xhr, desc, err) {
            alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요.");
            return;
        }
    });	
}








if(btnIns != undefined) {
    btnIns.addEventListener('click', (e)=> {

        e.preventDefault();
    
        var form = $('fake-form')[0];        
        var formData = new FormData(form);
    
        // 썸네일로 선택된 이미지가 맨 앞에 들어갈 것
        formData.append('attach_file', filesArr[thumbIdx]);
    
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
                    for(let i = 0; i < data.result.length; ++i) {
                        console.log(i + '번째로 업로드된 이미지 : ' + data.result[i]);    		
                        }
        
    
                    filePaths = data.result;
                    //alert('이미지 업로드 완료');				                    
                    document.getElementById('file-paths').value = filePaths.join('#');
    
                    prodRegister();
    
                }
    
            },
            error: function (xhr, status, error) {
                alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
            return false;
            }
        });
    
    });
}