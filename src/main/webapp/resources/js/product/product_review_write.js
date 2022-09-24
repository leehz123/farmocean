

const memberIdInput = document.getElementById('member-id');
const memberNicknameInput = document.getElementById('member-nickname');
const prodIdxInput = document.getElementById('prod-idx'); 
const submitBtn = document.getElementById('form1-submit-btn');
const reviewContent = document.getElementById('review-contents');


var fileNo = 0;
var filesArr = new Array(); //파일을 담을 배열
let filePaths = new Array(); //이미지 파일 업로드 후 리턴 받은 파일 경로 문자열을 담을 배열
let isUploaded = false;



window.addEventListener('load',() => {
	var path = window.location.pathname;
    prodIdx = path.replace('/farmocean/product/product_review_write/', '');
    prodIdx = prodIdx.substring(0, prodIdx.indexOf('/'));
	prodIdxInput.value = prodIdx;   
});



//라디오 버튼이 선택 됐는지 (name속성 이용. 라디오버튼은 name속성이 공통)
function isRadioBtnSelected() {
	var obj_length = document.getElementsByName("review_starnum").length;
	for (var i=0; i<5; i++) {
		if (document.getElementsByName("review_starnum").item(i).checked == true) {
			return true; // 선택됨
		}
	}
	return false; //선택 안 됨
}


// 별점 얼마인지
function whichRadioBtnSelected() {
	var obj_length = document.getElementsByName("review_starnum").length;
	for (var i=0; i<5; i++) {
		if (document.getElementsByName("review_starnum").item(i).checked == true) {
			return document.getElementsByName("review_starnum").item(i).value; // 선택됨
		}
	}
	return 0; //선택 안 됨
}


// 폼 빈값 체크
function formNullChk() {
	if(
		!isRadioBtnSelected() || 
		memberIdInput.value.length < 1 ||
		memberNicknameInput.value.length < 1 ||
		prodIdxInput.value.length < 1 ||
		reviewContent.value.length < 1
	) {
		return true; //빈 항목 있음
	}
	return false; //빈 항목 없음
};



/////input file 다중이미지 선택 (input type="file")
/* 첨부파일 추가 */
function addFile(obj){
    var maxFileCnt = 5;   // 첨부파일 최대 개수
    var attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    var curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수(파일 선택창에서 ctrl 로 파일 여러개 선택 가능)

    // 첨부파일 개수 확인
    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + " 개까지 첨부 가능합니다.");
    }

    for (var i = 0; i < Math.min(curFileCnt, remainFileCnt); i++) { //선택한 파일이 4개고 추가로 첨부 가능한 파일 개수가 1개면 1개만 업로드 됨
		
        const file = obj.files[i];
		var imgPreview;
        // 첨부파일 검증
        if (validation(file)) {
            // 파일 배열에 담기
            var reader = new FileReader();
            reader.onload = function (e) {
                if(!imgPreview) {
                var previewCont = document.getElementById('img-preview');
				imgPreview = new Image();
				imgPreview.style.width = '82.5px';
				imgPreview.style.height = '82.5px'; 				
				previewCont.appendChild(imgPreview);                	
                }
                imgPreview.src = e.target.result; 
                imgPreview.id = 'fileImg' + (fileNo - 1);
                imgPreview.className = 'preview-img';
                filesArr.push(file);
            };
            reader.readAsDataURL(file);
			
            // 목록 추가
            let htmlData = '';
            htmlData += '<div id="file' + fileNo + '" class="filebox">';
            //console.log('파일명div id : file', fileNo);
            htmlData += '   <p class="name">' + file.name + '</p>';
            htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');">삭제</a>';
            htmlData += '</div>';
            $('#file-list').append(htmlData);
            fileNo++;
        } else {
            continue;
        }
    }
	
    // file input 초기화
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
    document.querySelector("#file" + num).remove();
    document.querySelector("#fileImg" + num).remove();
    filesArr[num].is_delete = true;
}




