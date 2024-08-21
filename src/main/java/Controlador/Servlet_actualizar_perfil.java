/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ActualizarPerfil;  // Importa la clase ActualizarPerfil que maneja la lógica de actualización del perfil del usuario
import javax.servlet.ServletException;  // Importa la clase ServletException para manejar errores específicos de los servlets
import javax.servlet.annotation.WebServlet;  // Importa la anotación WebServlet para definir el servlet
import javax.servlet.http.HttpServlet;  // Importa la clase HttpServlet para la funcionalidad del servlet
import javax.servlet.http.HttpServletRequest;  // Importa la clase HttpServletRequest para manejar solicitudes HTTP
import javax.servlet.http.HttpServletResponse;  // Importa la clase HttpServletResponse para manejar respuestas HTTP
import java.io.IOException;  // Importa la clase IOException para manejar errores de entrada/salida
import java.io.PrintWriter;  // Importa la clase PrintWriter para escribir la respuesta

@WebServlet("/Actualizar")  // Define el URL pattern para el servlet. Este servlet se accede mediante la URL "/actualizar_perfil"
public class Servlet_actualizar_perfil extends HttpServlet {

    private static final long serialVersionUID = 1L;  // Define un ID de versión de serialización para el servlet

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String userIdParam = request.getParameter("userId");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            System.out.println("userId: " + userIdParam);
            System.out.println("username: " + username);
            System.out.println("email: " + email);
            System.out.println("password: " + password);

            if (userIdParam == null || username == null || email == null || password == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"success\": false, \"message\": \"Missing parameters.\"}");
                return;
            }

            int userId;
            try {
                userId = Integer.parseInt(userIdParam);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"success\": false, \"message\": \"Invalid user ID.\"}");
                return;
            }

            ActualizarPerfil actualizarPerfil = new ActualizarPerfil();
            boolean isUpdated = actualizarPerfil.actualizarPerfil(userId, username, email, password);

            if (isUpdated) {
                out.println("{\"success\": true, \"message\": \"Perfil actualizado exitosamente.\"}");
            } else {
                out.println("{\"success\": false, \"message\": \"Error al actualizar el perfil.\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("{\"success\": false, \"message\": \"An unexpected error occurred.\"}");
        }
    }
}
