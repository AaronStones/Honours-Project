<?php

include("../Model/Messages.php");

$check = null;
$email = $_POST["email"];
$message = $_POST["message"]; //recieve the mobile data

sendMessageP($email, $message); //send the message

?>