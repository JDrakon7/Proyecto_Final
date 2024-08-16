/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


    // Función para cerrar sesión y evitar volver atrás
   export function logout() {
        // Invalida la sesión en el servidor
        fetch('login?action=logout', {
            method: 'GET',
            credentials: 'same-origin'
        })
        .then(response => {
            if (response.redirected) {
                window.location.href = response.url;  // Redirigir a la página de inicio de sesión
            }
        });

        // Manipula el historial del navegador para prevenir regresar a la página anterior
        (function(global) {
            if (typeof(global) === "undefined") {
                throw new Error("Ventana no disponible");
            }
            
            var _hash = "!";
            global.location.href += "#";

            global.setTimeout(function() {
                global.location.href += "!";
            }, 50);

            global.onhashchange = function() {
                if (global.location.hash !== _hash) {
                    global.location.hash = _hash;
                }
            };

            global.onload = function() {
                document.body.onkeydown = function(e) {
                    var elm = e.target.nodeName.toLowerCase();
                    if (e.which === 8 && (elm !== 'input' && elm  !== 'textarea')) {
                        e.preventDefault();
                    }
                    e.stopPropagation();
                };
            };
        })(window);
    }   