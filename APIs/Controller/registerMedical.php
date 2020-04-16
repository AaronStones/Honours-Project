<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

include("../Model/User.php");

function registerUsersM($email, $password, $name){ //register a new medical professional

    $check = registerUser($email, $password, $name);

    if ($check != null){ //if the registration has succeeeded 
         return $check;
    }
    else {
        return "failed";
    }
}
?>