function imageRegisterAction() {

	var form = $('form2')[0];        
	var formData = new FormData(form);
	for (var i = 0; i < filesArr.length; i++) {
	    // 삭제되지 않은 파일만 폼데이터에 담기
	    if (!filesArr[i].is_delete) {
	        formData.append('attach_file', filesArr[i]);
	    }
	}
	   /*
	   * 파일업로드 multiple ajax처리
	   */  
	$.ajax({
		type: 'POST',
			enctype: 'multipart/form-data',
		url: '/farmocean/prod/upload_review_image',
			//dataType: 'json', 
			data : formData,
			async :false,
			cache: false,
			processData: false,
		contentType: false,
		success: function (data) {
			if(data.result == null){
				alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
			} else {
				for(let i = 0; i < data.result.length; ++i) {
				}
				filePaths = data.result;
				//alert('이미지 업로드 완료');
				document.getElementById('file-paths').value = filePaths.join('#');
				isUploaded = true;
			}
		},
		error: function (xhr, status, error) {
			alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
		return false;
		}
	});

	
}




// document.getElementById('img-submit-btn').addEventListener('click', (e)=> {
// 	//이미지 파일 서버에 업로드
// 	imageRegisterAction();
// });











function reviewRegister() {
    
	// 폼데이터 담기
	//var form = $('form[id=form1]')[0];        
	//var formData = new FormData(form);
	
	//formData.append('filePaths', filePaths); 이렇게 추가하면 form 안의 input에 들어가있는 한글 데이터들이 컨트롤러에서 깨져서 나타남 어떤 방법을 써도 해결이 안 돼서 그냥 input hidden에 넣고 저
	
	var formData = {
					member_id: document.getElementById('member-id').value,	
					prod_idx: document.getElementById('prod-idx').value,
					file_paths: document.getElementById('file-paths').value,
					review_starnum: whichRadioBtnSelected(),
					review_content: document.getElementById('review-content').value,
					buy_idx: document.getElementById('buy-idx').value
					};



	$.ajax({
        method: 'POST',
        url: '/farmocean/prod/insert_review',
        data: JSON.stringify(formData),
        async: false,
        cache: false,
        processData: false,
	    contentType: 'application/json; charset=UTF-8',
        success: function (data) {
        	if(data.result == 'OK') {
            	alert("리뷰가 등록되었습니다.");
            	window.close();
        	} else {
        		alert("리뷰 등록에 실패했습니다. 다시 시도해주세요.");
        	}
        },
        error: function (xhr, desc, err) {
            alert('에러가 발생 하였습니다.');
            return;
        }
    });	
}



document.getElementById('form1-submit-btn').addEventListener('click', (e)=> {
	
	let validFileCnt = 0;
	for (var i = 0; i < filesArr.length; i++) {
	    if (!filesArr[i].is_delete) {
	        ++validFileCnt;
	    }
	}
	if(validFileCnt > 0) {
		//이미지 업로드
		imageRegisterAction();
		
		if(isUploaded) {
			// 이미지 업로드 완료되면 폼 업로드
			reviewRegister();
		}
	} else {
		reviewRegister();
	}


});



// document.getElementById('show-upload-img-btn').addEventListener('click', (e)=> {
	
// 	if(isUploaded == true) {
// 		var result = confirm("선택된 이미지 목록이 모두 삭제됩니다. 수정하시겠습니까?");
// 		if(result){	
// 			isUploaded = false;
// 			//비우기
// 			for(let i = 0; i < filesArr.length; ++i) {
// 				filesArr[i].is_delete = true;
// 			}
// 			document.getElementById('img-preview').innerHTML = '';
// 			document.getElementById('file-list').innerHTML = '';							
			
// 			document.getElementById('img-file-input-cont').style.visibility = "visible";	
// 			//★★★file-path 인풋에 있는 패스들 보내서 서버에 업로드된 파일들 삭제하는 ajax 실행
// 			filePaths = new Array();
// 		}
// 	} else {
// 		document.getElementById('img-file-input-cont').style.visibility = "visible";
// 	}
	
// });





