/* 모달창 이벤트 */
const $modalBg = $("#commonMessageBoxBg");
const $body = $("body");
const $modalSuccess = $("#successMessageBox");
const $modalChange = $("#commonMessageBox");
const $launderetteModal = $("#launderetteMsgBox");
const $okModal = $("#successMessageBox .btn-close-msg-Box");
const $closeBtn = $("#commonMessageBox .btn-close-msg-Box");
const $closeModal = $(".modalCloseBtn");


function showSuccessModal() {
    $modalSuccess.css("display", "block");
    modalOn();
}

function showChangeModal(){
    $modalChange.css("display", "block");
    modalOn();
}

$okModal.on("click", function(){
    location.href = "/order/requestGuide";
});
$closeBtn.on("click", function(){
    modalOff();
});
$closeModal.on("click", function(){
    modalOff();
});
function modalOn(){
    $modalBg.css("display", "block");
    $body.css("overflow", "hidden");
}
function modalOff(){
    $modalBg.css("display", "none");
    $modalChange.css("display", "none");
    $launderetteModal.css("display", "none");
    $body.css("overflow", "auto");
}

/* 런드렛 클릭 이벤트 */
if(selectedServiceType == "Several"){
    const $launderette = $(".payment .choice-wrap li");
    const $noChangeBtn = $("#launderetteMsgBox .btn-close-msg-Box");
    const $changeBtn = $("#launderetteMsgBox .btn-change-launderette");
    const $regular = $("#Regular");
    const $mini = $("#Mini");
    $regular.prop("checked", true);

    $launderette.eq(0).on("click", function(){
        $launderette.removeClass("focus");
        $(this).addClass("focus");
    });
    $launderette.eq(1).on("click", function () {
        $mini.prop("checked", false);
        $launderetteModal.css("display", "block");
        modalOn();
    });
    $noChangeBtn.on("click", function () {
        $regular.prop("checked", true);
        modalOff();

    });
    $changeBtn.on("click", function () {
        $launderette.removeClass("focus");
        $launderette.eq(1).addClass("focus");
        $mini.prop("checked", true);
        modalOff();
    });

    /* 런드렛 알아보기 이벤트 */
    const $launderetteMore = $(".launderette-choice .more-info");
    const $launderetteInfo = $(".modal-container.launderetteDetail");
    const $InfoClose = $(".launderetteDetail .modal-close");
    $launderetteMore.on("click", function () {
        $launderetteInfo.css("display", "block");
        modalOn();
    });
    $InfoClose.on("click", function () {
        $launderetteInfo.css("display", "none");
        modalOff();
    })
}


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
const $serviceChangeBtn = $("#serviceChangeBtn");
const $btnOk = $("#commonMessageBox .btnOk");
let launderetteType;

$subscribeBtn.on("click", function () {
    changeService(showSuccessModal);
});

$serviceChangeBtn.on("click", function () {
    showChangeModal();
});
$btnOk.on("click", function () {
    changeService();
    location.href = "/user/serviceChangeComplete";
});
function changeService(callback) {
    if(checkForm()){
        if(selectedServiceType == "Several"){
            let launderetteTypeVal = $("input[name='userLaunderetteType']:checked").val();
            launderetteType = launderetteTypeVal;
        } else {
            launderetteType = "선택 안 함";
        }
        let serviceData = {
            userServiceType : selectedServiceType,
            userLaunderetteType : launderetteType
        }
        $.ajax({
            url: "/user/servicePaymentOk",
            type: "patch",
            data: JSON.stringify(serviceData),
            contentType:"application/json; charset=utf-8",
            success: function (isSuccess) {
                if(isSuccess){
                    if(callback) callback();
                }
            }
        });
    }
}

/* 검사 */
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

/* 주소 수정 버튼 클릭 이벤트 */
const $modifyAddressBtn = $(".payment #modify");
$modifyAddressBtn.on("click", function () {
    const $newForm = $('<form></form>')
    $newForm.attr("name", "newForm");
    $newForm.attr("method", "post");
    $newForm.attr("action", "/user/modifyAddress");

    $newForm.append($('<input/>', {type: 'hidden', name: 'userAddress', value: address}));
    $newForm.append($('<input/>', {type: 'hidden', name: 'userAddressDetail', value: addressDetail}));
    $newForm.append($('<input/>', {type: 'hidden', name: 'userEntranceType', value: entranceType}));
    $newForm.append($('<input/>', {type: 'hidden', name: 'userEntrancePw', value: entrancePw}));
    $newForm.append($('<input/>', {type: 'hidden', name: 'userEntranceMessage', value: entranceMessage}));
    $newForm.append($('<input/>', {type: 'hidden', name: 'selectedServiceType', value: selectedServiceType}));

    $newForm.appendTo('body');

    $newForm.submit();
})