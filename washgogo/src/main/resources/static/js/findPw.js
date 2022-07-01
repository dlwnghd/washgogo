/* 파라미터 해석 */
const param = window.location.search;
if (param.includes("id")) {
    console.log("들어옴");
    $("#show-findId-container").trigger('click');
} else if (param.includes("pw")) {
    $("#show-findPw-container").trigger('click');
};

function findPw() {
    let userEmail = $('#userEmail').val(); //userEmail값이 "userEmail"인 입력란의 값을 저장
    $.ajax({	// ajax선언
        url: '/user/findIdPw', //Controller에서 인식할 주소
        type: 'post', //POST 방식으로 전달
        dataType: "json",
        data: userEmail,	// 데이터 타입은 userEmail타입이고 userEmail이 들어감
        contentType: "application/json",
        success: function (result) {	// 외부로부터 result를 받아오면 => 만약 이메일 전송에 성공하면
            let str = "";	// str 빈칸으로 정의
            const feedback = $("div.invalid-feedback-email");	// const타입 feedback은 클래스가 invalid-feedback인 div태그
            console.log("처리 성공 시 변경되는 내용");	// 출력문

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