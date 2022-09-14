//���۽� �ȿ� ���۽� ���� ���� ���� .. �� �� �׷��� ������

const inputProdIdx = document.getElementById('input-prod-idx');
const inputSellerId = document.getElementById('input-seller-id');
const loginBtn = document.getElementById('login');
const logoutBtn = document.getElementById('logout');
const commentContainer = document.getElementById('comment-container');
const reviewContainer = document.getElementById('review-container');
const commentSecretchk = document.getElementById('comment-secret');
const commentInputBtn = document.getElementById('prod-comment-input-btn');
const prodHeartBtn = document.getElementById('prod-heart-btn');


let currentProdIdx = inputProdIdx.value;
let currentProdSeller = inputSellerId.value;    
let commentSecretNum = 0;
let commentText = null;    
let commentTextarea = null;
let commentPage = 1;
let reviewPage = 1;


function onLinkClick(btn) {
    document.getElementById(btn.getAttribute('data-scroll-to')).scrollIntoView();
}


//�ӽ� �α��� �����۽�
// const xhttp1 = new XMLHttpRequest();
// xhttp1.addEventListener('readystatechange', (e)=> {
//     const readyState = e.target.readyState;
    
//     if(readyState == 4) {
//         //const s = JSON.parse(responseText); ��Ʈ�ѷ����� return (LoginMember)session.getAttribute("loginId"); �س��� ������ �� �޾Ƶ� �� �̷��� ���� �ʾƵ� ��
//         const responseText = e.target.responseText;
//         window.location.reload();
//     }
// });
// loginBtn.addEventListener('click', (e)=> {
//     xhttp1.open('GET', '/farmocean/prod/temp_login'); 
//     xhttp1.send();
// });


//�α׾ƿ� �����۽�
// const xhttp2 = new XMLHttpRequest();
// xhttp2.addEventListener('readystatechange', (e)=> {
//     const readyState = e.target.readyState;
//     if(readyState == 4) { 
//         //const s = JSON.parse(responseText); ��Ʈ�ѷ����� return (LoginMember)session.getAttribute("loginId"); �س��� ������ �ٷ� ���ǿ� ����� �̷��� ���� �ʾƵ� ��
//         const responseText = e.target.responseText;
//         window.location.reload();
//     }
// });
// logoutBtn.addEventListener('click', (e)=> {
//     xhttp2.open('GET', '/farmocean/prod/temp_logout'); 
//     xhttp2.send();
// });





function getReviewPictureList(j, current_review_idx, reviewList, reviewDate) {
    
    
    //review_idx�� review_picture_url ����Ʈ ��������(��������� ó���ؾ� ������� ó���ʡڡڡڡ� �񵿱������ �ϸ� ���丮��Ʈ ������ ���׹��׵�)
    $.ajax ( {
        url: '/farmocean/prod/select_review_picture_list/' + current_review_idx,
        dataType: 'json',
        async: false,
        success: function( data ) {
            let reviewPictureList = data;
            let reviewTxt = '';
            
            
            //html�±� ���ڿ� �����ؼ� ������ ����
            let reviewPicturesHTML = ``;
            if(reviewPictureList[0] != null) { //�����̹����� �ִٸ�
                
                reviewPicturesHTML += `<div class="review-pic-cont">`;
                for(var reviewPicture of reviewPictureList) {
                    reviewPicturesHTML +=  `<img src="`+ reviewPicture.review_picture_url + `"class="review-pic"></img>`;
                }
                reviewPicturesHTML += `</div>`;

                reviewTxt +=    `<div class="review-container">                                                                    
                                <div class="review-info-container">
                                    <a href="#"><img class ="prod-review-user-profile" src="/farmocean/resources/image/mypage/` + reviewList[j].member_image + `" alt=""></a>&nbsp&nbsp<a href="#">` + reviewList[j].member_nickName + `</a> ` + reviewDate + ` ` + '�ڡڡڡڡ�' +                                                                    	
                                `</div>
                                <div class ="prod-review-content">` + reviewList[j].review_content + `</div>
                                ` + reviewPicturesHTML + `                                
                                </div>`;
            

            } else {
              
                reviewTxt +=    `<div class="review-container">                                                                    
                                <div class="review-info-container">
                                    <a href="#"><img class ="prod-review-user-profile" src="/farmocean/resources/image/mypage/` + reviewList[j].member_image + `" alt=""></a>&nbsp&nbsp<a href="#">` + reviewList[j].member_nickName + `</a> ` + reviewDate + ` ` + '�ڡڡڡڡ�' +                                                                    	
                                `</div>
                                <div class ="prod-review-content">` + reviewList[j].review_content + `</div>
                                </div>`;
            
            }
            //alert(j);
            reviewContainer.innerHTML += reviewTxt;
        }
      } );
   
}



