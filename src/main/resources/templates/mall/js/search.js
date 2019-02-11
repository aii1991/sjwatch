$(document).ready(function (){
    var width = window.screen.width;
    if(width <= 420){
        $('.boxes li').width(180);
        $('.boxes img').width(180);
    }
    if (width <= 380){
        $('.boxes li').width(161);
        $('.boxes img').width(161);
    }
});