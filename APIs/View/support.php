<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <?php include("templates/header.php"); ?>
</head>
    <body>
        <?php include("templates/nav.php"); ?>
        <h1>Support</h1>
<!-- Start of page content -->

<div class="container my-3" role="main">
    <div class="row">
        <div class="col-md-6 col-sm-12">
            <h2>Frequently Asked Questions</h2>

            <h6>Lorem ipsum sit dolar amet?</h6>
            <p class="small">
            Nulla quis consectetur augue. Cras eget augue eget ipsum dictum hendrerit
            non vel mauris. Duis lacinia egestas porttitor. Aliquam eleifend enim sem,
            quis facilisis ipsum faucibus a. In non semper dui, eget consectetur eros.
            </p>

            <h6>Nullam vehicula arcu eu pulvinar sodales. Donec commodo dictum magna?</h6>
            <p class="small">
            Aliquam et maximus nisl. Morbi sollicitudin fermentum eros, quis mollis
            elit. Donec semper mattis purus, lobortis ultrices felis. Donec ut viverra
            nisl. Duis maximus et nisi gravida malesuada. Vestibulum elementum augue ut
            rutrum egestas.
            </p>

            <h6>Aliquam et maximus nisl. Morbi sollicitudin fermentum eros, quis mollis elit?</h6>
            <p class="small">
            Pellentesque finibus vel nisi non pulvinar. Nullam eu nunc vitae libero
            eleifend gravida. Suspendisse ullamcorper pharetra elit eget rhoncus. Nullam
            vel scelerisque diam. Curabitur sit amet lorem sem. Nunc dictum turpis eget
            dapibus porttitor.
            </p>

            <h6>In sed tellus in neque imperdiet eleifend ac non eros?</h6>
            <p class="small">
            Vestibulum in orci malesuada lacus sodales lacinia. Suspendisse potenti.
            Phasellus enim tortor, tincidunt ornare tortor in, finibus feugiat libero.
            Integer tellus risus, ultrices eu ipsum ut, auctor laoreet metus.
            </p>
        </div>
        <div class="col-md-6 col-sm-12">
            <h2>Contact Us</h2>
            <form action="../controller/contact.php" method="POST">
                <label for="subject">Subject</label>
                <select class="form-control" name="subject">
                    <option value="Feedback">Feedback</option>
                    <option value="Report">Bug Report</option>
                    <option value="Complaint">Complaint</option>
                    <option value="Other">Other</option>
                </select>
                <label for="email">Email Address</label>
                <input class="form-control" type="email" name="email" required pattern="^(?=.{5,80}$)[\S]+@[\S]+\.[\S]+$">
                <label for="message">Message</label>
                <textarea class="form-control" name="message" rows="6" required pattern="^.{1,1200}$"></textarea>
                <input class="form-control btn btn-danger mt-3" type="submit" value="Submit">
            </form>
        </div>
    </div>
</div>

<!-- End of page content -->
<?php include("templates/footer.php"); ?>
</body>
</html>