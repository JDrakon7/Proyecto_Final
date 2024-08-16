/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


import { validar_campos } from './validar_campos';

document.addEventListener('DOMContentLoaded', function() {
    validarCampos('login-form');
    if (form) {
        form.addEventListener('submit', function(event) {
            let email = document.getElementById('user').value.trim();
            let password = document.getElementById('pass').value.trim();
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
