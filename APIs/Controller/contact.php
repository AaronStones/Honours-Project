<?php
    $from = $_POST["email"];
    $message = $_POST["message"];
    $subject = $_POST["subject"];

    // Validation
    if (!preg_match('/^(?=.{5,80}$)[\S]+@[\S]+\.[\S]+$/', $from)) // Validate email
    {
        header("location: ../view/error");
        exit;
    }

    if (!preg_match('/^.{1,1200}$/', $message)) // Validate message
    {
        header("location: ../view/error");
        exit;
    }

    // Send email to movie recommender address
    $to = "1600964@uad.ac.uk";
    $fullMessage = wordwrap("Elderly Helper - From: $from\n\n$message");
    mail($to, 'Elderly Helper - ' . $subject, $fullMessage);


    // Send email to sender to confirm that the message was sent
    $fullMessage = wordwrap("You sent the following message to the Elderly Helper team under the subject \"$subject\":\n\n$message");
    mail($from, 'Thank you for your feedback', $fullMessage);

    header("location: ../view/support");
?>