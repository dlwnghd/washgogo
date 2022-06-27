const $inputemail = $("#inputemail");

console.log($inputemail.val());    // 사용자가 웹사이트에서 입력한 email값
// DB에 있는 email과 일치하지 않는지 확인하는 js가 필요함

//아이디 중복검사
let check = false;

function checkId(memberId){
    console.log(memberId);
    $.ajax({
        url: contextPath + "/member/MemberCheckIdOk.me?memberId=" + memberId,
        type: "get",
        dataType: "json",
        success: function(result){
            const $result = $("span#result");
            if(result.result == "success"){
                $result.text("사용가능한 아이디입니다.");
                $result.css("color", "blue");
                check = true;
            }else{
                $result.text("중복된 아이디입니다.");
                $result.css("color", "red");
                check = false;
            }
        }
    });
}