<?php
include("../Model/User.php");
    function registerUsersM($email, $password){
        $check = registerUser($email, $password);

        if ($check != null){
            return $check;
        }
        else {
            return "failed";
        }
    }
?>