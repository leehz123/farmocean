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
			//console.log(responseText);
			const cate = JSON.parse(responseText);
			//console.log('-------------------------');
			cate.forEach((cate) => {
				//console.log(cate);
				console.log(cate.cate_idx + ' : ' + cate.cate_name);
			});

			//console.log(cate.cate_name);
			//console.log('-------------------------');
		}

	});

	$('#ajax-test').click(function () {
		console.log('ajax 테스트');	

		xhttp.open('GET', loot_depth + "/board/catelist");    
    	xhttp.send();

		// $.ajax({
		// 	//type: "post",
		// 	type: "get",
		// 	url: loot_depth + "/board/catelist",
		// 	//data: jsonData,
		// 	dataType: "json",
		// 	success: function (result) {
		// 		//const lunchDiv = $('#lunch_list');
		// 		const resultList = Object.values(result)[0];

		// 		resultList.forEach(function (item) {
		// 			console.log('1');
		// 			// // 특정 카테고리 예외처리
		// 			// if (checkCategoryName(item.category_name)) {
		// 			// 	return;
		// 			// }

		// 			// const address = item.address_name;
		// 			// const url = item.place_url;
		// 			// const phone = item.phone;
		// 			// const x = item.x;
		// 			// const y = item.y;
		// 			// const name = item.place_name;
		// 			// const div = '<div class="lunch_list_content" ' + 'data-address=' + address + 'data-url=' + url + 'data-x=' + x + 'data-y=' + y + 'data-phone=' + phone + '>' + name + '</div>'
		// 			// lunchDiv.append(div);
		// 		});
		// 	},
		// 	error: function (e) {
		// 		alert("Fail");
		// 		console.log(e);
		// 	}
		// });

		// jQuery.ajax({
		// 	type: "GET",
		// 	url: loot_depth + "/board/catelist",
		// 	cache: false,
		// 	data: {},
		// 	datatype: "JSON",
		// 	success: function (obj) {
		// 		//var data = JSON.parse(obj);
		// 		console.log(obj);
		// 		var data = JSON.parse(obj);				

		// 		$.each(obj, function (k, v) {
		// 			console.log(k + ' : ' + v);

		// 			var cateInfo = JSON.parse(v);
					
		// 			console.log(cateInfo.cate_name);
		// 			//$('<option></option>').val(k).text(v).appendTo($('#exhibition_id'));
		// 			//console.log(data);
		// 		});

		// 	},
		// 	error: function (xhr, status, error) {
		// 		console.log("ERROR!!!");
		// 	}
		// });
	});

});


function cateList(){

}