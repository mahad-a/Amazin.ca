$(document).ready(function() {
    // Fetch all carts when the page loads
    console.log("cart.js loaded");
    $.ajax({
        url: "/cart/getCart",
        type: "GET",
        success: function(carts) {
            // Iterate over each cart and display its details
            carts.forEach(cart => {
                const cartHtml = `
                    <li>
                        <strong>Cart ID:</strong> ${cart.id} <br>
                        <strong>Books:</strong> 
                        <ul>
                            ${cart.books.map(book => `<li>${book.title} by ${book.author} (ISBN: ${book.isbn}) 
                            <button class="removeFromCartButton" data-book-id="${book.id}">Remove from Cart</button></li>`).join('')}
                        </ul>
                    </li>
                `;
                $("#cartItems").append(cartHtml);
            });
            $(".remove-btn").click(function() {
                let bookId = $(this).data("book-id");
                $.ajax({
                    url: `/cart/removeFromCart?bookId=${bookId}`,
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
