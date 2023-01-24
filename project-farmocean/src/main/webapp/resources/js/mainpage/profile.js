const xhttpProfile = new XMLHttpRequest();

xhttpProfile.addEventListener('readystatechange', (e) => {

	const readyState = e.target.readyState;
   
	if(readyState == 4){

        // <div class ="profile_Img">
		// 		<c:forEach items="${memberinfo }" var="info">
		// 			<a href="<c:url value="/mypage/main"/>">
		// 			<img id="preview" src="/farmocean/resources/image/mypage/${info.member_image }" width="auto" height="50%"/>
		// 			</a>	
		// 		</c:forEach> // ��� �ϳ����� json���� �ҷ��� �� forEach �� �ص� �ȴ�
		// 	</div>

        // �ٱ��� div
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

        // ���������� a��ũ
        let aProfile = document.createElement('a');
        aProfile.className = 'profile_img_link';

        // aProfile.href = loot_depth + '/mypage/changeimg';

        aProfile.addEventListener("click", function() {

            window.open(loot_depth + '/mypage/changeimg',"new","width=500,height=600,top=100,left=100");
        });
            
        // console.log("a��ũ: " + aProfile);

        // ������ �̹��� img
        let imgProfile = document.createElement('img');
        imgProfile.src = loot_depth + '/resources/image/mypage/' + pInfo.member_image;
        imgProfile.className = 'profile_Img';

        imgProfile.style.width = '65px';
        imgProfile.style.height = '65px';
        imgProfile.style.borderRadius = '50%';

        aProfile.appendChild(imgProfile);
            
        profileDivID.appendChild(aProfile);

        
    }
});

window.addEventListener('load',() => {
	xhttpProfile.open('GET', loot_depth + "/user/profileImg");       
	xhttpProfile.send();
 });