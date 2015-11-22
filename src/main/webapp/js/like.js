

function like(id) {
    $.ajax({
        url: "/like",
        type: "POST",
        data: {
            idPost: id
        },
        success: function () {

        }
    })
}