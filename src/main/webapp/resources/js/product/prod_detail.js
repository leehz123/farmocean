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






//���������̴�
$(".slick").slick();


//���������̴� �ɼ�
$(function(){
    $("#slider-div").slick({
        slide: 'div',        //�����̵� �Ǿ�� �� �±� ex) div, li 
        infinite : true,     //���� �ݺ� �ɼ�     
        slidesToShow : 1,        // �� ȭ�鿡 ������ ������ ����
        slidesToScroll : 1,        //��ũ�� �ѹ��� ������ ������ ����
        speed : 100,     // ���� ��ư ������ ���� ȭ�� �ߴµ����� �ɸ��� �ð�(ms)
        arrows : true,         // ������ �̵��ϴ� ȭ��ǥ ǥ�� ����
        dots : true,         // ��ũ�ѹ� �Ʒ� ������ ���������̼� ����
        autoplay : false,            // �ڵ� ��ũ�� ��� ����
        autoplaySpeed : 10000,         // �ڵ� ��ũ�� �� �������� �Ѿ�µ� �ɸ��� �ð� (ms)
        pauseOnHover : true,        // �����̵� �̵�    �� ���콺 ȣ���ϸ� �����̴� ���߰� ����
        vertical : false,        // ���� ���� �����̵� �ɼ�
        prevArrow : "<button type='button' class='slick-prev'>Previous</button>",        // ���� ȭ��ǥ ��� ����
        nextArrow : "<button type='button' class='slick-next'>Next</button>",        // ���� ȭ��ǥ ��� ����
        dotsClass : "slick-dots",     //�Ʒ� ������ ���������̼�(��) css class ����
        draggable : true,     //�巡�� ���� ���� 
        
        responsive: [ // ������ �� ���� �ɼ�
            {  
                breakpoint: 960, //ȭ�� ������ 960px
                settings: {
                    //���� �ɼ��� ����Ʈ , ���⿡ �߰��ϸ� �װɷ� ����
                    slidesToShow:3 
                } 
            },
            { 
                breakpoint: 768, //ȭ�� ������ 768px
                settings: {    
                    //���� �ɼ��� ����Ʈ , ���⿡ �߰��ϸ� �װɷ� ����
                    slidesToShow:2 
                } 
            }
        ]

    });
})











//����idx�� �ش��ϴ� ���� ���� ��� ��������
function getReviewPictureList(j, current_review_idx, reviewList, reviewDate) {
    
    //review_idx�� review_picture_url ����Ʈ ��������(��������� ó���ؾ� ������� ó���ʡڡڡڡ� �񵿱������ �ϸ� ���丮��Ʈ ������ ���׹��׵�)
    $.ajax ( {
        type: 'GET',
        url: '/farmocean/prod/select_review_picture_list/' + current_review_idx,
        dataType: 'json',
        async: false,
        success: function( data ) {
            let reviewPictureList = data;
            let reviewTxt = '';
            
            //���� ������ ���� �� ǥ��
            let reviewStarHTML = '';
            reviewStarHTML += '<span style="color: #3a65ff;">';
            for(let i = 0; i < reviewList[j].review_starNum; ++i) {
                reviewStarHTML += '��';
            }
            reviewStarHTML += '</span>';

            //html�±� ���ڿ� �����ؼ� ������ ����
            let reviewPicturesHTML = ``;
            if(reviewPictureList[0] != null) { //�����̹����� �ִٸ�
                
                reviewPicturesHTML += `<div class="review-pic-cont">`;
                for(var reviewPicture of reviewPictureList) {
                    reviewPicturesHTML += `<img src="` + '/farmocean' + reviewPicture.review_picture_url + `" class="review-pic modal-img"></img>`;
                }
                reviewPicturesHTML += `</div>`;


                reviewTxt +=    `<div class="review-container" id="review-cont` + reviewList[j].review_idx + `">                                                                    
                                <div class="review-info-container">
                                    <img class ="prod-review-user-profile" src="/farmocean/resources/image/mypage/` + reviewList[j].member_image + `" alt="">&nbsp&nbsp` + reviewList[j].member_nickName + `&nbsp` + reviewDate + `&nbsp` + reviewStarHTML +                                                                    	
                                    `&nbsp&nbsp<button id="delete-review-btn` + reviewList[j].review_idx + `" class="delete-review-btn btn-hover color-2 round-btn" data-writer="` + reviewList[j].member_id + `"  onclick="deleteReview(` + reviewList[j].review_idx + `);">����</button>
                                </div>
                                <div class ="prod-review-content">` + reviewList[j].review_content + `</div>
                                ` + reviewPicturesHTML + `                                
                                </div>`;
            

            } else { // ���� �̹����� ���ٸ�
              
                reviewTxt +=    `<div class="review-container" id="review-cont` + reviewList[j].review_idx + `">                                                                    
                                <div class="review-info-container">
                                    <img class ="prod-review-user-profile" src="/farmocean/resources/image/mypage/` + reviewList[j].member_image + `" alt="">&nbsp&nbsp` + reviewList[j].member_nickName + `&nbsp` + reviewDate + `&nbsp` + reviewStarHTML +                                                                    	
                                    `&nbsp&nbsp<button id="delete-review-btn` + reviewList[j].review_idx + `" class="delete-review-btn btn-hover color-2 round-btn" data-writer="` + reviewList[j].member_id + `" onclick="deleteReview(` + reviewList[j].review_idx + `);">����</button>
                                </div>
                                <div class ="prod-review-content">` + reviewList[j].review_content + `</div>
                                </div>`;
            
            }
            //alert(j);
            reviewContainer.innerHTML += reviewTxt;
        }
    });
   
}

