<%-- 
    Document   : cambiarpass
    Created on : 4/07/2024, 10:35:05 a. m.
    Author     : JDBJ
--%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Restablecer Contraseña</title>
        <link rel="stylesheet" href="css/password.css">
    </head>
    <body>
        <div class="reset-password-wrap">
            <div class="reset-password-html">
                <h2 class="title">Restablecer Contraseña</h2>
                <form id="reset-password-form" action="RecuperarContrasena" method="post">
                    <input type="hidden" name="token" value="<%= request.getParameter("token") %>">
                    <div class="group">
                        <label for="password" class="label">Nueva Contraseña</label>
                        <input id="password" name="password" type="password" class="input" required>
                    </div>
                    <div class="group">
                        <label for="confirmar_password" class="label">Confirmar Contraseña</label>
                        <input id="confirmar_password" name="confirmar_password" type="password" class="input" required>
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="Enviar">
                    </div>
                </form>
                <div class="foot-lnk">
                    <a href="./index.jsp">Volver al Login</a>
                </div>
            </div>
        </div>
        <script src="js/cambiar_password.js"></script>
    </body>
</html>


