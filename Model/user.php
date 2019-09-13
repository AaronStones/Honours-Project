<?php
include("connection.php");
$db = new dbObj();
$conn =  $db->getConnstring();
ini_set("display_errors", 1);
error_reporting(E_ALL);

//function Login($email, $pword){
    
    $email = "stones@gmail.com";
    global $conn;

		
    $statement = $conn->prepare('SELECT Primary_Key, email, password, JSON FROM Honours.JSON WHERE email = :email');
    $statement->bindParam(':email', $email);
    $statement->execute();
    
    /*if ($statement->rowCount() == 0)
    {
        return null; // Email does not exist
    }
    
    $row = $statement->fetch();
    
    if (!password_verify($pword, $row['password']))
    {
        return null; // Password does not match hash
    }

    // Everything in order, return user data as JSON
    return json_encode(array(
        'id' => $row['Primary_Key'],
        'email' => $row['email'],
        'admin' => $row['JSON']));*/

//}

?>