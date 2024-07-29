/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener("DOMContentLoaded", function() {
    // Validación del formulario
    function validateForm() {
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirm-password").value;

        if (password !== confirmPassword) {
            alert("Las contraseñas no coinciden.");
            return false;
        }
        return true;
    }

    // Manejo del envío del formulario con AJAX
    $('#profile-form').on('submit', function(event) {
        event.preventDefault(); // Prevenir el envío del formulario

        if (!validateForm()) {
            return; // Si la validación falla, no continuar
        }

        const formData = $(this).serialize(); // Serializar los datos del formulario

        $.ajax({
            type: 'POST',
            url: 'updateProfile',
            data: formData,
            success: function(response) {
                $('#message').html('<p>Perfil actualizado exitosamente.</p>');
            },
            error: function() {
                $('#message').html('<p>Error al actualizar el perfil.</p>');
            }
        });
    });
});
