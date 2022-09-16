
const btn = document.getElementById('join_btn');
var selectBank = document.getElementById('post_member_bank');

btn.addEventListener('click',(e)=>{
        if(!(post_naver_phoneNum1.value+
        post_naver_phoneNum2.value+
        post_naver_phoneNum3.value)){
            alert('�޴��� ��ȣ�� �ʼ� �Է� �����Դϴ�');
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
            member_accountNum : '['+selectBank.options[selectBank.selectedIndex].value+']'+post_member_accountNum.value,
            member_address : '['+sample6_postcode.value+']'+
                                sample6_address.value+' '+
                                sample6_extraAddress.value+' '+
                                sample6_detailAddress.value,
            member_account_status : 1,
            member_type : 'S',
            member_report : 0,
            member_image : 'profile_basic_image.jpg',
            member_join_date : 'sysdate',
            member_modify_date : 'sysdate'
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

function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ�.

                    // �� �ּ��� ���� ��Ģ�� ���� �ּҸ� �����Ѵ�.
                    // �������� ������ ���� ���� ��쿣 ����('')���� �����Ƿ�, �̸� �����Ͽ� �б� �Ѵ�.
            var addr = ''; // �ּ� ����
            var extraAddr = ''; // �߰� �ּ� 

            // ����ڰ� ������ �ּ� Ÿ�Կ� ���� �ش� �ּ� ���� �����´�.
            if (data.userSelectedType === 'R') {  // ����ڰ� ���θ� �ּҸ� �������� ���
                addr = data.roadAddress;
            } else { // ����ڰ� ���� �ּҸ� �������� ���(J)
                addr = data.jibunAddress;
            }

             // ����ڰ� ������ �ּҰ� ���θ� Ÿ���϶� �����Ѵ�.
            if(data.userSelectedType === 'R'){
                // ���������� ���� ��� �߰��Ѵ�. (�������� ����)
                // �������� ��� ������ ���ڰ� "��/��/��"�� ������.
                if (data.bname !== '' && /[��|��|��]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
               // �ǹ����� �ְ�, ���������� ��� �߰��Ѵ�.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
               // ǥ���� �����׸��� ���� ���, ��ȣ���� �߰��� ���� ���ڿ��� �����.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                 // ���յ� �����׸��� �ش� �ʵ忡 �ִ´�.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

             // �����ȣ�� �ּ� ������ �ش� �ʵ忡 �ִ´�.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // Ŀ���� ���ּ� �ʵ�� �̵��Ѵ�.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}





	
	
	
