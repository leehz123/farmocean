//아작스 안에 아작스 넣을 일이 없대 .. ㅋ 나 그런거 많은디

const inputProdIdx = document.getElementById('input-prod-idx');
const inputSellerId = document.getElementById('input-seller-id');
const loginBtn = document.getElementById('login');
const logoutBtn = document.getElementById('logout');
const commentContainer = document.getElementById('comment-container');
const reviewContainer = document.getElementById('review-container');
const commentSecretchk = document.getElementById('comment-secret');
const commentInputBtn = document.getElementById('prod-comment-input-btn');



let currentProdIdx = inputProdIdx.value;
let currentProdSeller = inputSellerId.value;    
let commentSecretNum = 0;
let commentText = null;    
let commentTextarea = null;
let commentPage = 1;
let reviewPage = 1;


//임시 로그인 에이작스
// const xhttp1 = new XMLHttpRequest();
// xhttp1.addEventListener('readystatechange', (e)=> {
//     const readyState = e.target.readyState;
    
//     if(readyState == 4) {
//         //const s = JSON.parse(responseText); 컨트롤러에서 return (LoginMember)session.getAttribute("loginId"); 해놨기 때문에 안 받아도 됨 이렇게 받지 않아도 됨
//         const responseText = e.target.responseText;
//         window.location.reload();
//     }
// });
// loginBtn.addEventListener('click', (e)=> {
//     xhttp1.open('GET', '/farmocean/prod/temp_login'); 
//     xhttp1.send();
// });



//로그아웃 에이작스
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












// 리뷰 목록 띄우기 에이작스 (JoinReviewMember dto 이용)
function ajaxReview() {
    
    const xhttp5 = new XMLHttpRequest();
    xhttp5.open('GET', '/farmocean/prod/select_prod_review/' + currentProdIdx);
    xhttp5.send();

    xhttp5.addEventListener('readystatechange', (e)=> {
        
        const readyState = e.target.readyState; 
        
        if(readyState == 4) {
            const responseText = e.target.responseText;
            let reviewList = JSON.parse(responseText); 
            
            if(reviewList[0] != undefined) {
                //여기서 얻은 reviewList는 prod_review와 members 테이블을 조인해서 prod_idx로 select한 결과
                
                //리뷰리스트.length로 페이지 수 구하기 (1페이지 당 댓글 5 개씩 표시)
		        var pageNum = null;
		        if(reviewList.length % 5 == 0) {
		            pageNum = commentList.length / 5;
		        } else {
		            pageNum = Math.floor(reviewList.length / 5) + 1;
		        }
		        //console.log('리뷰수 : ', reviewList.length);
		        //console.log('페이지수 : ', pageNum);
                
                //댓글 페이지 수만큼 페이지네이션 버튼 만들기
                document.getElementById('review-pagination-out').innerHTML = '';
                let paginationTxt = '';
                paginationTxt += `<li class="page-item"><a class="page-link" href="#">이전</a></li>`;
                for(let i = 1; i <= pageNum; ++i) {
                    paginationTxt += `<li class="comment-page-item"><a class="page-link">` + i + ` </a></li>`
                }
                paginationTxt += `<li class="page-item"><a class="page-link" href="#">다음</a></li>`;				
                document.getElementById('review-pagination-out').innerHTML = paginationTxt;

                
                //현재 페이지에 포함될 댓글만 댓글목록에 생성
                reviewContainer.innerHTML = '';
                let reviewTxt = '';
                for(let i = 5 * (reviewPage - 1); i < 5 * reviewPage; ++i) {
                    var writer = reviewList[i].member_id;
                    var sysdate = new Date(reviewList[i].review_date);
                    var reviewDate = sysdate.toLocaleDateString();
                    
                    reviewTxt += `<div class="review-container">                                                                    
                                <div class="review-info-container">
                                    <a href="#"><img class ="prod-review-user-profile" src="` + reviewList[i].member_image + `" alt=""></a>&nbsp&nbsp<a href="#">` + reviewList[i].member_nickName + `</a> ` + reviewDate + ` ` + '★★★★★' +                                                                    	
                                `</div>
                                <div class ="prod-review-content">` + reviewList[i].review_content + `</div>
                                </div>`;			
                                //밑에 애들도 추가해야 됨!!
                                //<div class ="prod-review-picture-preview">`  + `</div>
                                //<div class ="prod-review-picture-number">`  + `</div>
                }
                reviewContainer.innerHTML = reviewTxt;
            } else {
                document.getElementById('review-container').innerHTML = '<div>리뷰가 존재하지 않습니다.</div>';
            } 
            
        }
    });
}

