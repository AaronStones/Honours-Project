<!DOCTYPE html>
<?php
session_start();
if (isset($_SESSION['userData']) == false){
    header('Location: '.'https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/login.php');
}
?>

<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/View/css/index.css">
    <?php include("templates/header.php"); ?>
</head>
    <body>
        <?php include("templates/nav.php"); ?>

            <div class="header">
                <a href="index"><h1>Honours Project</h1></a>
                <h3><a href="results">View Patient Results</a> | <a href="advice">Give Advice to Patients</a> | <a href="messenger">Patient Chats</a></h3>
            </div>
            

            
        <?php include("templates/footer.php"); ?>
    </body>
</html>