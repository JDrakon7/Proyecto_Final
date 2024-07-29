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
    <div class="profile-wrap">
        <div class="profile-html">
            <h2>Entrenar Chatbot</h2>
            <form id="training-form" action="AgregarEntrenamiento" method="post">
                <div class="group">
                    <label for="pregunta" class="label">Pregunta</label>
                    <input id="pregunta" type="text" class="input" name="pregunta" required>
                </div>
                <div class="group">
                    <label for="respuesta" class="label">Respuesta</label>
                    <textarea id="respuesta" class="input" name="respuesta" rows="4" required></textarea>
                </div>
                <div class="group">
                    <label for="categoria" class="label">Categoria</label>
                    <select id="categoria" name="categoria" class="input" required>
                        <option value="">Selecciona una categoria</option>
                    </select>
                </div>
                <div class="group">
                    <label for="palabras_clave" class="label">Palabras Clave</label>
                    <input id="palabras_clave" type="text" class="input" name="palabras_clave" placeholder="Separadas por comas" required>
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
        </div>
    </div>
    <script src="js/entrenamiento.js"></script>
</body>
</html>
    
