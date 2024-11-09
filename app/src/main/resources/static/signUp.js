$(document).ready(function() {
    $("#signUpForm").on("submit", function(event) {
        // Prevent the default form submission
        event.preventDefault();
        
        // Get form values
        const userName = $("#username").val();
        const password = $("#password").val();
        
        // Determine which button was clicked
        const action = event.originalEvent.submitter.getAttribute("data-action");
        
        let url = '';
        if (action === "user") {
            url = "/user/register";  // For user registration
        } else if (action === "admin") {
            url = "/admin/register";  // For admin registration
        }

        // Send data via AJAX
        $.ajax({
            url: url,
            type: "POST",
            data: {
                username: userName,
                password: password
            },
            success: function(response) {
                console.log("Registration successful", response);  
            },
            error: function(xhr, status, error) {
                console.error("Registration failed", error);
                console.log(error);
                console.log(status);
                console.log(xhr);
            }
        });
    });
});