<%-- 
    Document   : perfil
    Created on : 4/07/2024, 9:13:12 a. m.
    Author     : JDBJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actualizar Perfil</title>
    <link rel="stylesheet" href="css/perfil.css">
</head>
<body>
    <div class="profile-wrap">
        <div class="profile-html">
            <h2>Actualizar Perfil</h2>
            <form id="profile-form">
                <div class="group">
                    <label for="username" class="label">Nombre de usuario</label>
                    <input id="username" type="text" class="input" name="username" required>
                </div>
                <div class="group">
                    <label for="email" class="label">Email</label>
                    <input id="email" type="email" class="input" name="email" required>
                </div>
                <div class="group">
                    <label for="password" class="label">Contraseña</label>
                    <input id="password" type="password" class="input" name="password" required>
                </div>
                <div class="group">
                    <label for="confirm-password" class="label">Confirmar Contraseña</label>
                    <input id="confirm-password" type="password" class="input" required>
                </div>
                <div class="group">
                    <input type="submit" class="button" value="Actualizar">
                </div>
                <div id="message"></div>
            </form>
            <div class="foot-lnk">
                <a href="index.jsp">Volver al Login</a>
            </div>
        </div>
    </div>
    <script src="js/profile.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>


