$(document).ready(function () {
    updateComments();

});

function addComment(id) {
    $input = $("#js-post-text");
    var text = $input.val();
    if (text.length == 0) {
        return;
    }
    $.ajax({
        url: "/comment",
        type: "POST",
        data: {
            text: text,
            postId: id
        },
        success: function () {
            updateComments();
        }
    })
}

function updateComments() {
    $.ajax({
        url:"/comment/all",
        type:"POST",
        data:{
            id:$('#post_id').val()
        },
        dataType: "html",
        success: function(data) {
            $("#comments-list").html(data);
        }
    })
}