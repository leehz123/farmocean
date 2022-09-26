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
					var row = tableAdd.insertRow( tableAdd.rows.length ); // �ϴܿ� �߰�
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
					var row = tableAdd.insertRow( tableAdd.rows.length ); // �ϴܿ� �߰�
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
						cell6.innerHTML = '<button class="btn btn-danger" onclick="fnUserBlock(\'B\',\''+fUser.member_id+'\')">��</button>';
					}else{
						cell6.innerHTML = '<button class="btn btn-primary" onclick="fnUserBlock(\'C\',\''+fUser.member_id+'\')">�����</button>';
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
			alert('����');
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
			confMsg = '���';
			break;
		case 'C':
			confMsg = '��� ���';			
			break;
		default :
			break;
	}
	
	if(confirm(confMsg + ' �Ͻ� �ڽ��ϱ�? ')){
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

		tableAdd.innerHTML = "";

		result.buyList.forEach(function (buyInfo) {	
			var row = tableAdd.insertRow( tableAdd.rows.length ); // �ϴܿ� �߰�
			var cell1 = row.insertCell(0);
			cell1.innerHTML = buyInfo.view_regdate + '<br>' + buyInfo.prod_name;
			var cell2 = row.insertCell(1);
			cell2.innerHTML = buyInfo.member_nickname+'('+buyInfo.sell_id+')';
			var cell3 = row.insertCell(2);
			cell3.innerHTML = buyInfo.view_price;
			var cell4 = row.insertCell(3);
			cell4.innerHTML = buyInfo.post_code;
			var cell5 = row.insertCell(4);
			cell5.innerHTML = buyInfo.view_address;
			var cell6 = row.insertCell(5);
			cell6.innerHTML = fnStatMsg(buyInfo.state);
			var cell7 = row.insertCell(6);
			cell7.innerHTML = '<button class="btn btn-danger" onclick="fnUserBlock(\'B\',\''+buyInfo.buy_idx+'\')">����</button>';
			
		});

		fnPageCreate(result.totalPage, result.thisPage);
	}
});

function searchBuyList(thisPage){
	const searchValue = document.getElementById("member_id");

	xhttpBuyList.open('GET', loot_depth + "/list/buylist/" + thisPage); 		
	xhttpBuyList.setRequestHeader('Content-type','application/json; charset=utf-8');    
	xhttpBuyList.send(searchValue.value);
}

function fnStatMsg(num){
	let returnMsg = '';
	//(0 : ��û, 1:����, 2:�����, 3:���Ȯ��, 4:��ǰ, 5:���, 10:�ǸſϷ�)
	switch (num){
		case 0:
			returnMsg = '��û';
			break;
		case 1:
			returnMsg = '����';
			break;
		case 2:
			returnMsg = '�����';
			break;
		case 3:
			returnMsg = '���Ȯ��';
			break;
		case 4:
			returnMsg = '��ǰ';
			break;
		case 5:
			returnMsg = '���';
			break;
		case 10:
			returnMsg = '�ǸſϷ�';
			break;
	}

	return returnMsg;
}

function fnPageCreate(maxPage, thisPage){

	const pageUl = document.getElementById('pageNav');

	//<li class="page-item">
	//	<a class="page-link" href="<c:url value="/board/notice/1"/>">Previous</a>
	//</li>
	//<li class="page-item active" aria-current="page"><a class="page-link" href="#">1</a></li>
	//<li class="page-item"><a class="page-link" href="#">2</a></li>						
	//<li class="page-item">
	//	<a class="page-link" href="<c:url value="/board/notice/${pageLsit}"/>">Next</a>
	//</li>
	pageUl.innerHTML = "";

	const prevLi = document.createElement('li');
	prevLi.className = 'page-item';

	const prevPA = document.createElement('a');
	prevPA.className = 'page-link';
	prevPA.href = 'javascript:searchBuyList(1);';
	prevPA.innerText = 'Previous';

	prevLi.appendChild(prevPA);
	pageUl.appendChild(prevLi);

	for(let i = 0; i < maxPage; i++){

		const pageLi = document.createElement('pageLi');
		if(i+1 == thisPage){
			pageLi.className = 'page-item active';	
			pageLi.ariaCurrent = 'page';
		}else{
			pageLi.className = 'page-item';	
		}

		const pagePA = document.createElement('a');
	
		pagePA.className = 'page-link';
		pagePA.href = 'javascript:searchBuyList('+(i+1)+');';
		pagePA.innerText = i + 1;

		pageLi.appendChild(pagePA);
		pageUl.appendChild(pageLi);

	}

	const nextLi = document.createElement('li');
	nextLi.className = 'page-item';

	const nextPA = document.createElement('a');
	nextPA.className = 'page-link';
	nextPA.href = 'javascript:searchBuyList('+ maxPage +');';
	nextPA.innerText = 'Next';

	nextLi.appendChild(nextPA);
	pageUl.appendChild(nextLi);

}

const xhttpBuyUpt = new XMLHttpRequest();
xhttpBuyUpt.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const result = JSON.parse(responseText);

		if(result.code == 1){
			alert('�����Ǿ����ϴ�.');
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

function fnSubmit(frmName, page){
	const setPage = document.getElementById('pageNum');
	const sendFrm = document.getElementById(frmName);
	setPage.value = page;
	sendFrm.submit();
}

const xhttpUserAdminAdd = new XMLHttpRequest();
xhttpUserAdminAdd.addEventListener('readystatechange', (e) => {
	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const result = JSON.parse(responseText);

		console.log(result);
		if(result.code == 1){
			alert('��� �Ǿ����ϴ�.');
			location.reload();
		}else{
			alert(result.msg)
		}
		
	}
});


const xhttpUserSearch = new XMLHttpRequest();
xhttpUserSearch.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const mInfo = JSON.parse(responseText);

		if(mInfo.member_id == null){
			alert('ȸ�� ������ �����ϴ�.');
		}else{
			if(confirm('������ ��� �Ͻðڽ��ϱ�?')){
				xhttpUserAdminAdd.open('POST', loot_depth + "/admin/userAdd"); 		
				xhttpUserAdminAdd.setRequestHeader('Content-type','application/json; charset=utf-8');    
				xhttpUserAdminAdd.send(mInfo.member_id );	
			}
		}
	}
});

function fnAdminAdd(){
	const member_id = document.getElementById("member_id");
	xhttpUserSearch.open('GET', loot_depth + "/admin/usersearch/" + member_id.value); 		
	xhttpUserSearch.send();	
}

const xhttpUserAdminDel = new XMLHttpRequest();
xhttpUserAdminDel.addEventListener('readystatechange', (e) => {
	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const result = JSON.parse(responseText);

		console.log(result);
		if(result.code == 1){
			alert('���� �Ǿ����ϴ�.');
			location.reload();
		}else{
			alert(result.msg)
		}
		
	}
});

function fnAdminDel(mem_id){

	if(confirm('�����ڸ� ���� �Ͻðڽ��ϱ�?')){
		xhttpUserAdminDel.open('POST', loot_depth + "/admin/userDel"); 		
		xhttpUserAdminDel.setRequestHeader('Content-type','application/json; charset=utf-8');    
		xhttpUserAdminDel.send(mem_id );
	}
}