const xhttpMember = new XMLHttpRequest();
const xhttpProd = new XMLHttpRequest();
const xhttpFaulty = new XMLHttpRequest();

xhttpMember.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const mInfo = JSON.parse(responseText);

		//console.log(mInfo);
		document.getElementById("resultMember").innerText = responseText;
	}

});

xhttpProd.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const mInfo = JSON.parse(responseText);		

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

					// let viewValue = '<tr>';
					// viewValue += '<th scope="row">1</th>';
					// viewValue += '<td>Mark</td>';
					// viewValue += '<td>Otto</td>';
					// viewValue += '<td>@mdo</td>';
					// viewValue += '</tr>';

					// console.log(viewValue);

					// tableAdd.innerHTML += viewValue;


					// // 특정 카테고리 예외처리
					// if (checkCategoryName(item.category_name)) {
					// 	return;
					// }

					// const address = item.address_name;
					// const url = item.place_url;
					// const phone = item.phone;
					// const x = item.x;
					// const y = item.y;
					// const name = item.place_name;
					// const div = '<div class="lunch_list_content" ' + 'data-address=' + address + 'data-url=' + url + 'data-x=' + x + 'data-y=' + y + 'data-phone=' + phone + '>' + name + '</div>'
					// lunchDiv.append(div);
		});

		//console.log(mInfo);
		//document.getElementById("resultProd").innerText = responseText;
	}

});

xhttpFaulty.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		const responseText = e.target.responseText;
		const mInfo = JSON.parse(responseText);

		//console.log(mInfo);
		document.getElementById("resultFaulty").innerText = responseText;
	}

});

window.addEventListener('load',() => {

	console.log('불러옴');

	const btnSearchMember = document.getElementById("btnSearchMember");
	const btnSearchProd = document.getElementById("btnSearchProd");

	btnSearchMember.addEventListener('click', () => {
		const pInfo = {			
			type : $('#searchMember').val(),
			value : $('#searchMemberValue').val()
		}

		xhttpMember.open('POST', loot_depth + "/admin/memberInfo"); 		
		xhttpMember.setRequestHeader('Content-type','application/json; charset=utf-8');    
		xhttpMember.send(JSON.stringify(pInfo) );	
	});

	btnSearchProd.addEventListener('click', () => {
		const pInfo = {			
			type : $('#searchProd').val(),
			value : $('#searchProdValue').val()
		}

		xhttpProd.open('POST', loot_depth + "/admin/prodInfo"); 		
		xhttpProd.setRequestHeader('Content-type','application/json; charset=utf-8');    
		xhttpProd.send(JSON.stringify(pInfo) );	
	});

	xhttpFaulty.open('GET', loot_depth + "/admin/memberFaultyList"); 		
	xhttpFaulty.send();	

});