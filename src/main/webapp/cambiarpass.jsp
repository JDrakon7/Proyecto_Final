<%-- 
    Document   : cambiarpass
    Created on : 4/07/2024, 10:35:05 a.�m.
    Author     : JDBJ
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restablecer Contrase�a</title>
    <link rel="stylesheet" href="css/password.css">
</head>
<body>
       <%
    // Verificar si el usuario est� autenticado
    if (session == null || session.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    // Configurar las cabeceras de no cach�
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.
%>
    <div class="reset-password-wrap">
        <div class="reset-password-html">
            <h2 class="title">Restablecer Contrase�a</h2>
            <form id="reset-password-form">
                <div class="group">
                    <label for="password" class="label">Nueva Contrase�a</label>
                    <input id="password" name="password" type="password" class="input" required>
                </div>
                <div class="group">
                    <label for="confirmar_password" class="label">Confirmar Contrase�a</label>
                    <input id="confirmar_password" name="confirmar_password" type="password" class="input" required>
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
            document.getElementById('reset-password-form').addEventListener('submit', function(event) {
                event.preventDefault();
                const password = document.getElementById('password').value;
                const confirmarPassword = document.getElementById('confirmar_password').value;

                if (password !== confirmarPassword) {
                    alert('Las contrase�as no coinciden.');
                    return;
                }

                const formData = new FormData(this);

                fetch('contrasena', {
                    method: 'POST',
                    body: formData
                })
                .then(response => response.text())
                .then(data => {
                    alert(data);
                    if (data === "Contrase�a cambiada exitosamente.") {
                        window.location.href = 'index.jsp';
                    }
                })
                .catch(error => console.error('Error:', error));
            });
        });
    </script>
</body>
</html>


