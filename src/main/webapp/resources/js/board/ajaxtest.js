$(document).ready(function () {
	$('#btn-ins').click(function () {
		console.log('등록');
		$('#frm-ins').submit();
		return false;
	});

	const xhttp = new XMLHttpRequest();

	xhttp.addEventListener('readystatechange', (e) => {

		const readyState = e.target.readyState;

		if(readyState == 4){
			const responseText = e.target.responseText;
			const pInfo = JSON.parse(responseText);
			//alert(responseText);
			if(pInfo.code == '1'){
				alert('등록 성공');
			}else{
				$('#result').html(pInfo.title + pInfo.memo);
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

		xhttp.open('POST', loot_depth + "/board/notice_insert"); 		
		xhttp.setRequestHeader('Content-type','application/json; charset=utf-8');    
		xhttp.send(JSON.stringify({getUrl : $('#getUrl').val() }) );		

	});

});