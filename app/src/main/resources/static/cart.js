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
                            ${cart.books.map(book => `<li>${book.title} by ${book.author} (ISBN: ${book.isbn})</li>`).join('')}
                        </ul>
                    </li>
                `;
                $("#cartItems").append(cartHtml);
            });
        },
        error: function(xhr, status, error) {
            console.error("Failed to retrieve carts:", error);
        }
    });
});
