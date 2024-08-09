/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DAO_login;  // Importa la clase DAO_login que maneja la actualización de roles de usuario
import java.io.IOException;  // Importa la clase IOException para manejar errores de entrada/salida
import javax.servlet.ServletException;  // Importa la clase ServletException para manejar errores en servlets
import javax.servlet.annotation.WebServlet;  // Importa la anotación WebServlet para definir el servlet
import javax.servlet.http.HttpServlet;  // Importa la clase HttpServlet para la funcionalidad del servlet
import javax.servlet.http.HttpServletRequest;  // Importa la clase HttpServletRequest para manejar solicitudes HTTP
import javax.servlet.http.HttpServletResponse;  // Importa la clase HttpServletResponse para manejar respuestas HTTP

@WebServlet("/actualizarRol")  // Define el URL pattern para el servlet. Este servlet se accede mediante la URL "/actualizarRol"
public class Servlet_Rol extends HttpServlet {

    private static final long serialVersionUID = 1L;  // Define un ID de versión de serialización para el servlet

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Método que maneja las solicitudes HTTP POST

        // Obtiene el ID de usuario y el nuevo rol desde la solicitud
        String userId = request.getParameter("userId");
        String newRole = request.getParameter("newRole");

        // Crea una instancia del DAO_login para interactuar con la base de datos
        DAO_login daoLogin = new DAO_login();
        
        // Llama al método para actualizar el rol del usuario en la base de datos
        boolean success = daoLogin.updateUserRole(userId, newRole);

        // Envía una respuesta al cliente indicando si la actualización fue exitosa o no
        if (success) {
            response.getWriter().write("Rol actualizado exitosamente.");
        } else {
            response.getWriter().write("Error al actualizar el rol.");
        }
    }
}
