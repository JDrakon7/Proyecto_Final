/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.getElementById('forgot-password-form').addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(this);

            fetch('http://localhost:8080/Proyecto2/ValidarCorreo', {
                method: 'POST',
                body: formData
            })
            .then(response => response.text())
            .then(data => {
                if (data === "Correo validado.") {
                    window.location.href = 'cambiarpass.jsp';
                } else {
                    alert(data);
                }
            })
            .catch(error => console.error('Error:', error));
        });



document.getElementById('reset-password-form').addEventListener('submit', function(event) {
            event.preventDefault();
            const password = document.getElementById('password').value;
            const confirmarPassword = document.getElementById('confirmar_password').value;

            if (password !== confirmarPassword) {
                alert('Las contraseñas no coinciden.');
                return;
            }

            const formData = new FormData(this);

            fetch('http://localhost:8080/Proyecto2/CambiarContrasena', {
                method: 'POST',
                body: formData
            })
            .then(response => response.text())
            .then(data => {
                alert(data);
                if (data === "Contraseña cambiada exitosamente.") {
                    window.location.href = 'index.jsp';
                }
            })
            .catch(error => console.error('Error:', error));
        });