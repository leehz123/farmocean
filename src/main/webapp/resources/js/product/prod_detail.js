






const loginBtn = document.getElementById('login');
const logoutBtn = document.getElementById('logout');
const reviewInputBtn = document.getElementById('prod-comment-input-btn');
const inputProdIdx = document.getElementById('input-prod-idx');
const commentSecretchk = document.getElementById('comment-secret');
const commentContainer = document.getElementById('comment-container');
const reviewContainer = document.getElementById('review-container');
let commentSecretNum = 0;
let commentTextarea = null;

let commentText = null;    
let currentProdIdx = inputProdIdx.value;
// let commentTextarea = null;
// let commentText = null;    
let commentPage = 1;
let reviewPage = 1;


function getLogInInfo() {
    //로그인 계정 정보 얻기 아작스 /board/temp_login_info
    // const xhttp7 = new XMLHttpRequest();
    
    // xhttp7.open('GET', '/farmocean/board/temp_login_info'); 
    // xhttp7.send();
    
    // xhttp7.addEventListener('readystatechange', (e)=> {
    //     const readyState = e.target.readyState;
    //     if(readyState == 4) { 
    //         const responseText = e.target.responseText;
    //         let member = JSON.parse(responseText);
    //         console.log(member);
    //     }
    // });
}

function getUserProfile(writer) {

    for(let i = 0; i < reviewList.length; ++i) {
        var writer = reviewList[i].member_id;
        console.log(writer);
        var imgPath = getUserProfile(writer);
         console.log('여기는.. ' + imgPath);
        document.getElementById('prod-review').innerHTML += `<div class="review-container"> 
                                                            <img class ="prod-review-user-profile" src="` + imgPath + `" alt="">
                                                            <div class ="prod-review-user-nickname">` + reviewList[i].member_id + `</div>
                                                            <div class ="prod-review-star">` + reviewList[i].review_starnum + `</div>
                                                            <div class ="prod-review-date">` + reviewList[i].review_date + `</div>
                                                            <div class ="prod-review-content">` + reviewList[i].review_content + `</div>
                                                            <div class ="prod-review-picture-preview">` + '리뷰픽쳐미리보기' + `</div>
                                                            <div class ="prod-review-picture-number">` + '리뷰픽처 개수' + `</div>
                                                            </div>`;			
    }

}


// let imgPath = '실패';
// function getUserProfile(writer) {
        
    // const xhttp6 = new XMLHttpRequest();
    // xhttp6.open('GET', '/farmocean/prod/get_member_image/' + writer);
    // xhttp6.send();
    // xhttp6.addEventListener('readystatechange', (e)=>{
    //     const readyState = e.target.readyState;
    //     if(readyState == 4) {
    //         const responseText = e.target.responseText;
    //         imgPath = responseText;
    //         console.log(imgPath);
    //     }     
    // });
// }



function ajaxReview() {
    const xhttp5 = new XMLHttpRequest();
    xhttp5.open('GET', '/farmocean/prod/select_prod_review/' + currentProdIdx);
    xhttp5.send();


    xhttp5.addEventListener('readystatechange', (e)=> {
        
        const readyState = e.target.readyState; 
        
        if(readyState == 4) {
            const responseText = e.target.responseText;
 			let reviewList = JSON.parse(responseText); 			
	
			let pageNum = null;
			if(reviewList.length % 5 == 0) {
				pageNum = reviewList.length / 5; //이건 사과바구니 적용 해야 되네 ;
			} else {
				pageNum = reviewList.length / 5 + 1;
			}
			
			document.getElementById('review-pagination-out').innerHTML = '';
			document.getElementById('review-pagination-out').innerHTML += `<li class="page-item"><a class="page-link" href="#">이전</a></li>`;
			for(let i = 1; i < pageNum; ++i) {
                var li = document.createElement('li');
                li.className = 'review-page-item';
                var a = document.createElement('a');
                //a.href = '#';
                a.className = 'page-link';
                var aText = document.createTextNode((i));
                a.append(aText);
                li.append(a);
                document.getElementById('review-pagination-out').append(li);
            }
			document.getElementById('review-pagination-out').innerHTML += `<li class="page-item"><a class="page-link" href="#">다음</a></li>`;				
			
			
			
			reviewContainer.innerHTML = '';
			for(let i = 5 * (reviewPage - 1); i < 5 * reviewPage; ++i) {
                var writer = reviewList[i].member_id;
                var sysdate = new Date(reviewList[i].review_date);
                var reviewDate = sysdate.toLocaleDateString();
	 			
                reviewContainer.innerHTML += `<div class="review-container">                                                                    

                                                                    <div class="review-info-container">
																		<a href="#"><img class ="prod-review-user-profile" src="` + '/farmocean/resources/image/prod/default_user_img.png' + `" alt=""></a>&nbsp&nbsp<a href="#">` + reviewList[i].member_nickName + `</a> ` + reviewDate + ` ` + '★★★★★' +                                                                    	
                                                                    `</div>
                                                                    <div class ="prod-review-content">` + reviewList[i].review_content + `</div>
																	</div>`;			
																	//밑에 애들도 추가해야 됨!!
																	//<div class ="prod-review-picture-preview">`  + `</div>
																	//<div class ="prod-review-picture-number">`  + `</div>
			}

        }
    });
}






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
            if(commentList.length % 5 == 0) {
                pageNum = commentList.length / 5;
            } else {
                pageNum = commentList.length / 5 + 1;
            }
            

			document.getElementById('comment-pagination-out').innerHTML = '';
			document.getElementById('comment-pagination-out').innerHTML += `<li class="page-item"><a class="page-link" href="#">이전</a></li>`;
			for(let i = 1; i < pageNum; ++i) {
                var li = document.createElement('li');
                li.className = 'comment-page-item';
                var a = document.createElement('a');
                //a.href = '#';
                a.className = 'page-link';
                var aText = document.createTextNode((i));
                a.append(aText);
                li.append(a);
                document.getElementById('comment-pagination-out').append(li);
            }
			document.getElementById('comment-pagination-out').innerHTML += `<li class="page-item"><a class="page-link" href="#">다음</a></li>`;				

            commentContainer.innerHTML = '';
            
            for(let i = 5 * (commentPage - 1); i < 5 * commentPage; ++i) {
				
                var sysdate = new Date(commentList[i].comment_date);
                var commentDate = sysdate.toLocaleDateString();
                var commentTitle = null;
                if(commentList[i].comment_secret == 1) {
                    commentTitle = `<span>비밀글입니다.` + ` ` + commentList[i].member_id + ` ` + commentDate + '</span>';
                } else {
                    commentTitle = `<span>` + commentList[i].comment_content + ` ` + commentList[i].member_id + ` ` + commentDate + ` 답변여부</span>`;
                }

                commentContainer.innerHTML +=  
                                                `<div class="comment-title">`
                                                    + commentTitle +   
                                                `</div>
                                                <div class="comment-content">
                                                    <p>` + commentList[i].comment_content + `</p>
                                                    <button>삭제</button>
                                                    <button>수정</button>
                                                    <button>답변하기</button>
                                                </div>`;
            }
        }

    });
};

