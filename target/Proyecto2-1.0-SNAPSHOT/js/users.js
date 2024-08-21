/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', function () {
    const userSelect = document.getElementById('user');
    const roleForm = document.getElementById('roleForm');

    // Leer los usuarios
    function loadUsers() {
        const usuarios = new XMLHttpRequest();
        usuarios.open('GET', 'getUsers', true); //se retornan los usuarios en formato JSON
        usuarios.onreadystatechange = function () {
            if (usuarios.readyState === 4 && usuarios.status === 200) {
                const users = JSON.parse(usuarios.responseText);
                userSelect.innerHTML = '<option value="">Seleccionar Usuario</option>'; // Listarlos
                users.forEach(user => {
                    const option = document.createElement('option');
                    option.value = user.userId;
                    option.textContent = `${user.username} (${user.role || 'Sin rol'})`;
                    userSelect.appendChild(option);
                });
            }
        };
        usuarios.send();
    }

    // cargar los usuarios cuando se entra a la pagina
    loadUsers();

    // Manejo del envio del formulario utilizando AJAX
    roleForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevenir el envío del formulario

        // desactivar el boton una vez se halla hecho un submit
        const submitButton = roleForm.querySelector('button[type="submit"]');
        submitButton.disabled = true;

        const userId = document.getElementById('user').value;
        const role = document.getElementById('role').value;

        const roles = new XMLHttpRequest();
        roles.open('POST', 'actualizarRol', true);
        roles.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        roles.onreadystatechange = function () {
            if (roles.readyState === 4) {
                // verificar la respuesta 
                if (roles.status === 200) {
                    alert(roles.responseText); // Mostrar respuesta del servlet
                    if (roles.responseText.includes("exitosamente")) {
                        loadUsers(); // refrecar la lista para que los cambios se reflejen
                    }
                } else {
                    alert("Error al actualizar el rol."); 
                }
                submitButton.disabled = false; // habilitar el boton otra vez
            }
        };
        roles.send(`userId=${encodeURIComponent(userId)}&newRole=${encodeURIComponent(role)}`);
    });
});

