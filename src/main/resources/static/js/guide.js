/* 헤더 스크롤 이벤트 */
const $header = $("#app-header");
document.onscroll = function(){
    if(window.scrollY>0){
        $header.removeClass("hide-border-bottom");
    } else {
        $header.addClass("hide-border-bottom");
    }
}

/* tab 이벤트 */
const $tabs = $(".guide .tab a");
const $firstUse = $(".first-use");
const $nextUse = $(".next-use");
const $serviceArea = $(".service-area");
const $infoItems = $(".info-item");
const $tabDepthCont = $(".tab-depth");
const $tabDepths = $(".tab-depth a");

$tabs.click(function(){
    let idx = $(this).index();
    $tabs.removeClass("active");
    $tabs.eq(idx).addClass("active");

    $firstUse.css("display", "none");
    $nextUse.css("display", "none");
    $serviceArea.css("display", "none");
    $tabDepthCont.css("display", "none");

    if(idx==0){
        $firstUse.css("display", "block");
    } else if(idx==1){
        $nextUse.css("display", "block");
        $tabDepthCont.css("display", "flex");
        $tabDepths.removeClass("active");
        $tabDepths.eq(0).addClass("active");
        $laundret.css("display", "block");
        $lightbag.css("display", "none");
    } else {
        $serviceArea.css("display", "block");
        $infoItems.css("display", "none");
        $infoItems.eq(0).css("display", "block");
    }
});

const $laundret = $(".guide .laundret");
const $lightbag = $(".guide .lightbag") ;

$tabDepths.on("click", function(){
    let idx = $(this).index();
    $tabDepths.removeClass("active");
    $tabDepths.eq(idx).addClass("active");

    $laundret.css("display", "none");
    $lightbag.css("display", "none");

    if(idx==0){
        $laundret.css("display", "block");
    } else {
        $lightbag.css("display", "block");
    }
});

/* 이용지역 카테고리 이벤트 */
const $categories = $(".location-item");

$categories.click(function(){
    let idx = $(this).index();
    let region = "";
    console.log(idx);
    $infoItems.css("display", "none");

    switch(idx){
        case 1: region = "seoul"; break;
        case 2: region = "seong-nam"; break;
        case 3: region = "yong-in"; break;
        case 4: region = "dong-tan"; break;
        case 5: region = "ha-nam"; break;
        case 6: region = "an-yang"; break;
        case 7: region = "gwang-myeong"; break;
        case 8: region = "bu-cheon"; break;
        case 9: region = "in-cheon"; break;
        case 10: region = "gim-po"; break;
        case 11: region = "go-yang"; break;
        case 12: region = "pa-ju"; break;
    }

    if(idx==0){
        $infoItems.css("display", "block");
        $infoItems.eq(0).css("display", "none");
    } else {
        $infoItems.each(function(index, item){
            if($(item).hasClass(region)){
                $(item).css("display", "block");
            }
        });
    }
});

/* 모달창 클릭 이벤트 */
const $kitDetailBtn = $("#btnKitDetail");
const $cautionDetailBtn = $("#btnCautionDetail");
const $dialKeyInfoBtn = $("#btnDialKeyInfo");
const $smartKeyInfoBtn = $("#btnSmartKeyInfo");

const $modalBg = $(".modal-bg");
const $modals = $(".modal-container");
const $closeModal = $(".modal-close");
const $okModal = $(".modal-container .btn-black")
const $body = $("body");

$closeModal.on("click", function(){
    modalOff();
});
$okModal.on("click", function(){
    modalOff();
});
$kitDetailBtn.on("click", function(){
    $modals.eq(0).css("display", "block");
    modalOn();
});
$cautionDetailBtn.on("click", function(){
    $modals.eq(1).css("display", "block");
    modalOn();
});
$dialKeyInfoBtn.on("click", function(){
    $modals.eq(2).css("display", "block");
    modalOn();
});
$smartKeyInfoBtn.on("click", function(){
    $modals.eq(3).css("display", "block");
    modalOn();
});

function modalOn(){
    $modalBg.css("display", "block");
    $body.css("overflow", "hidden");
}
function modalOff(){
    $modalBg.css("display", "none");
    $modals.css("display", "none");
    $body.css("overflow", "auto");
}