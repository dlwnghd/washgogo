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
const $tabs = $(".price .tab a");
const $infoItems = $(".info-item");
$tabs.click(function(){
    let idx = $(this).index();
    $tabs.removeClass("active");
    $tabs.eq(idx).addClass("active");

    $firstUse.css("display", "none");
    $nextUse.css("display", "none");
    $serviceArea.css("display", "none");
    $tabDepth.css("display", "none");

    if(idx==0){
        $firstUse.css("display", "block");
    } else if(idx==1){
        $nextUse.css("display", "block");
        $tabDepth.css("display", "block");
    } else {
        $serviceArea.css("display", "block");
        $infoItems.css("display", "none");
        $infoItems.eq(0).css("display", "block");
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