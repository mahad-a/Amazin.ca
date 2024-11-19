$(document).ready(function() {
    console.log("cart.js loaded");
    const username = sessionStorage.getItem("username");
    
    if (!username) {
        $("#emptyCartMessage").text("Please log in to view your cart").show();
        return;
    }

    $.ajax({
        url: `/cart/getCart?username=${username}`,
        type: "GET",
        success: function(response) {
            $("#cartItems").empty();
            
            if (!response.books || response.books.length === 0) {
                $("#emptyCartMessage").show();
                return;
            }

            response.books.forEach(book => {
                $("#cartItems").append(
                    `<li>
                        <div class="book-details">
                            <strong>ISBN:</strong> ${book.isbn} <br>
                            <strong>Title:</strong> ${book.title} <br>
                            <strong>Author:</strong> ${book.author}
                        </div>
                        <button class="removeFromCartButton" data-book-id="${book.id}">Remove</button>
                    </li>`
                );
            });

            $(".removeFromCartButton").on("click", function() {
                const bookId = $(this).data("book-id");
                
                $.ajax({
                    url: `/cart/removeFromCart?bookID=${bookId}&username=${username}`,
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
            $("#emptyCartMessage").text("Failed to retrieve cart. Please try again.").show();
            console.error("Failed to retrieve carts:", error);
        }
    });
});