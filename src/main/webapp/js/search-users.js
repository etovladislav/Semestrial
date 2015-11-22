$(document).ready(function () {
    searchUsers();
    $('#search-input').on('keyup', function () {
        searchUsers();
    });
});
function searchUsers() {
        var value = $('#search-input').val();
        console.log(value);
        $.ajax({
            type: "POST",
            url: "/search/users",
            data: {username: value},
            success: function (data) {
                $("#all-users-list").html(data);
            }
        });
}
