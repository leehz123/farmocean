
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix= "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">

<!-- ���� �����̴� -->
<!-- �������� �ҷ����� -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- slick �ҷ����� -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css">

<link rel="stylesheet" href="${path}/resources/css/product/product_detail.css">

<title>${product.prod_name }</title>
<%@ include file="/resources/jspf/header.jspf" %>

<style type="text/css">
#slider-div {    
	width: 350px;
	height: 350px;
    <!-- background-color: yellow; -->
}

#slider-div img {
	width: 350px;
	height: 350px;
}




.slick-dots {

}

.slick-prev {
    left: 15px;
}

.slick-next {
    right: 15px;
}
    
</style>
</head>



<body>
<%@ include file="/resources/jspf/body_header.jspf" %>   
<input id="input-prod-idx" type="hidden" value="${product.prod_idx }"></input>
<input id="input-seller-id" type="hidden" value="${product.member_id}">
<input id="input-cate-idx" type="hidden" value="${product.cate_idx }" >

	
    <div id="prod-detail-container">
        <div id="prod-info1" class="prod-detail shadow" >
            <c:choose>
                <c:when test="${fn:length(prodImg) eq 0}">
                       <img id="prod-img" src="http://localhost:8888/farmocean/resources/upload/prod_img/34a828af-e0cc-4aa6-a807-769d253b56dc.jpg" alt="" />     		
                </c:when>
                <c:otherwise>
                    <div id="slider-div">
                        <c:forEach items="${prodImg}" var="img">
                         	<c:set var = "imgURL" value = "${img.img_url}"/>
                            <c:choose>
                            	<c:when test="${fn:contains(imgURL, 'http')}">
									<img class="prod-img hover01" src="${img.img_url}" alt="" />
                            	</c:when>
								<c:otherwise>
									<img class="prod-img hover01" src="/farmocean${img.img_url}" alt="" />
								</c:otherwise>
                            </c:choose>                            
                        </c:forEach>    
                    </div>
                </c:otherwise>
            </c:choose>
            
            <div id="prod-info1-simple">
                <div id="prod-info1-name"><h4>${product.prod_name }</h4></div>
                <div id="prod-info1-price" data-price="${product.prod_price }" ></div>
                <div id="prod-info1-sell-status"></div>
                <div id="prod-info1-deadline"></div>
                <div id="prod-info1-deadline-timer" data-deadline="${product.prod_sell_deadline }" style="color: gray;"></div>
                
                <button id="buy-btn" class="btn-hover color-3 round-btn" onClick="fnWinOpen(290, 860, '<c:url value="/buy/prod/${product.prod_idx }" />');">��ǰ ����</button>
                <button id="prod-heart-btn" data-text="����" class="btn-hover color-11">��</button>
                <c:choose>
            		<c:when test="${sessionScope.loginId.member_id eq product.member_id || sessionScope.loginId.member_id eq 'sample63'}">
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button id="prod-edit-btn" class="btn-hover color-2 round-btn" onclick="location.href='/farmocean/product/product_detail_edit/${product.prod_idx}';">��ǰ ����</button>
                        &nbsp;
                        <button id="prod-delete-btn" class="btn-hover color-2 round-btn">��ǰ ����</button>
            		</c:when>
            	</c:choose>
            </div>
        </div>        
		
        <div id="prod-seller" class="prod-detail shadow">
           	<a id="seller-img-a" href="/farmocean/Sell/member/${product.member_id}" alt="�Ǹ��� ������ �̹���" class="margin-little"><img id="seller-img" src="/farmocean/resources/image/mypage/${member.member_image}" alt="" /></a>
           	<div id="seller-detail">
                <a id="seller-nickname" href="/farmocean/Sell/member/${product.member_id}" class="seller-detail-part a-link margin-right-10" alt="�Ǹ��� �г���">${member.member_nickName }</a>
                <c:choose>
                    <c:when test="${member.member_report eq 0 || member.member_report eq null}">
                        <span style="color:gray">�Ǹ��� �Ű� Ƚ�� ����</span>
                    </c:when>
                    <c:otherwise>
                        <span style="color:#8E37D7; font-size: 15px;">�Ǹ��� ���� �Ű� Ƚ��: ${member.member_report } ȸ</span>
                    </c:otherwise>
                </c:choose>
                
                <div id="seller-phone" class="seller-detail-part ">����ó : ${sellerPhoneNum }</div><div id="seller-account" class="seller-detail-part">���� : ${sellerAccountNum }</div>
                <div>
                    <button id="seller-contact" class="btn-hover color-2 round-btn" name="/farmocean/mypage/sendMessages?id=${member.member_id}" onclick="window.open(this.name,'_blank', 'width=500, height=600, scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;">����</button>
                    &nbsp;
                    <button id="seller-follow" class="btn-hover color-2 round-btn" data-text="�ȷο�">�ȷο�</button>
                    &nbsp;
                    <button id="seller-report" class="btn-hover color-2 round-btn" data-text="�Ű��ϱ�">�Ű��ϱ�</button>
                </div>
            </div>    
        </div>
            
            

        <div id="prod-detail-nav" class="prod-detail">
			<button id="prod-detail-nav-prod-info" class="nav-btn btn-hover color-1" onclick="onLinkClick(this);" data-scroll-to="prod-info2">������</button>
			<button id="prod-detail-nav-prod-review" class="nav-btn btn-hover color-1" onclick="onLinkClick(this);" data-scroll-to="prod-review">�ı�</button>
			<button id="prod-detail-nav-prod-comment" class="nav-btn btn-hover color-1" onclick="onLinkClick(this);" data-scroll-to="prod-comment">����</button>
        </div>

        <div id="prod-info2" class="prod-detail shadow">
            ${product.prod_info }
        </div>

        <div id="prod-review" class="prod-detail shadow"> <!--flex. column-->
            <div id="review-advice">
                <h3>��ǰ ����</h3>
                <div style="color: gray;">�����Ͻ� ��ǰ�� ���� ���並 �����ּ���.</div>
            </div>
            <div id="review-write-popup-btn-area">
                <c:choose>
                    <c:when test="${sessionScope.loginId eq null }">
                        <button id="review-write-popup-btn" class="btn-hover color-2 round-btn" onclick="banReview();">���� �ۼ�</button>
                    </c:when>
                    <c:otherwise>
                        <button id="review-write-popup-btn" class="btn-hover color-2 round-btn" onclick="permitReview();">���� �ۼ�</button>
                    </c:otherwise>
                </c:choose>
            </div>
            <div id="review-container"></div>
            
			<nav aria-label="Page navigation example">
				<ul class="pagination" id="review-pagination-out">
				</ul>
			</nav>
        </div>

        <div id="prod-comment" class="prod-detail shadow"> 
            <div id="comment-advice">
                <h3>��ǰ ����</h3>
				<div style="color: gray;">��ǰ�� ���� �ñ��Ͻ� ���� �����ø� �������ּ���.
				<a href="/farmocean/mypage/sendMessages?id=${member.member_id}" onclick="window.open(this.href,'_blank', 'width=500, height=600, scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;">�Ǹ��ڿ��� ���� ������</a>
				</div>
            </div>
            <c:choose>
                <c:when test="${sessionScope.loginId eq null }"></c:when>
                <c:otherwise>
                    <div id="prod-comment-input">
                        <textarea id="prod-comment-textarea" placeholder="������ �Է����ּ���."></textarea><button id="prod-comment-input-btn" class="btn-hover color-2">�Է�</button>
 	                    <div id="comment-secret-div"><input id="comment-secret" type="checkbox"><label for="comment-secret">&nbsp;��б�</label></div>
                    </div>
                </c:otherwise>
            </c:choose>

			<div id="comment-container">
			</div>
            
			<nav aria-label="Page navigation example">
				<ul class="pagination" id="comment-pagination-out">
		
				</ul>
			</nav>
            <div id="no-comment"></div>
        </div>
    </div>



    <div class="modal">
        <span class="close">x</span>
        <img id="modal-content" class="modal_content"></img>
    </div>


    <script>

    var currentLoginId = "<c:out value ='${sessionScope.loginId.member_id }'/>";    
    var seller = "<c:out value ='${product.member_id }'/>";    


    
    //�Ǹ��� �ȷο� ��ư
    $("#seller-follow").off().on('click', function() {
    
        if(this.getAttribute('data-text') == '�ȷο�') {
            const xhttp15 = new XMLHttpRequest();
            xhttp15.open('POST', '/farmocean/follow');
            var follow = {
                followee_id : seller
            }
            xhttp15.setRequestHeader('Content-Type', 'application/json;characterset=UTF-8');
            xhttp15.send(JSON.stringify(follow));
            xhttp15.addEventListener('readystatechange', (e)=> {
                const readyState = e.target.readyState;
                if(readyState == 4) {
                    const responseText = e.target.responseText;
                    const result = JSON.parse(responseText);
                    if(result.result == 1) {
                        alert('�Ǹ��ڸ� �ȷο� �Ͽ����ϴ�.');
                        this.classList.replace('color-2', 'color-13');
                        this.innerText = '�ȷ���';
                        this.setAttribute('data-text', '���ȷο�');
                    } else if(result.result == 2) {
                        alert("�̹� �ȷο� ���Դϴ�.");
                        this.classList.replace('color-2', 'color-13');
                        this.innerText = '�ȷ���';
                        this.setAttribute('data-text', '���ȷο�');
                    } else if(result.result == 0) {
                        alert('�α����� �ʿ��մϴ�.');
                    }
                }
            });

        } else if(this.getAttribute('data-text') == '���ȷο�') {
            const xhttp16 = new XMLHttpRequest();
            xhttp16.open('DELETE', '/farmocean/unfollow');
            var follow = {
                followee_id : seller
            }
            xhttp16.setRequestHeader('Content-Type', 'application/json;characterset=UTF-8');
            xhttp16.send(JSON.stringify(follow));
            xhttp16.addEventListener('readystatechange', (e)=> {
                const readyState = e.target.readyState;
                if(readyState == 4) {
                    const responseText = e.target.responseText;
                    const result = JSON.parse(responseText);
                    if(result.result == 1) {
                        alert('�Ǹ��ڸ� ���ȷο� �Ͽ����ϴ�.');
                        this.classList.replace('color-13', 'color-2');
                        this.innerText = '�ȷο�';
                        this.setAttribute('data-text', '�ȷο�');
                    } else if(result.result == 2) {
                        alert("�̹� ���ȷο� ���Դϴ�.");
                        this.classList.replace('color-13', 'color-2');
                        this.innerText = '�ȷο�';
                        this.setAttribute('data-text', '�ȷο�');
                    } else if(result.result == 0) {
                        alert('�α����� �ʿ��մϴ�.');
                    }
                }
            });        
        }
    });

    
    
    const deleteReview = function deleteReview(reviewIdx) {

        let writer;//���� �ۼ���
        writer = $("#delete-review-btn" + reviewIdx).attr('data-writer');

        if(currentLoginId == writer) {

            if(confirm('���� �����Ͻðڽ��ϱ�?')) {
                $.ajax ( {
                    type: 'DELETE',
                    url: '/farmocean/delete_review/' + reviewIdx,
                    dataType: 'json',
                    async: true,
                    success: function( data ) {
                        const result = data; 
                        if(data.result == 1) {
                            
                            alert('���䰡 �����Ǿ����ϴ�.');
                            ajaxReview();

                        } else if (data.result == -1) {
                            alert('���� ������ �����߽��ϴ�. �ٽ� �õ����ּ���.');
                        }
                    }
                });   
            } else {
                return false;
            }
    
        } else {
            alert('�ۼ��� ���θ� ������ �� �ֽ��ϴ�.');
        }         
    }



