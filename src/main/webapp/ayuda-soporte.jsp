<%-- 
    Document   : ayuda-soporte.jsp
    Created on : 16/07/2024, 4:46:25 p. m.
    Author     : JDBJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ayuda y Soporte</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

    <div class="help-support-wrap">
        <div class="help-support-html">
            <h2>Ayuda y Soporte</h2>
            <div class="section">
                <h3>FAQs</h3>
                <ul>
                    <li><a href="#faq1">¿Cómo uso el chatbot?</a></li>
                    <li><a href="#faq2">¿Qué tipo de preguntas puedo hacer?</a></li>
                    <li><a href="#faq3">¿Cómo se protege mi privacidad?</a></li>
                </ul>
            </div>
            <div class="section">
                <h3>Documentación</h3>
                <ul>
                    <li><a href="#doc1">Guía de inicio rápido</a></li>
                    <li><a href="#doc2">Manual del usuario</a></li>
                    <li><a href="#doc3">Mejores prácticas</a></li>
                </ul>
            </div>
            <div class="section">
                <h3>Contacto</h3>
                <p>Para soporte técnico, puedes contactar con nosotros a través de los siguientes medios:</p>
                <ul>
                    <li>Email: ejemplo@tuempresa.com</li>
                    <li>Teléfono: +57 301 3251928</li>
                    <li><a href="contact-form.html">Formulario de contacto</a></li>
                    <li><a href="live-chat.html">Chat en vivo</a></li>
                </ul>
            </div>
            <div class="section">
                <h3>Foro/Comunidad</h3>
                <p>Únete a nuestra comunidad para discutir problemas, compartir soluciones y colaborar en proyectos de Python:</p>
                <ul>
                    <li><a href="https://foro.tuempresa.com">Foro de la comunidad</a></li>
                    <li><a href="https://discord.gg/tuempresa">Discord</a></li>
                    <li><a href="https://www.reddit.com/r/tuempresa">Reddit</a></li>
                </ul>
            </div>
        </div>
        <div class="group">
        <a href="javascript:history.back()" class="back-button">Volver</a>
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

.help-support-wrap {
    width: 100%;
    max-width: 800px;
    background: #fff;
    padding: 40px;
    box-shadow: 0 12px 15px rgba(0, 0, 0, 0.24), 0 17px 50px rgba(0, 0, 0, 0.19);
    border-radius: 8px;
}

.help-support-html {
    text-align: left;
}

.help-support-html h2 {
    font-size: 28px;
    margin-bottom: 20px;
    color: var(--primary-color);
    text-align: center;
}

.section {
    margin-bottom: 30px;
}

.section h3 {
    font-size: 20px;
    margin-bottom: 10px;
    color: var(--secondary-color);
}

.section ul {
    list-style: none;
    padding: 0;
}

.section ul li {
    margin-bottom: 10px;
}

.section ul li a {
    color: var(--primary-color);
    text-decoration: none;
}

.section ul li a:hover {
    text-decoration: underline;
}

.section p {
    margin-bottom: 10px;
    font-size: 16px;
}

group .button {
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

</style>