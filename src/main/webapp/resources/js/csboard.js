$(document).ready(function () {
	$('#btn-ins').click(function () {
		console.log('µî·Ï');
		$('#frm-ins').submit();
		return false;
	});

	CKEDITOR.replace('editor1',{filebrowserUploadUrl: loot_depth + '/board/upload/cs_img'});

	alert('1');
});