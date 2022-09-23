const xhttpProfile = new XMLHttpRequest();

xhttpProfile.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;

	if(readyState == 4){

        // <div class ="profile_Img">
		// 		<c:forEach items="${memberinfo }" var="info">
		// 			<a href="<c:url value="/mypage/main"/>">
		// 			<img id="preview" src="/farmocean/resources/image/mypage/${info.member_image }" width="auto" height="50%"/>
		// 			</a>	
		// 		</c:forEach>
		// 	</div>

        // 바깥쪽 div
        // const profileDIv = document.getElementsByClassName('profile_Img');
        const profileDivID = document.getElementById('profileMain');
        const responseText = e.target.responseText;
		const pInfo = JSON.parse(responseText);

        // let divSub = document.createElement('div');			
		// divSub.className = 'profile_div';
		// profileDivID.appendChild(divSub); // 얘만 생성됨

        pInfo.forEach(function (info) {

            // 이 안에서는 div도 안 만들어진다
            // 마이페이지 a링크
            let aProfile = document.createElement('a');
            aProfile.href = loot_depth + '/mypage/main';
            
            console.log("a링크: " + aProfile);

            // 프로필 이미지 img
            let imgProfile = document.createElement('img');
            imgProfile.src = loot_depth + '/resources/image/mypage/' + info.member_image;

            aProfile.appendChild(imgProfile);

            // profileDIv.appendChild(aProfile);
            
            
            profileDivID.appendChild(aProfile);
        });
        
    }
});

window.addEventListener('load',() => {
	xhttpProfile.open('GET', loot_depth + "/user/profileImg");       
	xhttpProfile.send();
 });