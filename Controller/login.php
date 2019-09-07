<?php
    header('Content-Type', 'application/json');

    include_once("../Model/user.php");
    $user = Login($_POST['email'], $_POST['password']); //Login function

    if ($user != null) //If User is logged in
    {
        session_start();
        $user = json_decode($user);
        $_SESSION['user'] = $user;
        echo json_encode(array('success' => true, 'message' => 'Login Successful. Redirecting...'));
    }
    else //if User is not logged in
    {
        echo json_encode(array('success' => false, 'message' => 'Incorrect email or password.'));
    }
?>