// ���� ��� ���� �����۽� (JoinReviewMember dto �̿�)
function ajaxReview() {
    

    $.ajax ( {
        type: 'GET',
        url: '/farmocean/prod/select_prod_review/' + currentProdIdx,
        dataType: 'json',
        async: false,
        success: function( data ) {
            let reviewList = data;
            
            if(reviewList[0] != undefined || reviewList[0] != null) { //���⼭ ���� reviewList�� prod_review�� members ���̺��� �����ؼ� prod_idx�� select�� ���
                


                //���������̼�__________________________________________________________________________________________
                //���丮��Ʈ.length�� ������ �� ���ϱ� (1������ �� ��� 5 ���� ǥ��)
		        var pageNum = null;
		        if(reviewList.length % 5 == 0) {
		            pageNum = reviewList.length / 5;
		        } else {
		            pageNum = Math.floor(reviewList.length / 5) + 1;
		        }
                
                //���� ������ ����ŭ ���������̼� ��ư �����
                document.getElementById('review-pagination-out').innerHTML = '';
                let paginationTxt = '';
                paginationTxt += `<li class="page-item"><a id="review-before-btn" class="page-link">����</a></li>`;
                for(let i = 1; i <= pageNum; ++i) {
                    paginationTxt += `<li class="review-page-item"><a class="page-link">` + i + ` </a></li>`
                }
                paginationTxt += `<li class="page-item"><a id="review-next-btn" class="page-link">����</a></li>`;				
                document.getElementById('review-pagination-out').innerHTML = paginationTxt;

                
                reviewContainer.innerHTML = '';
                //���� �������� ���Ե� ���丸 ��Ͽ� ����__________________________________________________________
                for(let j = 5 * (reviewPage - 1); j < 5 * reviewPage; ++j) {
                    
                    if(reviewList[j] != undefined) {
                        //DB���� ���� Timestamp�� Date�� ��ȯ �� ���ڿ��� ��ȯ
                        var sysdate = new Date(reviewList[j].review_date);
                        var reviewDate = sysdate.toLocaleDateString();

                        //���� �������� �ٷ����� ������ idx ���ϱ� (�� review_idx�� review_picture���� review_picture_url�ҷ��;� ��)
                        const current_review_idx = reviewList[j].review_idx;


                        //���� ���� ��� �����ͼ� ���� �����̳� �����ϴ� ajax
                        getReviewPictureList(j, current_review_idx, reviewList, reviewDate);
                    }
                    
                }               
            
            } else {
                reviewContainer.innerHTML = '<div style="color: gray;">���䰡 �������� �ʽ��ϴ�.</div>';
            } 
        }
    });
}


