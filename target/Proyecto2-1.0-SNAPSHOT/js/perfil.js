/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.getElementById('profile-form').addEventListener('submit', function (event) {
    event.preventDefault(); // Prevenir el envío del formulario

    if (!validateForm()) {
        return; // Si la validación falla, no continuar
    }

    const actualizar = new XMLHttpRequest();
    const url = 'http://localhost:8080/Proyecto2/Actualizar';
    actualizar.open('POST', url, true);
    actualizar.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    actualizar.onreadystatechange = function () {
        if (actualizar.readyState === 4 && actualizar.status === 200) {
            const response = JSON.parse(actualizar.responseText);
            const message = document.getElementById('message');
            if (response.success) {
                message.innerHTML = '<p>Perfil actualizado exitosamente.</p>';

                // Limpiar los campos del formulario 
                document.getElementById('userId').value = '';
                document.getElementById('username').value = '';
                document.getElementById('email').value = '';
                document.getElementById('password').value = '';
                document.getElementById('confirm-password').value = '';

            } else {
                message.innerHTML = '<p>Error al actualizar el perfil: ' + response.message + '</p>';
            }
        } else if (actualizar.readyState === 4) {
            const message = document.getElementById('message');
            message.innerHTML = '<p>Error al actualizar el perfil. Código de estado: ' + actualizar.status + '</p>';
        }
    };

    // Construir los datos del formulario manualmente
    const formData = `userId=${encodeURIComponent(document.getElementById('userId').value)}&` +
            `username=${encodeURIComponent(document.getElementById('username').value)}&` +
            `email=${encodeURIComponent(document.getElementById('email').value)}&` +
            `password=${encodeURIComponent(document.getElementById('password').value)}`;

    actualizar.send(formData); // Enviar los datos al servidor
});




function validateForm() {
    const username = document.getElementById("username").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    // Validar que el nombre de usuario no esté vacío
    if (username === "") {
        alert("El nombre de usuario es obligatorio.");
        return false;
    }

    // Validar que el email no esté vacío y tenga un formato correcto
    if (email === "") {
        alert("El email es obligatorio.");
        return false;
    }

    if (!emailPattern.test(email)) {
        alert("Por favor, introduce un email válido.");
        return false;
    }

    if (password === "") {
        alert("La contraseña es obligatoria para guardar los cambios");
        return false;
    }

    // Validar que si se ingresa una contraseña, coincida con la confirmación
    if (password && password !== confirmPassword) {
        alert("Las contraseñas no coinciden.");
        return false;
    }

    return true;
}
