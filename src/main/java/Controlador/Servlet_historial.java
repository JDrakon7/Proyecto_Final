/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Historial.Historial;  // Importa la clase Historial que representa una entrada en el historial
import Modelo.Historial.HistorialDAO;  // Importa la clase HistorialDAO que maneja las operaciones de acceso a datos para el historial
import com.google.gson.Gson;  // Importa la biblioteca Gson para convertir objetos a JSON y viceversa
import javax.servlet.ServletException;  // Importa la clase ServletException para manejar errores específicos de servlets
import javax.servlet.annotation.WebServlet;  // Importa la anotación WebServlet para definir el servlet
import javax.servlet.http.*;  // Importa las clases necesarias para manejar solicitudes y respuestas HTTP
import java.io.IOException;  // Importa la clase IOException para manejar errores de entrada/salida
import java.util.List;  // Importa la clase List para manejar listas de objetos

@WebServlet("/historial")  // Define el URL pattern para el servlet. Este servlet se accede mediante la URL "/historial"
public class Servlet_historial extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Método que maneja las solicitudes HTTP GET para obtener el historial del usuario

        request.setCharacterEncoding("UTF-8");  // Establece el encoding de la solicitud a UTF-8
        response.setContentType("application/json; charset=UTF-8");  // Establece el tipo de contenido de la respuesta a JSON con codificación UTF-8
        HttpSession session = request.getSession();  // Obtiene la sesión HTTP actual
        Integer idUsuario = (Integer) session.getAttribute("userId");  // Obtiene el ID del usuario desde la sesión

        if (idUsuario == null) {
            // Si el ID del usuario no está en la sesión, envía un error 401 (No autorizado)
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in");
            return;
        }

        // Crea una instancia de HistorialDAO y obtiene la lista de historial para el usuario
        HistorialDAO historialDAO = new HistorialDAO();
        List<Historial> historialList = historialDAO.leerHistorial(idUsuario);

        // Convierte la lista de historial a JSON y la envía en la respuesta
        response.getWriter().write(new Gson().toJson(historialList));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirige las solicitudes POST a doGet, ya que en este caso se maneja de la misma manera
        doGet(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Método que maneja las solicitudes HTTP DELETE para eliminar una entrada del historial

        String path = request.getPathInfo();  // Obtiene la parte del path de la solicitud después de la URL del servlet
        if (path == null || !path.startsWith("/")) {
            // Si el path es inválido, envía un error 400 (Solicitud incorrecta)
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request path");
            return;
        }

        // Obtiene el ID de la entrada del historial a eliminar del path
        int id = Integer.parseInt(path.substring(1));
        HistorialDAO historialDAO = new HistorialDAO();
        // Elimina la entrada del historial con el ID especificado
        historialDAO.eliminarHistorial(id);
        // Envía una respuesta con estado 204 (Sin contenido) para indicar que la eliminación fue exitosa
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Método que maneja las solicitudes HTTP PUT para actualizar una entrada del historial

        String path = request.getPathInfo();  // Obtiene la parte del path de la solicitud después de la URL del servlet
        if (path == null || !path.startsWith("/")) {
            // Si el path es inválido, envía un error 400 (Solicitud incorrecta)
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request path");
            return;
        }

        // Obtiene el ID de la entrada del historial a actualizar del path
        int id = Integer.parseInt(path.substring(1));
        HistorialDAO historialDAO = new HistorialDAO();
        // Convierte el cuerpo de la solicitud de JSON a un objeto Historial
        Historial historial = new Gson().fromJson(request.getReader(), Historial.class);
        historial.setId(id);  // Establece el ID en el objeto Historial
        // Actualiza la entrada del historial con el objeto Historial actualizado
        historialDAO.actualizarHistorial(historial);
        // Envía una respuesta con estado 204 (Sin contenido) para indicar que la actualización fue exitosa
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
