
  var naver_id_login = new naver_id_login("tFcf6kO8bBQSvTpMwwWV", "http://3.39.84.37:8888/farmocean/member/naver_callback");
  // 접근 토큰 값 출력
  alert(naver_id_login.oauthParams.access_token);
  // 네이버 사용자 프로필 조회
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  function naverSignInCallback() {

    member_id.value = naver_id_login.getProfileData('email');
    member_pw.value = 'NAVER_LOGIN_MEMBER';
    member_name.value = naver_id_login.getProfileData('name');
    member_nickName.value = naver_id_login.getProfileData('nickname');
    member_type.value = 'NAVER';

    document.getElementById('frm').submit();
  }

  function a (){

  };
  setInterval(a(),4000);

    
    
  
