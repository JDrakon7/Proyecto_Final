package Controlador;

import Modelo.DAO_login;
import Modelo.DAO_login.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")  // Anotación que mapea este servlet a la URL /login
public class Login_servlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;  // Identificador de versión para la serialización

    // Método que maneja las solicitudes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros de la solicitud
        String email = request.getParameter("email");  // Obtener el email del usuario
        String password = request.getParameter("password");  // Obtener la contraseña del usuario

        // Crear una instancia del DAO_login
        DAO_login daoLogin = new DAO_login();
        // Validar las credenciales del usuario y obtener el objeto User
        User user = daoLogin.validateAndGetUser(email, password);

        if (user != null) {
            // Si el usuario es válido, se crea una sesión y se redirige al usuario a la interfaz
            HttpSession session = request.getSession();  // Crear una nueva sesión
            session.setAttribute("email", email);  // Guardar el email en la sesión
            session.setAttribute("role", user.getRole());  // Guardar el rol en la sesión
            session.setAttribute("userId", user.getUserId());  // Guardar el user_id en la sesión
            session.setAttribute("nombre", user.getUsername());  // Guardar el nombre del usuario en la sesión
            response.sendRedirect("interfaz.jsp");  // Redirigir a la interfaz principal
        } else {
            // Si la validación falla, se redirige al usuario a la página de inicio con un error
            response.sendRedirect("index.jsp?error=invalid_credentials");  // Redirigir a la página de inicio con un mensaje de error
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