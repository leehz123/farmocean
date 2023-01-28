$.noConflict();

const inputProdIdx = document.getElementById('input-prod-idx');
const inputSellerId = document.getElementById('input-seller-id');
const commentContainer = document.getElementById('comment-container');
const reviewContainer = document.getElementById('review-container');
const commentSecretchk = document.getElementById('comment-secret');
const commentInputBtn = document.getElementById('prod-comment-input-btn');
const prodHeartBtn = document.getElementById('prod-heart-btn');
const reviewWriteBtn = document.getElementById('review-write-popup-btn');
const prodDeleteBtn = document.getElementById('prod-delete-btn');
const inputCateIdx = document.getElementById('input-cate-idx');
const followBtn = document.getElementById('seller-follow');
const prodPrice = document.getElementById('prod-info1-price');
const buyBtn = document.getElementById('buy-btn');


let currentProdIdx = inputProdIdx.value;
let currentProdSeller = inputSellerId.value;    
let currentCateIdx = inputCateIdx.value; 
let commentSecretNum = 0;
let commentText = null;    
let commentTextarea = null;
let commentPage = 1;
let reviewPage = 1;






//슬릭슬라이더
$(".slick").slick();


//슬릭슬라이더 옵션
$(function(){
    $("#slider-div").slick({
        slide: 'div',        //슬라이드 되어야 할 태그 ex) div, li 
        infinite : true,     //무한 반복 옵션     
        slidesToShow : 1,        // 한 화면에 보여질 컨텐츠 개수
        slidesToScroll : 1,        //스크롤 한번에 움직일 컨텐츠 개수
        speed : 100,     // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
        arrows : true,         // 옆으로 이동하는 화살표 표시 여부
        dots : true,         // 스크롤바 아래 점으로 페이지네이션 여부
        autoplay : false,            // 자동 스크롤 사용 여부
        autoplaySpeed : 10000,         // 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
        pauseOnHover : true,        // 슬라이드 이동    시 마우스 호버하면 슬라이더 멈추게 설정
        vertical : false,        // 세로 방향 슬라이드 옵션
        prevArrow : "<button type='button' class='slick-prev'>Previous</button>",        // 이전 화살표 모양 설정
        nextArrow : "<button type='button' class='slick-next'>Next</button>",        // 다음 화살표 모양 설정
        dotsClass : "slick-dots",     //아래 나오는 페이지네이션(점) css class 지정
        draggable : true,     //드래그 가능 여부 
        
        responsive: [ // 반응형 웹 구현 옵션
            {  
                breakpoint: 960, //화면 사이즈 960px
                settings: {
                    //위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
                    slidesToShow:3 
                } 
            },
            { 
                breakpoint: 768, //화면 사이즈 768px
                settings: {    
                    //위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
                    slidesToShow:2 
                } 
            }
        ]

    });
})











