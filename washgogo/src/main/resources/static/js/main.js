/* 메인페이지 배너 슬릭 이벤트 */
const $track = $(".main-banner .slick-track");
$(document).ready(function(){
    $track.slick({
        infinite: true,
        autoplay: true,
        autoplaySpeed: 4000,
        prevArrow: $(".main-banner .slick-prev"),
        nextArrow: $(".main-banner .slick-next"),
    });
});

/* 메인페이지 배너 호버 이벤트*/
const $arrows = $(".main-banner .slick-arrow");
$track.hover(function(){
    $arrows.css("opacity", 1);
}, function(){
    $arrows.css("opacity", 0);
});
$arrows.hover(function(){
    $arrows.css("opacity", 1);
}, function(){
    $arrows.css("opacity", 0);
});

/* 메인페이지 배너 인디케이터 이벤트 */
const $indicator = $(".main-banner .indicator");
$track.on("init reInit afterChange", function(event, slick, currentSlide, nextSlide){
    //currentSlide is undefined on init -- set it to 0 in this case (currentSlide is 0 based)
    var i = (currentSlide ? currentSlide : 0) + 1;
    $indicator.text(i + ' / ' + slick.slideCount);
});

/* 메인페이지 워시고고라이프 배너 슬릭 이벤트 */
const $lifeTrack = $(".washgogo-life .slick-track");
$(document).ready(function(){
    $lifeTrack.slick({
        infinite: false,
        slidesToShow: 3,
        slidesToScroll: 1,
        prevArrow: $(".washgogo-life .slick-prev"),
        nextArrow: $(".washgogo-life .slick-next"),
    });
});
