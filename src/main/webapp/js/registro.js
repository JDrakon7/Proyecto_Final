/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

import { correo } from './validar_correo.js';



document.addEventListener('DOMContentLoaded', () => {
  const form = document.querySelector('.login-form form');

  form.addEventListener('submit', (event) => {
    const emailInput = document.querySelector('input[name="email"]');
    const confirmarEmailInput = document.querySelector('input[name="confirmar_email"]');
    
    correo(event, emailInput);  // Valida el primer correo
    correo(event, confirmarEmailInput);  // Valida el segundo correo

    // Validar si lso correos coninciden
    if (emailInput.value !== confirmarEmailInput.value) {
      alert("Los correos electrónicos no coinciden.");
      event.preventDefault(); // si no coinciden no se envia el formulario
    }
  });
});


document.addEventListener("DOMContentLoaded", function() {
    // seleccionar el formulario
    const form = document.querySelector("#registro-form");

    // Agrega un manejador de eventos para el submit
    form.addEventListener("submit", function(event) {
        // Llamar a la funcion validar
        if (!validarFormulario()) {
            // Si la validacion falla, no se envia el formulario
            event.preventDefault();
        }
    });

    //  validaciones del formulario
    function validarFormulario() {
        const nombre = document.getElementById("nombre").value.trim();
        const email = document.getElementById("email").value.trim();
        const confirmarEmail = document.getElementById("confirmar_email").value.trim();
        const password = document.getElementById("password").value.trim();
        const confirmarPassword = document.getElementById("confirmar_password").value.trim();

        // Validaciones de campos
        if (nombre === "" || email === "" || confirmarEmail === "" || password === "" || confirmarPassword === "") {
            alert("Todos los campos son obligatorios.");
            return false;
        }

        if (email !== confirmarEmail) {
            alert("Los correos electrónicos no coinciden.");
            return false;
        }

        if (password !== confirmarPassword) {
            alert("Las contraseñas no coinciden.");
            return false;
        }

        // Si todo está bien, devuelve true
        return true;
    }
});


/*
// Mostrar alerta si hay un error en los parámetros de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');
    if (error === 'email_mismatch') {
        event.preventDefault();
        alert('Los correos no coinciden. Por favor, intente de nuevo.');
    }
    
    // Mostrar alerta si hay un error en los parámetros de la URL
    const urlParams2 = new URLSearchParams(window.location.search);
    const error2 = urlParams.get('error');
    if (error2 === 'password_mismatch') {
        event.preventDefault();
        alert('Las contraseñas no coinciden. Por favor, intente de nuevo.');
    }
*/  
    
    