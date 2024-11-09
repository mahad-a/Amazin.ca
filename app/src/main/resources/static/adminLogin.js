$(document).ready(function(){

    $("#adminLoginForm").on("submit", function(event){
        
        event.preventDefault();
        
        // Get values from form
        const password = $("#password").val();
        const username = $("#username").val();

        
        $.ajax({
            url : "/admin/login",  // Make sure the URL is correct
            type : "POST",
            data: {
                username: username,
                password: password
            },
            success: function(response){
                console.log("Success!");
                window.location.href = "/admin/home";
                
            },
            error: function(xhr, status, error){
                console.log("Login failed.");
                console.log(error);
            }
        });
    });

});
