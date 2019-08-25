<?php
    //header('Content-Type', 'application/json');
    //include_once("../Model/user.php");
    echo $_POST['email'];
    /*$user = Login($_POST['email'], $_POST['password']); //Login function

    if ($user != null) //If User is logged in
    {
        $user = json_decode($user);
        echo json_encode(array('success' => true, 'message' => 'Login Successful. Redirecting...'));
    }
    else //if User is not logged in
    {
        echo json_encode(array('success' => false, 'message' => 'Incorrect email or password.'));
    }*/


    function debug_to_console($data) {
        $output = $data;
        if (is_array($output))
            $output = implode(',', $output);
    
        echo "<script>alert('Debug Objects: " . $output . "' );</script>";
    }
?>