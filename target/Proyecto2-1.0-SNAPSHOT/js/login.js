/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */




document.addEventListener('DOMContentLoaded', function() {
    let form = document.getElementById('login-form');

    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            let email = document.querySelector('.input[name="email"]').value.trim();
            let password = document.querySelector('.input[name="password"]').value.trim();

            // Validar campos vacíos
            if (!email || !password) {
                alert('Por favor, complete todos los campos.');
                return; // Detiene la ejecución si hay campos vacíos
            }

            // Enviar la solicitud Ajax
            fetch('login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    email: email,
                    password: password
                })
            })
            .then(response => response.text())
            .then(text => {
                if (text === 'invalid_credentials') {
                    alert('Credenciales incorrectas. Por favor, intente de nuevo.');
                } else {
                    // Se inicia sesion y se le permite ingresar a la interfaz
                    window.location.href = 'interfaz.jsp';
                }
            })
            .catch(error => console.error('Error:', error));
        });
    } else {
        console.error('Formulario de login no encontrado');
    }
});



 /*document.addEventListener('DOMContentLoaded', function() {
     
 let form = document.getElementById('login-form');
 if (form){
 form.addEventListener('submit', function(event) {
let email = document.querySelector('.input[name="email"]').value.trim();
let password = document.querySelector('.input[name="password"]').value.trim();

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
*/ 