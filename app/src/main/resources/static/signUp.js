$(document).ready(function() {
    $("#signUpForm").on("submit", function(event) {
        
        event.preventDefault();
        
        
        const userName = $("#username").val();
        const password = $("#password").val();
        
        
        const action = event.originalEvent.submitter.getAttribute("data-action");
        
        let url = '';
        if (action === "user") {
            url = "/user/register";  
        } else if (action === "admin") {
            url = "/admin/register";  
        }

        
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