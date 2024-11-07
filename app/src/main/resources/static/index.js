$(document).ready(function(){


    $.ajax({
        url: "/book/getAll",
        type: "GET",
        success: function(response){
            $("#bookList").empty()

            response.forEach(function(book){
                // Print book information to the console
                const bookItem = `
                    <li class="book-item">
                        <div class="image-container">
                            <img src="${book.coverImage}" 
                                 alt="Cover of ${book.title}"
                                 class="book-cover"
                                 onerror="this.src='/images/default-cover.jpg'">
                        </div>
                        <div class="book-details">
                            <strong>ISBN:</strong> ${book.isbn} <br>
                            <strong>Title:</strong> ${book.title} <br>
                            <strong>Author:</strong> ${book.authour}
                        </div>
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