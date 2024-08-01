    /* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener("DOMContentLoaded", function() {
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
    document.getElementById('profile-form').addEventListener('submit', function(event) {
        event.preventDefault(); // Prevenir el envío del formulario

        if (!validateForm()) {
            return; // Si la validación falla, no continuar
        }

        const formData = new FormData(this); // Obtener los datos del formulario

        fetch('/Proyecto2/actualizar_perfil', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            const message = document.getElementById('message');
            if (data.success) {
                message.innerHTML = '<p>Perfil actualizado exitosamente.</p>';
            } else {
                message.innerHTML = '<p>Error al actualizar el perfil: ' + data.message + '</p>';
            }
        })
        .catch(error => {
            const message = document.getElementById('message');
            message.innerHTML = '<p>Error al actualizar el perfil.</p>';
            console.error('Error:', error);
        });
    });
});

