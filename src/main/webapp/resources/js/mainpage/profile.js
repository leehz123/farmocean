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

        // <div class ="profile_Img">
		// 		<a href="/farmocean/mypage/main">
		// 		<img id="preview" src="/farmocean/resources/image/mypage/profile_basic_image.jpg" width="auto" height="50%"/>
		// 		</a>   		 
		// </div>

        const profileDIv = document.getElementsByClassName('profile_Img');
        const profileDivID = document.getElementById('profileMain');

        const responseText = e.target.responseText;
		const pInfo = JSON.parse(responseText);

        pInfo.forEach(function (profileImg) {
            let aProfile = document.createElement('a');
            aProfile.href = loot_depth + '/mypage/main';

            let imgProfile = document.createElement('img');
            imgProfile.className = 'imgPro';

            imgProfile.src = loot_depth + '/resources/image/mypage/' + profileImg.member_image;

            aProfile.appendChild(imgProfile);

            // profileDIv.appendChild(aProfile);
            profileDivID.appendChild(aProfile);

            console.log("a¸µÅ©: " + aProfile);

        });
        
    }
});

window.addEventListener('load',() => {
	xhttpProfile.open('GET', loot_depth + "/user/profileImg");       
	xhttpProfile.send();   
 });