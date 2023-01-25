
// 파일 사진 미리보기 구현

function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
      document.getElementById('preview').src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
    document.getElementById("checkImg").value = 'change';
  } else {
    document.getElementById('preview').src = "";
  }
}

// 파일 사진 기본 이미지 변경 구현

function changeBasicImg() {
	document.getElementById('preview').src = '/farmocean/resources/image/mypage/profile_basic_image.jpg';
	document.getElementById("checkImg").value = 'basic';
}


// 파일 업로드를 버튼으로 구현
function onClickFile() {
            let fileInput = document.getElementById("fileInput");
            fileInput.click();
}

// utf-8을 euc-kr로 바꾸기

function emulAcceptCharset(form) {
    if (form.canHaveHTML) {
        document.charset = form.acceptCharset;
    }

    return true;
}


function OpenWin() {
    var f = document.cplogn;

    shape = 'width=520,height=650,';
    shape += 'left=70,top=10,toolbar=no,location=no,directories=no,status=yes,';
    shape += 'menubar=yes,scrollbars=no,resizable=yes';

    var win = open('', 'MC', shape);

    f.target = 'MC';

    emulAcceptCharset(f);

    f.submit();

    if (win.focus) {
        win.focus();
    }
}