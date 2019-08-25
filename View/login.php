<!DOCTYPE html>
<html lang="en">
<head>
    <?php include("templates/header.php"); ?>
    <link rel="stylesheet" type="text/css" href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/View/css/login.css">
</head>
    <body>
        <?php include("templates/nav.php"); ?>

        <div style="margin-top: 100px;" class="container">
            <div class="d-flex justify-content-center h-100">
                <div class="card">
                    <div class="card-header">
                        <h3>Sign In</h3>
                    </div>
                    <div class="card-body">
                        <form action="../controller/login.php" method="post">
                        <div style="margin-left: auto; margin-right: auto;" class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" name="email" class="form-control" placeholder="Username" required>
                                
                            </div>
                            <div style="margin-left: auto; margin-right: auto;" class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" name="password" class="form-control" placeholder="Password" required>
                            </div>
                            <div class="form-group">
                                <input type="submit" value="Login" class="btn float-right login_btn">
                            </div>
                        </form>
                            <script>
                            $('form').on('submit', function(e)
                            {
                                e.preventDefault(); // Prevent default form behaviour so we can override it

                                $('#submit').prop('disabled', true); // Disable submit button to prevent multiple submissions

                                $.post('../Controller/login.php', $('form').serialize(), function(data)
                                {
                                let response = '<div class="alert alert-' + (data.success ? 'success' : 'danger') + '">' + data.message + '</div>';
                                $('#response').html(response); // Display response message

                                if (data.success)
                                {
                                    location.href = 'index'; // Success, redirect to home page
                                }
                                else
                                {
                                    // Failure, enable submit button to allow resubmission
                                    $('#submit').prop('disabled', false);
                                }
                                }, 'json');
                            });
                            </script>
                    </div>
                    <div class="card-footer">
                        <div class="d-flex justify-content-center links">
                            Don't have an account?<a href="#">Sign Up</a>
                        </div>
                        <div class="d-flex justify-content-center">
                            <a href="#">Forgot your password?</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <?php include("templates/footer.php"); ?>
    </body>
</html>