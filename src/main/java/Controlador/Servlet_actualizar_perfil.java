/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.ActualizarPerfil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JDBJ
 */
@WebServlet(name = "actualizar_perfil", urlPatterns = {"/actualizar_perfil"})
public class Servlet_actualizar_perfil extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Obtener el ID del usuario de la sesión
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // Validar la existencia del userId en la sesión
        if (userId == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"success\": false, \"message\": \"Usuario no autenticado.\"}");
            return;
        }

        // Crear una instancia de ActualizarPerfil para interactuar con la base de datos
        ActualizarPerfil actualizarPerfil = new ActualizarPerfil();

        // Actualizar el perfil del usuario
        boolean isUpdated = actualizarPerfil.actualizarPerfil(userId, username, email, password);

        // Configurar la respuesta en formato JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (isUpdated) {
            response.getWriter().write("{\"success\": true}");
        } else {
            response.getWriter().write("{\"success\": false, \"message\": \"El correo ya está en uso.\"}");
        }
    }
}