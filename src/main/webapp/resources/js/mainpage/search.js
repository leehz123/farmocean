window.addEventListener('load',() => {
	const searchInput = document.getElementById("searchInput");
    // const s = document.getElementById("searchSelect");
    // const searchSelect = s.options[s.selectedIndex].value;
    const searchSelect = document.getElementById("searchSelect");

    const searchBtn = document.getElementById("searchBtn");
    const searchForm = document.getElementById('searchForm');
    
    searchBtn.addEventListener('click', (e) => {     
        location.href = loot_depth + "/product/list/" + searchSelect.options[searchSelect.selectedIndex].value + "/" + searchInput.value;
		// searchForm.submit();   
        // http://localhost:8888/farmocean/product/list/sell/sample64?searchSelect=sell
        // http://localhost:8888/farmocean/product/list/name/%EC%82%AC%EA%B3%BC/1
        // http://localhost:8888/farmocean/product/list/sell_nick/sample64?searchSelect=sell_nick
    });

    // location.href = loot_depth + "/product/list/" + searchSelect + "/" + searchInput.value;
    // console.log("可记 蔼: " + searchSelect);
 });
 
// var target = document.getElementById("selectBox");
// alert('急琶等 可记 value 蔼=' + target.options[target.selectedIndex].value);