
// 파일 사진 미리보기 구현
function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
      document.getElementById('preview').src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById('preview').src = "";
  }
}

// 파일 업로드를 버튼으로 구현
function onClickFile() {
            let fileInput = document.getElementById("fileInput");
            fileInput.click();
}
