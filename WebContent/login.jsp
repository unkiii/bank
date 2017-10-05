<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Bank | Login</title>
<script type="text/javascript">
		$(document).ready(function() {
    		setTimeout(function() {
        	$(".content").fadeOut(1500);
    		},1500);
		});
	</script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">
<%
int incorrecte;
try{
	incorrecte = Integer.parseInt(session.getAttribute("correcte").toString());
}catch(Exception e){
	incorrecte = 0;
}


%>
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">Bank</h1>

            </div>
            <h3>Bienvenido a Bank</h3>
            <p>El mejor banco con las mejores posibilidades!
                <!--Continually expanded and constantly improved Inspinia Admin Them (IN+)-->
            </p>
            <br>
            <p>Introduce tus datos para acceder a tus cuentas.</p>
            <form class="m-t" role="form" method="Post" action="login" name="frmLogin">
                <div class="form-group">
                    <input type="text" name="dni" class="form-control" placeholder="DNI" required="">
                   
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="Contraseña" required="">
                   
                </div>
                <button type="submit" value="login" class="btn btn-primary block full-width m-b">Login</button>
                

                <a href="#"><small>Has perdido la contraseña?</small></a>
                <p class="text-muted text-center"><small>Todavía no tienes una cuenta?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="register.html">Crea una cuenta</a>
            </form>
            </div>
  <% if(incorrecte == 1){ %>
  	<div class="content" id="alerta">
  	<br>
  		Error, <strong>fail </strong>
  	</div>
  <%} %>
</div>

	<script src="/js/login.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>    
    
            <p class="m-t"> <small>I'm the best &copy; 2017</small> </p>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
