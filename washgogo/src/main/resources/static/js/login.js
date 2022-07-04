/* 비밀번호 보이기, 감추기 */
$(".pw-show").on("click",function(){
    $("input").toggleClass("active");   // class변경 토글형식으로 줫다 뺏다가 가능
    if($("input").hasClass("active")){  // active라는 이름의 class를 가진다면
        $(".pw-show").prev("input").attr("type","text");    // pw-show의 이전 input은 타입이 text로 변경된다
        $(".pw-show").toggleClass("pw-hide");   // class변경 pw-hide
    }else{
        $(".pw-show").prev("input").attr("type","password");
        $(".pw-show").removeClass("pw-hide");
    }
    checkPassWord();
    close();
});

// console.log($("#userPw").val());

function close() {
    // 비밀번호 미입력시
    if(!$("#userPw").val() || $("div.invalid-feedback-password").html() != ""){
        pwwrong.style.display = "block";
        userPw.style.boxShadow = "0 0 0 0.1rem rgb(251 89 114)";
    }else if($("div.invalid-feedback-password").html() == ""){
        userPw.style.boxShadow = "none";
    }

    // 이메일 미입력시
    if(!$("#userEmail").val() || $("div.invalid-feedback-email").html() != ""){
        emailwrong.style.display = "block";
        userEmail.style.boxShadow = "0 0 0 0.1rem rgb(251 89 114)";
    }else if($("div.invalid-feedback-email").html() == ""){
        userEmail.style.boxShadow = "none";
    }
}

form.addEventListener("mouseover", e => {
    close();
})

form.addEventListener("mouseout", e => {
    close();
})

body.addEventListener("keyup", e => {
    close();
})

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
    close();
    notEnough();
}

// 비밀번호 확인
function checkPassWord() {
    let userPw = $('#userPw').val(); //userPw값이 "userPw"인 입력란의 값을 저장
    const feedback = $("div.invalid-feedback-password");
    let str = "";
    var chk_num = userPw.search(/[0-9]/g);
    var chk_eng = userPw.search(/[a-z]/ig);
    if (!userPw) {
        str = "<small>비밀번호를 입력해주세요.</small>";
    } else if(chk_num < 0 || chk_eng < 0){
        str = "<small>비밀번호는 숫자와 영문자를 혼용하여야 합니다.</small>";
    } else if(userPw.length < 8){
        str = "<small>비밀번호는 최소 8자 이상 입력해주세요.</small>";
    }
    else {
        str = "";
    }
    feedback.html(str);
    close();
    notEnough();
}

// 로그인 버튼 type 바꾸기
// 만약 입력칸에 입력을 안하거나 필수체크란에 동의를 하지 않았다면
// 전송을 불가능하게 막는 구간
function notEnough() {
    if($("div.invalid-feedback-email").html() != ""
        || $("div.invalid-feedback-password").html() != ""){
        $(".login-btn").prop("type", "button");
    }
}

// 로그인 버튼 클릭시
function checkUser() {
    let userEmail = $('#userEmail').val();
    let userPw = $('#userPw').val();
    var userVO = {
        userPw : userPw,
        userEmail : userEmail
    }
    let userform = document.querySelector("form[name='userVO']");
    if($("div.invalid-feedback-email").html() != ""){
        checkEmail();
        checkPassWord();
        return;
    } else if($("div.invalid-feedback-password").html() != ""){
        checkEmail();
        checkPassWord();
        return;
    }else{
        // 회원 확인
        $.ajax({	// ajax선언
            url: '/user/loginCheck', //Controller에서 인식할 주소
            type: 'post', //POST 방식으로 전달
            dataType: "json",
            data: JSON.stringify(userVO), 	// 전송할 데이터 타입은 userVO타입
            contentType: "application/json",    // 보내는 데이터의 타입
            success: function (result) {
                if(result == true){
                    // console.log("success result1 :" + result);
                    $("form#form").attr("action","/user/login");
                    $("button.login-btn").prop("type", "submit");
                    userform.submit();
                }else if(result == false){
                    $("button.login-btn").prop("type", "button");
                    modal.style.display = "flex";
                }
            },
            error: function () {
                $("button.login-btn").prop("type", "button");
                alert("에러입니다.");
            }
        });
        return;
    }
}

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