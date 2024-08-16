/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function() {
    let form = document.getElementById('login-form');
    if (form){
        form.addEventListener('submit', function(event) {
            let email = document.getElementById('user').value.trim();
            let password = document.getElementById('pass').value.trim();
            const error = urlParams.get('error');
            if (!email||!password) {
                event.preventDefault();
                alert('Por favor, complete todos los campos.');
                
            }
        });
    } else {
        console.error('Formulario de login no encontrado');
    }


    // Mostrar alerta si hay un error en los parámetros de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');
    if (error === 'invalid_credentials') {
        event.preventDefault();
        alert('Credenciales incorrectas. Por favor, intente de nuevo.');
    }
});
