

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



//���� ��ư�� ���� �ƴ��� (name�Ӽ� �̿�. ������ư�� name�Ӽ��� ����)
function isRadioBtnSelected() {
	var obj_length = document.getElementsByName("review_starNum").length;
	for (var i=0; i<5; i++) {
		if (document.getElementsByName("review_starNum").item(i).checked == true) {
			return true; // ���õ�
		}
	}
	return false; //���� �� ��
}



function formNullChk() {

	if(
		!isRadioBtnSelected() || 
		memberIdInput.value.length < 1 ||
		memberNicknameInput.value.length < 1 ||
		prodIdxInput.value.length < 1 ||
		reviewContent.value.length < 1
	) {
		return true; //�� �׸� ����
	}
	return false; //�� �׸� ����
};










/////input file �����̹��� ���� (input type="file")

var fileNo = 0;
var filesArr = new Array();

/* ÷������ �߰� */
function addFile(obj){
    var maxFileCnt = 5;   // ÷������ �ִ� ����
    var attFileCnt = document.querySelectorAll('.filebox').length;    // ���� �߰��� ÷������ ����
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
			
            // ��� �߰�
            let htmlData = '';
            htmlData += '<div id="file' + fileNo + '" class="filebox">';
            console.log('���ϸ�div id : file', fileNo);
            htmlData += '   <p class="name">' + file.name + '</p>';
            htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');">����</a>';
            htmlData += '</div>';
            $('#file-list').append(htmlData);
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
    } else if (obj.size > (5 * 1024 * 1024)) {
        alert("�ִ� ���� �뷮�� 5MB�� �ʰ��� ������ ���ܵǾ����ϴ�.");
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
    document.querySelector("#file" + num).remove();
    document.querySelector("#fileImg" + num).remove();
    filesArr[num].is_delete = true;
}


function imageRegisterAction() {
			
	var form = $('form2')[0];        
	var formData = new FormData(form);
	for (var i = 0; i < filesArr.length; i++) {
	    // �������� ���� ���ϸ� �������Ϳ� ���
	    if (!filesArr[i].is_delete) {
	        formData.append('attach_file', filesArr[i]);
	    }
	}
	   /*
	   * ���Ͼ��ε� multiple ajaxó��
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
				alert("������ ������ ó���� �����ǰ��ֽ��ϴ�. ��� �� �ٽ� �õ����ּ���");
			} else {
	    		for(let i = 0; i < data.result.length; ++i) {
	    		console.log(data.result[i]);    		
	    		}
	    		filePaths = data.result;
				alert('�̹��� ���ε� �Ϸ�');
	    	}
	      },
	      error: function (xhr, status, error) {
	    	alert("���������� �����ǰ��ֽ��ϴ�. ��� �� �ٽ� �õ����ֽñ� �ٶ��ϴ�.");
	     return false;
	     }
	});
}

document.getElementById('img-submit-btn').addEventListener('click', (e)=> {
	imageRegisterAction();
});











function reviewRegister() {
    
	// �������� ���
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
            	alert("���䰡 ��ϵǾ����ϴ�.");
        	}
        },
        error: function (xhr, desc, err) {
            alert('������ �߻� �Ͽ����ϴ�.');
            return;
        }
    });	
}


document.getElementById('form1-submit-btn').addEventListener('click', (e)=> {
	reviewRegister();
});

