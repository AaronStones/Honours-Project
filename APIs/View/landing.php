<!-- 
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests
-->

<!DOCTYPE html>
<?php
session_start();
if (isset($_SESSION['userData']) == false){
    header('Location: '.'https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/login.php'); //check the user is logged in
}
?>

<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/css/index.css">
    <?php include("templates/header.php"); ?>
</head>
    <body>
        <?php include("templates/nav.php"); ?>

            <div class="header">
                <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/index"><h1>Honours Project</h1></a>
                <h3><a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/results">View Patient Results</a> | <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/advice">Give Advice to Patients</a> | <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/messenger">Patient Chats</a></h3>
            </div>
            

            
        <?php include("templates/footer.php"); ?>
    </body>
</html>