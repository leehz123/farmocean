const xhttpProfile = new XMLHttpRequest();

xhttpProfile.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){

        // <div class ="profile_Img">
		// 		<c:forEach items="${memberinfo }" var="info">
		// 			<a href="<c:url value="/mypage/main"/>">
		// 			<img id="preview" src="/farmocean/resources/image/mypage/${info.member_image }" width="auto" height="50%"/>
		// 			</a>
		// 			
		// 		</c:forEach>
		// 	</div>

        const profileDIv = document.getElementsByClassName('profile_Img');
        const responseText = e.target.responseText;
		const pInfo = JSON.parse(responseText);

        pInfo.forEach(function (info) {
            let aProfile = document.createElement('a');
            aProfile.href = loot_depth + '/mypage/main';

            let imgProfile = document.createElement('img');
            imgProfile.className = 'imgPro';

            imgProfile.src = loot_depth + '/resources/image/mypage/' + info.member_image;

            aProfile.appendChild(imgProfile);

        });
        
    }
});

window.addEventListener('load',() => {
	xhttpCateTop.open('GET', loot_depth + "/user/profileImg");       
	xhttpCateTop.send();   
 });