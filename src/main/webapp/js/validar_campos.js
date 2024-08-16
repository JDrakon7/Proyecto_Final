/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


export function validar_campos(formId, fieldIds) {
    const form = document.getElementById(formId);
    if (form) {
        form.addEventListener('submit', function(event) {
            let allFieldsFilled = true;
            fieldIds.forEach(id => {
                const field = document.getElementById(id);
                if (field && field.value.trim() === '') {
                    allFieldsFilled = false;
                }
            });

            if (!allFieldsFilled) {
                alert('Por favor, complete todos los campos.');
                event.preventDefault();
            }
        });
    } else {
        console.error('Formulario no encontrado');
    }
}


