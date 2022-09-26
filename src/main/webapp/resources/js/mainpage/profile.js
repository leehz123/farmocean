const xhttpProfile = new XMLHttpRequest();

xhttpProfile.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;
   
	if(readyState == 4){

        // <div class ="profile_Img">
		// 		<c:forEach items="${memberinfo }" var="info">
		// 			<a href="<c:url value="/mypage/main"/>">
		// 			<img id="preview" src="/farmocean/resources/image/mypage/${info.member_image }" width="auto" height="50%"/>
		// 			</a>	
		// 		</c:forEach> // 얘는 하나여서 json으로 불러올 때 forEach 안 해도 된다
		// 	</div>

        // 바깥쪽 div
        // const profileDIv = document.getElementsByClassName('profile_Img');
        const profileDivID = document.getElementById('profileMain');
        const responseText = e.target.responseText;

        // alert(responseText);
        // console.log(responseText);
		const pInfo = JSON.parse(responseText);
        
        console.log(pInfo.member_image);

        // <a href="<c:url value="/mypage/main"/>">
        // <img id="preview" src="/farmocean/resources/image/mypage/${info.member_image }" width="auto" height="50%"/>
        // </a>

        // 이 안에서는 div도 안 만들어진다
        // 마이페이지 a링크
        let aProfile = document.createElement('a');
        aProfile.href = loot_depth + '/mypage/main';
            
        // console.log("a링크: " + aProfile);

        // 프로필 이미지 img
        let imgProfile = document.createElement('img');
        imgProfile.src = loot_depth + '/resources/image/mypage/' + pInfo.member_image;

        imgProfile.style.width = 'auto';
        imgProfile.style.height = '70%';
        imgProfile.style.borderRadius = '50%';

        aProfile.appendChild(imgProfile);
            
        profileDivID.appendChild(aProfile);

        
    }
});

window.addEventListener('load',() => {
	xhttpProfile.open('GET', loot_depth + "/user/profileImg");       
	xhttpProfile.send();
 });