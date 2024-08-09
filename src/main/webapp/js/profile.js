    /* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


 document.addEventListener("DOMContentLoaded", function() {
            // Obtener la información del usuario al cargar la página
            fetch('/Proyecto2/UsuarioServlet?action=obtenerInformacionUsuario')
                .then(response => response.json())
                .then(data => {
                    if (data.error) {
                        alert(data.error);
                        return;
                    }

                    document.getElementById('username').value = data.nombre;
                    document.getElementById('email').value = data.email;
                })
                .catch(error => console.error('Error:', error));

            function validateForm() {
                const password = document.getElementById("password").value;
                const confirmPassword = document.getElementById("confirm-password").value;

                if (password && password !== confirmPassword) {
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

                fetch('/Proyecto2/UsuarioServlet?action=actualizarUsuario', {
                    method: 'POST',
                    body: formData
                })
                .then(response => response.text())
                .then(data => {
                    const message = document.getElementById('message');
                    if (data.includes("exitosamente")) {
                        message.innerHTML = '<p>Perfil actualizado exitosamente.</p>';
                    } else {
                        message.innerHTML = '<p>Error al actualizar el perfil: ' + data + '</p>';
                    }
                })
                .catch(error => {
                    const message = document.getElementById('message');
                    message.innerHTML = '<p>Error al actualizar el perfil.</p>';
                    console.error('Error:', error);
                });
            });
        });