/* 파라미터 해석 */
const param = window.location.search;
if (param.includes("id")) {
    console.log("들어옴");
    $("#show-findId-container").trigger('click');
} else if (param.includes("pw")) {
    $("#show-findPw-container").trigger('click');
};

let resultNum = "";

// 이메일 부적합
function emailclose() {
    // 이메일 미입력시
    if($("div.invalid-feedback-email").html() != ""){
        emailwrong.style.display = "block";
        userEmail.style.boxShadow = "0 0 0 0.1rem rgb(251 89 114)";
    }else if($("div.invalid-feedback-email").html() == ""){
        userEmail.style.boxShadow = "0 0 0 0.2rem rgb(0 199 174 / 25%)";
        userEmail.addEventListener("focusout", e => {
            userEmail.style.boxShadow = "none";
        })
    }
}

userEmail.addEventListener("click", e => {
    emailclose();
})

// 휴대전화 부적합
function phonenumclose() {
    // 전화번호 미입력시
    if($("div.invalid-feedback-phonenum").html() != ""){
        phonenumwrong.style.display = "block";
        userPhonenum.style.boxShadow = "0 0 0 0.1rem rgb(251 89 114)";
    }else if($("div.invalid-feedback-phonenum").html() == ""){
        userPhonenum.style.boxShadow = "0 0 0 0.2rem rgb(0 199 174 / 25%)";
        userPhonenum.addEventListener("focusout", e => {
            userPhonenum.style.boxShadow = "none";
        })
    }
}

userPhonenum.addEventListener("click", e => {
    phonenumclose();
})

// 전화번호 확인
function checkPhoneNum() {
    let userPhoneNum = $('#userPhonenum').val(); //userEmail값이 "userEmail"인 입력란의 값을 저장
    const feedback = $("div.invalid-feedback-phonenum");
    let str = "";
    if (!userPhoneNum) {
        str = "<small>휴대 전화 번호를 입력해주세요.</small>";
    } else if(isNaN(userPhoneNum)){
        str = "<small>숫자만 입력해주세요.</small>";
    } else if(userPhoneNum.length < 11 || userPhoneNum.length > 11 ){
        str = "<small>올바른 휴대 전화 번호를 입력해주세요.</small>";
    }
    else {
        str = "";
    }
    feedback.html(str);
    phonenumclose();
    return;
}

// 이메일 확인
function checkEmail() {
    let userEmail = $('#userEmail').val(); //userEmail값이 "userEmail"인 입력란의 값을 저장
    const feedback = $("div.invalid-feedback-email");
    let str = "";
    var pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    var chk_at = userEmail.search(pattern);
    if (!userEmail) {
        str = "<small>이메일 주소를 입력해주세요.</small>";
        feedback.html(str);
    } else if(chk_at < 0){
        str = "<small>이메일 형식으로 입력해주세요.</small>";
        feedback.html(str);
    } else {
        $.ajax({	// ajax선언
            url: '/user/checkEmail', //Controller에서 인식할 주소
            type: 'post', //POST 방식으로 전달
            dataType: "json",
            data: userEmail,	// 데이터 타입은 userEmail타입이고 userEmail이 들어감
            contentType: "application/json",
            success: function (result) {	// 외부로부터 result를 받아오면 => 만약 이메일 전송에 성공하면
                let str = "";	// str 빈칸으로 정의
                const feedback = $("div.invalid-feedback-email");	// const타입 feedback은 클래스가 invalid-feedback인 div태그
                feedback.html(str);
            },
            error: function () {
                console.log("로그인 오류 체크");
                alert("에러입니다");
            }
        });
    }
    emailclose();
}

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
        checkPhoneNum();
        checkEmail();
        return;
    } else if(userEmail == ""){	// 이메일이 없다면
        checkPhoneNum();
        checkEmail();
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
                $("button.find-btn").html("<span>인증번호 확인</span>");	// 버튼 내용 변경
                $("button.find-btn").attr("onclick", "checkNumber()");	// onclick변경
                $("div.verifyNum").css('display','block');
                $("form.submit").attr("action","/user/checkVerifyNum");
                resultNum = result;
            },
            error: function () {
                modal.style.display = "flex";
            }
        });return;
    }
}

function checkVerify() {
    let verifyNumber = $('#verifyNumber').val();
    const feedback = $("div.invalid-feedback-verifyNumber");
    let str = "";
    if(verifyNumber == ""){
        // 인증 번호가 없다면
        str = "<small>인증 번호를 입력해주세요.</small>";
        feedback.html(str);
    }else{
        str = "";
        feedback.html(str);
    }
}

// 인증번호 확인 버튼 클릭시
function checkNumber() {
    let verifyNumber = $('#verifyNumber').val();
    const feedback = $("div.invalid-feedback-verifyNumber");
    let str = "";
    if(verifyNumber != resultNum){
        $(".find-btn").prop("type", "button");
        str = "<small>인증 번호가 틀렸습니다.</small>";
        feedback.html(str);
    }

    if(verifyNumber == resultNum){
        $("button.find-btn").prop("type", "submit");	// 버튼 타입 변경
    }
}


// 모달
function closeModal() {
    modal.style.display = "none";
}

// 모달창 X버튼으로 닫기
const closeBtn = modal.querySelector(".close-area")
closeBtn.addEventListener("click", e => {
    closeModal();
})

// 모달창 확인 버튼으로 닫기
const okayBtn = modal.querySelector(".close")
okayBtn.addEventListener("click", e => {
    closeModal();
})

// 아무곳이나 눌러서 모달창 닫기
modal.addEventListener("click", e => {
    const evTarget = e.target
    if (evTarget.classList.contains("modal-overlay")) {
        closeModal();
    }
})