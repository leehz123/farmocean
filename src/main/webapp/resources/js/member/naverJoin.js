
const btn = document.getElementById('join_btn');


btn.addEventListener('click',(e)=>{
        if(!(post_naver_phoneNum1.value+
        post_naver_phoneNum2.value+
        post_naver_phoneNum3.value)){
            alert('휴대폰 번호는 필수 입력 사항입니다');
            e.preventDefault();
        }else {
        const postMember = {
            member_id : post_naver_id.value,
            member_pw : 'NAVER_LOGIN_MEMBER',
            member_name : post_naver_name.value,
            member_nickName : post_naver_nickName.value,
            member_point : 3000,        
            member_email : post_naver_email.value,
            member_phoneNum : post_naver_phoneNum1.value+'-'+
                                post_naver_phoneNum2.value+'-'+
                                post_naver_phoneNum3.value,
            member_accountNum : 'null',
            member_address : 'null',
            member_account_status : 1,
            member_type : 'B',
            member_report : 0,
            member_image : '/farmocean/resources/image/prod/default_user_img.png'
        }    
        xhttp = new XMLHttpRequest();
        xhttp.open('POST', '/farmocean/member/insert/member');
        xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        console.log('JSON string : ' , JSON.stringify(postMember));
        xhttp.send(JSON.stringify(postMember));

        xhttp.addEventListener('readystatechange',(e)=>{
            const readyState = e.target.readyState;
            console.dir(e.target);
            if(readyState == 4 ){
                
                const httpStatus = e.target.status;
               
                if(httpStatus == 200){
                    alert('success');
                    window.location.replace("/farmocean/member/login");
                    
                } else {
                    out.innerText = 'SIGNUP FAILED!';
                    out.style.color = 'red';
                }
            }
        });
        }
    }
               
                        
);







	
	
	
