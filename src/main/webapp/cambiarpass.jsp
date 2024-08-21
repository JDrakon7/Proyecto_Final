<%-- 
    Document   : cambiarpass
    Created on : 4/07/2024, 10:35:05 a. m.
    Author     : JDBJ
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restablecer Contraseña</title>
    <link rel="stylesheet" href="css/password.css">
</head>
<body>
    <div class="reset-password-wrap">
        <div class="reset-password-html">
            <h2 class="title">Restablecer Contraseña</h2>
            <form id="reset-password-form">
                <div class="group">
                    <label for="password" class="label">Nueva Contraseña</label>
                    <input id="password" name="password" type="password" class="input" >
                </div>
                <div class="group">
                    <label for="confirmar_password" class="label">Confirmar Contraseña</label>
                    <input id="confirmar_password" name="confirmar_password" type="password" class="input">
                </div>
                <div class="group">
                    <button type="submit" class="button">Enviar</button>
                </div>
            </form>
            <div class="foot-lnk">
                <a href="./index.jsp">Volver al Login</a>
            </div>
        </div>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            document.getElementById('reset-password-form').addEventListener('submit', function(event) {
                event.preventDefault();
                const password = document.getElementById('password').value;
                const confirmarPassword = document.getElementById('confirmar_password').value;
                
                if (!password){
                    alert('Ingrese la nueva contraseña');
                    return;
                }
                
                if (password !== confirmarPassword) {
                    alert('Las contraseñas no coinciden.');
                    return;
                }

                const formData = new FormData(this);

                fetch('contrasena', {
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
        });
    </script>
</body>
</html>


