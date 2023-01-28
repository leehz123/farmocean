const arrCateTitle = ['', '°úÀÏ·ù', '½Ä·®ÀÛ¹°', '»Ñ¸®·ù', '°Ç°­Áó', '¼ö»ê¹°', 'Ã¤¼Ò·ù', 'Æ¯¿ëÀÛ¹°'];

// console.log(arrCateTitle[1]);
// console.log(arrCateTitle[0]);
// console.log(arrCateTitle[2]);

const xhttpCateTop = new XMLHttpRequest();
const xhttpCateTop2 = new XMLHttpRequest();
xhttpCateTop2.addEventListener('readystatechange', (e) => {
	const readyState = e.target.readyState;

	if(readyState == 4){
		// console.log("....11");
		const responseText = e.target.responseText;
		const cInfo = JSON.parse(responseText);
		// console.log(responseText);
	}

});

xhttpCateTop.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		const naviCate = document.getElementById('navi_cate');
		const responseText = e.target.responseText;
		const cInfo = JSON.parse(responseText);
		const subInfo = JSON.parse(responseText);
		/*
		<div class="dropdown">
			<button class="dropbtn">
				<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">						
				<c:forEach items="${cates2 }" var="cate">
					<a href="${path }/prod/prodjsonlist/cate/${cate.cate_idx }">${cate.cate_name }</a>
				</c:forEach>
			</div>
		</div>
		*/
		cInfo.forEach(function (cate) {
			// Ä«Å×°í¸® Á¦¸ñ Ç¥½ÃµÇ´Â div »ý¼º
			let divTitle = document.createElement('div');			
			divTitle.className = 'dropdown_f';
			divTitle.style.margin = '0';
			
			// Ä«Å×°í¸® Á¦¸ñ ¹öÆ° »ý¼º
			let btnTitle = document.createElement('button');
			btnTitle.innerText = arrCateTitle[cate];
			btnTitle.className = 'dropbtn';
			btnTitle.style.textAlign = 'center';
			btnTitle.style.margin = '0';
			btnTitle.style.width = '185px';
			
			divTitle.appendChild(btnTitle);

			
			
			// ÀÌºÎºÐ¿¡ ¼­ºê Ä«Å×°í¸® ³ÖÀ¸¸é µÊ
			// ¼­ºê Ä«Å×°í¸® Á¤º¸ = loot_depth + "/prodJson/cateSubList/{cate_main}"
			// Å×½ºÆ®
			
			const xhttpCateSub = new XMLHttpRequest();
			
			xhttpCateSub.addEventListener('readystatechange', (e) => {
				const readyState = e.target.readyState;
				
				if(readyState == 4){
					let divSub = document.createElement('div');			
					divSub.className = 'dropdown-content';
					divTitle.appendChild(divSub);
					
					const responseText = e.target.responseText;
					const cSubInfo = JSON.parse(responseText);
					
					cSubInfo.forEach(function (subCate) {

						let aSub = document.createElement('a');			
						aSub.href = loot_depth + '/product/list/' + subCate.cate_idx;
						aSub.innerText = subCate.cate_name;

						divSub.appendChild(aSub);

						// console.log(aSub);
					});
				}
			});

			

			xhttpCateSub.open('GET', loot_depth + "/prodJson/cateSubList/" + cate); 		
			xhttpCateSub.send();

			// let aSub = document.createElement('a');			
			// aSub.href = loot_depth + '/product/list/' + subCate.cate_idx + '/1'; // ï¿½ï¿½ ï¿½Ö¼Ò´ï¿½ cate_idx ï¿½Ö¼Ò¶ï¿½ ï¿½ï¿½ï¿½ï¿½ Ä«ï¿½×°ï¿½ ï¿½Ï¼ï¿½ï¿½Ø¾ï¿½
			// aSub.innerText = 'ï¿½ï¿½ï¿½ï¿½Ä«ï¿½×°ï¿½';
			// aSub.value = 'ï¿½ï¿½ï¿½ï¿½Ä«ï¿½×°ï¿½';
			// xhttpCateTop2.open('GET', loot_depth + "/prodJson/cateSubList/" + cate); 		
			// xhttpCateTop2.send();	
			// divSub.appendChild(aSub);
			// divTitle.appendChild(divSub);



			naviCate.appendChild(divTitle);

			// console.log(arrCateTitle[cate]);		
			//naviCate.innerHTML ='<h3>'+arrCateTitle[cate]+'</h3>';
		});

		

	}

});

window.addEventListener('load',() => {
	xhttpCateTop.open('GET', loot_depth + "/prodJson/cateTopList");       
	xhttpCateTop.send();   
 });
 