<?php

include("../Model/User.php");

function LoginMedical($email, $password){


    $check = LoginM($email, $password);

    if ($check != null){
        return $check;
    }
    else {
        return null;
    }
}


?>

