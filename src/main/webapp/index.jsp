<%-- 
Document   : index
Created on : 25/04/2024, 1:49:11 p. m.
Author     : JDBJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Login</title>
    </head>

    <body>
        <div class="login-wrap">
            <div class="login-html">
                <div class="img">
                    <img class="header__icon" src="icono.png" alt="Icono del sitio web">
                </div>
                <div class="login-form">
                    <form id="form" action="login" method="POST">
                        <div class="group">
                            <label for="user" id="user" class="label">Usuario</label>
                            <input id="user" type="text" class="input" name="email">
                        </div>
                        <div class="group">
                            <label for="pass" id="password" class="label">Password</label>
                            <input id="pass" type="password" class="input" data-type="password" name="password">
                        </div>
                        <div class="group">
                            <input type="submit" class="button" value="Ingresar">
                        </div>
                        <div class="hr"></div>
                        <div class="foot-lnk">
                            <a href="./password.jsp">¿Olvido su contraseña?</a>
                        </div>
                        <div class="foot-lnk">
                            <a href="registro.jsp">¿No tienes cuenta? registrate aqui</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/login.js"></script>
    </body>
</html>
