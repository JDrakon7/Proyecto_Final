<%-- 
    Document   : login
    Created on : 2/07/2024, 4:07:04 p. m.
    Author     : JDBJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, javax.servlet.*, javax.servlet.http.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
  <nav class="navbar">
    <img src="icono.png" class="navbar-logo" alt="logo" />
    <ul class="navbar-list">
      <li><a href="interfaz.jsp">Inicio</a></li>
      <li><a href="historial.jsp">Historial</a></li>
      <c:if test="${sessionScope.role == 'admin' || sessionScope.role == 'superadmin'}">
        <li><a href="./entrenamiento.jsp">Entrenamiento</a></li>
      </c:if>
      <li><a href="./ayuda-soporte.jsp">About</a></li>      
    </ul>

    <div class="profile-dropdown">
      <div onclick="toggle()" class="profile-dropdown-btn">
        <div class="profile-img">
          <i class="fa-solid fa-circle"></i>
        </div>
         <span><c:out value="${sessionScope.usuario}"/> <i class="fa-solid fa-angle-down"></i></span>
      </div>

      <ul class="profile-dropdown-list">
        <li class="profile-dropdown-list-item"><a href="./perfil.jsp"><i class="fa-regular fa-user"></i> Edit Profile</a></li>
        <li class="profile-dropdown-list-item"><a href="#"><i class="fa-solid fa-chart-line"></i> Analytics</a></li>
        <li class="profile-dropdown-list-item"><a href="#"><i class="fa-solid fa-sliders"></i> Settings</a></li>
        <li class="profile-dropdown-list-item"><a href="./ayuda-soporte.jsp"><i class="fa-regular fa-circle-question"></i> Help & Support</a></li>
        <hr />
        <li class="profile-dropdown-list-item"><a href="index.jsp"><i class="fa-solid fa-arrow-right-from-bracket"></i> Log out</a></li>
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
</body>
</html>