// ���� ��� ���� �����۽� (JoinReviewMember dto �̿�)
function ajaxReview() {
    
    const xhttp5 = new XMLHttpRequest();
    xhttp5.open('GET', '/farmocean/prod/select_prod_review/' + currentProdIdx); //���⼭ JoinReviewMember�� ���� 263
    xhttp5.send();
    xhttp5.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState; 
        

        if(readyState == 4) {
            const responseText = e.target.responseText;
            let reviewList = JSON.parse(responseText); 
            
            
            if(reviewList[0] != undefined || reviewList[0] != null) { //���⼭ ���� reviewList�� prod_review�� members ���̺��� �����ؼ� prod_idx�� select�� ���
                


                //���������̼�__________________________________________________________________________________________
                //���丮��Ʈ.length�� ������ �� ���ϱ� (1������ �� ��� 5 ���� ǥ��)
		        var pageNum = null;
		        if(reviewList.length % 5 == 0) {
		            pageNum = reviewList.length / 5;
		        } else {
		            pageNum = Math.floor(reviewList.length / 5) + 1;
		        }
		        //console.log('����� : ', reviewList.length);
		        //console.log('�������� : ', pageNum);
                
                //���� ������ ����ŭ ���������̼� ��ư �����
                document.getElementById('review-pagination-out').innerHTML = '';
                let paginationTxt = '';
                paginationTxt += `<li class="page-item"><a class="page-link" href="#">����</a></li>`;
                for(let i = 1; i <= pageNum; ++i) {
                    paginationTxt += `<li class="review-page-item"><a class="page-link">` + i + ` </a></li>`
                }
                paginationTxt += `<li class="page-item"><a class="page-link" href="#">����</a></li>`;				
                document.getElementById('review-pagination-out').innerHTML = paginationTxt;
                


                
                reviewContainer.innerHTML = '';
                //���� �������� ���Ե� ��۸� ��۸�Ͽ� ����__________________________________________________________
                for(let j = 5 * (reviewPage - 1); j < 5 * reviewPage; ++j) {
                    
                    //DB���� ���� Timestamp�� Date�� ��ȯ �� ���ڿ��� ��ȯ
                    var sysdate = new Date(reviewList[j].review_date);
                    var reviewDate = sysdate.toLocaleDateString();                   
                    
                    //���� �������� �ٷ����� ����� idx ���ϱ� (�� review_idx�� review_picture���� review_picture_url�ҷ��;� ��)
                    const current_review_idx = reviewList[j].review_idx;
                    
                    
                    //���� ���� ��� �����ͼ� ���� �����̳� �����ϴ� ajax
                    getReviewPictureList(j, current_review_idx, reviewList, reviewDate);
                }               
            
            } else {
                reviewContainer.innerHTML = '<div>���䰡 �������� �ʽ��ϴ�.</div>';
            } 
        }
    });
}





//������ �˾�â ���� ��ư
const reviewWriteBtn = document.getElementById('review-write-popup-btn');
reviewWriteBtn.addEventListener('click', (e)=> {
    // �ϴ� URL�ڸ��� "URL" �־ �׽�Ʈ �� ��� ��� ������ ���ϸ� �� �ڿ� ���� �κ��� /{prod_idx}
    // prod_idx�� inputProdIdx.value�� �־���� �������� ��(input hidden)
    // �α��� ���� ������̵�� �˾�â������ ���� ���̵� ���� �������״ϱ� �н������� �� �־ ��
    window.open("../product_review_write/" + inputProdIdx.value, "������ �˾�â", "width=600, height=600, top=10, left=30");
});












