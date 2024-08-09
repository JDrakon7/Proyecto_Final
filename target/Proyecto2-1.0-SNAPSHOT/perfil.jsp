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
       <%
    // Verificar si el usuario está autenticado
    if (session == null || session.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    // Configurar las cabeceras de no caché
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.
%>
    <div class="profile-wrap">
        <div class="profile-html">
            <h2>Actualizar Perfil</h2>
            <form id="profile-form" method="POST">
                <input type="hidden" id="userId" name="userId>
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
                    <input id="password" type="password" class="input" name="password">
                </div>
                <div class="group">
                    <label for="confirm-password" class="label">Confirmar Contraseña</label>
                    <input id="confirm-password" type="password" class="input">
                </div>
                <div class="group">
                    <input type="submit" class="button" value="Actualizar">
                </div>
                <div id="message"></div>
            </form>
            <div class="foot-lnk">
                <a href="interfaz.jsp">Volver</a>
            </div>
        </div>
    </div>
    <script>
        document.getElementById('profile-form').addEventListener('submit', function (event) {
            event.preventDefault(); // Prevenir el envío del formulario

            if (!validateForm()) {
                return; // Si la validación falla, no continuar
            }

            const formData = new FormData(this); // Obtener los datos del formulario

            fetch('http://localhost:8080/actualizar_perfil', {
                method: 'POST',
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                const message = document.getElementById('message');
                if (data.success) {
                    message.innerHTML = '<p>Perfil actualizado exitosamente.</p>';
                } else {
                    message.innerHTML = '<p>Error al actualizar el perfil: ' + data.message + '</p>';
                }
            })
            .catch(error => {
                const message = document.getElementById('message');
                message.innerHTML = '<p>Error al actualizar el perfil.</p>';
                console.error('Error:', error);
            });
        });

        function validateForm() {
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("confirm-password").value;

            if (password && password !== confirmPassword) {
                alert("Las contraseñas no coinciden.");
                return false;
            }
            return true;
        }
    </script>
</body>
</html>
