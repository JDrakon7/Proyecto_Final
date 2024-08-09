<%-- 
    Document   : historial
    Created on : 4/07/2024, 9:13:58 a. m.
    Author     : JDBJ
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, javax.servlet.*, javax.servlet.http.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="UTF-8">
        <title>Historial de Conversaciones</title>
        <link rel="stylesheet" type="text/css" href="css/historial.css">
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
        <div class="history-wrap">
            <div class="history-html">
                <h2>Historial de Conversaciones</h2>
                <div class="history-content" id="chat-container">
                    <c:forEach var="historial" items="${historiales}">
                        <div class="conversation">
                            <p><strong>Fecha:</strong> ${historial.fecha}</p>
                            <p><strong>Pregunta:</strong> ${historial.pregunta}</p>
                            <p><strong>Respuesta:</strong> ${historial.respuesta}</p>
                        </div>
                        <hr>
                    </c:forEach>
                </div>
            </div>
            <div class="group centered">
                <a href="javascript:history.back()" class="back-button">Volver</a>
            </div>
        </div>

    </body>
    <script src="js/historial.js"></script>
</html>

