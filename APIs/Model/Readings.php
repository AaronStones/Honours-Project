<?php
	include("conn.php");
	$db = new dbObj();
	$conn =  $db->getConnstring();


    function recordReading($email, $result, $doctor){
        global $conn;
        $value = 0;
        $sql = $conn->prepare("INSERT INTO Results (email, Doctor, Result) Values(?,?,?)");
        $sql->bind_param("ssi", $email, $doctor, $result);
        $sql->execute();

        $affectedRows = $sql->affected_rows;

            if ($affectedRows == 1) {
                return "Success";
            } 
            else {
                return null;
            }
    }

    function retrieveResults($email){
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $Result,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }

    function retrieveBPM($email){
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $BPM,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }

    function retrieveWeight($email){
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $weight,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }

?>