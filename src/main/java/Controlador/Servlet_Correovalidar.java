/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.RecuperarContrasena;  // Importa la clase RecuperarContrasena para validar el correo
import java.io.IOException;  // Importa la clase IOException para manejar errores de entrada/salida
import javax.servlet.ServletException;  // Importa la clase ServletException para manejar excepciones en servlets
import javax.servlet.annotation.WebServlet;  // Importa la anotación WebServlet para definir el servlet en el archivo de despliegue
import javax.servlet.http.HttpServlet;  // Importa la clase HttpServlet para manejar solicitudes HTTP
import javax.servlet.http.HttpServletRequest;  // Importa la clase HttpServletRequest para manejar las solicitudes HTTP
import javax.servlet.http.HttpServletResponse;  // Importa la clase HttpServletResponse para manejar las respuestas HTTP

/**
 * Servlet para validar la existencia de un correo electrónico registrado.
 */
@WebServlet(name = "Servlet_Correovalidar", urlPatterns = {"/ValidarCorreo"})  // Define el URL pattern para este servlet
public class Servlet_Correovalidar extends HttpServlet {  // Define la clase Servlet_Correovalidar que extiende HttpServlet

    private static final long serialVersionUID = 1L;  // Define un ID de versión de serialización para asegurar la compatibilidad entre versiones

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // Maneja las solicitudes POST
        request.setCharacterEncoding("UTF-8");  // Establece la codificación de caracteres para la solicitud
        response.setContentType("text/html; charset=UTF-8");  // Establece el tipo de contenido de la respuesta
        response.setCharacterEncoding("UTF-8");  // Establece la codificación de caracteres para la respuesta
        String email = request.getParameter("email");  // Obtiene el email del parámetro de la solicitud
        RecuperarContrasena recuperarContrasena = new RecuperarContrasena();  // Crea una instancia de RecuperarContrasena para manejar la validación del correo

        // Valida si el correo electrónico existe
        boolean existe = recuperarContrasena.validarCorreo(email);

        // Envía una respuesta basada en la validación del correo
        if (existe) {
            request.getSession().setAttribute("emailValidado", email);  // Guarda el email en la sesión si es válido
            response.getWriter().write("Correo validado.");  // Envía un mensaje de éxito si el correo está registrado
        } else {
            response.getWriter().write("El correo electrónico no está registrado.");  // Envía un mensaje de error si el correo no está registrado
        }
    }
}
