const arrCateTitle = ['','�ķ��۹�','Ư���۹�','���Ϸ�','ä�ҷ�','���깰','�Ѹ���','�ǰ���'];

console.log(arrCateTitle[1]);
console.log(arrCateTitle[0]);
console.log(arrCateTitle[2]);

const xhttpCateTop = new XMLHttpRequest();
const xhttpCateTop2 = new XMLHttpRequest();
xhttpCateTop2.addEventListener('readystatechange', (e) => {
	const readyState = e.target.readyState;

	if(readyState == 4){
		console.log("....11");
		const responseText = e.target.responseText;
		const cInfo = JSON.parse(responseText);
		console.log(responseText);
	}

});

xhttpCateTop.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){
		// ī�װ� ���� �ٱ��� div ����
		const naviCate = document.getElementById('navi_cate');
		const responseText = e.target.responseText;
		const cInfo = JSON.parse(responseText);
		const subInfo = JSON.parse(responseText);
		/*
		<div class="dropdown">
			<button class="dropbtn">�ķ��۹�
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
			// ī�װ� ���� ǥ�õǴ� div ����
			let divTitle = document.createElement('div');			
			divTitle.className = 'dropdown';

			// ī�װ� ���� ��ư ����
			let btnTitle = document.createElement('button');
			btnTitle.innerText = arrCateTitle[cate];
			btnTitle.className = 'dropbtn';

			// 
			let iTitle = document.createElement('i');
			iTitle.className = 'fa fa-caret-down';
			btnTitle.appendChild(iTitle);
			divTitle.appendChild(btnTitle);

			let divSub = document.createElement('div');			
			divSub.className = 'dropdown-content';
		

			// �̺κп� ���� ī�װ� ������ ��
			// ���� ī�װ� ���� = loot_depth + "/prodJson/cateSubList/{cate_main}"
			// �׽�Ʈ

			let aSub = document.createElement('a');			
			aSub.href = loot_depth + '/product/list/' + subCate.cate_idx + '/1'; // �� �ּҴ� cate_idx �ּҶ� ���� ī�װ� �ϼ��ؾ�
			aSub.innerText = '����ī�װ�';
			aSub.value = '����ī�װ�';
			xhttpCateTop2.open('GET', loot_depth + "/prodJson/cateSubList/" + cate); 		
			xhttpCateTop2.send();	
			divSub.appendChild(aSub);
			divTitle.appendChild(divSub);



			naviCate.appendChild(divTitle);

			console.log(arrCateTitle[cate]);		
			//naviCate.innerHTML ='<h3>'+arrCateTitle[cate]+'</h3>';
		});

		

	}

});

window.onload = () => {
	xhttpCateTop.open('GET', loot_depth + "/prodJson/cateTopList"); 		
	xhttpCateTop.send();	
};