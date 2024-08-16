/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// Función para validar los campos del formulario
export function validarCampos(form) {
    const email = form.querySelector('#user').value.trim();
    const password = form.querySelector('#pass').value.trim();

    // Comprobar si alguno de los campos está vacío
    if (!email || !password) {
        return false; // Campos vacíos
    }
    return true; // Campos completos
}


