<?php
	include("conn.php");
	$db = new dbObj();
	$conn =  $db->getConnstring();


    function recordReading($email, $doctor, $count, $weight, $temp, $hr, $dys, $sys){
        global $conn;
        $value = 0;
        $sql = $conn->prepare("INSERT INTO Results (email, Doctor, Result, HR, Weight, Temperature, Sys, Dys) Values(?,?,?,?,?,?,?,?)");
        $sql->bind_param("ssiiiiii", $email, $doctor, $count, $hr, $weight, $temp, $sys, $dys);
        $sql->execute();

        $affectedRows = $sql->affected_rows;

            if ($affectedRows == 1) {
                return "Test Complete!";
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
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $Result,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }

    function retrieveAll($email){
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'result' => $Result,
                'hr' => $BPM,
                'weight' => $weight,
                'temp' => $Temp,
                'sys' => $Sys,
                'dys' => $Dys,
                'time' => $time);
            $count++;
        }
        return json_encode($json);
    }

    function retrieveBPM($email){
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

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
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $weight,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }

    function retrieveTemp($email){
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $Temp,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }

    function retrieveSys($email){
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $Sys,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }
    function retrieveDys($email){
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $Dys,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }

?>