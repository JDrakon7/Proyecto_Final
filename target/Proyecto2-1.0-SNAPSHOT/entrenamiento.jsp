<%-- 
    Document   : entrenamiento.jsp
    Created on : 18/07/2024, 10:17:31 a. m.
    Author     : JDBJ
--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Entrenar Chatbot</title>
    <link rel="stylesheet" href="css/entrenamiento.css">
</head>
<body>
       <%
    // Verificar si el usuario está autenticado
    if (session == null || session.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    // Configurar las cabeceras de no caché
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.
%>
    <div class="profile-wrap">
        <div class="profile-html">
            <h2>Entrenar Chatbot</h2>
            <form id="training-form" action="AgregarEntrenamiento" method="post">
                <input type="hidden" name="action" value="agregarEntrenamiento">
                <div class="group">
                    <label for="pregunta" class="label">Pregunta</label>
                    <input id="pregunta" type="text" class="input" name="pregunta">
                </div>
                <div class="group">
                    <label for="respuesta" class="label">Respuesta</label>
                    <textarea id="respuesta" class="input" name="respuesta" rows="4"></textarea>
                </div>
                <div class="group">
                    <label for="categoria" class="label">Categoria</label>
                    <select id="categoria" name="categoria" class="input">
                        <option value="">Selecciona una categoria</option>
                    </select>
                </div>
                <div class="group">
                    <label for="palabras_clave" class="label">Palabras Clave</label>
                    <input id="palabras_clave" type="text" class="input" name="palabras_clave" placeholder="Separadas por comas" >
                </div>
                <div class="group">
                    <input type="submit" class="button" value="Agregar">
                </div>
            </form>
            <div class="group centered">
                <button id="add-category-btn" class="button">Agregar Categoría</button>
                <form id="category-form" style="display:none;">
                    <input type="text" id="new-category" class="input" placeholder="Nueva Categoria">
                    <button type="button" id="submit-category-btn" class="button">Agregar</button>
                </form>
            </div>
            <div class="group centered">
                <a href="javascript:history.back()" class="back-button">Volver</a>
            </div>
            <div class="group centered">
                <a href="gestionentrenamiento.jsp">Verificar Lista de Entrenamientos </a> 
            </div>
        </div>
    </div>
    <script src="js/entrenar.js"></script>
</body>
</html>

    