//�Ǹ��� �Ű� ��ư
$("#seller-report").off().on('click', function() { 

    if(currentLoginId == null || currentLoginId == undefined || currentLoginId == '') {
    alert('�α����� �ʿ��մϴ�.');
    return false;
    }

    if(this.innerText == '�Ű��ϱ�') {
    
        if(confirm('���� �Ű��Ͻðڽ��ϱ�?')) {
            const xhttp17 = new XMLHttpRequest();
            xhttp17.open('GET', 'http://localhost:8888/farmocean/member/memberfaulty/' + seller);
            xhttp17.send();
            xhttp17.addEventListener('readystatechange', (e)=> {
            const readyState = e.target.readyState;      
            if(readyState == 4) {
                
                const responseText = e.target.responseText;
                const result = JSON.parse(responseText);
                
                if(result.code == 1) {
                alert('�Ű�Ǿ����ϴ�.');
                this.innerText = '�Ű����';
                this.classList.replace('color-2', 'color-13');
                } else if (result.code == -5) {
                alert('�̹� �Ű�� �Ǹ����Դϴ�.');
                this.innerText = '�Ű����';
                this.classList.replace('color-2', 'color-13');
                } else if (result.code == 0) {
                alert('�α����� �ʿ��մϴ�.');
                }
            }
            });
                    
        } else {
            return false;
        }

        } else if(this.innerText == '�Ű����') {

        const xhttp18 = new XMLHttpRequest();
        xhttp18.open('GET', 'http://localhost:8888/farmocean/member/memberfaultycancel/' + seller);
        xhttp18.send();
        xhttp18.addEventListener('readystatechange', (e)=> {
            const readyState = e.target.readyState;
            if(readyState == 4) {
            const responseText = e.target.responseText;
            const result = JSON.parse(responseText);

            if(result.code == 1) {
                alert('�Ű� ��ҵǾ����ϴ�.');
                this.innerText = '�Ű��ϱ�';
                this.classList.replace('color-13', 'color-2');
            } else if (result.code == -5) {
                alert('�̹� �Ű� ��ҵǾ����ϴ�.');
                this.innerText = '�Ű��ϱ�';
                this.classList.replace('color-13', 'color-2');
            } else if (result.code == 0) {
                alert('�α����� �ʿ��մϴ�.');
            }
            }
        });

    }


});





    </script>



<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>

	<script charset="EUC-KR" src="${path}/resources/js/product/prod_detail.js"></script>

</html>




