$(document).ready(function() {

    $.ajax({
        url: "/book/getAll",
        type: "GET",
        success: function(response) {
            // Clear the current book list
            $("#bookList").empty();

            response.forEach(function(book) {
                // Use base64 image if available; otherwise, use default cover image
                const coverImage = book.coverImage && book.coverImage.trim()
                    ? `data:image/jpeg;base64,${book.coverImage}`
                    : '/images/default-cover.jpg';

                // Generate HTML for each book item
                const bookItem = `
                    <li class="book-item">
                        <div class="image-container">
                            <img src="${coverImage}" 
                                 alt="Cover of ${book.title}" 
                                 class="book-cover">
                        </div>
                        <div class="book-details">
                            <strong>ISBN:</strong> ${book.isbn} <br>
                            <strong>Title:</strong> ${book.title} <br>
                            <strong>Author:</strong> ${book.author}
                        </div>
                    </li>`;

                // Append each book item to the list
                $("#bookList").append(bookItem);
            });
        },

        // Error handling for AJAX request
        error: function(xhr, status, error) {
            alert("ERROR");
            console.log(error);
            console.log(status);
        }
    });

});