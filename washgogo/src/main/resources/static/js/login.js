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

console.log($("#userPw").val());

function close() {
    // 비밀번호 미입력시
    if(!$("#userPw").val()){
        pwwrong.style.display = "block";
    }

    // 이메일 미입력시
    if(!$("#userEmail").val()){
        emailwrong.style.display = "block";
    }
}

body.addEventListener("click", e => {
    close();
})

// 이메일 확인
function checkEmail() {
    let userEmail = $('#userEmail').val(); //userEmail값이 "userEmail"인 입력란의 값을 저장
    const feedback = $("div.invalid-feedback-email");
    let str = "";
    var chk_at = userEmail.search("@");
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
                console.log("처리 성공 시 변경되는 내용");	// 출력문
                feedback.html(str);
            },
            error: function () {
                console.log("로그인 오류 체크1");
                alert("에러입니다");
            }
        });
    }
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
        feedback.html(str);
    } else if(chk_num < 0 || chk_eng < 0){
        str = "<small>비밀번호는 숫자와 영문자를 혼용하여야 합니다.</small>";
        feedback.html(str);
    } else if(userPw.length < 8){
        str = "<small>비밀번호는 최소 8자 이상 입력해주세요.</small>";
        feedback.html(str);
    }
    else {
        str = "";
        feedback.html(str);
    }
    notEnough();
}

// 로그인 버튼 type 바꾸기
// 만약 입력칸에 입력을 안하거나 필수체크란에 동의를 하지 않았다면
// 전송을 불가능하게 막는 구간
function notEnough() {
    if($("div.invalid-feedback-email").html() != ""
        || $("div.invalid-feedback-password").html() != ""){
        $(".login-btn").prop("type", "button");
        console.log("로그인 X");
    }else{
        // $(".login-btn").prop("type", "submit");
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
        alert('이메일을 입력하세요!')
        return;
    } else if($("div.invalid-feedback-password").html() != ""){
        alert('비밀번호를 입력하세요!')
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
                    console.log("success result2 :" + result);
                    $("button.login-btn").prop("type", "button");
                    alert("존재하지 않는 회원입니다1.");
                }
            },
            error: function () {
                $("button.login-btn").prop("type", "button");
                alert("존재하지 않는 회원입니다2.");
            }
        });
        return;
    }
}