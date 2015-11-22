$(document).ready(function () {
    var menu = "<style>.context-menu {display: none;z-index: 100;position: absolute;width: 200px;border: 1px solid black;background-color: #ffffff;}.menu-item a {text-decoration: none;font-size: 20px;padding: 10px;}.context-menu a:hover {background-color: #edefff;}.menu-item {margin-bottom: 10px;width: 100%;}.context-menu__items { padding: 0;  !important;  }</style>" +
        "<div class=\"context-menu\"><ul class=\"context-menu__items\"><div class=\"menu-item\" id=\"select-context-menu-item\"><a href=\"#\" class=\"context-menu__link\"> Select</a></div><div class=\"menu-item\" id=\"css-selector-context-menu-item\"><a href=\"#\" class=\"context-menu__link\">getCSSSelector</a></div><div class=\"menu-item\" id=\"event-context-menu-item\"><a href=\"#\" class=\"context-menu__link\">getEventListener</a></div></ul></div>" +
        "<div style=\"width: 99%;height: 100px;position: absolute;background-color:#ffffff;border-top:1px solid #000; z-index: 1000;display: none; bottom: 0;\" id=\"footer-out-text\"></div>";
    $('body').append(menu);
    var block;

    if (document.addEventListener) {
        document.addEventListener('contextmenu', function (event) {
            $('.context-menu').css("display", "block");
            $('#footer-out-text').css("display", "block");
            var clickX = (event.layerX == undefined ? event.offsetX : event.layerX) + 1;
            var clickY = (event.layerY == undefined ? event.offsetY : event.layerY) + 1;
            $('.context-menu').css('top', clickY);
            $('.context-menu').css('left', clickX);
            block = event.target;
            block.addEventListener('click',function(event){
            })
            event.preventDefault();
        }, false);
    } else {
        document.attachEvent('oncontextmenu', function () {
            alert("You've tried to open context menu");
            window.event.returnValue = false;
        });
    }
    $('#select-context-menu-item').on('click', function (event) {
        $(block).css({"border": "3px solid lightblue"});
    })

    $(window).on('click', function () {
        $('.context-menu').css("display", "none");
        $('#footer-out-text').css("display", "none");

    })

    $('#css-selector-context-menu-item').on('click', function (event) {
        var aAttr = $(block);
        $('#footer-out-text').html("");
        while (aAttr[0].tagName) {
            if (aAttr.attr('id')) {
                $('#footer-out-text').append(aAttr.attr('id'));
            } else if (aAttr.attr('class')) {
                $('#footer-out-text').append(aAttr.attr('class'));
            } else {
                $('#footer-out-text').append(aAttr[0].tagName);
            }
            $('#footer-out-text').append(" < ");
            aAttr = aAttr.parent(0);
        }
    })

    $('#event-context-menu-item').on('click', function (event) {
        console.log(getEventListeners($('#event-context-menu-item')));

    })
});