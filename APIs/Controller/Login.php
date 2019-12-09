<?php

include("../Model/User.php");

$check = null;
$email = $_POST["user_name"];
$password = $_POST["password"];

$check = Login($email, $password);
echo $check;


?>

