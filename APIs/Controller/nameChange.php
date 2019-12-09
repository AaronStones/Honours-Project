<?php
ini_set("display_errors", 1);
error_reporting(E_ALL);

include("../Model/User.php");

$check = null;

$email = $_POST["email"];
$file = $_POST["file"];

$check = Name($email, $file);
echo $check;

?>