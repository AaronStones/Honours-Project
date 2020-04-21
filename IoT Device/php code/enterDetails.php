<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/css/index.css">
    <?php include("https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/templates/header.php"); ?>
</head>
    <body>
        <?php include("https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/templates/nav.php"); ?>

            <div class="header">
                <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/index"><h1>Honours Project</h1></a>
                <h3>Aaron Stones | BSc Computing | Abertay University</h3> <!-- A simple page where the user is directed to view the website-->
            </div>
<h1>Enter Your Test Results</h1>
<div class="container my-3">
  <form method="post">

    <label for="email">Email</label>
    <input class="form-control" type="text" placeholder="Enter Email" name="email" required>

    <label for="doctor">Doctor</label>
    <input class="form-control" type="text" placeholder="Enter Doctor" name="doctor" required>

    <label for="hr">Heart Rate</label>
    <input class="form-control" placeholder="Enter your HR" name="hr" value="<?=shell_exec('sudo /home/pi/Submission2/Driver_Test_App/a.out getRandHR');?>"required>

    <label for="sys">Systolic Blood Pressure</label>
    <input class="form-control" placeholder="Enter your Systolic BP" name="sys" value="<?=shell_exec('sudo /home/pi/Submission2/Driver_Test_App/a.out getRandSYS');?>"required>

    <label for="dys">Dystolic Blood Pressure</label>
    <input class="form-control" placeholder="Enter your Dystolic BP" name="dys" value="<?=shell_exec('sudo /home/pi/Submission2/Driver_Test_App/a.out getRandDYS');?>"required>

    <label for="weight">Weight</label>
    <input class="form-control" placeholder="Enter your Weight" name="weight" required>

    <label for="bp">Temperature</label>
    <input class="form-control" placeholder="Enter your temperature" name="temperature" value="<?=shell_exec('sudo /home/pi/Submission2/Driver_Test_App/a.out getRandTemp');?>"required>

    <input class="form-control" type="hidden" name="count" value="<?=shell_exec('sudo /home/pi/Submission2/Driver_Test_App/a.out getRandShake');?>"required>

    

    <button type="submit" class="form-control btn btn-success mt-3">Enter Results</button>
  </form>
</div>
<?php


if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["email"])){
    $var = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/iotRecorder.php?email=". $_POST['email'] . "&count=" . $_POST['count'] . "&doctor=" . $_POST['doctor'] . "&hr=" . $_POST['hr'] . "&sys=" . $_POST['sys'] . "&dys=" . $_POST['dys'] . "&weight=" . $_POST['weight'] . "&temperature=" . $_POST['temperature'];
    $var = str_replace(" ", "%20", $var);


    include($var);


}

?>

<?php include("https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/templates/footer.php"); ?>
    </body>
</html>
