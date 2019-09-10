<?php
include("connection.php");
$db = new dbObj();
$conn =  $db->getConnstring();


function Login($email, $pword){
    

    global $conn;

    $sql = "SELECT * FROM Honours.JSON WHERE email ='stones@gmail.com'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        // output data of each row
        while($row = $result->fetch_assoc()) {
            if (!password_verify($pword, $row['password']))
            {
                return null; // Password does not match hash
            }
            else {
                return json_encode(array(
                    'id' => $row['Primary_Key'],
                    'email' => $row['email'],
                    'admin' => $row['JSON']));
                }
        }
    } 


    /*$stmt = $mysqli->prepare("SELECT * FROM Honours.JSON WHERE email ='?'");
    $stmt->bind_param("s", $email);
    $stmt->execute();
    $result = $stmt->get_result();
    if($result->num_rows === 0) 
    {
        return null;
    }
    while($row = $result->fetch_assoc()) {
        $ids[] = $row['id'];
        $names[] = $row['name'];
        $ages[] = $row['age'];
    }
    var_export($ages);
    $stmt->close();*/

}

?>