/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('forgot-password-form').addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = new FormData(this);


        for (let pair of formData.entries()) {
            console.log(pair[0] + ': ' + pair[1]);
        }

        fetch('http://localhost:8080/Proyecto2/ValidarCorreo', {
            method: 'POST',
            body: new URLSearchParams(formData)
        })
                .then(response => response.text())
                .then(data => {
                    alert(data);
                    if (data === "Correo validado.") {
                        window.location.href = 'cambiarpass.jsp';
                    }
                })
                .catch(error => console.error('Error:', error));
    });
});
