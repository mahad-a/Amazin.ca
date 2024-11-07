$(document).ready(function(){


    $.ajax({
        url: "/book/getAll",
        type: "GET",
        success: function(response){
            $("#bookList").empty()

            response.forEach(function(book){
                
                const bookItem = `
                    <li class="book-item">
                        <div class="image-container">
                            <img src="${book.coverImagePath}" 
                                 alt="Cover of ${book.title}"
                                 class="book-cover"
                                 onerror="this.src='/images/default-cover.jpg'">
                        </div>
                        <div class="book-details">
                            <strong>ISBN:</strong> ${book.ISBNnum} <br>
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