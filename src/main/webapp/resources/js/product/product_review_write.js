

const memberIdInput = document.getElementById('member-id-input');
const memberNicknameInput = document.getElementById('member-nickname-input');
const prodIdxInput = document.getElementById('prod-idx-input'); 
const submitBtn = document.getElementById('submit-btn');
const reviewContent = document.getElementById('review-contents');
const myForm = document.getElementById('myform');

let filePaths = new Array();

window.onload = function() {


	if(memberIdInput.value.length >= 1) {
		const xhttp1 = new XMLHttpRequest();
		xhttp1.open('GET', '/farmocean/prod/get_member_nickname_by_member_id/' + memberIdInput.value);
		xhttp1.send();
		xhttp1.addEventListener('readystatechange', (e)=> {
			const readyState = e.target.readyState; 
			if(readyState == 4) {
				const memberNickname = e.target.responseText;
				memberNicknameInput.value = memberNickname;	
			}	
		}); 		
	}

	var path = window.location.pathname;
	// /farmocean/product/product_review_write/{prod_idx}
    prodIdx = path.replace('/farmocean/product/product_review_write/', '');
    prodIdxInput.value = prodIdx;
    
};



//라디오 버튼이 선택 됐는지 (name속성 이용. 라디오버튼은 name속성이 공통)
function isRadioBtnSelected() {
	var obj_length = document.getElementsByName("review_starNum").length;
	for (var i=0; i<5; i++) {
		if (document.getElementsByName("review_starNum").item(i).checked == true) {
			return true; // 선택됨
		}
	}
	return false; //선택 안 됨
}



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

var fileNo = 0;
var filesArr = new Array();

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
				imgPreview.style.width = '15vh';
				imgPreview.style.height = '15vh'; 				
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
            console.log('파일명div id : file', fileNo);
            htmlData += '   <p class="name">' + file.name + '</p>';
            htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');">삭제</a>';
            htmlData += '</div>';
            $('#file-list').append(htmlData);
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
    } else if (obj.size > (5 * 1024 * 1024)) {
        alert("최대 파일 용량인 5MB를 초과한 파일은 제외되었습니다.");
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
	      url: '../../prod/upload_image',
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
	    		console.log(data.result[i]);    		
	    		}
	    		filePaths = data.result;
				alert('이미지 업로드 완료');
	    	}
	      },
	      error: function (xhr, status, error) {
	    	alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
	     return false;
	     }
	});
}

document.getElementById('img-submit-btn').addEventListener('click', (e)=> {
	imageRegisterAction();
});











function reviewRegister() {
    
	// 폼데이터 담기
	var form = $('form1')[0];        
	var formData = new FormData(form);
	formData.append('filePaths', filePaths);
	formData.append('test', 'testResult');

	$.ajax({
        method: 'POST',
        url: '../../prod/insert_review',
        data: formData,
        async: false,
        cache: false,
        processData: false,
	    contentType: false,
        success: function (data) {
        	if(data.result == 'OK') {
            	alert("리뷰가 등록되었습니다.");
        	}
        },
        error: function (xhr, desc, err) {
            alert('에러가 발생 하였습니다.');
            return;
        }
    });	
}


document.getElementById('form1-submit-btn').addEventListener('click', (e)=> {
	reviewRegister();
});

