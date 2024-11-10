$(document).ready(function(){




    $("#userLoginForm").on("submit", function(event){
        event.preventDefault();

        const username = $("#username").val();
        const password = $("#password").val();

        $.ajax({
            url : "/user/login",
            type : "POST",
            data : {
                username : username,
                password : password
            },
            success : function(resource){
                console.log("User login Success!");
                window.location.href = "user/home";
            },

            error : function(xhr, status, error){
                console.log(error);
                console.log(xhr)
            }


        });
        

    });



});