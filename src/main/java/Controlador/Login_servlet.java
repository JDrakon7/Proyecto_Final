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
            if ("inhabilitado".equals(role)) {
                response.getWriter().write("inhabilitado");  // Usuario inhabilitado
            } else {
                response.getWriter().write("success");  // Autenticación exitosa
            }
        } else {
            response.getWriter().write("invalid_credentials");  // Credenciales incorrectas
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
