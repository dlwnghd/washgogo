const $month = $(".month");
const $onemonth = $(".onemonth");
const $threemonth = $(".threemonth");
const $sixmonth = $(".sixmonth");

$month.on("click", function () {
    let idx = $(this).index();
    let category = "";
    switch (idx) {
        case 0:
            $onemonth.addClass("select");
            $threemonth.removeClass("select");
            $sixmonth.removeClass("select");
            break;
        case 1:
            $onemonth.removeClass("select");
            $threemonth.addClass("select");
            $sixmonth.removeClass("select");
            break;
        case 2:
            $onemonth.removeClass("select");
            $threemonth.removeClass("select");
            $sixmonth.addClass("select");
            break;
    }
});