//페이지 로드되자마자 댓글 목록 띄우기
window.onload = function() {
    ajaxComment();
    ajaxReview();
};


//페이지네이션 클릭된 페이지 텍스트 반환
$(document).on("click",".comment-page-item",function(){  //동적으로 생성한 요소는 이렇게 document에 직접 이벤트 지정해줘야 함!★★★★
//$('.pagination').children('li').on('click', function(e) {
    //console.log($(this).text());
    commentPage = $(this).text();
    ajaxComment();
})

$(document).on("click",".review-page-item",function(){  
    reviewPage = $(this).text();
    ajaxReview();
})


//임시 로그인 아작스
const xhttp1 = new XMLHttpRequest();
xhttp1.addEventListener('readystatechange', (e)=> {
    const readyState = e.target.readyState;
    
    if(readyState == 4) {
        //const s = JSON.parse(responseText); 컨트롤러에서 return (LoginMember)session.getAttribute("loginId"); 해놨기 때문에 안 받아도 됨 이렇게 받지 않아도 됨
        const responseText = e.target.responseText;
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
    

    if(readyState == 4) { 
        //const s = JSON.parse(responseText); 컨트롤러에서 return (LoginMember)session.getAttribute("loginId"); 해놨기 때문에 바로 세션에 저장됨 이렇게 받지 않아도 됨
        const responseText = e.target.responseText;
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
            commentSecretNum = 0;
            ajaxComment();
		} else {
			alert('comment insert failed');
		}
    }
});


if(reviewInputBtn!=null) {


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

        const xhttp7 = new XMLHttpRequest();
    
        xhttp7.open('GET', '/farmocean/board/temp_login_info'); 
        xhttp7.send();
        
        xhttp7.addEventListener('readystatechange', (e)=> {
            const readyState = e.target.readyState;
            if(readyState == 4) { 
                const responseText = e.target.responseText;
                let member = JSON.parse(responseText);
                console.log(member);

                const productComment = {
                    prod_idx : currentProdIdx, 
                    member_id : member.member_id, 
                    comment_content : commentText, 
                    comment_secret : commentSecretNum
                }
                /*comment_idx, comment_date는 매퍼, 레컨에서 처리*/ 
                xhttp3.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
                xhttp3.send(JSON.stringify(productComment));
            }
        });
    });
}









//댓글 아코디언 
$(document).on("click",".comment-title",function(){



    //여기 ajax이용해서 member_id불러온 다음에 세션id랑 같은지 인증하는 if문?
    
    const xhttp7 = new XMLHttpRequest();
    
    xhttp7.open('GET', '/farmocean/board/temp_login_info'); 
    xhttp7.send();
    
    xhttp7.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;
        if(readyState == 4) { 
            const responseText = e.target.responseText;
            let member = JSON.parse(responseText);
            console.log(member);
            
        
        
        
        
        
        }
    });
    
    //$(".comment-title").click(function() {  
    $(this).next(".comment-content").stop().slideToggle(300);
    $(this).toggleClass('on').siblings().removeClass('on');
    $(this).next(".comment-content").siblings(".comment-content").slideUp(300); // 1개씩 펼치기


});
 
 
 
 
const reviewWriteBtn = document.getElementById('review-write-popup-btn');
reviewWriteBtn.addEventListener('click', (e)=> {
	
    
    //일단 URL자리에 "URL" 넣어서 테스트 후 경로 어떻게 넣을지 정하면 됨
    window.open("../product_review_write/1/1", "리뷰등록 팝업창", "width=500, height=500, top=50, left=50");
});



