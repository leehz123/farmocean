<!doctype html>
<html lang="ko">
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="EUC-KR"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>

<script type="text/javascript">
  var naver_id_login = new naver_id_login("tFcf6kO8bBQSvTpMwwWV", "http://localhost:8888/farmocean/member/naver_callback");
  // 접근 토큰 값 출력
  // alert(naver_id_login.oauthParams.access_token);
  // 네이버 사용자 프로필 조회
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  function naverSignInCallback() {
	  
	const xhttp = new XMLHttpRequest();

    const postMember = {
            member_id : naver_id_login.getProfileData('email'),
            member_pw : 'NAVER_LOGIN_MEMBER',
            member_name : naver_id_login.getProfileData('name'),
            member_nickName : naver_id_login.getProfileData('nickname'),           
            member_type : 'NAVER'
        }
    
    xhttp.open('POST', '/farmocean/member/insert/naver');
    xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    console.log('JSON string : ' , JSON.stringify(postMember));
    xhttp.send(JSON.stringify(postMember));

    xhttp.addEventListener('readystatechange',(e)=>{
        const readyState = e.target.readyState;
        console.dir(e.target);
        if(readyState == 4 ){
            
            const httpStatus = e.target.status;
           
            if(httpStatus == 200){
               
                window.location.replace("/farmocean/member/success");
                
            } else if(httpStatus == 500){
                
                window.location.replace("/farmocean/member/success");
            } else {
                out.innerText = 'SIGNUP FAILED!';
                out.style.color = 'red';
            }
        }
    });
    
  }
</script>
</body>
</html>
