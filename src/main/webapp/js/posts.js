$(document).ready(function () {
    updateTweets();

    $("#js-sendpost").on("click", function () {
        alert(1);
        sendTweet()
    });
});

function sendTweet() {
    $input = $("#js-post-text");
    var text = $input.val();
    if (text.length == 0) {
        return;
    }
    $.ajax({
        url: "posts/add/${page}",
        type: "POST",
        data: {
            text: text
        },
        success: function () {

        }
    })
}

function updateTweets() {
    $.ajax({
        url:"posts/getAll/${page}",
        type:"GET",
        dataType: "html",
        success: function(data) {
            $("#posts-user").html(data);
        }
    })
}