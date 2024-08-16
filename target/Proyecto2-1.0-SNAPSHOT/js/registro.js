/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


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