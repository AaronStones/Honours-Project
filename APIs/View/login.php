
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <?php include("templates/header.php"); ?>
</head>
    <body>

<!-- Start of page content -->

<div class="container my-3">
  <h1>Login</h1>
  <p>Please enter your details on this page to login to your account or <a class="link" href="signup">register as a new user</a> if you do not already have an account.</p>
  <hr>
  <div id="response"></div> <!-- Response will go here -->
  <form method="post">

    <label for="email">Email</label>
    <input class="form-control" type="text" placeholder="Enter Email" name="email" required>

    <label for="password">Password</label>
    <input class="form-control" type="password" placeholder="Enter Password" name="password" required>

    <button type="submit" class="form-control btn btn-success mt-3">Login</button>
  </form>
  <?php
if ($_SERVER["REQUEST_METHOD"] == "POST") { //get user data

  include("../Controller/medicalLogin.php");


  $login = LoginMedical( $_POST['email'], $_POST['password']);

  if ($login == null){
    echo "Invalid Entry";
  }
  else {
    session_start();
    $_SESSION['userData'] = $login;
    
    echo "Success";

    header('Location: '.'https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/landing');
  }
    
}
?>
</div>

<!-- End of page content -->
<?php include("templates/footer.php"); ?>

<!-- Start of AJAX script -->



<!-- End of AJAX script -->
</body>
</html>
