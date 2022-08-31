

const loginBtn = document.getElementById('login');
const logoutBtn = document.getElementById('logout');
const reviewInputBtn = document.getElementById('prod-comment-input-btn');
const inputProdIdx = document.getElementById('input-prod-idx');
const commentSecretchk = document.getElementById('comment-secret');
let commentTextarea = null;


let commentText = null;    
let currentProdIdx = inputProdIdx.value;
// let commentTextarea = null;
// let commentText = null;    




//로그인 아작스
const xhttp1 = new XMLHttpRequest();
xhttp1.addEventListener('readystatechange', (e)=> {
    const readyState = e.target.readyState;
    const responseText = e.target.responseText;

    if(readyState == 4) {
        //const s = JSON.parse(responseText); 컨트롤러에서 return (LoginMember)session.getAttribute("loginId"); 해놨기 때문에 안 받아도 됨 이렇게 받지 않아도 됨
        window.location.reload();
    }
});
loginBtn.addEventListener('click', (e)=> {
    xhttp1.open('GET', '/farmocean/prod/temp_login'); 
    xhttp1.send();
});

//로그아웃 아작스
const xhttp2 = new XMLHttpRequest();
xhttp2.addEventListener('readystatechange', (e)=> {
    const readyState = e.target.readyState;
    const responseText = e.target.responseText;

    if(readyState == 4) { 
        //const s = JSON.parse(responseText); 컨트롤러에서 return (LoginMember)session.getAttribute("loginId"); 해놨기 때문에 바로 세션에 저장됨 이렇게 받지 않아도 됨
        window.location.reload();
    }
});
logoutBtn.addEventListener('click', (e)=> {
    xhttp2.open('GET', '/farmocean/prod/temp_logout'); 
    xhttp2.send();
});



//리뷰 등록 아작스
const xhttp3 = new XMLHttpRequest();
xhttp3.addEventListener('readystatechange', (e)=> {
    const readyState = e.target.readyState;
    
    if(readyState == 4) {
        const responseText = e.target.responseText;
        //console.log('리판텍 : ' + responseText);
		if(responseText=='1') {
			commentTextarea.value = '';	
			alert('리뷰등록 완료');	
            commentSecretchk.checked = false;
		} else {
			alert('comment insert failed');
		}
    }
});





if(reviewInputBtn!=null) {

        let commentSecretNum = 0;
        commentSecretchk.addEventListener('click', (e)=> {
            if(e.target.checked == true) {
                commentSecretNum = 1;
                console.log(commentSecretNum);
            } else {
                commentSecretNum = 0;
                console.log(commentSecretNum);
            }
        });


	reviewInputBtn.addEventListener('click', (e)=> {
    	xhttp3.open('POST', '/farmocean/prod/insert_review');
	    
        commentTextarea = document.getElementById('prod-comment-textarea');
        commentText = commentTextarea.value;    

        
		const productComment = {
			prod_idx : currentProdIdx, 
			member_id : 'kingbambbang', 
			comment_content : commentText, 
			comment_secret : commentSecretNum
		}
		/*comment_idx, comment_date는 매퍼, 레컨에서 처리*/ 
    
        xhttp3.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        xhttp3.send(JSON.stringify(productComment));
    });
}



