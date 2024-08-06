/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function() {
    let form = document.getElementById('login-form');
    if (form) {
        form.addEventListener('submit', function(event) {
            var email = document.getElementById('user').value.trim();
            var password = document.getElementById('pass').value.trim();
            if (!email || !password) {
                alert('Por favor, complete todos los campos.');
                event.preventDefault();
            }
        });
    } else {
        console.error('Formulario de login no encontrado');
    }

    // Mostrar alerta si hay un error en los parámetros de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');
    if (error === 'invalid_credentials') {
        alert('Credenciales incorrectas. Por favor, intente de nuevo.');
    }
});
