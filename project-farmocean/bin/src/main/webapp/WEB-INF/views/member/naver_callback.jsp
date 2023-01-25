<!doctype html>
<html lang="ko">
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="EUC-KR"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
<form id="frm" method="post" action="/farmocean/member/naverlogincheck">			
<input id="member_id" name="member_id" type="hidden">				
<input id="member_pw" name="member_pw" type="hidden">
<input id="member_name" name="member_name" type="hidden">
<input id="member_nickName" name="member_nickName" type="hidden">
<input id="member_type" name="member_type" type="hidden">
			
</form>
<script type="text/javascript">

var naver_id_login = new naver_id_login("tFcf6kO8bBQSvTpMwwWV", "http://localhost:8888/farmocean/member/naver_callback");
// 접근 토큰 값 출력
//alert(naver_id_login.oauthParams.access_token);
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

</script>
</body>
</html>
