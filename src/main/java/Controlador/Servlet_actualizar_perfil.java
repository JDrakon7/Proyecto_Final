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

@WebServlet("/actualizar_perfil")  // Define el URL pattern para el servlet. Este servlet se accede mediante la URL "/actualizar_perfil"
public class Servlet_actualizar_perfil extends HttpServlet {

    private static final long serialVersionUID = 1L;  // Define un ID de versión de serialización para el servlet

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Método que maneja las solicitudes HTTP POST

        response.setContentType("application/json");  // Define el tipo de contenido de la respuesta como JSON
        response.setCharacterEncoding("UTF-8");  // Define la codificación de caracteres como UTF-8
        PrintWriter out = response.getWriter();  // Obtiene un escritor para enviar la respuesta al cliente

        try {
            // Obtener parámetros del formulario
            int userId = Integer.parseInt(request.getParameter("userId"));  // Obtiene el ID del usuario desde la solicitud y lo convierte a entero
            String username = request.getParameter("username");  // Obtiene el nombre de usuario desde la solicitud
            String email = request.getParameter("email");  // Obtiene el email desde la solicitud
            String password = request.getParameter("password");  // Obtiene la contraseña desde la solicitud

            // Crear instancia de ActualizarPerfil y actualizar perfil
            ActualizarPerfil actualizarPerfil = new ActualizarPerfil();  // Crea una instancia de ActualizarPerfil para manejar la actualización
            boolean isUpdated = actualizarPerfil.actualizarPerfil(userId, username, email, password);  // Llama al método para actualizar el perfil

            // Enviar respuesta en formato JSON
            if (isUpdated) {
                out.println("{\"success\": true}");  // Envia respuesta de éxito en formato JSON
            } else {
                out.println("{\"success\": false, \"message\": \"Error al actualizar el perfil.\"}");  // Envia respuesta de error en formato JSON
            }
            
            out.flush();  // Asegurarse de que el contenido se envíe al cliente
            
        } catch (NumberFormatException e) {
            // Maneja el error si el ID del usuario no es un número válido
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // Establece el estado de la respuesta como 400 Bad Request
            out.println("{\"success\": false, \"message\": \"Invalid user ID.\"}");  // Envia respuesta de error en formato JSON
        } catch (Exception e) {
            // Maneja cualquier otro error inesperado
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  // Establece el estado de la respuesta como 500 Internal Server Error
            out.println("{\"success\": false, \"message\": \"An unexpected error occurred.\"}");  // Envia respuesta de error en formato JSON
        } finally {
            out.close();  // Cierra el escritor para liberar recursos
        }
    }
}