//리뷰idx에 해당하는 리뷰 사진 목록 가져오기
function getReviewPictureList(j, current_review_idx, reviewList, reviewDate) {
    
    //review_idx로 review_picture_url 리스트 가져오기(동기식으로 처리해야 순서대로 처리됨★★★★ 비동기식으로 하면 리뷰리스트 순서가 뒤죽박죽됨)
    $.ajax ( {
        type: 'GET',
        url: '/farmocean/prod/select_review_picture_list/' + current_review_idx,
        dataType: 'json',
        async: false,
        success: function( data ) {
            let reviewPictureList = data;
            let reviewTxt = '';
            
            //리뷰 별점에 따라 별 표시
            let reviewStarHTML = '';
            reviewStarHTML += '<span style="color: #3a65ff;">';
            for(let i = 0; i < reviewList[j].review_starNum; ++i) {
                reviewStarHTML += '★';
            }
            reviewStarHTML += '</span>';

            //html태그 문자열 생성해서 저장할 변수
            let reviewPicturesHTML = ``;
            if(reviewPictureList[0] != null) { //리뷰이미지가 있다면
                
                reviewPicturesHTML += `<div class="review-pic-cont">`;
                for(var reviewPicture of reviewPictureList) {
                    reviewPicturesHTML += `<img src="` + '/farmocean' + reviewPicture.review_picture_url + `" class="review-pic modal-img"></img>`;
                }
                reviewPicturesHTML += `</div>`;


                reviewTxt +=    `<div class="review-container" id="review-cont` + reviewList[j].review_idx + `">                                                                    
                                <div class="review-info-container">
                                    <img class ="prod-review-user-profile" src="/farmocean/resources/image/mypage/` + reviewList[j].member_image + `" alt="">&nbsp&nbsp` + reviewList[j].member_nickName + `&nbsp` + reviewDate + `&nbsp` + reviewStarHTML +                                                                    	
                                    `&nbsp&nbsp<button id="delete-review-btn` + reviewList[j].review_idx + `" class="delete-review-btn btn-hover color-2 round-btn" data-writer="` + reviewList[j].member_id + `"  onclick="deleteReview(` + reviewList[j].review_idx + `);">삭제</button>
                                </div>
                                <div class ="prod-review-content">` + reviewList[j].review_content + `</div>
                                ` + reviewPicturesHTML + `                                
                                </div>`;
            

            } else { // 리뷰 이미지가 없다면
              
                reviewTxt +=    `<div class="review-container" id="review-cont` + reviewList[j].review_idx + `">                                                                    
                                <div class="review-info-container">
                                    <img class ="prod-review-user-profile" src="/farmocean/resources/image/mypage/` + reviewList[j].member_image + `" alt="">&nbsp&nbsp` + reviewList[j].member_nickName + `&nbsp` + reviewDate + `&nbsp` + reviewStarHTML +                                                                    	
                                    `&nbsp&nbsp<button id="delete-review-btn` + reviewList[j].review_idx + `" class="delete-review-btn btn-hover color-2 round-btn" data-writer="` + reviewList[j].member_id + `" onclick="deleteReview(` + reviewList[j].review_idx + `);">삭제</button>
                                </div>
                                <div class ="prod-review-content">` + reviewList[j].review_content + `</div>
                                </div>`;
            
            }
            //alert(j);
            reviewContainer.innerHTML += reviewTxt;
        }
    });
   
}

// 리뷰 목록 띄우기 에이작스 (JoinReviewMember dto 이용)
function ajaxReview() {
    

    $.ajax ( {
        type: 'GET',
        url: '/farmocean/prod/select_prod_review/' + currentProdIdx,
        dataType: 'json',
        async: false,
        success: function( data ) {
            let reviewList = data;
            
            if(reviewList[0] != undefined || reviewList[0] != null) { //여기서 얻은 reviewList는 prod_review와 members 테이블을 조인해서 prod_idx로 select한 결과
                


                //페이지네이션__________________________________________________________________________________________
                //리뷰리스트.length로 페이지 수 구하기 (1페이지 당 댓글 5 개씩 표시)
		        var pageNum = null;
		        if(reviewList.length % 5 == 0) {
		            pageNum = reviewList.length / 5;
		        } else {
		            pageNum = Math.floor(reviewList.length / 5) + 1;
		        }
                
                //리뷰 페이지 수만큼 페이지네이션 버튼 만들기
                document.getElementById('review-pagination-out').innerHTML = '';
                let paginationTxt = '';
                paginationTxt += `<li class="page-item"><a id="review-before-btn" class="page-link">이전</a></li>`;
                for(let i = 1; i <= pageNum; ++i) {
                    paginationTxt += `<li class="review-page-item"><a class="page-link">` + i + ` </a></li>`
                }
                paginationTxt += `<li class="page-item"><a id="review-next-btn" class="page-link">다음</a></li>`;				
                document.getElementById('review-pagination-out').innerHTML = paginationTxt;

                
                reviewContainer.innerHTML = '';
                //현재 페이지에 포함될 리뷰만 목록에 생성__________________________________________________________
                for(let j = 5 * (reviewPage - 1); j < 5 * reviewPage; ++j) {
                    
                    if(reviewList[j] != undefined) {
                        //DB에서 받은 Timestamp를 Date로 변환 후 문자열로 변환
                        var sysdate = new Date(reviewList[j].review_date);
                        var reviewDate = sysdate.toLocaleDateString();

                        //현재 루프에서 다뤄지는 리뷰의 idx 구하기 (이 review_idx로 review_picture에서 review_picture_url불러와야 됨)
                        const current_review_idx = reviewList[j].review_idx;


                        //리뷰 사진 목록 가져와서 리뷰 컨테이너 구성하는 ajax
                        getReviewPictureList(j, current_review_idx, reviewList, reviewDate);
                    }
                    
                }               
            
            } else {
                reviewContainer.innerHTML = '<div style="color: gray;">리뷰가 존재하지 않습니다.</div>';
            } 
        }
    });
}


