<%-- 
Document   : index
Created on : 25/04/2024, 1:49:11 p.m.
Author     : JDBJ
--%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Login</title>
    </head>
    <body>
        <main>
            <section class="login-wrap">
                <article class="login-html">
                    <div class="img">
                    <img class="header__icon" src="icono.png" alt="Icono del sitio web">
                </div>
                <div class="login-form">
                    <form action="login" method="POST">
                        <div class="group">
                            <label for="user" class="label">Usuario</label>
                            <input id="user" type="text" class="input" name="email">
                        </div>
                        <div class="group">
                            <label for="pass" class="label">Password</label>
                            <input id="pass" type="password" class="input" name="password">
                        </div>
                        <div class="group">
                            <input type="submit" class="button" value="Ingresar">
                        </div>
                        <div class="hr"></div>
                        <div class="foot-lnk">
                            <a href="./password.jsp">¿Olvidó su contraseña?</a>
                        </div>
                        <div class="foot-lnk">
                            <a href="registro.jsp">¿No tienes cuenta? Regístrate aquí?</a>
                        </div>
                    </form>
                </div>
                </article>
            </section>
        </main>
        <footer>
            <p>&copy; 2024 Proyecto Masterbot.</p>
        </footer>
        <script src="js/login.js" type="module"></script>
    </body>
</html>
