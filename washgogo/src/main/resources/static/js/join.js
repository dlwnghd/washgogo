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

/* 모달 클릭 시 닫힘 */
$(".modal-outer").click(function() {
    $(".modal-outer").hide();
    $(".docs-component").hide();
});

/* 모달 X 클릭 시 닫힘 */
$("button.close").click(function() {
    $(".modal-outer").hide();
    $(".docs-component").hide();
});

/* 이용 약관 버튼 클릭 시 모달 열림 */
$("#btn-terms-of-service").click(function() {
    $(".modal-outer").show();
    $(".terms-of-service").show();
});

/* 개인정보 약관 버튼 클릭 시 모달 열림 */
$("#btn-privacy-policy").click(function() {
    $(".modal-outer").show();
    $(".privacy-policy").show();
});

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
    } else{
        $.ajax({	// ajax선언
            url: '/user/checkEmail', //Controller에서 인식할 주소
            type: 'post', //POST 방식으로 전달
            dataType: "json",
            data: userEmail,	// 데이터 타입은 userEmail타입이고 userEmail이 들어감
            contentType: "application/json",
            success: function (result) {	// 외부로부터 result를 받아오면 => 만약 이메일 전송에 성공하면
                let str = "";	// str 빈칸으로 정의
                const feedback = $("div.invalid-feedback-email");	// const타입 feedback은 클래스가 invalid-feedback인 div태그
                if (result) { // 이메일 중복있을 때 result는 boolean타입
                    str = "<small>이미 사용중인 이메일입니다.</small>";
                } //else { // 이메일 중복 없을 때
                  //  str = "<small>사용가능한 이메일입니다.</small>";
                //}

                feedback.html(str);
            },
            error: function () {
                alert("에러입니다");
            }
        });
    }
    notEnough();
}

function checkName() {
    let userName = $('#userName').val(); //userEmail값이 "userEmail"인 입력란의 값을 저장
    const feedback = $("div.invalid-feedback-name");
    let str = "";
    if (!userName) {
        str = "<small>이름을 입력해주세요.</small>";
    } else if(userName.length < 2){
        str = "<small>이름은 최소 2자 이상 입력해주세요.</small>";
    }
    else {
        str = "";
    }
    feedback.html(str);
    notEnough();
    return;
}

function checkPassWord() {
    let userPw = $('#userPw').val(); //userEmail값이 "userEmail"인 입력란의 값을 저장
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
    notEnough();
    return;
}

function checkPhoneNum() {
    let userPhoneNum = $('#userPhoneNum').val(); //userEmail값이 "userEmail"인 입력란의 값을 저장
    const feedback = $("div.invalid-feedback-phonenum");
    let str = "";

    if (!userPhoneNum) {
        str = "<small>휴대 전화 번호를 입력해주세요.</small>";
    } else if(userPhoneNum.length < 11 || userPhoneNum.length > 11 ){
        str = "<small>올바른 휴대 전화 번호를 입력해주세요.</small>";
    }
    else {
        str = "";
    }
    feedback.html(str);

    notEnough();
    return;
}

function checkAgreeTerms() {
    const feedback = $("div.invalid-feedback-agree-terms");
    let str = "";

    if($("#agree-terms").hasClass("checked")) {
        str = "";
    }else if ($("#agree-terms").hasClass("")) {
        str = "<small>이용약관에 동의해주세요.</small>";
    }
    feedback.html(str);

    notEnough();
    return;
}

function checkAgree14() {
    const feedback = $("div.invalid-feedback-agree-14");
    let str = "";

    if (!$("#agree-14").hasClass("checked")) {
        str = "<small>만 14세 이상 가입에 동의해주세요.</small>";
    } else if($("#agree-14").hasClass("checked")){
        str = "";
    }
    feedback.html(str);
    notEnough();
    return;
}

$("#agree-terms").on('click', function() {
    let str = "";
    const feedback = $("div.invalid-feedback-agree-terms");
    $("#agree-terms").toggleClass("checked");
    feedback.html(str);
    notEnough();
});

$("#agree-14").on('click', function() {
    const feedback = $("div.invalid-feedback-agree-14");
    let str = "";
    $("#agree-14").toggleClass("checked");
    feedback.html(str);
    notEnough();
});

// 회원가입 버튼 type 바꾸기
// 만약 입력칸에 입력을 안하거나 필수체크란에 동의를 하지 않았다면
function notEnough() {
    if($("div.invalid-feedback-name").html() != ""
        || $("div.invalid-feedback-email").html() != ""
        || $("div.invalid-feedback-password").html() != ""
        || $("div.invalid-feedback-phonenum").html() != ""
        || !$("#agree-terms").hasClass("checked")
        || !$("#agree-14").hasClass("checked")){
        $(".join-btn").prop("type", "button");
    }else{
        $(".join-btn").prop("type", "submit");
    }
}


//회원가입 버튼 클릭 시
$(".join-btn").on('click', function(){
    if($("div.invalid-feedback-name").html() != ""){
        checkName();
    } else if($("div.invalid-feedback-email").html() != ""){
        checkEmail();
    } else if($("div.invalid-feedback-password").html() != ""){
        checkPassWord();
    } else if($("div.invalid-feedback-phonenum").html() != ""){
        checkPhoneNum();
    } else if(!$("#agree-terms").hasClass("checked")){
        checkAgreeTerms();
    } else if(!$("#agree-14").hasClass("checked")){
        checkAgree14();
    }
});