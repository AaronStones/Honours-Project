<?php
session_start();
if (isset($_SESSION['userData']) == false){
    header('Location: '.'https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/login.php');
}

$Details = json_decode($_SESSION['userData']);  //get and decode the sessions data
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <?php include("templates/header.php"); ?>
</head>
    <body>
        <?php include("templates/nav.php"); ?>
        <div class="container my-3" role="main">
        <h1>My Account</h1>
        <p>Here you can view and modify the your account details.</p> 
        <hr>
        
        <h2 class="mt-5">Change Email</h2>
        <div id="responseChangeEmail"></div> <!-- Response will go here -->
        <form id="formChangeEmail" method="post">
            <label for="password">Current Password</label>
            <input name="password" class="form-control" type="password" placeholder="Enter Password" required pattern="^(?=.*\d)(?=.*[a-zA-Z]).{8,80}$" title="Please ensure the password is 8 characters and contains at least one letter and one number.">
            <label for="newEmail">New Email</label>
            <input name="newEmail" class="form-control" type="text" placeholder="Enter Email" required pattern="^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$" title="Please ensure the email address is valid.">
            <button id="submitChangeEmail" type="submit" class="form-control btn btn-success mt-3">Change Account Email</button>
        </form>

        <h2 class="mt-5">Change Password</h2>
        <div id="responseChangePassword"></div> <!-- Response will go here -->
        <form id="formChangePassword" method="post">
            <label for="password">Current Password</label>
            <input name="password" class="form-control" type="password" placeholder="Enter Password" required pattern="^(?=.*\d)(?=.*[a-zA-Z]).{8,80}$" title="Please ensure the password is 8 characters and contains at least one letter and one number.">
            <label for="newPassword">New Password</label>
            <input name="newPassword" class="form-control" type="password" placeholder="Enter New Password" required pattern="^(?=.*\d)(?=.*[a-zA-Z]).{8,80}$" title="Please ensure the password is 8 characters and contains at least one letter and one number.">
            <label for="newConfirm">Confirm New Password</label>
            <input name="newConfirm" class="form-control" type="password" placeholder="Confirm New Password" required pattern="^(?=.*\d)(?=.*[a-zA-Z]).{8,80}$" title="Please ensure the password is 8 characters and contains at least one letter and one number.">
            <button id="submitChangePassword" type="submit" class="form-control btn btn-success mt-3">Change Account Password</button>
        </form>

        <h2 class="mt-5">Change Name</h2>
        <div id="responseChangePassword"></div> <!-- Response will go here -->
        <form id="formChangePassword" method="post">
            <label for="password">New Name</label>
            <input name="name" class="form-control" type="text" placeholder="Enter Name">
            <button id="submitChangePassword" type="submit" class="form-control btn btn-success mt-3">Change Account Name</button>
        </form>

        </div>

        <!-- Start of AJAX script -->

        <script>
        // Change email form
        $('#formChangeEmail').on('submit', function(e)
        {
            e.preventDefault(); // Prevent default form behaviour so we can override it

            $('#submitChangeEmail').prop('disabled', true); // Disable submit button to prevent multiple submissions

            $.post('../Controller/change_email.php', $('#formChangeEmail').serialize(), function(data)
            {
            let response = '<div class="alert alert-' + (data.success ? 'success' : 'danger') + '">' + data.message + '</div>';
            $('#responseChangeEmail').html(response); // Display response message
            $('#submitChangeEmail').prop('disabled', false);
            }, 'json');
        });

        // Change password form
        $('#formChangePassword').on('submit', function(e)
        {
            e.preventDefault(); // Prevent default form behaviour so we can override it

            $('#submitChangePassword').prop('disabled', true); // Disable submit button to prevent multiple submissions
            
            $.post('../controller/change_password.php', $('#formChangePassword').serialize(), function(data)
            {
            let response = '<div class="alert alert-' + (data.success ? 'success' : 'danger') + '">' + data.message + '</div>';
            $('#responseChangePassword').html(response); // Display response message
            $('#submitChangePassword').prop('disabled', false);
            }, 'json');
        });

        // Delete user form
        $('#formDeleteAccount').on('submit', function(e)
        {
            e.preventDefault(); // Prevent default form behaviour so we can override it

            if (window.confirm("Are you sure you want to permanently delete your account?"))
            {
            $('#submitDeleteAccount').prop('disabled', true); // Disable submit button to prevent multiple submissions

            $.post('../controller/delete_user.php', $('#formDeleteAccount').serialize(), function(data)
            {
                let response = '<div class="alert alert-' + (data.success ? 'success' : 'danger') + '">' + data.message + '</div>';
                $('#responseDeleteAccount').html(response); // Display response message
                $('#submitDeleteAccount').prop('disabled', false);
                if (data.success)
                setTimeout(function () { location.href = 'index' }, 1500);  
            }, 'json');
            }
        });
        </script>

        <!-- End of AJAX script -->


        <?php include("templates/footer.php"); ?>
    </body>
</html>