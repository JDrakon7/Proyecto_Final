<%-- 
    Document   : password
    Created on : 4/07/2024, 9:02:43 a. m.
    Author     : JDBJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recuperar Contraseña</title>
    <link rel="stylesheet" href="css/password.css">
</head>
<body>
    <div class="forgot-password-wrap">
        <div class="forgot-password-html">
            <h2 class="title">Recuperar Contraseña</h2>
            <form id="forgot-password-form">
                <div class="group">
                    <label for="email" class="label">Email</label>
                    <input id="email" name="email" type="email" class="input" required>
                </div>
                <div class="group">
                    <button type="submit" class="button">Enviar</button>
                </div>
            </form>
            <div class="foot-lnk">
                <a href="./index.jsp">Volver al Login</a>
            </div>
        </div>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            document.getElementById('forgot-password-form').addEventListener('submit', function(event) {
                event.preventDefault();
                const formData = new FormData(this);

                fetch('ValidarCorreo', {
                    method: 'POST',
                    body: formData
                })
                .then(response => response.text())
                .then(data => {
                    if (data === "Correo validado.") {
                        window.location.href = 'cambiarpass.jsp';
                    } else {
                        alert(data);
                    }
                })
                .catch(error => console.error('Error:', error));
            });
        });
    </script>
</body>
</html>
