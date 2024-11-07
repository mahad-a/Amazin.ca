$(document).ready(function(){
    $("#createBookForm").on('submit', function(event){
        const formData = new FormData(this)
        event.preventDefault();
        $.ajax({
            url: '/book/add',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function(response){
                alert('Book added successfully');
                $("#createBookForm")[0].reset();
                $("#imagePreview").css('display', 'none');
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

    window.deleteBook = function(bookId) {
        if (confirm('Are you sure you want to delete this book?')) {
            $.ajax({
                url: `/book/del?id=${bookId}`,
                type: 'DELETE',
                success: function(response) {
                    alert('Book deleted successfully');
                    loadBooks(); // Reload the book list
                },
                error: function(xhr, status, error) {
                    console.error("Error deleting book:", error);
                    alert('Error deleting book: ' + error);
                }
            });
        }
    };
});
