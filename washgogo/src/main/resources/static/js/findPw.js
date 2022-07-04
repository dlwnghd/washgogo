/* 파라미터 해석 */
const param = window.location.search;
if (param.includes("id")) {
    console.log("들어옴");
    $("#show-findId-container").trigger('click');
} else if (param.includes("pw")) {
    $("#show-findPw-container").trigger('click');
};

let resultNum = "";



// 인증번호 받기 버튼 클릭시
function getNumber() {

    let userEmail = $('#userEmail').val();
    let userPhonenum = $('#userPhonenum').val();
    var userVO = {
        userPhonenum : userPhonenum,
        userEmail : userEmail
    }

    // 휴대 전화 번호가 없다면
    if(userPhonenum == ""){
        alert("휴대전화를 입력해주세요");
        return;
    } else if(userEmail == ""){	// 이메일이 없다면
        alert("이메일을 입력해주세요");
        return;
    } else{
        // 회원 확인
        $.ajax({	// ajax선언
            url: '/user/checkUser', //Controller에서 인식할 주소
            type: 'post', //POST 방식으로 전달
            dataType: "json",
            data: JSON.stringify(userVO), 	// 데이터 타입은 userVO타입
            contentType: "application/json",
            success: function (result) {
                console.log("success result :" + result);
                $("button.find-btn").html("<span>인증번호 확인</span>");	// 버튼 내용 변경
                $("button.find-btn").attr("onclick", "checkNumber()");	// onclick변경
                $("div.verifyNum").css('display','block');
                $("form.submit").attr("action","/user/checkVerifyNum");
                resultNum = result;
            },
            error: function () {
                alert("존재하지 않는 회원입니다.");
            }
        });return;
    }
}

// 인증번호 확인 버튼 클릭시
function checkNumber() {
    let verifyNumber = $('#verifyNumber').val();
    if(verifyNumber == ""){
        // 인증 번호가 없다면
        $(".find-btn").prop("type", "button");
        alert("인증번호를 입력해주세요.");
        return;
    }else if(verifyNumber != resultNum){
        $(".find-btn").prop("type", "button");
        alert("인증번호가 틀렸습니다. 다시 입력해주세요.");
    }
    if(verifyNumber == resultNum){
        $("button.find-btn").prop("type", "submit");	// 버튼 타입 변경
    }
}