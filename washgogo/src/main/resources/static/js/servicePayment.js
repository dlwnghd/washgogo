/* 헤더 스크롤 이벤트 */
const $header = $("#app-header");
document.onscroll = function(){
    if(window.scrollY>0){
        $header.removeClass("hide-border-bottom");
    } else {
        $header.addClass("hide-border-bottom");
    }
}

/* 런드렛 클릭 이벤트 */
const $laundret = $(".payment .choice-wrap li");
$laundret.on("click", function(){
    $laundret.removeClass("focus");
    $(this).addClass("focus");
});

/* 체크박스 클릭 이벤트 */
const $checkboxes = $(".payment input[type='checkbox']");
$checkboxes.on("click", function(){
    if($(this).is(":checked")){
        $(this).parent("label").addClass("active");
    } else {
        $(this).parent("label").removeClass("active");
    }
});

/* 가입버튼 클릭 이벤트 */
const $subscribeBtn = $("#subscribeBtn");
$subscribeBtn.on("click", function () {
    if(checkForm()){
        serviceForm.submit();
    }
})

function checkForm() {
    if(!$("input[name='useAgreement']").is(":checked")){
        alert("이용약관에 동의해주세요.");
        return false;
    }
    if(!$("input[name='personalInfoAgreement']").is(":checked")){
        alert("개인정보 수집 및 이용에 동의해주세요.");
        return false;
    }
    if(!$("input[name='serviceAgreement']").is(":checked")){
        alert("서비스 이용사항에 동의해주세요.");
        return false;
    }

    return true;
}