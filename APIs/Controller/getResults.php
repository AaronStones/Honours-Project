<?php
ini_set("display_errors", 1);
error_reporting(E_ALL);

include("../Model/Readings.php");

$check = null;

$email = $_POST["email"];

echo retrieveResults($email);

?>