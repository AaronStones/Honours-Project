<?php
ini_set("display_errors", 1);
error_reporting(E_ALL);

include("../Model/Messages.php");

$check = null;

$email = $_POST["email"];

echo retrieveMessages($email);

?>