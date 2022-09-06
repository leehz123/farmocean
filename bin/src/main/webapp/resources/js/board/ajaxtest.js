$(document).ready(function () {
	$('#btn-ins').click(function () {
		console.log('등록');
		$('#frm-ins').submit();
		return false;
	});

	let chkUrl = false;
	const xhttp = new XMLHttpRequest();

	xhttp.addEventListener('readystatechange', (e) => {

		const readyState = e.target.readyState;

		if(readyState == 4){
			const responseText = e.target.responseText;
			const pInfo = JSON.parse(responseText);
			//alert(responseText);
			if(pInfo.code == '1'){
				alert('등록 성공');
				$('#title').val(' ');
				$('#memo').val(' ');
				chkUrl = false;
			}else{
				//$('#result').html(pInfo.title + pInfo.memo);
				$('#title').val(pInfo.title);
				$('#memo').val(pInfo.memo);
				chkUrl = true;
			}			
			
		}

	});

	$('#btnConf').click(function () {

		// const pizza = {			
		// 	price : post_pizza_price.value,
		// 	calories :post_pizza_calories.value
		// }

		//alert($('#getUrl').val());

		xhttp.open('POST', loot_depth + "/board/notice_conf"); 		
		xhttp.setRequestHeader('Content-type','application/json; charset=utf-8');    
		xhttp.send(JSON.stringify({getUrl : $('#getUrl').val() }) );		

	});


	$('#btnIns').click(function () {

		if(!chkUrl){
			alert('데이터확인이 안됬습니다.');
			return;
		}

		const pInfo = {			
			title : $('#title').val(),
			memo : $('#memo').val()
		}

		xhttp.open('POST', loot_depth + "/board/notice_insert"); 		
		xhttp.setRequestHeader('Content-type','application/json; charset=utf-8');    
		xhttp.send(JSON.stringify(pInfo) );		

	});

});