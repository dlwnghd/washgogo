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
const $priceItemsBasic = $(".price-basic .price-item");

$categoriesBasic.on("click", function(){
    let idx = $(this).index();
    let category = "";
    console.log(idx);
    $priceItemsBasic.css("display", "none");

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
        $priceItemsBasic.css("display", "block");
    } else {
        $priceItemsBasic.each(function(index, item){
            if($(item).hasClass(category)){
                $(item).css("display", "block");
            }
            if($(item).hasClass("always")){
                $(item).css("display", "block");
            }
        });
    }
});

/* premium */
const $categoriesPremium = $(".price-premium .laundry-item");
const $priceItemsPremium = $(".price-premium .price-item");

$categoriesPremium.on("click", function(){
    let idx = $(this).index();
    let category = "";
    console.log(idx);
    $priceItemsPremium.css("display", "none");

    switch(idx){
        case 1: category = "outer"; break;
        case 2: category = "top"; break;
        case 3: category = "bottom"; break;
        case 4: category = "onepiece"; break;
        case 5: category = "shoes-pr"; break;
        case 6: category = "living-pr"; break;
        case 7: category = "leather"; break;
        case 8: category = "etc"; break;
    }

    if(idx==0){
        $priceItemsPremium.css("display", "block");
    } else {
        $priceItemsPremium.each(function(index, item){
            if($(item).hasClass(category)){
                $(item).css("display", "block");
            }
            if($(item).hasClass("always")){
                $(item).css("display", "block");
            }
        });
    }
});

/* 모달창 클릭 이벤트 */
const $brandBtn = $(".show-brands");
const $modalBg = $(".modal-bg");
const $modal = $(".modal-container");
const $closeModal = $(".modal-close");
const $body = $("body");
$brandBtn.on("click", function(){
    $modalBg.css("display", "block");
    $modal.css("display", "block");
    $body.css("overflow", "hidden");
});
$closeModal.on("click", function(){
    $modalBg.css("display", "none");
    $modal.css("display", "none");
    $body.css("overflow", "auto");
})