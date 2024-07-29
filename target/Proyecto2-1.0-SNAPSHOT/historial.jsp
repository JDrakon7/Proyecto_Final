<%-- 
    Document   : historial
    Created on : 4/07/2024, 9:13:58 a. m.
    Author     : JDBJ
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Historial de Conversaciones</title>
    <link rel="stylesheet" type="text/css" href="css/historial.css">
</head>
<body>
    <div class="history-wrap">
        <div class="history-html">
            <h2>Historial de Conversaciones</h2>
            <div class="history-content">
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
    </div>
</body>
</html>

