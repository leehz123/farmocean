const xhttpProdBuy = new XMLHttpRequest();

xhttpProdBuy.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const resultInfo = JSON.parse(responseText);
		
		if(resultInfo.code == 1){
			alert('���� ��û�� �Ϸ� �Ǿ����ϴ�.');
			window.close();
		}else{
			alert(resultInfo.msg);
		}
		console.log(responseText);
	}

});


window.addEventListener('load',() => {
	const productId = document.getElementById('productId');
	const postcode = document.getElementById('postcode');
	const roadAddress = document.getElementById('roadAddress');
	const jibunAddress = document.getElementById('jibunAddress');
	const detailAddress = document.getElementById('detailAddress');
	const extraAddress = document.getElementById('extraAddress');
});

function fnBuyProd(){			
	if(postcode.value == ''){
		alert('�����ȣ�� �Է��� �ּ���.');
		return;
	}
	
	if(detailAddress.value == ''){
		alert('���ּҸ� �Է��� �ּ���.');
		detailAddress.focus();
		return;
	}

	const buyInfo = {
		prod_idx : productId.value,
		post_code : postcode.value,
		road_address : roadAddress.value,
		jibun_address : jibunAddress.value,
		detail_address : detailAddress.value,
		extraa_ddress : extraAddress.value		
	}

	xhttpProdBuy.open('POST', loot_depth + "/prodJson/buyAdd"); 		
	xhttpProdBuy.setRequestHeader('Content-type','application/json; charset=utf-8');    
	xhttpProdBuy.send(JSON.stringify(buyInfo) );
	   
}