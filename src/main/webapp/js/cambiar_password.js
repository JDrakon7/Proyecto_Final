/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function() {
    // Función para validar el correo electrónico en el formulario de recuperación de contraseña
    function validateEmail() {
        const email = document.getElementById("email").value;
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (!emailPattern.test(email)) {
            alert("Por favor, introduce un correo electrónico válido.");
            return false;
        }
        return true;
    }

    // Validación del formulario de restablecimiento de contraseña
    function validateResetPasswordForm() {
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirmar_password").value;

        if (password !== confirmPassword) {
            alert("Las contraseñas no coinciden.");
            return false;
        }
        return true;
    }

    // Asignar la validación al formulario de recuperación de contraseña
    const forgotPasswordForm = document.getElementById("forgot-password-form");
    if (forgotPasswordForm) {
        forgotPasswordForm.addEventListener("submit", function(event) {
            if (!validateEmail()) {
                event.preventDefault();
            }
        });
    }

    // Asignar la validación al formulario de restablecimiento de contraseña
    const resetPasswordForm = document.getElementById("reset-password-form");
    if (resetPasswordForm) {
        resetPasswordForm.addEventListener("submit", function(event) {
            if (!validateResetPasswordForm()) {
                event.preventDefault();
            }
        });
    }
});
