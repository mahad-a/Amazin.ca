$(document).ready(function() {
    // Fetch all carts when the page loads
    console.log("cart.js loaded");
    const username = sessionStorage.getItem("username");
    console.log(username);

    $.ajax({
        url: `/cart/getCart?username=${username}`,
        type: "GET",
        success: function(response) {
            $("#cartItems").empty(); // empty out the cart to avoid duplicates

            // for each cart in list of carts
            // for each book in the cart
            response.books.forEach(book => {

                $("#cartItems").append(
                    `<li>
                    <div class="book-details">
                        <strong>ISBN:</strong> ${book.isbn} <br>
                        <strong>Title:</strong> ${book.title} <br>
                        <strong>Author:</strong> ${book.author}
                    </div>
                    <button class="removeFromCartButton" data-book-id="${book.id}">Remove from Cart</button>
                </li>`
                )
                
            });
           
            // remove from cart button functionality
            $(".removeFromCartButton").on("click", function() {
                const bookId = $(this).data("book-id");
                $.ajax({
                    url: `/cart/removeFromCart?bookID=${bookId}`,
                    type: "DELETE",
                    success: function(response) {
                        alert("Book removed from cart.");
                        location.reload();
                    },
                    error: function(xhr, status, error) {
                        alert("Failed to remove the book from cart.");
                        console.error("Error:", error);
                    }
                });
            });
        },
        error: function(xhr, status, error) {
            console.error("Failed to retrieve carts:", error);
        }
    });
});
