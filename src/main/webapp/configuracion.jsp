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

   
    <script> 
    
document.addEventListener('DOMContentLoaded', (event) => {
    obtenerInformacionUsuario();
});

// Función para cargar la información del usuario
function obtenerInformacionUsuario() {
    fetch('/UsuarioServlet?action=obtenerInformacionUsuario', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        const usuarioInfoDiv = document.getElementById('usuario-info');
        usuarioInfoDiv.innerHTML = `
            <p><strong>Nombre:</strong> ${data.nombre}</p>
            <p><strong>Email:</strong> ${data.email}</p>
            <p><strong>Usuario:</strong> ${data.usuario}</p>
        `;
    })
    .catch(error => console.error('Error al cargar la información del usuario:', error));
}

// Función para mostrar el modal de confirmación
function confirmarEliminacion() {
    document.getElementById('confirm-modal').style.display = 'block';
}

// Función para cerrar el modal
function cerrarModal() {
    document.getElementById('confirm-modal').style.display = 'none';
}

// Función para eliminar la cuenta del usuario
function eliminarCuenta() {
    fetch('/UsuarioServlet?action=eliminarCuenta', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        // Redirigir al usuario a la página de inicio de sesión o inicio
        window.location.href = 'login.jsp';
    })
    .catch(error => console.error('Error al eliminar la cuenta:', error));
}
</script>
</body>
</html>
