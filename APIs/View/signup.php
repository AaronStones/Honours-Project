<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <?php include("templates/header.php"); ?>
</head>
    <body>
<!-- Start of page content -->

<div class="container my-3" role="main">
  <h1>Register</h1>
  <p>Please fill in this form to register a new account or <a class="link" href="login">login as an existing user</a> if you already have an account.</p>
  <hr>
  <div id="response"></div> <!-- Response will go here -->
  <form method = "post">
    <label for="email">Email</label>
    <input class="form-control" type="text" placeholder="Enter Email" pattern="^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$" title="Please ensure the email address is valid." name="email" required>
    <label for="password">Password</label>
    <input class="form-control" type="password" placeholder="Enter Password" pattern="^(?=.*\d)(?=.*[a-zA-Z]).{8,80}$" title="Please ensure the password is 8 characters and contains at least one letter and one number." name="password" required>
    <label for="confirm">Confirm Password</label>
    <input class="form-control" type="password" placeholder="Confirm Password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,80}$" title="Please ensure the password is 8 characters and contains at least one letter and one number." name="confirm" required>
    <button id="submit" type="submit" class="form-control btn btn-success mt-3">Register</button>
  </form>
</div>


<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") { //get user data

  include("../Controller/registerMedical.php");
  
  $login = registerUsersM( $_POST['email'], $_POST['password']);

  if ($login == null){
    echo "Invalid Entry";
  }
  else {
    echo "Success";
    header('Location: '.'https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/login.php');
  }
    
}
?>

<?php include("templates/footer.php"); ?>
    </body>
</html>
