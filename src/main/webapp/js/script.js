$(document).ready(function () {
    updateTweets();
    $("#js-sendpost").on("click", function () {
        sendTweet()
    });
});

function getUsername() {
    return $('#username').text().trim();
}
function sendTweet() {
    $input = $("#js-post-text");
    var text = $input.val();
    if (text.length == 0) {
        return;
    }
    $.ajax({
        url: "/posts/add/" + getUsername(),
        type: "POST",
        data: {
            text: text
        },
        success: function () {
            updateTweets();
        }
    })
}

function deletePost(postid) {
    $button = $("#delete-post");
    $.ajax({
        url: "/posts/delete",
        type: "POST",
        data: {
            id: postid
        },
        success: function () {
            updateTweets();
        }
    })
}

function updateTweets() {
    $.ajax({
        url: "/posts/getAll/" + getUsername(),
        type: "GET",
        dataType: "html",
        success: function (data) {
            $("#posts-user").html(data);
        }
    })
}