$(document).ready(function () {
    updateButton();
});


function getUsername() {
    return $('#username').text().trim();
}
function follow() {
    $.ajax({
        url: "/follow",
        type: "POST",
        data: {
            usernameFollow: getUsername()
        },
        success: function () {
            updateButton();
        }
    })
}

function unFollow() {
    $.ajax({
        url: "/unFollow",
        type: "POST",
        data: {
            usernameFollow: getUsername()
        },
        success: function () {
            updateButton();
        }
    })
}

function updateButton() {
    $.ajax({
        url: "/updateFollowers/"+getUsername(),
        type: "GET",
        dataType: "html",
        success: function (data) {
            $("#inf-followers").html(data);
        }
    })
}