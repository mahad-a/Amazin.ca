$(document).ready(function(){


    $.ajax({
        url: "/book/getAll",
        type: "GET",
        success: function(response){
            $("#bookList").empty()

            response.forEach(function(book){

                const bookItem = `<li>
                <strong>ISBN:</strong> ${book.ISBN} <br>
                <strong>Title:</strong> ${book.title} <br>
                <strong>Author:</strong> ${book.authour}
            </li>`;
            $("#bookList").append(bookItem);

            });
        },

        error: function(xhr, status, error){
            alert("ERROR");
            console.log(error);
            console.log(status);

        }


    })



});