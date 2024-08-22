<%-- 
    Document   : user.jsp
    Created on : 2/07/2024, 6:14:46 p. m.
    Author     : JDBJ
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Actualizar Rol de Usuario</title>
    <link rel="stylesheet" href="css/user.css">
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
    <div class="container">
        <h1>Actualizar Rol de Usuario</h1>
        <form id="roleForm">
            <div class="form-group">
                <label for="user">Seleccionar Usuario:</label>
                <select id="user" name="user">
                    <option value="">Seleccionar Usuario</option>
                </select>
            </div>
            <div class="form-group">
                <label for="role">Nuevo Rol:</label>
                <select id="role" name="role">
                    <option value="1">User</option>
                    <option value="2">Admin</option>
                    <option value="4">Inhabilitado</option>
                </select>
            </div>
            <button type="submit" class="btn">Actualizar Rol</button>
        </form>
        <br><br>
        <div class="group centered">
            <a href="javascript:history.back()" class="back-button">Volver</a>
        </div>
    </div>
</body>
<script src="js/users.js"></script>
</html>