//���� �ۼ� ��ư ������ �� �α׾ƿ� ����
function banReview() {
    alert('�α����� �ʿ��մϴ�.');
}

//���� �ۼ� ��ư ������ �� �α��� ����
function permitReview() {
    const xhttp1 = new XMLHttpRequest();
    xhttp1.open('GET', '/farmocean/buyer_authentication/' + currentProdIdx);
    xhttp1.send();
    xhttp1.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;
        if(readyState == 4) {
            const availableBuyIdx = e.target.responseText;

            if(availableBuyIdx == '') {
                alert('��ǰ ���� ȸ���� ���並 �ۼ��� �� �ֽ��ϴ�.');
            } else {
                window.open("../product_review_write/" + inputProdIdx.value + "/" + availableBuyIdx, "������ �˾�â", "width=600, height=600, top=10, left=30");    
            }
        } 
    });
    
}










//��� ��� ���� �����۽�
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
                paginationTxt += `<li class="page-item"><a id="comment-before-btn" class="page-link">����</a></li>`;
                for(let i = 1; i <= pageNum; ++i) {
                    paginationTxt += `<li class="comment-page-item"><a class="page-link">` + i + `</a></li>`
                }
                paginationTxt += `<li class="page-item"><a id="comment-next-btn" class="page-link">����</a></li>`;				
                document.getElementById('comment-pagination-out').innerHTML = paginationTxt;
        
                commentContainer.innerHTML = '';
                for(let i = 5 * (commentPage - 1); i < 5 * commentPage; ++i) {
                    
                    //�� �̻� ǥ���� ��� ���� ���� ��� for�� ����
                    if(commentList[i] == undefined) {
                        break;
                    }
                    
                    var sysdate = new Date(commentList[i].comment_date);
                    var commentDate = sysdate.toLocaleDateString();
                    var commentTitle = null;
                    
                    var commentReply = commentList[i].comment_reply == null ? '�̴亯' : '�亯�Ϸ�';
                    
                    
                    var commentTxt = '';
                    if(commentList[i].comment_secret == 1) { // ��б��̸�
                        commentTitle = `<span><b>��б��Դϴ�.</b>` + ` ` + commentList[i].member_id + ` ` + commentDate + ` ` + commentReply + ` </span>`;
                        
                        if(commentList[i].comment_accessible == 1) { // ���� �����ϸ�
                            commentTxt +=  `<div class="comment-title">` + commentTitle + `</div>`;
                            commentTxt +=  `<div class="comment-content">
                                                                <p>` + commentList[i].comment_content + `</p>
                                                                <button class = "comment-delete-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">����</button>
                                                                <button class="comment-reply-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">�亯�ϱ�</button>
                                                            </div>`;
                        } else { //������ �� ������
                            commentTxt +=  `<div class="comment-title secret-comment">` + commentTitle + `</div>`;
                        }
        
                        
                    } else { //��б��� �ƴϸ�
                        // �������� ��� ���� �̸����� (20�� �̻���� ���� �ϰ� ... ���̱�) 
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
                                                            `<button class = "comment-delete-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">����</button>
                                                            ` + comment_reply + `
                                                        </div>`;
 
                        } else {

                        }
                        commentTxt += 
                                                        `<div class="comment-content">
                                                            <p>` + commentList[i].comment_content + `</p>` +
                                                            `<button class = "comment-delete-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">����</button>
                                                            <button class="comment-reply-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">�亯�ϱ�</button>
                                                        </div>`;        
                    }
                    commentContainer.innerHTML += commentTxt;
                }
            } else {
                document.getElementById('comment-pagination-out').innerHTML = '';
                commentContainer.innerHTML = '';
                document.getElementById('no-comment').innerHTML = '';
                document.getElementById('no-comment').innerHTML += '<div style="color: gray; margin-bottom: 30px;">����� �������� �ʽ��ϴ�.</div>';
            }
        
        }
    });
};

