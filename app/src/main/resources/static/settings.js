$(document).ready(function() {
    const settingsActions = $("#settings-actions");
    const username = sessionStorage.getItem("username");
    console.log("Username from sessionStorage:", username);

    $("#change-username").click(function() {
        settingsActions.empty();

        const changeUsernameForm = `
            <div id="change-username-section" class="settings-section">
                <h3>Change Username</h3>
                <form id="change-username-form">
                    <label for="old-username">Old Username:</label>
                    <input type="text" id="old-username" name="old-username" required>
                    <br>
                    <label for="new-username">New Username:</label>
                    <input type="text" id="new-username" name="new-username" required>
                    <br>
                    <button type="submit">Submit</button>
                </form>
            </div>
        `;

        settingsActions.append(changeUsernameForm);
        $("#change-username-section").show();
    });

    $("#change-password").click(function() {
        settingsActions.empty();

        const changePasswordForm = `
            <div id="change-password-section" class="settings-section">
                <h3>Change Password</h3>
                <form id="change-password-form">
                    <label for="old-password">Old Password:</label>
                    <input type="password" id="old-password" name="old-password" required>
                    <br>
                    <label for="new-password">New Password:</label>
                    <input type="password" id="new-password" name="new-password" required>
                    <br>
                    <button type="submit">Submit</button>
                </form>
            </div>
        `;

        settingsActions.append(changePasswordForm);
        $("#change-password-section").show();
    });

    $("#delete-account").click(function () {
        settingsActions.empty();

        const deleteAccountForm = `
            <div id="delete-account-section" class="settings-section">
                <h3>Delete Account</h3>
                <form id="delete-account-form">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                    <br>
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                    <br>
                    <button type="submit">Delete Account</button>
                </form>
            </div>
        `;

        settingsActions.append(deleteAccountForm);
        $("#delete-account-section").show();
    });


    $(document).on("submit", "#change-username-form", function(event) {
        event.preventDefault();

        const oldUsername = $("#old-username").val();
        const newUsername = $("#new-username").val();

        $.ajax({
            type: "POST",
            url: "/settings/changeUsername",
            data: {
                oldUsername: oldUsername,
                newUsername: newUsername
            },
            success: function(response) {
                alert("Username changed successfully!");
                sessionStorage.setItem("username", newUsername);
            },
            error: function(xhr, status, error) {
                alert("Error changing username: " + error);
            }
        });
    });

    $(document).on("submit", "#change-password-form", function(event) {
        event.preventDefault();

        const oldPassword = $("#old-password").val();
        const newPassword = $("#new-password").val();

        $.ajax({
            type: "POST",
            url: "/settings/changePassword",
            data: {
                username: username,
                currentPassword: oldPassword,
                newPassword: newPassword
            },
            success: function(response) {
                alert("Password changed successfully!");
            },
            error: function(xhr, status, error) {
                alert("Error changing password: " + error);
            }
        });
    });

    $(document).on("submit", "#delete-account-form", function (event) {
        event.preventDefault();

        const username = $("#username").val();
        const password = $("#password").val();

        if (confirm("Are you sure you want to delete your account? This action cannot be undone.")) {
            $.ajax({
                type: "DELETE",
                url: "/settings/deleteAccount",
                data: {
                    username: username,
                    password: password,
                },
                success: function (response) {
                    alert("Your account has been deleted successfully.");
                    window.location.href = "/loginEntry";
                },
                error: function (xhr, status, error) {
                    alert("Error deleting account: " + error);
                },
            });
        } else {
            alert("Account deletion canceled.");
        }
    });
});
