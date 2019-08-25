<?php
include("connection.php");
$db = new dbObj();
$conn =  $db->getConnstring();

function Login($email, $pword){
    $stmt = $conn->prepare("SELECT * FROM Honours.JSON WHERE email = :email");
    $stmt->bind_param('$email', $email);
    $stmt->execute();

    if ($stmt->rowCount() == 0){
        return null;
    }

    $row = $stmt->fetch();

    if (!password_verify($pword, $row['password']))
    {
        return null;
    }
    return json_encode(array(
        'id' => $row['Primary_Key'],
        'email' => $row['email'],
        'data' => $row['JSON']));

}
?>