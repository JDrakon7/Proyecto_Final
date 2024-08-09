/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.RecuperarContrasena;  // Importa la clase RecuperarContrasena para manejar la lógica de actualización de contraseñas
import java.io.IOException;  // Importa la clase IOException para manejar errores de entrada/salida
import javax.servlet.ServletException;  // Importa la clase ServletException para manejar excepciones en servlets
import javax.servlet.annotation.WebServlet;  // Importa la anotación WebServlet para definir el servlet en el archivo de despliegue
import javax.servlet.http.HttpServlet;  // Importa la clase HttpServlet para manejar solicitudes HTTP
import javax.servlet.http.HttpServletRequest;  // Importa la clase HttpServletRequest para manejar las solicitudes HTTP
import javax.servlet.http.HttpServletResponse;  // Importa la clase HttpServletResponse para manejar las respuestas HTTP

@WebServlet(name = "Servlet_Cambiarpass", urlPatterns = {"/contrasena"})  // Define el URL pattern para este servlet
public class Servlet_Cambiarpass extends HttpServlet {  // Define la clase Servlet_Cambiarpass que extiende HttpServlet

   private static final long serialVersionUID = 1L;  // Define un ID de versión de serialización para asegurar la compatibilidad entre versiones

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // Maneja las solicitudes POST
        request.setCharacterEncoding("UTF-8");  // Establece la codificación de caracteres para la solicitud
        response.setContentType("text/html; charset=UTF-8");  // Establece el tipo de contenido de la respuesta como HTML y la codificación de caracteres

        // Obtiene el email validado de la sesión
        String email = (String) request.getSession().getAttribute("emailValidado");
        // Obtiene la nueva contraseña del parámetro de la solicitud
        String nuevaContrasena = request.getParameter("password");

        // Verifica si el email ha sido validado
        if (email == null) {
            response.getWriter().write("Error: No se ha validado el correo.");  // Envía un mensaje de error si el email no está validado
            return;
        }

        // Crea una instancia de RecuperarContrasena para manejar la actualización de contraseñas
        RecuperarContrasena recuperarContrasena = new RecuperarContrasena();
        // Intenta actualizar la contraseña y obtiene el resultado
        boolean actualizada = recuperarContrasena.actualizarContrasena(email, nuevaContrasena);

        // Verifica si la actualización fue exitosa y envía el mensaje correspondiente
        if (actualizada) {
            response.getWriter().write("Contraseña cambiada exitosamente.");  // Envía un mensaje de éxito si la contraseña fue actualizada
        } else {
            response.getWriter().write("Contraseña cambiada exitosamente");  // Envía un mensaje de error si la actualización falló
        }
    }
}
