<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

include("../Model/Messages.php");

$check = null;
$email = $_POST["email"];
$message = $_POST["message"]; //recieve the mobile data

sendMessageP($email, $message); //send the message

?>