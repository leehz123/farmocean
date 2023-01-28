const xhttpBanner = new XMLHttpRequest();

xhttpBanner.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;
   
	if(readyState == 4){
        // <div class="slide_div_wrap">
		// 		<div class="slide_div">
		// 			<div>
		// 				<a>
		// 					<img src="${path }/resources/image/mainpage/slide_01.jpg" style="max-width: 100%; height: auto;">
		// 				</a>
		// 			</div>
		// 			<div>
		// 				<a>
		// 					<img src="${path }/resources/image/mainpage/slide_02.jpg" style="max-width: 100%; height: auto;">
		// 				</a>
		// 			</div>
		// 			<div>
		// 				<a>
		// 					<img src="${path }/resources/image/mainpage/slide_03.jpg" style="max-width: 100%; height: auto;">
		// 				</a>
		// 			</div>			
		// 		</div>	
		// 	</div>

        const bannerDiv = document.getElementById('slide_div');
        const responseText = e.target.responseText;
		const bInfo = JSON.parse(responseText);
        
        console.log(bInfo.filename);

        bInfo.forEach(function (bannercate){

            let bannerD = document.createElement('div');

            let aBanner = document.createElement('a');

            let aNotice = document.createElement('a');
            aNotice.href = bannercate.link;

            
            let bannerImg = document.createElement('img');
            bannerImg.src = loot_depth + bannercate.filename;
            
            bannerImg.style.maxWidth = '100%';
            bannerImg.style.height = 'auto';
            console.log("a��ũ: " + bannerImg.src); 
    
            aNotice.append(aBanner);
            aBanner.appendChild(bannerImg);
            bannerD.appendChild(aNotice);
            bannerDiv.appendChild(bannerD);
        });

        $(document).ready(function() {
			$(".slide_div").slick({
				dots: true,
				autoplay : true,
				autoplaySpeed: 5000
			});
				
		});

    }
});

window.addEventListener('load',() => {
	xhttpBanner.open('GET', loot_depth + "/banner/maintop");       
	xhttpBanner.send();
 });