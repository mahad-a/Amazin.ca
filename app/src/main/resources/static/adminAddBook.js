$(document).ready(function(){
    $("#createBookForm").on('submit', function(event){
        event.preventDefault();
        $.ajax({
            url: '/book/add',
            type: 'POST',
            data: {
                ISBN: $("#ISBN").val(),
                title: $("#titleText").val(),
                authour: $("#authourText").val()

            },
            success: function(response){
                alert('Book added successfully');
                console.log(response);
            },
            error: function(xhr, status, error){
                alert('Failed to add book');
                console.log(error);
                console.log(xhr);
                console.log(status);
            }
        });
    });
});
