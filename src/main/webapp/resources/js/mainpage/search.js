window.addEventListener('load',() => {
	const searchInput = document.getElementById("searchInput");
    // const s = document.getElementById("searchSelect");
    // const searchSelect = s.options[s.selectedIndex].value;
    const searchSelect = document.getElementById("searchSelect");

    const searchBtn = document.getElementById("searchBtn");
    const searchForm = document.getElementById('searchForm');
    
    searchBtn.addEventListener('click', (e) => {     
        location.href = loot_depth + "/product/list/" + searchSelect.options[searchSelect.selectedIndex].value + "/" + searchInput.value;
    });
});