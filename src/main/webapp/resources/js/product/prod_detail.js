

const loginBtn = document.getElementById('login');
const logoutBtn = document.getElementById('logout');
const reviewInputBtn = document.getElementById('prod-comment-input-btn');
const inputProdIdx = document.getElementById('input-prod-idx');
const commentSecretchk = document.getElementById('comment-secret');
const commentContainer = document.getElementById('comment-container');

let commentTextarea = null;

let commentText = null;    
let currentProdIdx = inputProdIdx.value;
// let commentTextarea = null;
// let commentText = null;    
let commentPage = 1;



function ajaxComment() {
    const xhttp4 = new XMLHttpRequest();
    xhttp4.open('GET', '/farmocean/prod/select_prod_comment/' + currentProdIdx);    
    xhttp4.send();
    xhttp4.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;
        if(readyState==4) {
            const responseText = e.target.responseText;
			const commentList = JSON.parse(responseText);
			var pageNum = null;
			if(commentList.length%5 == 0) {
				pageNum = commentList.length / 5;			
			} else {
				pageNum = commentList.length / 5 + 1;
			}
			document.getElementById('pagination-out').innerHTML = '';
			document.getElementById('pagination-out').innerHTML += `<li class="page-item"><a class="page-link" href="#">Previous</a></li>`;
			for(let i = 0; i < pageNum; ++i) {
				document.getElementById('pagination-out').innerHTML += `<li class="page-item"><a class="page-link" href="#">` + (i+1) + `</a></li>`;
			}
			document.getElementById('pagination-out').innerHTML += `<li class="page-item"><a class="page-link" href="#">Next</a></li>`;				

            commentContainer.innerHTML = '';
            
            for(let i = 5 * (commentPage - 1); i < 5 * commentPage; ++i) {
				
                var sysdate = new Date(commentList[i].comment_date);
                var commentDate = sysdate.toLocaleDateString();

                commentContainer.innerHTML +=  
                                                `<div class="comment-title">
                                                    <span>` + commentList[i].comment_content + ` ` + commentList[i].member_id + ` ` + commentDate + ` 답변완료</span>
                                                </div>
                                                <div class="comment-content">
                                                    <p>비밀글 ㄴ ㅐ 용입니다~~~~~~~~~~~~~~~~~~~~~~</p>
                                                    <button>삭제</button>
                                                    <button>수정</button>
                                                    <button>답변하기</button>
                                                </div>`;
            }
        }

    });
};



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



//댓글 등록 아작스
const xhttp3 = new XMLHttpRequest();
xhttp3.addEventListener('readystatechange', (e)=> {
    const readyState = e.target.readyState;
    
    if(readyState == 4) {
        const responseText = e.target.responseText;
        //console.log('리판텍 : ' + responseText);
		if(responseText=='1') {
			commentTextarea.value = '';	
			//alert('insert Success 댓글등록 완');	
            commentSecretchk.checked = false;
            
            ajaxComment();
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






window.onload = function() {
    ajaxComment();
};



//아코디언 테스트
document.getElementsByClassName('comment-title').item(0).addEventListener('click', (e)=> {
	console.log('눌리긴 함?');
});
/*
$(".comment-title").click(function() {  
    $(this).next(".comment-content").stop().slideToggle(300);
    $(this).toggleClass('on').siblings().removeClass('on');
    $(this).next(".comment-content").siblings(".comment-content").slideUp(300); // 1개씩 펼치기
 });
*/
