/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.DAO_login; // Importa la clase DAO_login del paquete Modelo, que maneja operaciones de acceso a datos para el login.
import Modelo.DAO_login.User; // Importa la clase User anidada en DAO_login, probablemente una representación del usuario en el contexto del login.
import com.google.gson.Gson; // Importa la clase Gson de la biblioteca de Google para manipular JSON.
import java.io.IOException; // Importa la clase IOException para manejar excepciones relacionadas con operaciones de entrada/salida.
import java.io.PrintWriter; // Importa la clase PrintWriter para escribir datos en un flujo de salida, como en una respuesta HTTP.
import java.util.List; // Importa la interfaz List, que representa una colección de elementos ordenados.
import javax.servlet.ServletException; // Importa la clase ServletException para manejar errores en la ejecución de servlets.
import javax.servlet.annotation.WebServlet; // Importa la anotación WebServlet para definir un servlet en una aplicación web.
import javax.servlet.http.HttpServlet; // Importa la clase HttpServlet, que es la clase base para crear servlets que responden a solicitudes HTTP.
import javax.servlet.http.HttpServletRequest; // Importa la clase HttpServletRequest para manejar las solicitudes HTTP.
import javax.servlet.http.HttpServletResponse; // Importa la clase HttpServletResponse para manejar las respuestas HTTP.
/**
 *
 * @author JDBJ
 */
@WebServlet(name = "ObtenerUsuarios", urlPatterns = {"/getUsers"})
public class ObtenerUsuarios extends HttpServlet {

   private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO_login daoLogin = new DAO_login();
        List<DAO_login.User> users = daoLogin.getAllUsers(); // Obtener todos los usuarios

        // Convertir la lista de usuarios a JSON
        Gson gson = new Gson();
        String usersJson = gson.toJson(users);

        // Configurar la respuesta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Enviar la respuesta JSON
        PrintWriter out = response.getWriter();
        out.print(usersJson);
        out.flush();
    }
}
