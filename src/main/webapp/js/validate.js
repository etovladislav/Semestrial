$('#login').on('keyup', function () {
    var value = $('#login').val();
    console.log(value);
    $.ajax({
        type: "POST",
        url: "/checkuser",
        data: {username: value},
        success: function (data) {
            if(data){
                var span = $('<span class="errorForm">'+data+'</span>');
                $('#div_username').html(span);
                $(span).fadeIn();
            }else{
                $('#div_username').html("");
            }
        }
    });
});
$('#login').on('keyup', function(){
    $(this).children('.errors').html("");
});