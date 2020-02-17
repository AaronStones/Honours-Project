<!DOCTYPE html>
<?php
session_start();
if (isset($_SESSION['userData']) == false){
    header('Location: '.'https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/login.php');
}
?>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/css/index">
    <?php include("templates/header.php"); ?>
</head>
    <body>
        <?php include("templates/nav.php"); ?>

            <div class="header">
                <a href="index"><h1>Patient Results</h1></a>
                <h3><a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/landing">Home</a> | <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/advice" >Give Advice to Patients</a> | <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/messenger">Patient Chats</a></h3>
            </div>
            <h4>Select a patient to view their results</h4>
            <form method="post">

                <label for="email">Email</label>
            <input class="form-control" type="text" placeholder="Enter Email" title="Please ensure the email address is valid." name="email" required>   
            <button id="submit" type="submit" class="form-control btn btn-success mt-3">Select Patient</button>

            </form>
            <?php
            if ($_SERVER["REQUEST_METHOD"] == "POST") { //get user data

                include("../Controller/getResultsM.php");
                
                $results = getReadings($_POST['email']);

                include("../Controller/getBPM.php");

                $results2 = getBPM($_POST['email']);

                include("../Controller/getWeight.php");

                $results3 = getWeight($_POST['email']);


                if ($results == null){
                  echo "Invalid Entry";
                }
                
                else {
                    $dataPoints = json_decode($results);
                    $dataPoints2 = json_decode($results2);
                    $dataPoints3 = json_decode($results3);
                    
                }
            }

            ?>

            <script>
            window.onload = function () {
            
            var chart = new CanvasJS.Chart("chartContainer", {
                title: {
                    text: "Hand Shake test Results"
                },
                axisY: {
                    title: "Number of registered shakes"
                },
                data: [{
                    type: "line",
                    dataPoints: <?php echo json_encode($dataPoints, JSON_NUMERIC_CHECK); ?>
                }]
            });
            chart.render();

                var chart = new CanvasJS.Chart("chartContainer1", {
                title: {
                    text: "Heart Rate"
                },
                axisY: {
                    title: "Bpm (Beats per Minute)"
                },
                data: [{
                    type: "line",
                    dataPoints: <?php echo json_encode($dataPoints2, JSON_NUMERIC_CHECK); ?>
                }]
            });
            chart.render();
            var chart = new CanvasJS.Chart("chartContainer2", {
                title: {
                    text: "Blood Pressure"
                },
                axisY: {
                    title: "Systolic Blood Pressure"
                },
                data: [{
                    type: "line",
                    dataPoints: <?php echo json_encode($dataPoints, JSON_NUMERIC_CHECK); ?>
                }]
            });
            chart.render();
            var chart = new CanvasJS.Chart("chartContainer3", {
                title: {
                    text: "Patient's Weight"
                },
                axisY: {
                    title: "Kilograms"
                },
                data: [{
                    type: "line",
                    dataPoints: <?php echo json_encode($dataPoints3, JSON_NUMERIC_CHECK); ?>
                }]
            });
            chart.render();

            var chart = new CanvasJS.Chart("chartContainer4", {
                title: {
                    text: "Patient's Temperature"
                },
                axisY: {
                    title: "Degrees Celsius"
                },
                data: [{
                    type: "line",
                    dataPoints: <?php echo json_encode($dataPoints3, JSON_NUMERIC_CHECK); ?>
                }]
            });
            chart.render();
            
            }
            </script>
                 

            </head>
            <body>
            <div id="chartContainer" style="height: 370px; width: 100%;"></div>
            <div id="chartContainer1" style="height: 370px; width: 100%;"></div>
            <div id="chartContainer2" style="height: 370px; width: 100%;"></div>
            <div id="chartContainer3" style="height: 370px; width: 100%;"></div>
            <div id="chartContainer4" style="height: 370px; width: 100%;"></div>
            <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
        <?php include("templates/footer.php"); ?>
    </body>
</html>