//리뷰 작성 버튼 눌렀을 때 로그아웃 버전
function banReview() {
    alert('로그인이 필요합니다.');
}

//리뷰 작성 버튼 눌렀을 때 로그인 버전
function permitReview() {
    const xhttp1 = new XMLHttpRequest();
    xhttp1.open('GET', '/farmocean/buyer_authentication/' + currentProdIdx);
    xhttp1.send();
    xhttp1.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;
        if(readyState == 4) {
            const availableBuyIdx = e.target.responseText;

            if(availableBuyIdx == '') {
                alert('상품 구매 회원만 리뷰를 작성할 수 있습니다.');
            } else {
                window.open("../product_review_write/" + inputProdIdx.value + "/" + availableBuyIdx, "리뷰등록 팝업창", "width=600, height=600, top=10, left=30");    
            }
        } 
    });
    
}










//댓글 목록 띄우기 에이작스
function ajaxComment() {


    $.ajax ( {
        type: 'GET', 
        url: '/farmocean/prod/select_prod_comment/' + currentProdIdx,
        dataType: 'json',
        async: false,
        success: function( data ) {
            let commentList = data;
            

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
                paginationTxt += `<li class="page-item"><a id="comment-before-btn" class="page-link">이전</a></li>`;
                for(let i = 1; i <= pageNum; ++i) {
                    paginationTxt += `<li class="comment-page-item"><a class="page-link">` + i + `</a></li>`
                }
                paginationTxt += `<li class="page-item"><a id="comment-next-btn" class="page-link">다음</a></li>`;				
                document.getElementById('comment-pagination-out').innerHTML = paginationTxt;
        
                commentContainer.innerHTML = '';
                for(let i = 5 * (commentPage - 1); i < 5 * commentPage; ++i) {
                    
                    //더 이상 표시할 댓글 수가 없을 경우 for문 종료
                    if(commentList[i] == undefined) {
                        break;
                    }
                    
                    var sysdate = new Date(commentList[i].comment_date);
                    var commentDate = sysdate.toLocaleDateString();
                    var commentTitle = null;
                    
                    var commentReply = commentList[i].comment_reply == null ? '미답변' : '답변완료';
                    
                    
                    var commentTxt = '';
                    if(commentList[i].comment_secret == 1) { // 비밀글이면
                        commentTitle = `<span><b>비밀글입니다.</b>` + ` ` + commentList[i].member_id + ` ` + commentDate + ` ` + commentReply + ` </span>`;
                        
                        if(commentList[i].comment_accessible == 1) { // 접근 가능하면
                            commentTxt +=  `<div class="comment-title">` + commentTitle + `</div>`;
                            commentTxt +=  `<div class="comment-content">
                                                                <p>` + commentList[i].comment_content + `</p>
                                                                <button class = "comment-delete-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">삭제</button>
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
                        let comment_reply = '';
                        commentTitle = `<span><b>` + comment_content_omit + `</b> ` + commentList[i].member_id + ` ` + commentDate + ` ` + commentReply + ` </span>`;
                        commentTxt +=  `<div class="comment-title">` + commentTitle + `</div>`;
                        if(!(commentList[i].comment_reply == null)) {
                            comment_reply = `<div class="comment-reply"><hr>` + commentList[i].comment_reply + `</div>`;
                            commentTxt += 
                                                        `<div class="comment-content">
                                                            <p>` + commentList[i].comment_content + `</p>` +
                                                            `<button class = "comment-delete-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">삭제</button>
                                                            ` + comment_reply + `
                                                        </div>`;
 
                        } else {

                        }
                        commentTxt += 
                                                        `<div class="comment-content">
                                                            <p>` + commentList[i].comment_content + `</p>` +
                                                            `<button class = "comment-delete-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">삭제</button>
                                                            <button class="comment-reply-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">답변하기</button>
                                                        </div>`;        
                    }
                    commentContainer.innerHTML += commentTxt;
                }
            } else {
                document.getElementById('comment-pagination-out').innerHTML = '';
                commentContainer.innerHTML = '';
                document.getElementById('no-comment').innerHTML = '';
                document.getElementById('no-comment').innerHTML += '<div style="color: gray; margin-bottom: 30px;">댓글이 존재하지 않습니다.</div>';
            }
        
        }
    });
};

//댓글 입력 버튼은 세션아이디가 존재할 때(누군가 로그인 돼 있을 때) 생성됨 
//(<c:when test="${sessionScope.loginId eq null }"></c:when>)
if(commentInputBtn!=null) {

    //비밀글 첵박 이벤트(체크 상태에 따라 전역변수 commentSecretNum의 값 변경(체크 : 1, 체크해제 : 0))
    commentSecretchk.addEventListener('click', (e)=> {
        if(e.target.checked == true) {
            commentSecretNum = 1;
        } else {
            commentSecretNum = 0;
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
                    const responseText = e.target.responseText;	
                    if(responseText == 2) {
                    	alert('로그인 상태에서만 댓글 등록이 가능합니다. 다시 로그인해주세요.');
                    }	
                    if(responseText == 1) {
        	            commentTextarea.value = '';				
				        commentSecretchk.checked = false;
				        commentSecretNum = 0;
                        ajaxComment();
                    }
                }
            });
            
            

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
                
                if(confirm('정말 삭제하시겠습니까?')) {
                    deleteComment(commentIdx, commentWriter);
                    alert('삭제 되었습니다.');
                }
                
            } else {
                alert('작성자 본인만 삭제할 수 있습니다.');
            }   
        }
    });

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
            if(responseText == 1) {
                alert('답글이 등록되었습니다.');
                document.getElementById(commentIdx).value = '';
                document.getElementById('comment-reply-area').remove();
                ajaxComment();
            }
       }     
    });    
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


//다른 작성자의 비밀댓글 클릭 시 액션
$(document).on("click", ".secret-comment", function(){    
    alert('비밀글입니다.');
});

//댓글 아코디언 
$(document).on("click", ".comment-title", function(){
    $(this).next(".comment-content").stop().slideToggle(300);
    $(this).toggleClass('on').siblings().removeClass('on');
    $(this).next(".comment-content").siblings(".comment-content").slideUp(300); // 1개씩 펼치기
});











//페이지네이션_____________________________________________________________

//리뷰 페이지네이션 클릭된 페이지 텍스트 반환(전역변수 reviewPage) 
$(document).on("click",".review-page-item",function(){  
    reviewPage = $(this).text();
    ajaxReview();
})

//댓글 페이지네이션 클릭된 페이지 텍스트 반환(전역변수 commentPage)
$(document).on("click",".comment-page-item",function(){      
    commentPage = $(this).text();
    ajaxComment();
}) 

//댓페 이전
$(document).on("click","#comment-before-btn",function(){      
    if(commentPage > 1) {
        commentPage = commentPage - 1;
        ajaxComment();
    }
});

//댓페 다음
$(document).on("click","#comment-next-btn",function(){      
    if(commentPage < (document.getElementById('comment-pagination-out').childElementCount - 2)) {
        commentPage = commentPage + 1;
        ajaxComment();
    }
});

//리페 이전
$(document).on("click","#review-before-btn",function(){      
    if(commentPage > 1) {
        commentPage = commentPage - 1;
        ajaxComment();
    }
});
//리페 다음
$(document).on("click","#review-next-btn",function(){      
    if(commentPage < (document.getElementById('review-pagination-out').childElementCount - 2)) {
        commentPage = commentPage + 1;
        ajaxComment();
    }
});



//_________________________________________________________________________







//상품 찜 버튼

if(prodHeartBtn != null) {
    prodHeartBtn.addEventListener('click', (e)=> {
        if(prodHeartBtn.getAttribute('data-text')=='찜등록') {
            const xhttp12 = new XMLHttpRequest();
            xhttp12.open('GET', '/farmocean/prod/prodaddbids/' + currentProdIdx);
            xhttp12.send();
            xhttp12.addEventListener('readystatechange', (e)=> {
                const readyState = e.target.readyState;
                if(readyState == 4) {
                    const responseText = e.target.responseText;
                    const result = JSON.parse(responseText);
                    if(result.code == 1) {
                        alert('해당상품의 찜이 등록되었습니다.');
                        prodHeartBtn.classList.replace('color-11', 'color-12');
                        prodHeartBtn.setAttribute('data-text', '찜취소');
                    } else if(result.code == -1) {
                        alert('로그인이 필요합니다.');
                    } else if(result.code == -5) { //이미 등록된 경우
                        prodHeartBtn.classList.replace('color-11', 'color-12');
                        prodHeartBtn.setAttribute('data-text', '찜취소');
                    }
                }
            });
        } else if(prodHeartBtn.getAttribute('data-text')=='찜취소') {
            const xhttp13 = new XMLHttpRequest();
            xhttp13.open('GET', '/farmocean/prod/prodcancelbids/' + currentProdIdx);
            xhttp13.send();
            xhttp13.addEventListener('readystatechange', (e)=> {
                const readyState = e.target.readyState;
                if(readyState == 4) {
                    const responseText = e.target.responseText;
                    const result = JSON.parse(responseText);
                    if(result.code == 1) {
                        alert('해당상품의 찜이 취소되었습니다.');
                        prodHeartBtn.classList.replace('color-12', 'color-11');
                        prodHeartBtn.setAttribute('data-text', '찜등록');
                    } else if(result.code == -1) {
                        alert('로그인이 필요합니다.');
                    } else if(result.code == -5) { //이미 취소된 경우
                        prodHeartBtn.classList.replace('color-12', 'color-11');
                        prodHeartBtn.setAttribute('data-text', '찜등록');
                    }
                }
            });
        }
    });
    
}




//카운트다운 함수
const countDownTimer = function (id, date) {
    var _vDate = new Date(date); // 전달 받은 일자
    var _second = 1000;
    var _minute = _second * 60;
    var _hour = _minute * 60;
    var _day = _hour * 24;
    var timer;

    function showRemaining() {
        var now = new Date();
        var distDt = _vDate - now;

        if (distDt < 0) {
            clearInterval(timer);
            document.getElementById(id).textContent = '판매 종료된 상품입니다.';
            document.getElementById('prod-info1-sell-status').innerHTML = '<span style="color: rgb(133, 170, 255);">판매종료</span>';
            document.getElementById('buy-btn').remove();
            return false;
        }

        var days = Math.floor(distDt / _day);
        var hours = Math.floor((distDt % _day) / _hour);
        var minutes = Math.floor((distDt % _hour) / _minute);
        var seconds = Math.floor((distDt % _minute) / _second);
        
        document.getElementById(id).textContent = '판매종료일까지 ';
        document.getElementById(id).textContent += days + '일 ';
        document.getElementById(id).textContent += hours + '시간 ';
        document.getElementById(id).textContent += minutes + '분 ';
        document.getElementById(id).textContent += seconds + '초 남았습니다.';
    }

    timer = setInterval(showRemaining, 1000);
}


//날짜 원하는 형식으로(판매 종료일 표시) 
function dateFormat(date) {
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;
    hour = hour >= 10 ? hour : '0' + hour;
    minute = minute >= 10 ? minute : '0' + minute;
    second = second >= 10 ? second : '0' + second;

    return date.getFullYear() + '.' + month + '.' + day + ' ' + hour + ':' + minute;
}



//내비게이션 버튼 액션(해당 스크롤로 이동)
function onLinkClick(btn) {
    document.getElementById(btn.getAttribute('data-scroll-to')).scrollIntoView();
}



window.addEventListener('load',() => {

    var intPrice = prodPrice.getAttribute('data-price');
    var strPrice = intPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '원';
    prodPrice.innerText = strPrice;

    ajaxComment();
    ajaxReview();    
    const timerCont = document.getElementById('prod-info1-deadline-timer');
    const ts = timerCont.getAttribute('data-deadline');//타임스탬프형 판매종료일
    countDownTimer('prod-info1-deadline-timer', ts);
    const deadline = new Date(ts);
    document.getElementById('prod-info1-deadline').textContent = '판매종료일시 : ' + dateFormat(deadline);
    document.getElementById('prod-info1-sell-status').innerHTML = '<span style="color: rgb(0, 76, 255);">판매중</span>';


    //팔로우 상태 표시
    $.ajax ( {
        type: 'GET',
        url: '/farmocean/is_following/' + currentProdSeller,
        dataType: 'json',
        async: false,
        success: function( data ) {
            if(data == 1) { //팔로우 중이면
                followBtn.classList.replace('color-2', 'color-13');
                followBtn.innerText = '팔로잉';
                followBtn.setAttribute('data-text', '언팔로우');
            } 
        }
    });

    //찜 상태 표시 
    $.ajax ( {
        type: 'GET',
        url: '/farmocean/is_heart_prod/' + currentProdIdx,
        dataType: 'json',
        async: false,
        success: function( data ) {
            if(data == 1) { //찜한 상품이면
                prodHeartBtn.classList.replace('color-11', 'color-12');
                prodHeartBtn.setAttribute('data-text', '찜취소');
            } 
        }
    });

    const sellerReportBtn =document.getElementById('seller-report');
    //신고 상태 표시
    $.ajax ( {
        type: 'GET',
        url: '/farmocean/is_reported/' + currentProdSeller,
        dataType: 'json',
        async: false,
        success: function( data ) {
            if(data == 1) { //신고된 판매자면
                sellerReportBtn.classList.replace('color-2', 'color-13');
                sellerReportBtn.innerText = '신고취소';
            } 
        }
    });

});


const prodDelete = function prodDelete() {
	
	if(confirm('정말 삭제하시겠습니까?')) {

        const xhttp1 = new XMLHttpRequest();
		xhttp1.open('GET', '/farmocean/delete_prod/' + currentProdIdx);
		xhttp1.send();
		xhttp1.addEventListener('readystatechange', (e)=> {
			const readyState = e.target.readyState;
			if(readyState == 4) {
				const responseText = e.target.responseText;
				const result = JSON.parse(responseText);
				if(result.result == 1) {
					alert('상품이 삭제되었습니다. 상품 목록 페이지로 돌아갑니다.');
                    location.href = '/farmocean/product/list/' + currentCateIdx;
				} else if(result.result == -1) {
					alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
				}	
			}
		});
			
	} else {
		return false;	
	}

}


if(prodDeleteBtn != null) {
    prodDeleteBtn.addEventListener('click', (e)=> {
        prodDelete();
    });
}




//리뷰이미지 모달창
const modal = document.querySelector(".modal");
const img = document.querySelector(".img");
const modal_img = document.querySelector("#modal-content");
const span = document.querySelector(".close");

function modalDisplay(text){
    modal.style.display = text;
}

$(document).on("click", ".modal-img", function(){
    modalDisplay("block");
    //$(".modal-img").attr('src') = $(this).attr('src');
    modal_img.src = $(this).attr('src');
});

span.addEventListener('click', (e)=> {
    modalDisplay("none");
});

$(document).on("click", ".modal", function(){
    modalDisplay("none");
});










