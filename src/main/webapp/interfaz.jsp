<%-- 
    Document   : login
    Created on : 2/07/2024, 4:07:04 p. m.
    Author     : JDBJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, javax.servlet.*, javax.servlet.http.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>



<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
  <meta charset="utf-8">
  <title>Interfaz Masterbot</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/interfaz.css"/>
</head>

<body>
   <%
    
    // Verificar si el usuario está autenticado
    if (session == null || session.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
    } else {
        String role = (String) session.getAttribute("role");
      
        // Verificar si el rol es "inhabilitado" (sin distinción de mayúsculas y minúsculas)
        if ("inhabilitado".equalsIgnoreCase(role.trim())) {
            session.invalidate();
            response.sendRedirect("error.jsp");
            return;
        }
    }
    // Configurar las cabeceras de no caché
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.
%>
  <nav class="navbar">
    <img src="icono.png" class="navbar-logo" alt="logo" />
    <ul class="navbar-list">
      <li><a href="interfaz.jsp">Inicio</a></li>
      <li><a href="historial.jsp">Historial</a></li>
      <c:if test="${sessionScope.role == 'admin' || sessionScope.role == 'superadmin'}">
        <li><a href="./entrenamiento.jsp">Entrenamiento</a></li>
        <li><a href="./gestionentrenamiento.jsp">Gestionar entrenamiento</a></li>      
        </c:if>
      <li><a href="./ayuda-soporte.jsp">About</a></li>     
    </ul>

    <div class="profile-dropdown">
      <div onclick="toggle()" class="profile-dropdown-btn">
        <div class="profile-img">
          <i class="fa-solid fa-circle"></i>
        </div>
         <span><c:out value="${sessionScope.nombre}"/><i class="fa-solid fa-angle-down"></i></span>
      </div>

      <ul class="profile-dropdown-list">
        <li class="profile-dropdown-list-item"><a href="./perfil.jsp"><i class="fa-regular fa-user"></i> Editar Perfil</a></li>
        <c:if test="${sessionScope.role == 'superadmin'}">
        <li class="profile-dropdown-list-item"><a href="users.jsp"><i class="fa-solid fa-sliders"></i>Configuracion</a></li>
         </c:if>
        <li class="profile-dropdown-list-item"><a href="./ayuda-soporte.jsp"><i class="fa-regular fa-circle-question"></i> Ayuda y Soporte</a></li>
        <hr />
        <li class="profile-dropdown-list-item" id="logout-link"><a href="#" onclick="logout();"><i class="fa-solid fa-arrow-right-from-bracket"></i> Cerrar sesión</a></li>
      </ul>
    </div>
  </nav>
  
  <div class="chat-container" id="chat-container"></div>
  <div class="typing-container">
    <div class="typing-content">
      <div class="typing-textarea">
        <textarea id="chat-input" spellcheck="false" placeholder="Ingrese su pregunta aqui" required></textarea>
      </div>
      <div class="typing-controls">
        <span id="send-btn" class="material-symbols-rounded">send</span>
      </div>
      <div class="typing-controls">
        <span id="theme-btn" class="material-symbols-rounded">light_mode</span>
        <span id="delete-btn" class="material-symbols-rounded">delete</span>
      </div>
    </div>
  </div>
  <script src="js/main.js"></script>
 
  
  <script> 
      
     // Guarda el userId en localStorage cuando el usuario inicia sesión
    const userId = '<%= session.getAttribute("userId") %>';  // Asegúrate de que este valor esté disponible
    localStorage.setItem('userId', userId);
      
// Prevenir que el usuario pueda volver a la página anterior
    (function(global) {
        if (typeof(global) === "undefined") {
            throw new Error("Ventana no disponible");
        }
        
        let _hash = "!";
        let noBackPlease = function() {
            global.location.href += "#";

            global.setTimeout(function() {
                global.location.href += "!";
            }, 50);
        };

        global.onhashchange = function() {
            if (global.location.hash !== _hash) {
                global.location.hash = _hash;
            }
        };

        global.onload = function() {
            noBackPlease();

            document.body.onkeydown = function(e) {
                var elm = e.target.nodeName.toLowerCase();
                if (e.which === 8 && (elm !== 'input' && elm  !== 'textarea')) {
                    e.preventDefault();
                }
                e.stopPropagation();
            };
        };
    })(window);

    // Función para cerrar sesión y evitar volver atrás
    function logout() {
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
   </script> 
</body>
</html>
