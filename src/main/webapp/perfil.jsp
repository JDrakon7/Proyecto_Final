<%-- 
    Document   : perfil
    Created on : 4/07/2024, 9:13:12 a. m.
    Author     : JDBJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Perfil</title>
    <link rel="stylesheet" href="styles.css">
    <script src="js/profile.js" defer></script>
</head>
<body>
    <div class="profile-wrap">
        <div class="profile-html">
            <h2>Modificar Perfil</h2>
            <form id="profile-form" method="post">
                <div class="group">
                    <label for="username" class="label">Usuario</label>
                    <input id="username" type="text" class="input" name="username" value="${usuario.username}" required>
                </div>
                <div class="group">
                    <label for="email" class="label">Email</label>
                    <input id="email" type="email" class="input" name="email" value="${usuario.email}" required>
                </div>
                <div class="group">
                    <label for="password" class="label">Nueva Contraseña</label>
                    <input id="password" type="password" class="input" name="password">
                </div>
                <div class="group">
                    <label for="confirm-password" class="label">Confirmar Nueva Contraseña</label>
                    <input id="confirm-password" type="password" class="input" name="confirm-password">
                </div>
                <div class="group">
                    <input type="submit" class="button" value="Actualizar Perfil">
                </div>
            </form>
            <div class="group">
                <a href="javascript:history.back()" class="back-button">Volver</a>
            </div>
        </div>
    </div>
</body>
</html>


<style>
      
:root {
    --primary-color: #1161ee;
    --secondary-color: #0d4ecb;
    --main-bg-color: #f9f9f9;
    --main-font-color: #333;
    --font-family: 'Arial', sans-serif;
    --font-weight: 400;
    --font-size: 16px;
    --line-height: 1.5;
}

body {
    margin: 0;
    color: var(--main-font-color);
    background: var(--main-bg-color);
    font-family: var(--font-family);
    font-weight: var(--font-weight);
    font-size: var(--font-size);
    line-height: var(--line-height);
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.profile-wrap {
    width: 100%;
    max-width: 600px;
    background: #fff;
    padding: 40px;
    box-shadow: 0 12px 15px rgba(0, 0, 0, 0.24), 0 17px 50px rgba(0, 0, 0, 0.19);
    border-radius: 8px;
}

.profile-html {
    text-align: center;
}

.profile-html h2 {
    font-size: 24px;
    margin-bottom: 20px;
    color: var(--primary-color);
}

.group {
    margin-bottom: 20px;
    text-align: left;
}

.group .label {
    font-size: 14px;
    margin-bottom: 8px;
    display: block;
    color: var(--main-font-color);
}

.group .input {
    width: 100%;
    padding: 15px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 14px;
    color: var(--main-font-color);
    background: #f9f9f9;
}

.group .input:focus {
    border-color: var(--primary-color);
    outline: none;
}

.group .button {
    width: 100%;
    padding: 15px;
    background: var(--primary-color);
    color: #fff;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    cursor: pointer;
    text-decoration: none;
    display: inline-block;
    text-align: center;
    margin-bottom: 10px;
}

.group .button:hover {
    background: var(--secondary-color);
}

.group .back-button {
    display: inline-block;
    padding: 10px 20px;
    background: #fff;
    color: var(--primary-color);
    border: 2px solid var(--primary-color);
    border-radius: 4px;
    font-size: 14px;
    cursor: pointer;
    text-decoration: none;
    margin-top: 10px;
}

.group .back-button:hover {
    background: var(--primary-color);
    color: #fff;
}

.group.centered {
    text-align: center;
}

</style>
</body>
</html>