//리뷰등록 팝업창 띄우기 버튼
const reviewWriteBtn = document.getElementById('review-write-popup-btn');
reviewWriteBtn.addEventListener('click', (e)=> {
    //일단 URL자리에 "URL" 넣어서 테스트 후 경로 어떻게 넣을지 정하면 됨
    window.open("../product_review_write/1/1", "리뷰등록 팝업창", "width=500, height=500, top=50, left=50");
});












//댓글 목록 띄우기 에이작스
function ajaxComment() {

    const xhttp4 = new XMLHttpRequest();
    xhttp4.open('GET', '/farmocean/prod/select_prod_comment/' + currentProdIdx);    
    xhttp4.send();
    xhttp4.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;
        if(readyState==4) {
            const responseText = e.target.responseText;
			const commentList = JSON.parse(responseText);
            
            //drawCommenList(commentList);
            if(commentList[0] != undefined) {
                document.getElementById('no-comment').innerHTML = ''; 
        
                var pageNum = null;
                if(commentList.length % 5 == 0) {
                    pageNum = commentList.length / 5;
                } else {
                    pageNum = Math.floor(commentList.length / 5) + 1;
                }
        
                document.getElementById('comment-pagination-out').innerHTML = '';
                let paginationTxt = '';
                paginationTxt += `<li class="page-item"><a class="page-link" href="#">이전</a></li>`;
                for(let i = 1; i <= pageNum; ++i) {
                    paginationTxt += `<li class="comment-page-item"><a class="page-link">` + i + ` </a></li>`
                }
                paginationTxt += `<li class="page-item"><a class="page-link" href="#">다음</a></li>`;				
                document.getElementById('comment-pagination-out').innerHTML = paginationTxt;
        
                commentContainer.innerHTML = '';
                for(let i = 5 * (commentPage - 1); i < 5 * commentPage; ++i) {
                    console.log('i : ', i);
                    
                    //더 이상 표시할 댓글 수가 없을 경우 for문 종료
                    if(commentList[i] == undefined) {
                        break;
                    }
                    
                    var sysdate = new Date(commentList[i].comment_date);
                    var commentDate = sysdate.toLocaleDateString();
                    var commentTitle = null;
                    
                    var commentTxt = '';
                    if(commentList[i].comment_secret == 1) { // 비밀글이면
                        commentTitle = `<span>비밀글입니다.` + ` ` + commentList[i].member_id + ` ` + commentDate + ` 답변여부</span>`;
                        
                        if(commentList[i].comment_accessible == 1) { // 접근 가능하면
                            commentTxt +=  `<div class="comment-title">` + commentTitle + `</div>`;
                            commentTxt +=  `<div class="comment-content">
                                                                <p>` + commentList[i].comment_content + `</p>
                                                                <button class = "comment-delete-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">삭제</button>
                                                                <button value="" class="comment-edit-btn">수정</button>
                                                                <button class="comment-reply-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">답변하기</button>
                                                            </div>`;
                        } else { //접근할 수 없으면
                            commentTxt +=  `<div class="comment-title secret-comment">` + commentTitle + `</div>`;
                        }
        
                        
                    } else { //비밀글이 아니면
                        // 제목으로 댓글 내용 미리보기 (20자 이상부턴 생략 하고 ... 붙이기) 
                        let comment_content_omit = '';
                        if(commentList[i].comment_content.trim().length <= 20) {
                            comment_content_omit = commentList[i].comment_content;
                        } else {
                            comment_content_omit = commentList[i].comment_content.substr(0, 20) + '...';
                        }
        
        
                        commentTitle = 
                        `<span>` + comment_content_omit + ` ` + commentList[i].member_id + ` ` + commentDate + ` 답변여부</span>`;
                        commentTxt +=  `<div class="comment-title">` + commentTitle + `</div>`;
                        commentTxt += 
                                                        `<div class="comment-content">
                                                            <p>` + commentList[i].comment_content + `</p>` +
                                                            `<button class = "comment-delete-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">삭제</button>
                                                            <button class="comment-edit-btn">수정</button>    
                                                            <button class="comment-reply-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">답변하기</button>
                                                        </div>`;
        
                    }
                    commentContainer.innerHTML += commentTxt;
                }
            } else {
                document.getElementById('comment-pagination-out').innerHTML = '';
                commentContainer.innerHTML = '';
                document.getElementById('no-comment').innerHTML = '';
                document.getElementById('no-comment').innerHTML += '<div>댓글이 존재하지 않습니다.</div>';
            }
        
        }
    });
};

