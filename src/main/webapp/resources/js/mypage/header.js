const arrCateTitle = ['', '과일류', '식량작물', '뿌리류', '건강즙', '수산물', '채소류', '특용작물'];

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
			// 카테고리 제목 표시되는 div 생성
			let divTitle = document.createElement('div');			
			divTitle.className = 'dropdown_f';
			//divTitle.style.width = '185px';
			divTitle.style.margin = '0';
			
			// 카테고리 제목 버튼 생성
			let btnTitle = document.createElement('button');
			btnTitle.innerText = arrCateTitle[cate];
			btnTitle.className = 'dropbtn';
			btnTitle.style.textAlign = 'center';
			btnTitle.style.width = '185px';
			btnTitle.style.margin = '0';
			
			divTitle.appendChild(btnTitle);

			
			
			// 이부분에 서브 카테고리 넣으면 됨
			// 서브 카테고리 정보 = loot_depth + "/prodJson/cateSubList/{cate_main}"
			// 테스트
			
			const xhttpCateSub = new XMLHttpRequest();
			
			xhttpCateSub.addEventListener('readystatechange', (e) => {
				const readyState = e.target.readyState;
				
				if(readyState == 4){
					let divSub = document.createElement('div');			
					divSub.className = 'dropdown-content';
					divSub.style.width = '185px';
					divTitle.appendChild(divSub);
					
					const responseText = e.target.responseText;
					const cSubInfo = JSON.parse(responseText);
					
					cSubInfo.forEach(function (subCate) {

						let aSub = document.createElement('a');
						aSub.href = loot_depth + '/product/list/' + subCate.cate_idx;
						aSub.innerText = subCate.cate_name;
						aSub.style.width = '185px';
						divSub.appendChild(aSub);

						// console.log(aSub);
					});
				}
			});

			

			xhttpCateSub.open('GET', loot_depth + "/prodJson/cateSubList/" + cate); 		
			xhttpCateSub.send();

			// let aSub = document.createElement('a');			
			// aSub.href = loot_depth + '/product/list/' + subCate.cate_idx + '/1'; // 이 주소는 cate_idx 주소라 서브 카테고리 완성해야
			// aSub.innerText = '서브카테고리';
			// aSub.value = '서브카테고리';
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
 