<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

include("../Model/User.php");

function registerUsersM($email, $password, $name){ //register a new medical professional


    $error_message = "";
    $email_exp = '/^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/';

    if (!preg_match($email_exp, $email))
        $error_message .= 'The Email Address you entered does not appear to be valid.<br />';
    if (strlen($password) < 8)
        $error_message .= 'You have entered too weak a password, please ensure the password is 8 characters and contains at least one letter and number. <br />';
    if (!preg_match("#[0-9]+#", $password))
        $error_message .= 'Password must include at least one number. <br />';
    if (!preg_match("#[a-zA-Z]+#", $password))
         $error_message .= 'Password must include at least one letter. <br />'; 

    if(strlen($error_message) > 0){
        return $error_message;
    }

    else{
        $check = registerUser($email, $password, $name);

        if ($check != null){ //if the registration has succeeeded 
            return $check;
        }
        else {
            return "invalid entry";
        }  
    }
}
?>