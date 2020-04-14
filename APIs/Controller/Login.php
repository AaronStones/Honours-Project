<?php

include("../Model/User.php");

$check = null;

$email = $_POST["user_name"];
$password = $_POST["password"]; //recieve data from  mobile



$check = Login($email, $password); //perform login function


echo $check;


?>