//��� �Է� ��ư�� ���Ǿ��̵� ������ ��(������ �α��� �� ���� ��) ������ 
//(<c:when test="${sessionScope.loginId eq null }"></c:when>)
if(commentInputBtn!=null) {

    //��б� ý�� �̺�Ʈ(üũ ���¿� ���� �������� commentSecretNum�� �� ����(üũ : 1, üũ���� : 0))
    commentSecretchk.addEventListener('click', (e)=> {
        if(e.target.checked == true) {
            commentSecretNum = 1;
        } else {
            commentSecretNum = 0;
        }
    });

    //�Է� ��ư ������ �� �̺�Ʈ(xhttp3.send())
    $(document).on("click", "#prod-comment-input-btn", function(){
        
        commentTextarea = document.getElementById('prod-comment-textarea');
        commentText = commentTextarea.value;    
        if(commentText != '') {
            const productComment = {
                prod_idx : currentProdIdx, 
                comment_content : commentText, 
                comment_secret : commentSecretNum
            }
            /*member_id, comment_idx, comment_date�� ����, �������� ó��*/ 
            
            //��� ��� �����۽�
            const xhttp3 = new XMLHttpRequest();
            xhttp3.open('POST', '/farmocean/prod/insert_comment');
            xhttp3.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
            xhttp3.send(JSON.stringify(productComment));

            xhttp3.addEventListener('readystatechange', (e)=> {
                const readyState = e.target.readyState;
                if(readyState == 4) {
                    const responseText = e.target.responseText;	
                    if(responseText == 2) {
                    	alert('�α��� ���¿����� ��� ����� �����մϴ�. �ٽ� �α������ּ���.');
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
            alert('��� ������ �Է����ּ���.'); 
        }
});
}

// ��� ���� ajax (ajaxComment()ȣ���ϴϱ� ajaxComment() �Ʒ��� ��ġ��Ű��!)
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

//��� ���� ��ư ������ �� �׼�
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
                
                if(confirm('���� �����Ͻðڽ��ϱ�?')) {
                    deleteComment(commentIdx, commentWriter);
                    alert('���� �Ǿ����ϴ�.');
                }
                
            } else {
                alert('�ۼ��� ���θ� ������ �� �ֽ��ϴ�.');
            }   
        }
    });

});

//��� �亯 �Է� ��ư �׼�
$(document).on("click", ".comment-reply-input", function(){
    //����񸸡� ���⼭���ʹ� �Է¹�ư ������ �� ����� �͵��ε� �׷��� ������ ���� ��. �ٵ� commentIdx�� ���޹��� �� ���� ��¾��
    //�Է¹�ư�� name���� comment_idx�� �ɾ���߰ڴ� 

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
        alert('�亯�� �Է����ּ���');
    }
    
    
    xhttp8.addEventListener('readystatechange', (e)=>{
       const readyState = e.target.readyState;
       if(readyState == 4) {
            const responseText = e.target.responseText;
            if(responseText == 1) {
                alert('����� ��ϵǾ����ϴ�.');
                document.getElementById(commentIdx).value = '';
                document.getElementById('comment-reply-area').remove();
                ajaxComment();
            }
       }     
    });    
});


