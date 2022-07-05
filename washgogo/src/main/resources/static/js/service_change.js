const changeSeveralText = document.getElementById('chooseSeveralText');
const changeOnceText = document.getElementById('chooseOnceText');
const changeSeveralButton = $("#severalButton");
const changeOnceButton = $("#onceButton");
var userServiceTypeValue = document.getElementById("userServiceType");
var userLaunderetteValue = document.getElementById("userServiceType");

// 여러 번 이용 버튼을 눌렀을 경우
changeSeveralButton .click(function () {
    // 서비스 이용 중을 나타내는 class추가
    changeSeveralButton .addClass("using-service");
    changeSeveralButton .removeClass("no-service");
    changeOnceButton.removeClass("using-service");
    changeOnceButton.addClass("no-service");
    changeOnceButton.prop("type", "submit");
    // ⭐후에 시간 남으면 h3를 없애고 div에 text(ex.변경하기)를 넣고 아래와 같이 변경하기
    changeSeveralText.innerHTML = '서비스 이용 중';
    changeOnceText.innerHTML = '변경하기';
    userServiceTypeValue.value = "Several";
    userLaunderetteValue = "Regular"
});

// 한 번 이용 버튼을 눌렀을 경우
changeOnceButton.click(function () {
    // 서비스 이용 중을 나타내는 class추가
    changeOnceButton.addClass("using-service");
    changeOnceButton .removeClass("no-service");
    changeSeveralButton .removeClass("using-service");
    changeSeveralButton.addClass("no-service");
    changeSeveralButton.prop("type", "submit");
    changeSeveralText.innerHTML = '변경하기';
    changeOnceText.innerHTML = '서비스 이용 중';
    userServiceTypeValue.value = "Once";
    userLaunderetteValue = "선택안함"
});

