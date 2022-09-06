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

		//console.log(mInfo);
		document.getElementById("resultProd").innerText = responseText;
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

window.onload = () => {
	console.log('ºÒ·¯¿È');

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
};