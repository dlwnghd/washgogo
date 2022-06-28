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

let $pageForm = $(pageForm);

$("a.changePage").on("click", function(e){
    e.preventDefault();
    $pageForm.find("input[name='pageNum']").val($(this).attr("href"));
    $pageForm.submit();
});