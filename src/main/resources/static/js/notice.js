const $moreBtns= $($(".noticesheet .notice-item .more").closest("tr"));
const $moreInfoes = $($(".noticesheet .notice-item .more-info").closest("tr"))
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