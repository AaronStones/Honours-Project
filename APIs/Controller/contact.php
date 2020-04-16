<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

    $from = $_POST["email"];
    $message = $_POST["message"];
    $subject = $_POST["subject"];

    // Validation
    if (!preg_match('/^(?=.{5,80}$)[\S]+@[\S]+\.[\S]+$/', $from)) // Validate email
    {
        header("location: ../View/error");
        exit;
    }

    if (!preg_match('/^.{1,1200}$/', $message)) // Validate message
    {
        header("location: ../View/error");
        exit;
    }

    // Send email to movie recommender address
    $to = "1600964@uad.ac.uk";
    $fullMessage = wordwrap("Elderly Helper - From: $from\n\n$message");
    mail($to, 'Elderly Helper - ' . $subject, $fullMessage); //change


    // Send email to sender to confirm that the message was sent
    $fullMessage = wordwrap("You sent the following message to the Elderly Helper team under the subject \"$subject\":\n\n$message");
    mail($from, 'Thank you for your feedback', $fullMessage);
    header("location: ../View/messageSent");
?>