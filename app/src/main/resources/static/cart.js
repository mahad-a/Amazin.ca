$(document).ready(function() {
    // Fetch all carts when the page loads
    console.log("cart.js loaded");
    $.ajax({
        url: "/cart/getCart",
        type: "GET",
        success: function(carts) {
            $("#cartItems").empty();
            console.log("emptied cart items")
            // Iterate over each cart and display its details
            carts.forEach(cart => {
                    let cartHtml = `
                    <li>
                        <strong>Cart ID:</strong> ${cart.id} <br>
                        <strong>Books:</strong> 
                        <ul>`;
                    cart.books.forEach(book => {
                        cartHtml += `
                        <li>
                            <div class="book-details">
                                <strong>ISBN:</strong> ${book.isbn} <br>
                                <strong >Title:</strong> ${book.title} <br>
                                <strong>Author:</strong> ${book.author}
                            </div>
                            <button class="removeFromCartButton" data-book-id="${book.id}">Remove from Cart</button>
                        </li>`;
                    });
                    $("#cartItems").append(cartHtml);
            });
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
