<%-- 
    Document   : user.jsp
    Created on : 2/07/2024, 6:14:46 p. m.
    Author     : JDBJ
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Rol de Usuario</title>
        <link rel="stylesheet" href="css/user.css">
    </head>
    <body>
        <div class="container">
            <h1>Actualizar Rol de Usuario</h1>
            <form id="roleForm">
                <div class="form-group">
                    <label for="userId">ID de Usuario:</label>
                    <input type="text" id="userId" name="userId" required>
                </div>
                <div class="form-group">
                    <label for="role">Nuevo Rol:</label>
                    <select id="role" name="role" required>
                        <option value="1">User</option>
                        <option value="2">Admin</option>
                    </select>
                </div>
                <button type="submit" class="btn">Actualizar Rol</button>
            </form>
            <br><br>
            <div class="group centered">
                <a href="javascript:history.back()" class="back-button">Volver</a>
            </div>
        </div>


        <script>
            document.getElementById('roleForm').addEventListener('submit', function (event) {
                event.preventDefault();  // Evita el envío del formulario para manejarlo con JavaScript

                const userId = document.getElementById('userId').value;
                const role = document.getElementById('role').value;

                const xhr = new XMLHttpRequest();
                xhr.open("POST", "actualizarRol", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        alert(xhr.responseText);  // Muestra la respuesta del servlet
                        if (xhr.responseText.includes("exitosamente")) {
                            window.location.href = 'users.jsp';  // Redirige después del mensaje de éxito
                        }
                    }
                };
                // Enviar los datos utilizando concatenación de cadenas
                xhr.send("userId=" + encodeURIComponent(userId) + "&newRole=" + encodeURIComponent(role));
            });
        </script>
    </body>
</html>