package Controlador;

import Modelo.DAO_login; // Importa la clase DAO_login del paquete Modelo, que maneja operaciones relacionadas con el acceso a datos para el login de usuarios.
import Modelo.DAO_login.User; // Importa la clase User anidada en DAO_login, probablemente una representación del usuario en el contexto del login.
import java.io.IOException; // Importa la clase IOException para manejar excepciones relacionadas con operaciones de entrada/salida, como errores al leer o escribir datos.
import javax.servlet.ServletException; // Importa la clase ServletException para manejar excepciones que pueden ocurrir durante la ejecución de servlets.
import javax.servlet.annotation.WebServlet; // Importa la anotación WebServlet para definir y configurar un servlet en una aplicación web de manera declarativa.
import javax.servlet.http.HttpServlet; // Importa la clase HttpServlet, que es la clase base para crear servlets que responden a solicitudes HTTP.
import javax.servlet.http.HttpServletRequest; // Importa la clase HttpServletRequest para manejar la solicitud HTTP enviada por el cliente.
import javax.servlet.http.HttpServletResponse; // Importa la clase HttpServletResponse para manejar la respuesta HTTP que se envía al cliente.
import javax.servlet.http.HttpSession; // Importa la clase HttpSession para manejar la sesión del usuario en una aplicación web, permitiendo almacenar datos específicos de la sesión.

@WebServlet("/login")  // Anotación que mapea este servlet a la URL /login
public class Login_servlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;  // Identificador de versión para la serialización

    // Método que maneja las solicitudes POST
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        DAO_login daoLogin = new DAO_login();
        User user = daoLogin.validateAndGetUser(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("role", user.getRole());
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("nombre", user.getUsername());
            response.getWriter().write("success");  // Enviar respuesta de éxito
        } else {
            response.getWriter().write("invalid_credentials");  // Enviar respuesta de error
        }
    }

    
    // Método que maneja las solicitudes GET para cierre de sesión
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");  // Obtener la acción de la solicitud
       if ("logout".equals(action)) {
            HttpSession session = request.getSession();
            session.invalidate(); // Invalidar la sesión
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.
            response.sendRedirect("index.jsp"); // Redirigir a la página de inicio de sesión
        }
    }
}
