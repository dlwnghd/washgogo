/* 헤더 스크롤 이벤트 */
const $header = $("#app-header");
document.onscroll = function(){
    if(window.scrollY>0){
        $header.removeClass("hide-border-bottom");
    } else {
        $header.addClass("hide-border-bottom");
    }
}

//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $addressInput.val(roadAddr);
            $addressInput.attr("value", roadAddr);
        },
        onclose: function(state) {
            //state는 우편번호 찾기 화면이 어떻게 닫혔는지에 대한 상태 변수 이며, 상세 설명은 아래 목록에서 확인하실 수 있습니다.
            if(state === 'FORCE_CLOSE'){
                //사용자가 브라우저 닫기 버튼을 통해 팝업창을 닫았을 경우, 실행될 코드를 작성하는 부분입니다.
                $addressInput.removeClass("disabled");
                $addressInput.attr("disabled", false);
                $addressInput.val("");
                $search.css("display", "none");
                $addressInput.addClass("is-invalid");
                $invalidFeedback.eq(0).css("display", "block");
                $addressDetail.css("display", "none");
                $addressInput.blur();
            } else if(state === 'COMPLETE_CLOSE'){
                //사용자가 검색결과를 선택하여 팝업창이 닫혔을 경우, 실행될 코드를 작성하는 부분입니다.
                //oncomplete 콜백 함수가 실행 완료된 후에 실행됩니다.
                $addressInput.addClass("disabled");
                $addressInput.attr("disabled", true);
                $addressInput.removeClass("is-invalid");
                $invalidFeedback.eq(0).css("display", "none");
                $search.css("display", "block");
                $addressDetail.css("display", "block");
            }
        }
    }).open({popupKey : 'addrpopup1',
    });
}

const $addressInput = $("#sample4_roadAddress");
const $search = $("#search");
const $addressDetail = $("#addressDetail");
const $invalidFeedback = $(".invalid-feedback");

/* 주소창 클릭 이벤트 */
$addressInput.on("click", function(){
    sample4_execDaumPostcode();
});

/* 다른 주소 검색 클릭 이벤트 */
$search.on("click", function(){
    $addressInput.val("");
    $addressInput.removeClass("disabled");
    sample4_execDaumPostcode();
});

const $getIn = $(".get-in");

/* 상세 주소 블러 이벤트 */
$addressDetail.on("keyup", function(){
    if(!$(this).val()){
        $invalidFeedback.eq(1).css("display", "block");
        $(this).addClass("is-invalid");
        $getIn.css("display", "none");
    } else{
        $invalidFeedback.eq(1).css("display", "none");
        $(this).removeClass("is-invalid");
        $getIn.css("display", "block");
    }
    checkForm();
});

const $check = $(".get-in ul li");
const $getInInput = $(".get-in .getin-input");
const $caution = $(".get-in .getin-input + p.caution");

/* 체크박스 클릭 이벤트 */
$check.on("click", function(){
    let idx = $(this).index();
    if(!$(this).hasClass("active")){
        $check.removeClass("active");
        $(this).addClass("active");
    }

    if(idx==0){
        $getInInput.eq(0).css("display", "block");
        $caution.eq(0).css("display", "block");
    } else {
        $getInInput.eq(0).css("display", "none");
        $getInInput.eq(0).val("");
        $caution.eq(0).css("display", "none");
    }

    if(idx==3){
        $getInInput.eq(1).css("display", "block");
        $caution.eq(1).css("display", "block");
    } else {
        $getInInput.eq(1).css("display", "none");
        $getInInput.eq(1).val("");
        $caution.eq(1).css("display", "none");
    }

    checkForm();
});

/* 공동현관 출입방법 키업 이벤트 */
$getInInput.eq(0).on("keyup", function(){
    if($(this).val()){
        $caution.eq(0).css("display", "none");
    } else {
        $caution.css("display", "block");
    }
    checkForm();
});
$getInInput.eq(1).on("keyup", function(){
    if($(this).val()){
        $caution.eq(1).css("display", "none");
    } else {
        $caution.css("display", "block");
    }
    checkForm();
});

const $nextBtn = $("#nextBtn");
$nextBtn.on("click", function(){
    addressForm.submit();
});

function checkForm(){
    if(!$addressInput.val()){
        $nextBtn.attr("disabled", true);
        $nextBtn.addClass("disabled");
        return;
    }
    if(!$addressDetail.val()){
        $nextBtn.attr("disabled", true);
        $nextBtn.addClass("disabled");
        return;
    }
    if(!$("input[name='userEntranceType']").is(":checked")){
        $nextBtn.attr("disabled", true);
        $nextBtn.addClass("disabled");
        return;
    }
    if($("#byPw").is(":checked")){
        if(!$("#entrancePw").val()){
            $nextBtn.attr("disabled", true);
            $nextBtn.addClass("disabled");
            return;
        }
    }
    if($("#etc").is(":checked")){
        if(!$("#howto-getin").val()){
            $nextBtn.attr("disabled", true);
            $nextBtn.addClass("disabled");
            return;
        }
    }

    $addressInput.attr("disabled", false);
    $nextBtn.attr("disabled", false);
    $nextBtn.removeClass("disabled");
    return;
}