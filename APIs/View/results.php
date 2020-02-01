<!DOCTYPE html>
<?php
session_start();
if (isset($_SESSION['userData']) == false){
    header('Location: '.'https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/login.php');
}
?>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/View/css/index.css">
    <?php include("templates/header.php"); ?>
</head>
    <body>
        <?php include("templates/nav.php"); ?>

            <div class="header">
                <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/View/index"><h1>Patient Results</h1></a>
                <h3><a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/landing.php">Home</a> | <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/advice.php" >Give Advice to Patients</a> | <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/messenger">Patient Chats</a></h3>
            </div>
            <h4>Select a patient to view their results</h4>
            <form method="post">

                <label for="email">Email</label>
            <input class="form-control" type="text" placeholder="Enter Email" title="Please ensure the email address is valid." name="email" required>   
            <button id="submit" type="submit" class="form-control btn btn-danger mt-3">Select Patient</button>

            </form>
            <?php
            if ($_SERVER["REQUEST_METHOD"] == "POST") { //get user data

                include("../Controller/getResultsM.php");
                
              
                $results = getReadings($_POST['email']);
                $results = json_decode($results);

                if ($results == null){
                  echo "Invalid Entry";
                }
                
                else {
                    for ($i=0; $i<sizeof($results);$i++){
                        echo $results[$i]->result . "  " . $results[$i]->time . "</br>";
                    }
                }
            }

            ?>

            <?php
            
            $dataPoints = array(
                array("y" => 25, "label" => "Sunday"),
                array("y" => 15, "label" => "Monday"),
                array("y" => 25, "label" => "Tuesday"),
                array("y" => 5, "label" => "Wednesday"),
                array("y" => 10, "label" => "Thursday"),
                array("y" => 0, "label" => "Friday"),
                array("y" => 20, "label" => "Saturday")
            );
            
            ?>

            <script>
            window.onload = function () {
            
            var chart = new CanvasJS.Chart("chartContainer", {
                title: {
                    text: "Push-ups Over a Week"
                },
                axisY: {
                    title: "Number of Push-ups"
                },
                data: [{
                    type: "line",
                    dataPoints: <?php echo json_encode($dataPoints, JSON_NUMERIC_CHECK); ?>
                }]
            });
            chart.render();
            
            }
            </script>
            </head>
            <body>
            <div id="chartContainer" style="height: 370px; width: 100%;"></div>
            <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
        <?php include("templates/footer.php"); ?>
    </body>
</html>