//댓글 목록 데이터 받아서 페이지네이션 만들고 댓글 목록 띄우는 함수
// function drawCommenList(commentList) {
//     //그냥 다시 ajaxComment()에 집어넣음
// }


//댓글 입력 버튼은 세션아이디가 존재할 때(누군가 로그인 돼 있을 때) 생성됨 
//(<c:when test="${sessionScope.loginId eq null }"></c:when>)
if(commentInputBtn!=null) {

    //비밀글 첵박 이벤트(체크 상태에 따라 전역변수 commentSecretNum의 값 변경(체크 : 1, 체크해제 : 0))
    commentSecretchk.addEventListener('click', (e)=> {
        if(e.target.checked == true) {
            commentSecretNum = 1;
            console.log(commentSecretNum);
        } else {
            commentSecretNum = 0;
            console.log(commentSecretNum);
        }
    });

    //입력 버튼 눌렀을 때 이벤트(xhttp3.send())
    $(document).on("click", "#prod-comment-input-btn", function(){
        
        commentTextarea = document.getElementById('prod-comment-textarea');
        commentText = commentTextarea.value;    
        if(commentText != '') {
            const productComment = {
                prod_idx : currentProdIdx, 
                comment_content : commentText, 
                comment_secret : commentSecretNum
            }
            /*member_id, comment_idx, comment_date는 매퍼, 레컨에서 처리*/ 
            
            //댓글 등록 에이작스
            const xhttp3 = new XMLHttpRequest();
            xhttp3.open('POST', '/farmocean/prod/insert_comment');
            xhttp3.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
            xhttp3.send(JSON.stringify(productComment));

            xhttp3.addEventListener('readystatechange', (e)=> {
                const readyState = e.target.readyState;
                if(readyState == 4) {
                    const readyState = e.target.responseText;		
                    //const commentList = JSON.parse(readyState);
                    //drawCommenList(commentList);
                }
            });
            
            ajaxComment();

            commentTextarea.value = '';				
            commentSecretchk.checked = false;
            commentSecretNum = 0;

        } else {
            alert('댓글 내용을 입력해주세요.'); 
        }
});
}


// 댓글 삭제 ajax (ajaxComment()호출하니까 ajaxComment() 아래에 위치시키기!)
function deleteComment(commentIdx, memberId) {        
    const xhttp9 = new XMLHttpRequest();
    //console.log('/farmocean/prod/delete_comment/' + currentProdIdx + '/' + commentIdx); 
    xhttp9.open('GET', '/farmocean/prod/delete_comment/' + currentProdIdx + '/' + commentIdx);
    xhttp9.send();
    xhttp9.addEventListener('readystatechange', (e)=> {
        const readystatechange = e.target.readyState;
        if(readystatechange == 4) {
            const responseText = e.target.responseText;
            const commentList = JSON.parse(responseText);
            //drawCommenList(commentList);
        }
        ajaxComment();
    }); 
};


//댓글 삭제 버튼 눌렀을 때 액션
$(document).on("click", ".comment-delete-btn", function() {
    let commentWriter = $(this).val();
    let commentIdx = $(this).attr('name');

    const xhttp7 = new XMLHttpRequest();
    
    xhttp7.open('GET', '/farmocean/prod/get_login_id');
    xhttp7.send();
    
    xhttp7.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;
        if(readyState == 4) { 
            const currentLoginId = e.target.responseText;            
            if(commentWriter == currentLoginId) {
                deleteComment(commentIdx, commentWriter);
                alert('삭제 되었습니다.');
            } else {
                alert('작성자 본인만 삭제할 수 있습니다.');
            }   
        }
    });

});