//��� ��� ���� �����۽�
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
                paginationTxt += `<li class="page-item"><a class="page-link" href="#">����</a></li>`;
                for(let i = 1; i <= pageNum; ++i) {
                    paginationTxt += `<li class="comment-page-item"><a class="page-link">` + i + ` </a></li>`
                }
                paginationTxt += `<li class="page-item"><a class="page-link" href="#">����</a></li>`;				
                document.getElementById('comment-pagination-out').innerHTML = paginationTxt;
        
                commentContainer.innerHTML = '';
                for(let i = 5 * (commentPage - 1); i < 5 * commentPage; ++i) {
                    console.log('i : ', i);
                    
                    //�� �̻� ǥ���� ��� ���� ���� ��� for�� ����
                    if(commentList[i] == undefined) {
                        break;
                    }
                    
                    var sysdate = new Date(commentList[i].comment_date);
                    var commentDate = sysdate.toLocaleDateString();
                    var commentTitle = null;
                    
                    var commentTxt = '';
                    if(commentList[i].comment_secret == 1) { // ��б��̸�
                        commentTitle = `<span>��б��Դϴ�.` + ` ` + commentList[i].member_id + ` ` + commentDate + ` �亯����</span>`;
                        
                        if(commentList[i].comment_accessible == 1) { // ���� �����ϸ�
                            commentTxt +=  `<div class="comment-title">` + commentTitle + `</div>`;
                            commentTxt +=  `<div class="comment-content">
                                                                <p>` + commentList[i].comment_content + `</p>
                                                                <button class = "comment-delete-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">����</button>
                                                                <button value="" class="comment-edit-btn">����</button>
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
                        commentTitle = `<span>` + comment_content_omit + ` ` + commentList[i].member_id + ` ` + commentDate + ` �亯����</span>`;
                        commentTxt +=  `<div class="comment-title">` + commentTitle + `</div>`;
                        if(!(commentList[i].comment_reply == null)) {
                            comment_reply = `<div class="comment-reply"><hr>` + commentList[i].comment_reply + `</div>`;
                            commentTxt += 
                                                        `<div class="comment-content">
                                                            <p>` + commentList[i].comment_content + `</p>` +
                                                            `<button class = "comment-delete-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">����</button>
                                                            <button class="comment-edit-btn">����</button>    
                                                            ` + comment_reply + `
                                                        </div>`;
 
                        } else {

                        }
                        commentTxt += 
                                                        `<div class="comment-content">
                                                            <p>` + commentList[i].comment_content + `</p>` +
                                                            `<button class = "comment-delete-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">����</button>
                                                            <button class="comment-edit-btn">����</button>    
                                                            <button class="comment-reply-btn" value="` + commentList[i].member_id + `" name = "` + commentList[i].comment_idx + `">�亯�ϱ�</button>
                                                        </div>`;        
                    }
                    commentContainer.innerHTML += commentTxt;
                }
            } else {
                document.getElementById('comment-pagination-out').innerHTML = '';
                commentContainer.innerHTML = '';
                document.getElementById('no-comment').innerHTML = '';
                document.getElementById('no-comment').innerHTML += '<div>����� �������� �ʽ��ϴ�.</div>';
            }
        
        }
    });
};

//��� ��� ������ �޾Ƽ� ���������̼� ����� ��� ��� ���� �Լ�
// function drawCommenList(commentList) {
//     //�׳� �ٽ� ajaxComment()�� �������
// }


//��� �Է� ��ư�� ���Ǿ��̵� ������ ��(������ �α��� �� ���� ��) ������ 
//(<c:when test="${sessionScope.loginId eq null }"></c:when>)
if(commentInputBtn!=null) {

    //��б� ý�� �̺�Ʈ(üũ ���¿� ���� �������� commentSecretNum�� �� ����(üũ : 1, üũ���� : 0))
    commentSecretchk.addEventListener('click', (e)=> {
        if(e.target.checked == true) {
            commentSecretNum = 1;
            console.log(commentSecretNum);
        } else {
            commentSecretNum = 0;
            console.log(commentSecretNum);
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
                    const readyState = e.target.responseText;	
                    if(readyState == 2) {
                    	alert('�α��� ���¿����� ��� ����� �����մϴ�. �ٽ� �α������ּ���.');
                    }	
                    if(readyState == 1) {
        	            commentTextarea.value = '';				
				        commentSecretchk.checked = false;
				        commentSecretNum = 0;
                    }
                    //const commentList = JSON.parse(readyState);
                    //drawCommenList(commentList);
                }
            });
            
            ajaxComment();

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
                deleteComment(commentIdx, commentWriter);
                alert('���� �Ǿ����ϴ�.');
            } else {
                alert('�ۼ��� ���θ� ������ �� �ֽ��ϴ�.');
            }   
        }
    });

});



//��� ���ڵ�� 
$(document).on("click", ".comment-title", function(){
    $(this).next(".comment-content").stop().slideToggle(300);
    $(this).toggleClass('on').siblings().removeClass('on');
    $(this).next(".comment-content").siblings(".comment-content").slideUp(300); // 1���� ��ġ��
});

//�ٸ� �ۼ����� ��д�� Ŭ�� �� �׼�
$(document).on("click", ".secret-comment", function(){    
    alert('��б��Դϴ�.');
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
            console.log(responseText);
            if(responseText == 1) {
                alert('����� ��ϵǾ����ϴ�.');
                document.getElementById(commentIdx).value = '';
                document.getElementById('comment-reply-area').remove();
                ajaxComment();
            }
       }     
    });    
});





//������ �ε���ڸ��� ����, ��� ��� ����
window.addEventListener('load',() => {
    ajaxComment();
    ajaxReview();
    document.getElementById('prod-detail-nav').innerHTML =  `
                                                            <button id="prod-detail-nav-prod-info" onclick="onLinkClick(this);" data-scroll-to="prod-info2">������</button>
                                                            <button id="prod-detail-nav-prod-review" onclick="onLinkClick(this);" data-scroll-to="prod-review">�ı�</button>
                                                            <button id="prod-detail-nav-prod-comment" onclick="onLinkClick(this);" data-scroll-to="prod-comment">���</button>
                                                        `;

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
//_________________________________________________________________________



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
                    prodHeartBtn.setAttribute('data-text', '�����');
                } else if(result.code == -1) {
                    alert('�α����� �ʿ��մϴ�.');
                } else if(result.code == -5) { //�̹� ��ϵ� ���
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
                    prodHeartBtn.setAttribute('data-text', '����');
                } else if(result.code == -1) {
                    alert('�α����� �ʿ��մϴ�.');
                } else if(result.code == -5) { //�̹� ��ҵ� ���
                    prodHeartBtn.setAttribute('data-text', '����');
                }
            }
        });
    }
});







 





