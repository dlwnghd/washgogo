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
const $tabs = $(".pricesheet .tab a");
const $priceBasic = $(".pricesheet .price-basic")
const $pricePremium = $(".pricesheet .price-premium")
const $priceItems = $(".price-item");
$tabs.on("click", function(){
    let idx = $(this).index();
    $tabs.removeClass("active");
    $tabs.eq(idx).addClass("active");

    $priceBasic.css("display", "none");
    $pricePremium.css("display", "none");

    if(idx==0){
        $priceBasic.css("display", "block");
    } else {
        $pricePremium.css("display", "block");
    }
});

/* 가격표 토글 이벤트 */
const $moreBtns= $($(".pricesheet .price-item .more").closest("tr"));
const $moreInfoes = $($(".pricesheet .price-item .more-info").closest("tr"))
$moreBtns.css("cursor", "pointer");
$moreInfoes.css("display", "none");
$moreBtns.on("click", function(){
    if(!$(this).hasClass("on")){
        $(this).addClass("on");
    } else {
        $(this).removeClass("on");
    }
    let $moreInfo = $(this).next();
    $moreInfo.toggle();
});

/* 가격표 카테고리 이벤트 */
/* basic */
const $categoriesBasic = $(".price-basic .laundry-item");

$categoriesBasic.on("click", function(){
    let idx = $(this).index();
    let category = "";
    console.log(idx);
    $priceItems.css("display", "none");

    switch(idx){
        case 1: category = "daily"; break;
        case 2: category = "shirt"; break;
        case 3: category = "individual"; break;
        case 4: category = "bedding"; break;
        case 5: category = "living"; break;
        case 6: category = "goods"; break;
        case 7: category = "shoes"; break;
    }

    if(idx==0){
        $priceItems.css("display", "block");
    } else {
        $priceItems.each(function(index, item){
            if($(item).hasClass(category)){
                $(item).css("display", "block");
            }
            if($(item).hasClass("always")){
                $(item).css("display", "block");
            }
        });
    }
});

/* pre,ium */
const $categoriesPremium = $(".price-premium .laundry-item");

$categoriesPremium.on("click", function(){
    let idx = $(this).index();
    let category = "";
    console.log(idx);
    $priceItems.css("display", "none");

    switch(idx){
        case 1: category = "daily"; break;
        case 2: category = "shirt"; break;
        case 3: category = "individual"; break;
        case 4: category = "bedding"; break;
        case 5: category = "living"; break;
        case 6: category = "goods"; break;
        case 7: category = "shoes"; break;
    }

    if(idx==0){
        $priceItems.css("display", "block");
    } else {
        $priceItems.each(function(index, item){
            if($(item).hasClass(category)){
                $(item).css("display", "block");
            }
            if($(item).hasClass("always")){
                $(item).css("display", "block");
            }
        });
    }
});