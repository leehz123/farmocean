const xhttpMember = new XMLHttpRequest();
xhttpMember.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const mInfo = JSON.parse(responseText);

		document.getElementById("resultMember").innerText = responseText;
	}

});

const xhttpProd = new XMLHttpRequest();
xhttpProd.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const mInfo = JSON.parse(responseText);		

		$("#tableAdd").empty();

		mInfo.forEach(function (prod) {
					const tableAdd = document.getElementById('tableAdd');
					//console.log(tableAdd);
					var row = tableAdd.insertRow( tableAdd.rows.length ); // 하단에 추가
    				var cell1 = row.insertCell(0);
					cell1.innerHTML = prod.prod_idx;
    				var cell2 = row.insertCell(1);
					cell2.innerHTML = prod.prod_name;
					var cell3 = row.insertCell(2);
					cell3.innerHTML = prod.member_id;
					var cell4 = row.insertCell(3);
					cell4.innerHTML = prod.prod_sell;
		});
	}
});

const xhttpFaulty = new XMLHttpRequest();
xhttpFaulty.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const mInfo = JSON.parse(responseText);

		$("#tableAdd").empty();

		mInfo.forEach(function (fUser) {
					const tableAdd = document.getElementById('tableAdd');
					//console.log(tableAdd);
					var row = tableAdd.insertRow( tableAdd.rows.length ); // 하단에 추가
    				var cell1 = row.insertCell(0);
					cell1.innerHTML = fUser.member_id;
    				var cell2 = row.insertCell(1);
					cell2.innerHTML = fUser.member_name;
					var cell3 = row.insertCell(2);
					cell3.innerHTML = fUser.member_nickName;
					var cell4 = row.insertCell(3);
					cell4.innerHTML = fUser.member_account_status;
					var cell5 = row.insertCell(4);
					cell5.innerHTML = fUser.member_report;
					var cell6 = row.insertCell(5);
					if(fUser.member_account_status == 1){
						cell6.innerHTML = '<button class="btn btn-danger" onclick="fnUserBlock(\'B\',\''+fUser.member_id+'\')">블럭</button>';
					}else{
						cell6.innerHTML = '<button class="btn btn-primary" onclick="fnUserBlock(\'C\',\''+fUser.member_id+'\')">블럭취소</button>';
					}
					
		});
	}

});

const xhttpBlock = new XMLHttpRequest();
xhttpBlock.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const result = JSON.parse(responseText);

		if(result.code == 1){
			alert('성공');
			searchFaultyList();
		}else{
			alert(result.msg);
		}
	}
});

function fnUserBlock(type, userid){	

	let confMsg = '';
	
	switch(type){
		case 'B':
			confMsg = '블록';
			break;
		case 'C':
			confMsg = '블록 취소';			
			break;
		default :
			break;
	}
	
	if(confirm(confMsg + ' 하시 겠습니까? ')){
		const bInfo = {			
			type : type,
			userid : userid
		}
	
		xhttpBlock.open('POST', loot_depth + "/admin/memberBlock"); 		
		xhttpBlock.setRequestHeader('Content-type','application/json; charset=utf-8');    
		xhttpBlock.send(JSON.stringify(bInfo) );
	}
	
}

function searchMember(){

	const pInfo = {			
		type : $('#searchMember').val(),
		value : $('#searchMemberValue').val()
	}

	xhttpMember.open('POST', loot_depth + "/admin/memberInfo"); 		
	xhttpMember.setRequestHeader('Content-type','application/json; charset=utf-8');    
	xhttpMember.send(JSON.stringify(pInfo) );	

}

function searchProd(){

	const pInfo = {			
		type : $('#searchProd').val(),
		value : $('#searchProdValue').val()
	}

	xhttpProd.open('POST', loot_depth + "/admin/prodInfo"); 		
	xhttpProd.setRequestHeader('Content-type','application/json; charset=utf-8');    
	xhttpProd.send(JSON.stringify(pInfo) );	

}

function searchFaultyList(){
	xhttpFaulty.open('GET', loot_depth + "/admin/memberFaultyList"); 		
	xhttpFaulty.send();	
}

const xhttpBuyList = new XMLHttpRequest();
xhttpBuyList.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const result = JSON.parse(responseText);

		$("#tableAdd").empty();

		console.log(result.totalPage);
		console.log(result.thisPage);

		result.buyList.forEach(function (buyInfo) {	
			var row = tableAdd.insertRow( tableAdd.rows.length ); // 하단에 추가
			var cell1 = row.insertCell(0);
			cell1.innerHTML = buyInfo.reg_date;
			//cell1.innerHTML = Date.parse(buyInfo.reg_date);
			var cell2 = row.insertCell(1);
			cell2.innerHTML = buyInfo.prod_name;
			var cell3 = row.insertCell(2);
			cell3.innerHTML = buyInfo.member_nickname+'('+buyInfo.sell_id+')';
			var cell4 = row.insertCell(3);
			cell4.innerHTML = buyInfo.prod_price;
			var cell5 = row.insertCell(4);
			cell5.innerHTML = buyInfo.post_code;
			var cell6 = row.insertCell(5);
			cell6.innerHTML = buyInfo.road_address;
			var cell7 = row.insertCell(6);
			cell7.innerHTML = buyInfo.state;
			var cell8 = row.insertCell(7);
			cell8.innerHTML = '<button class="btn btn-danger" onclick="fnUserBlock(\'B\',\''+buyInfo.buy_idx+'\')">수정</button>';
			
		});
	}
});

function searchBuyList(){
	const searchValue = document.getElementById("member_id");

	xhttpBuyList.open('POST', loot_depth + "/admin/buyList/1"); 		
	xhttpBuyList.setRequestHeader('Content-type','application/json; charset=utf-8');    
	xhttpBuyList.send(searchValue.value);
}

const xhttpBuyUpt = new XMLHttpRequest();
xhttpBuyUpt.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const result = JSON.parse(responseText);

		if(result.code == 1){
			alert('수정되었습니다.');
			location.reload();
		}else{
			alert(result.msg);
		}
	}
});

function fnChgBuyInfo(idx, statusBox){
	const selBox = document.getElementById(statusBox);
	const selStatus = selBox.options[selBox.selectedIndex].value;

	const info = {			
		idx : idx,
		status : selStatus
	}

	xhttpBuyUpt.open('POST', loot_depth + "/admin/buySetatusUpt"); 		
	xhttpBuyUpt.setRequestHeader('Content-type','application/json; charset=utf-8');    
	xhttpBuyUpt.send(JSON.stringify(info) );	
}