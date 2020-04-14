<?php

include("../Model/User.php");

function LoginMedical($email, $password){ //login functionality for medical professionals


    $check = LoginM($email, $password);

    if ($check != null){
        return $check;
    }
    else {
        return null;
    }
}


?>

