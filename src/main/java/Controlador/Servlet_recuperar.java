/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.RecuperarContrasena;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/recuperar")
public class Servlet_recuperar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el email del formulario
        String email = request.getParameter("email");

        // Crear una instancia de RecuperarContrasena para interactuar con la base de datos
        RecuperarContrasena recuperarContrasena = new RecuperarContrasena();

        // Verificar que el correo existe y enviar el token de recuperación
        boolean isEmailSent = recuperarContrasena.enviarTokenRecuperacion(email);

        // Configurar la respuesta en formato JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Crear la respuesta JSON
        String jsonResponse;
        if (isEmailSent) {
            jsonResponse = "{\"success\": true, \"message\": \"El correo de recuperación ha sido enviado.\"}";
        } else {
            jsonResponse = "{\"success\": false, \"message\": \"El correo electrónico no se encuentra registrado.\"}";
        }

        // Enviar la respuesta JSON
        response.getWriter().write(jsonResponse);
    }
}
