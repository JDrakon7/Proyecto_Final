<%-- 
    Document   : configuracion
    Created on : 31/07/2024, 11:39:04 p. m.
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Configuración del Usuario</title>
    <link rel="stylesheet" href="css/configuracion.css">
</head>
<body>
    <div class="content-wrap">
        <div class="content-html">
            <h2>Configuración del Usuario</h2>
            <div id="usuario-info" class="usuario-info">
                <!-- Aquí se mostrará la información del usuario -->
            </div>
            <div class="group centered">
                <button onclick="confirmarEliminacion()" class="button eliminar">Eliminar Cuenta</button>
            </div>
        </div>
    </div>

    <!-- Modal de confirmación -->
    <div id="confirm-modal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="cerrarModal()">&times;</span>
            <p>¿Estás seguro de que deseas eliminar tu cuenta?</p>
            <button onclick="eliminarCuenta()" class="button eliminar">Sí, eliminar cuenta</button>
            <button onclick="cerrarModal()" class="button">Cancelar</button>
        </div>
    </div>

    <script src="js/configuracion.js"></script>
</body>
</html>
