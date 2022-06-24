const $changeSeveralBtn = $(".chooseSeveral");
const $changeOnceBtn = $(".chooseOnce");
const changeSeveralText = document.getElementById('chooseSeveralText');
const changeOnceText = document.getElementById('chooseOnceText');

$changeSeveralBtn.click(function () {
    // 서비스 이용중을 나타내는 class추가
    $changeSeveralBtn.addClass("using-service");
    $changeOnceBtn.removeClass("using-service");
    // ⭐후에 시간 남으면 h3를 없애고 div에 text(ex.변경하기)를 넣고 아래와 같이 변경하기
    // $changeSeveralBtn.text() = 'asda';
    changeSeveralText.innerHTML = '서비스 이용 중';
    changeOnceText.innerHTML = '변경하기';
});

$changeOnceBtn.click(function () {
    // 서비스 이용중을 나타내는 class추가
    $changeOnceBtn.addClass("using-service");
    $changeSeveralBtn.removeClass("using-service");
    changeSeveralText.innerHTML = '변경하기';
    changeOnceText.innerHTML = '서비스 이용 중';
});
