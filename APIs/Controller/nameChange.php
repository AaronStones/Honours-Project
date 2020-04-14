<?php


include("../Model/User.php");

$check = null;

$email = $_POST["email"];
$file = $_POST["file"]; //recieve data from mobile

$check = Name($email, $file); //change a user's name
echo $check;

?>