//��� �亯�ϱ� ��ư ������ �� �׼�
$(document).on("click", ".comment-reply-btn", function(){
    if(document.getElementById('comment-reply-area') != null) {
        alert('���� �������� �ٸ� �亯�� �ֽ��ϴ�.');
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
                                                    <button name=" ` + commentIdx + ` " class="comment-reply-input">�Է�</button>
                                                    <button name=" ` + commentIdx + ` " class="comment-reply-cancle">���</button>
                                                </div>
                                            `);
                }
            } else {
                alert('��ǰ �Ǹ��ڸ� �亯�� �� �ֽ��ϴ�.');
            }            
        }
    });   
});

//��� �亯 ��� ��ư �׼�
$(document).on("click", ".comment-reply-cancle", function(){
    //let commentIdx = $(this).attr('name');    
    document.getElementById('comment-reply-area').remove();
});


//�ٸ� �ۼ����� ��д�� Ŭ�� �� �׼�
$(document).on("click", ".secret-comment", function(){    
    alert('��б��Դϴ�.');
});

//��� ���ڵ�� 
$(document).on("click", ".comment-title", function(){
    $(this).next(".comment-content").stop().slideToggle(300);
    $(this).toggleClass('on').siblings().removeClass('on');
    $(this).next(".comment-content").siblings(".comment-content").slideUp(300); // 1���� ��ġ��
});











//���������̼�_____________________________________________________________

//���� ���������̼� Ŭ���� ������ �ؽ�Ʈ ��ȯ(�������� reviewPage) 
$(document).on("click",".review-page-item",function(){  
    reviewPage = $(this).text();
    ajaxReview();
})

//��� ���������̼� Ŭ���� ������ �ؽ�Ʈ ��ȯ(�������� commentPage)
$(document).on("click",".comment-page-item",function(){      
    commentPage = $(this).text();
    ajaxComment();
}) 

//���� ����
$(document).on("click","#comment-before-btn",function(){      
    if(commentPage > 1) {
        commentPage = commentPage - 1;
        ajaxComment();
    }
});

//���� ����
$(document).on("click","#comment-next-btn",function(){      
    if(commentPage < (document.getElementById('comment-pagination-out').childElementCount - 2)) {
        commentPage = commentPage + 1;
        ajaxComment();
    }
});

//���� ����
$(document).on("click","#review-before-btn",function(){      
    if(commentPage > 1) {
        commentPage = commentPage - 1;
        ajaxComment();
    }
});
//���� ����
$(document).on("click","#review-next-btn",function(){      
    if(commentPage < (document.getElementById('review-pagination-out').childElementCount - 2)) {
        commentPage = commentPage + 1;
        ajaxComment();
    }
});



//_________________________________________________________________________







//��ǰ �� ��ư

if(prodHeartBtn != null) {
    prodHeartBtn.addEventListener('click', (e)=> {
        if(prodHeartBtn.getAttribute('data-text')=='����') {
            const xhttp12 = new XMLHttpRequest();
            xhttp12.open('GET', '/farmocean/prod/prodaddbids/' + currentProdIdx);
            xhttp12.send();
            xhttp12.addEventListener('readystatechange', (e)=> {
                const readyState = e.target.readyState;
                if(readyState == 4) {
                    const responseText = e.target.responseText;
                    const result = JSON.parse(responseText);
                    if(result.code == 1) {
                        alert('�ش��ǰ�� ���� ��ϵǾ����ϴ�.');
                        prodHeartBtn.classList.replace('color-11', 'color-12');
                        prodHeartBtn.setAttribute('data-text', '�����');
                    } else if(result.code == -1) {
                        alert('�α����� �ʿ��մϴ�.');
                    } else if(result.code == -5) { //�̹� ��ϵ� ���
                        prodHeartBtn.classList.replace('color-11', 'color-12');
                        prodHeartBtn.setAttribute('data-text', '�����');
                    }
                }
            });
        } else if(prodHeartBtn.getAttribute('data-text')=='�����') {
            const xhttp13 = new XMLHttpRequest();
            xhttp13.open('GET', '/farmocean/prod/prodcancelbids/' + currentProdIdx);
            xhttp13.send();
            xhttp13.addEventListener('readystatechange', (e)=> {
                const readyState = e.target.readyState;
                if(readyState == 4) {
                    const responseText = e.target.responseText;
                    const result = JSON.parse(responseText);
                    if(result.code == 1) {
                        alert('�ش��ǰ�� ���� ��ҵǾ����ϴ�.');
                        prodHeartBtn.classList.replace('color-12', 'color-11');
                        prodHeartBtn.setAttribute('data-text', '����');
                    } else if(result.code == -1) {
                        alert('�α����� �ʿ��մϴ�.');
                    } else if(result.code == -5) { //�̹� ��ҵ� ���
                        prodHeartBtn.classList.replace('color-12', 'color-11');
                        prodHeartBtn.setAttribute('data-text', '����');
                    }
                }
            });
        }
    });
    
}




//ī��Ʈ�ٿ� �Լ�
const countDownTimer = function (id, date) {
    var _vDate = new Date(date); // ���� ���� ����
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
            document.getElementById(id).textContent = '�Ǹ� ����� ��ǰ�Դϴ�.';
            document.getElementById('prod-info1-sell-status').innerHTML = '<span style="color: rgb(133, 170, 255);">�Ǹ�����</span>';
            document.getElementById('buy-btn').remove();
            return false;
        }

        var days = Math.floor(distDt / _day);
        var hours = Math.floor((distDt % _day) / _hour);
        var minutes = Math.floor((distDt % _hour) / _minute);
        var seconds = Math.floor((distDt % _minute) / _second);
        
        document.getElementById(id).textContent = '�Ǹ������ϱ��� ';
        document.getElementById(id).textContent += days + '�� ';
        document.getElementById(id).textContent += hours + '�ð� ';
        document.getElementById(id).textContent += minutes + '�� ';
        document.getElementById(id).textContent += seconds + '�� ���ҽ��ϴ�.';
    }

    timer = setInterval(showRemaining, 1000);
}


//��¥ ���ϴ� ��������(�Ǹ� ������ ǥ��) 
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



//������̼� ��ư �׼�(�ش� ��ũ�ѷ� �̵�)
function onLinkClick(btn) {
    document.getElementById(btn.getAttribute('data-scroll-to')).scrollIntoView();
}



window.addEventListener('load',() => {

    var intPrice = prodPrice.getAttribute('data-price');
    var strPrice = intPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '��';
    prodPrice.innerText = strPrice;

    ajaxComment();
    ajaxReview();    
    const timerCont = document.getElementById('prod-info1-deadline-timer');
    const ts = timerCont.getAttribute('data-deadline');//Ÿ�ӽ������� �Ǹ�������
    countDownTimer('prod-info1-deadline-timer', ts);
    const deadline = new Date(ts);
    document.getElementById('prod-info1-deadline').textContent = '�Ǹ������Ͻ� : ' + dateFormat(deadline);
    document.getElementById('prod-info1-sell-status').innerHTML = '<span style="color: rgb(0, 76, 255);">�Ǹ���</span>';


    //�ȷο� ���� ǥ��
    $.ajax ( {
        type: 'GET',
        url: '/farmocean/is_following/' + currentProdSeller,
        dataType: 'json',
        async: false,
        success: function( data ) {
            if(data == 1) { //�ȷο� ���̸�
                followBtn.classList.replace('color-2', 'color-13');
                followBtn.innerText = '�ȷ���';
                followBtn.setAttribute('data-text', '���ȷο�');
            } 
        }
    });

    //�� ���� ǥ�� 
    $.ajax ( {
        type: 'GET',
        url: '/farmocean/is_heart_prod/' + currentProdIdx,
        dataType: 'json',
        async: false,
        success: function( data ) {
            if(data == 1) { //���� ��ǰ�̸�
                prodHeartBtn.classList.replace('color-11', 'color-12');
                prodHeartBtn.setAttribute('data-text', '�����');
            } 
        }
    });

    const sellerReportBtn =document.getElementById('seller-report');
    //�Ű� ���� ǥ��
    $.ajax ( {
        type: 'GET',
        url: '/farmocean/is_reported/' + currentProdSeller,
        dataType: 'json',
        async: false,
        success: function( data ) {
            if(data == 1) { //�Ű�� �Ǹ��ڸ�
                sellerReportBtn.classList.replace('color-2', 'color-13');
                sellerReportBtn.innerText = '�Ű����';
            } 
        }
    });

});


const prodDelete = function prodDelete() {
	
	if(confirm('���� �����Ͻðڽ��ϱ�?')) {

        const xhttp1 = new XMLHttpRequest();
		xhttp1.open('GET', '/farmocean/delete_prod/' + currentProdIdx);
		xhttp1.send();
		xhttp1.addEventListener('readystatechange', (e)=> {
			const readyState = e.target.readyState;
			if(readyState == 4) {
				const responseText = e.target.responseText;
				const result = JSON.parse(responseText);
				if(result.result == 1) {
					alert('��ǰ�� �����Ǿ����ϴ�. ��ǰ ��� �������� ���ư��ϴ�.');
                    location.href = '/farmocean/product/list/' + currentCateIdx;
				} else if(result.result == -1) {
					alert("������ ������ ó���� �����ǰ��ֽ��ϴ�. ��� �� �ٽ� �õ����ּ���");
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




//�����̹��� ���â
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










