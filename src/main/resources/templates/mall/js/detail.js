$(document).ready(function (){
    var width = window.screen.width;
    if (width <= 420){
        $('.am-tab-panel img').width(width - 20);
        $('.pay li').height(45);
        $('.pay li a').height(45).css("line-height","45px");
    }
});