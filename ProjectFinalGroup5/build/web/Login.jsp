<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <title>Login Form</title>
    </head>
    <body>
        <style>
        body {
            background-image: url('images/fashion5.jpeg'); 
            background-size: cover; 
            background-repeat: no-repeat; 
            background-position: center; 
        }
    </style>
        <jsp:include page="Menu.jsp"></jsp:include>

            <div id="logreg-forms" style="background-color: #999; padding: 20px;">
    <div class="alert" role="alert">
        <p style="color: red;">${error}</p>
    </div>
    
    <form class="form-signin" action="login" method="post">
        <h1 class="h3 mb-3 font-weight-normal" style="text-align: center; color: whitesmoke;"> Sign in</h1>  
        <input name="user" value="${username }"  type="text" id="inputEmail" class="form-control mb-2" placeholder="Username" required autofocus>
        <input name="pass" value="${password }" type="password" id="inputPassword" class="form-control mb-2" placeholder="Password" required>

        <div class="form-group form-check mb-2">
            <input name="remember" value="1" type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1" style="color: white;">Remember account</label>
        </div>

        <button class="btn btn-dark btn-block mb-2" type="submit"><i class="fas fa-sign-in-alt"></i> Sign in</button>
        <hr>
        <button class="btn btn-success btn-block" type="button" id="btn-signup"><i class="fas fa-user-plus"></i> Register</button>
    </form>

    <form action="signup" method="post" class="form-signup">
        <h1 class="h3 mb-3 font-weight-normal" style="text-align: center; color: #333;"> Sign up</h1>
        <input name="user" type="text" id="user-name" class="form-control mb-2" placeholder="User name" required autofocus>
        <input name="pass" type="password" id="user-pass" class="form-control mb-2" placeholder="Password" required autofocus>
        <input name="repass" type="password" id="user-repeatpass" class="form-control mb-2" placeholder="Repeat Password" required autofocus>
        <input name="email" type="email" id="email" class="form-control mb-2" placeholder="Email" required autofocus>
        <button class="btn btn-dark btn-block" type="submit"><i class="fas fa-user-plus"></i> Register</button>
        <a href="#" id="cancel_signup" style="color: #333;"><i class="fas fa-angle-left"></i> Back</a>
    </form>
</div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script>
            function toggleResetPswd(e) {
                e.preventDefault();
                $('#logreg-forms .form-signin').toggle() // display:block or none
                $('#logreg-forms .form-reset').toggle() // display:block or none
            }

            function toggleSignUp(e) {
                e.preventDefault();
                $('#logreg-forms .form-signin').toggle(); // display:block or none
                $('#logreg-forms .form-signup').toggle(); // display:block or none
            }

            $(() => {
                // Login Register Form
                $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
                $('#logreg-forms #cancel_reset').click(toggleResetPswd);
                $('#logreg-forms #btn-signup').click(toggleSignUp);
                $('#logreg-forms #cancel_signup').click(toggleSignUp);
            })

            window.addEventListener("load", function loadAmountCart() {
                $.ajax({
                    url: "/WebsiteBanGiay/loadAllAmountCart",
                    type: "get", //send it through get method
                    data: {

                    },
                    success: function (responseData) {
                        document.getElementById("amountCart").innerHTML = responseData;
                    }
                });
            }, false);
        </script>
    </body>
</html>