<%-- 
    Document   : registro.jsp
    Created on : 28/07/2024, 9:27:08 a. m.
    Author     : ASUS
--%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" type="text/css" href="css/style.css">
            <title>Registro</title>
        </head>

        <body>
            <div class="login-wrap">
                <div class="login-html">
                    <div class="img">
                        <img class="header__icon" src="icono.png" alt="Icono del sitio web">
                    </div>
                    <div class="login-form">
                        <form  id="registro-form" action="crear_usuario" method="POST">
                            <div class="group">
                                <label for="nombre" class="label">Nombre</label>
                                <input id="nombre" type="text" class="input" name="nombre">
                            </div>
                            <div class="group">
                                <label for="email" class="label">Email</label>
                                <input id="email" type="text" class="input" name="email">
                            </div>
                            <div class="group">
                                <label for="confirmar_email" class="label">Confirmar Email</label>
                                <input id="confirmar_email" type="text" class="input" name="confirmar_email">
                            </div>
                            <div class="group">
                                <label for="password" class="label">Contraseña</label>
                                <input id="password" type="password" class="input" name="password">
                            </div>
                            <div class="group">
                                <label for="confirmar_password" class="label">Confirmar Contraseña</label>
                                <input id="confirmar_password" type="password" class="input" name="confirmar_password">
                            </div>
                            <div class="group">
                                <input type="submit" class="button" value="Enviar">
                            </div>
                            <div class="hr"></div>
                            <div class="foot-lnk">
                                <a href="index.jsp">¿Ya está registrado?</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </body>
        <script src="js/registro.js" type="module"></script>
    </html>
