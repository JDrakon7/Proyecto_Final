/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


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
