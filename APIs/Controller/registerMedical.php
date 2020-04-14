<?php
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