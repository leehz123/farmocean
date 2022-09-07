//���۽� �ȿ� ���۽� ���� ���� ���� .. �� �� �׷��� ������

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
const xhttp2 = new XMLHttpRequest();
xhttp2.addEventListener('readystatechange', (e)=> {
    const readyState = e.target.readyState;
    if(readyState == 4) { 
        //const s = JSON.parse(responseText); ��Ʈ�ѷ����� return (LoginMember)session.getAttribute("loginId"); �س��� ������ �ٷ� ���ǿ� ����� �̷��� ���� �ʾƵ� ��
        const responseText = e.target.responseText;
        window.location.reload();
    }
});
logoutBtn.addEventListener('click', (e)=> {
    xhttp2.open('GET', '/farmocean/prod/temp_logout'); 
    xhttp2.send();
});












// ���� ��� ���� �����۽� (JoinReviewMember dto �̿�)
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
                //���⼭ ���� reviewList�� prod_review�� members ���̺��� �����ؼ� prod_idx�� select�� ���
                
                //���丮��Ʈ.length�� ������ �� ���ϱ� (1������ �� ��� 5 ���� ǥ��)
		        var pageNum = null;
		        if(reviewList.length % 5 == 0) {
		            pageNum = commentList.length / 5;
		        } else {
		            pageNum = Math.floor(reviewList.length / 5) + 1;
		        }
		        //console.log('����� : ', reviewList.length);
		        //console.log('�������� : ', pageNum);
                
                //��� ������ ����ŭ ���������̼� ��ư �����
                document.getElementById('review-pagination-out').innerHTML = '';
                let paginationTxt = '';
                paginationTxt += `<li class="page-item"><a class="page-link" href="#">����</a></li>`;
                for(let i = 1; i <= pageNum; ++i) {
                    paginationTxt += `<li class="comment-page-item"><a class="page-link">` + i + ` </a></li>`
                }
                paginationTxt += `<li class="page-item"><a class="page-link" href="#">����</a></li>`;				
                document.getElementById('review-pagination-out').innerHTML = paginationTxt;

                
                //���� �������� ���Ե� ��۸� ��۸�Ͽ� ����
                reviewContainer.innerHTML = '';
                let reviewTxt = '';
                for(let i = 5 * (reviewPage - 1); i < 5 * reviewPage; ++i) {
                    var writer = reviewList[i].member_id;
                    var sysdate = new Date(reviewList[i].review_date);
                    var reviewDate = sysdate.toLocaleDateString();
                    
                    reviewTxt += `<div class="review-container">                                                                    
                                <div class="review-info-container">
                                    <a href="#"><img class ="prod-review-user-profile" src="` + reviewList[i].member_image + `" alt=""></a>&nbsp&nbsp<a href="#">` + reviewList[i].member_nickName + `</a> ` + reviewDate + ` ` + '�ڡڡڡڡ�' +                                                                    	
                                `</div>
                                <div class ="prod-review-content">` + reviewList[i].review_content + `</div>
                                </div>`;			
                                //�ؿ� �ֵ鵵 �߰��ؾ� ��!!
                                //<div class ="prod-review-picture-preview">`  + `</div>
                                //<div class ="prod-review-picture-number">`  + `</div>
                }
                reviewContainer.innerHTML = reviewTxt;
            } else {
                document.getElementById('review-container').innerHTML = '<div>���䰡 �������� �ʽ��ϴ�.</div>';
            } 
            
        }
    });
}

//������ �˾�â ���� ��ư
const reviewWriteBtn = document.getElementById('review-write-popup-btn');
reviewWriteBtn.addEventListener('click', (e)=> {
    //�ϴ� URL�ڸ��� "URL" �־ �׽�Ʈ �� ��� ��� ������ ���ϸ� ��
    window.open("../product_review_write/1/1", "������ �˾�â", "width=500, height=500, top=50, left=50");
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
        
        
                        commentTitle = 
                        `<span>` + comment_content_omit + ` ` + commentList[i].member_id + ` ` + commentDate + ` �亯����</span>`;
                        commentTxt +=  `<div class="comment-title">` + commentTitle + `</div>`;
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
                    //const commentList = JSON.parse(readyState);
                    //drawCommenList(commentList);
                }
            });
            
            ajaxComment();

            commentTextarea.value = '';				
            commentSecretchk.checked = false;
            commentSecretNum = 0;

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
            }
       }     
    });    
});





//������ �ε���ڸ��� ����, ��� ��� ����
window.onload = function() {
    ajaxComment();
    ajaxReview();
};














//���������̼�_____________________________________________________________

//���� ���������̼� Ŭ���� ������ �ؽ�Ʈ ��ȯ(�������� reviewPage) 
$(document).on("click",".review-page-item",function(){  
    reviewPage = $(this).text();
    ajaxReview();
})

//��� ���������̼� Ŭ���� ������ �ؽ�Ʈ ��ȯ(�������� commentPage)
$(document).on("click",".comment-page-item",function(){  
    //$('.pagination').children('li').on('click', function(e) {    
        commentPage = $(this).text();
        ajaxComment();
    }) 
//_________________________________________________________________________










 





