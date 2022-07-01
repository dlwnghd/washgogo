/* 비밀번호 보이기, 감추기 */
$(".pw-show").on("click",function(){
    $("input").toggleClass("active");
    if($("input").hasClass("active")){
        $(".pw-show").prev("input").attr("type","text");
        $(".pw-show").toggleClass("pw-hide");
    }else{
        $(".pw-show").prev("input").attr("type","password");
        $(".pw-show").removeClass("pw-hide");
    }
});

console.log($("#password").val());

function close() {
    // 비밀번호 미입력시
    if(!$("#password").val()){
        pwwrong.style.display = "block";
    }else if($("#password").val()){
        pwwrong.style.display = "none";
    }

    // 이메일 미입력시
    if(!$("#email").val()){
        emailwrong.style.display = "block";
    }else if($("#email").val()){
        emailwrong.style.display = "none";
    }
}

body.addEventListener("click", e => {
    close();
})

function checkEmail() {
    let userEmail = $('#email').val(); //userEmail값이 "userEmail"인 입력란의 값을 저장
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
                alert("에러입니다");
            }
        });
    }
    notEnough();
}

function checkPassWord() {
    let userPw = $('#password').val(); //userEmail값이 "userEmail"인 입력란의 값을 저장
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
function notEnough() {
    if($("div.invalid-feedback-email").html() != ""
        || $("div.invalid-feedback-password").html() != ""){
        $(".login-btn").prop("type", "button");
        console.log("로그인 X");
    }else{
        $(".login-btn").prop("type", "submit");
    }
}



$(".login-btn").on('click', function(){
    if($("div.invalid-feedback-email").html() != ""){
        alert('이메일을 입력하세요!')
    } else if($("div.invalid-feedback-password").html() != ""){
        alert('비밀번호를 입력하세요!')
    }
});