//댓글 아코디언 
$(document).on("click", ".comment-title", function(){
    $(this).next(".comment-content").stop().slideToggle(300);
    $(this).toggleClass('on').siblings().removeClass('on');
    $(this).next(".comment-content").siblings(".comment-content").slideUp(300); // 1개씩 펼치기
});

//다른 작성자의 비밀댓글 클릭 시 액션
$(document).on("click", ".secret-comment", function(){    
    alert('비밀글입니다.');
});







//댓글 답변하기 버튼 눌렀을 때 액션
$(document).on("click", ".comment-reply-btn", function(){
    if(document.getElementById('comment-reply-area') != null) {
        alert('현재 진행중인 다른 답변이 있습니다.');
        return;
    }

    let commentWriter = $(this).val();
    let commentIdx = $(this).attr('name');

    const xhttp10 = new XMLHttpRequest();
    xhttp10.open('GET','/farmocean/prod/get_login_id');
    xhttp10.send();

    xhttp10.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;

        if(readyState == 4) {
            const responseText = e.target.responseText;

            if(responseText == currentProdSeller) {
                if($(this).parent().html().indexOf('<textarea') == -1) {
                    $(this).parent().append(`
                                                <div id="comment-reply-area">
                                                    <hr>
                                                    <textarea id=" ` + commentIdx + ` " class="comment-reply-textarea"></textarea>
                                                    <br>
                                                    <button name=" ` + commentIdx + ` " class="comment-reply-input">입력</button>
                                                    <button name=" ` + commentIdx + ` " class="comment-reply-cancle">취소</button>
                                                </div>
                                            `);
                }
    
            } else {
                alert('상품 판매자만 답변할 수 있습니다.');
            }            
        }
    });   
});



//댓글 답변 취소 버튼 액션
$(document).on("click", ".comment-reply-cancle", function(){
    //let commentIdx = $(this).attr('name');    
    document.getElementById('comment-reply-area').remove();
});

//댓글 답변 입력 버튼 액션
$(document).on("click", ".comment-reply-input", function(){
    //▲잠깐만▲ 여기서부터는 입력버튼 눌렀을 때 실행될 것들인데 그러면 밖으로 빼야 됨. 근데 commentIdx를 전달받을 수 없네 어쩐다
    //입력버튼의 name으로 comment_idx를 심어놔야겠다 

    let commentIdx = $(this).attr('name');

    const xhttp8 = new XMLHttpRequest();
    xhttp8.open('POST', '/farmocean/prod/update_comment_reply');
    replyTextarea = document.getElementById(commentIdx);
    replyText = replyTextarea.value;
    if(replyText != '') {        
        const productComment = {
            comment_idx : commentIdx, 
            comment_reply : replyText
        }
        xhttp8.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        xhttp8.send(JSON.stringify(productComment));    
    } else {
        alert('답변을 입력해주세요');
    }
    
    
    xhttp8.addEventListener('readystatechange', (e)=>{
       const readyState = e.target.readyState;
       if(readyState == 4) {
            const responseText = e.target.responseText;
            console.log(responseText);
            if(responseText == 1) {
                alert('답글이 등록되었습니다.');
                document.getElementById(commentIdx).value = '';
                document.getElementById('comment-reply-area').remove();
            }
       }     
    });    
});





//페이지 로드되자마자 리뷰, 댓글 목록 띄우기
window.onload = function() {
    ajaxComment();
    ajaxReview();
};














//페이지네이션_____________________________________________________________

//리뷰 페이지네이션 클릭된 페이지 텍스트 반환(전역변수 reviewPage) 
$(document).on("click",".review-page-item",function(){  
    reviewPage = $(this).text();
    ajaxReview();
})

//댓글 페이지네이션 클릭된 페이지 텍스트 반환(전역변수 commentPage)
$(document).on("click",".comment-page-item",function(){  
    //$('.pagination').children('li').on('click', function(e) {    
        commentPage = $(this).text();
        ajaxComment();
    }) 
//_________________________________________________________________________










 





