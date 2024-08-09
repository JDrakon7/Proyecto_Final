package Controlador;

import Modelo.Usuario.Crear_usuario;  // Importa la clase Crear_usuario que maneja la lógica de creación de usuarios
import java.io.IOException;  // Importa la clase IOException para manejar errores de entrada/salida
import javax.servlet.ServletException;  // Importa la clase ServletException para manejar errores específicos de servlets
import javax.servlet.annotation.WebServlet;  // Importa la anotación WebServlet para definir el servlet
import javax.servlet.http.HttpServlet;  // Importa la clase HttpServlet para la funcionalidad del servlet
import javax.servlet.http.HttpServletRequest;  // Importa la clase HttpServletRequest para manejar solicitudes HTTP
import javax.servlet.http.HttpServletResponse;  // Importa la clase HttpServletResponse para manejar respuestas HTTP

@WebServlet("/crear_usuario")  // Define el URL pattern para el servlet. Este servlet se accede mediante la URL "/crear_usuario"
public class Servlet_crear_usuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Método que maneja las solicitudes HTTP POST para crear un nuevo usuario

        // Obtiene parámetros del formulario
        String nombre = request.getParameter("nombre");  // Obtiene el nombre del usuario desde la solicitud
        String email = request.getParameter("email");  // Obtiene el email del usuario desde la solicitud
        String confirmarEmail = request.getParameter("confirmar_email");  // Obtiene la confirmación del email desde la solicitud
        String password = request.getParameter("password");  // Obtiene la contraseña del usuario desde la solicitud
        String confirmarPassword = request.getParameter("confirmar_password");  // Obtiene la confirmación de la contraseña desde la solicitud

        // Verifica si los emails coinciden
        if (!email.equals(confirmarEmail)) {
            // Redirige a index.jsp con un mensaje si los emails no coinciden
            response.sendRedirect("index.jsp?message=email_mismatch");
            return;
        }

        // Verifica si las contraseñas coinciden
        if (!password.equals(confirmarPassword)) {
            // Redirige a index.jsp con un mensaje si las contraseñas no coinciden
            response.sendRedirect("index.jsp?message=password_mismatch");
            return;
        }

        // Configura datos adicionales para el nuevo usuario
        String fechaRegistro = java.time.LocalDate.now().toString();  // Obtiene la fecha actual en formato String
        String rol = "1";  // Asigna el rol de usuario. Puedes cambiar esto según tus necesidades

        // Crea una instancia de Crear_usuario y llama al método para crear el usuario
        Crear_usuario crearUsuario = new Crear_usuario();
        boolean isUserCreated = crearUsuario.crearUsuario(nombre, email, fechaRegistro, password, Integer.parseInt(rol));

        if (isUserCreated) {
            // Envia una respuesta HTML con un script JavaScript que muestra una alerta y redirige a index.jsp
            response.setContentType("text/html");
            response.getWriter().write("<html><body>");
            response.getWriter().write("<script type='text/javascript'>");
            response.getWriter().write("alert('Cuenta creada exitosamente.');");
            response.getWriter().write("window.location.href = 'index.jsp';");
            response.getWriter().write("</script>");
            response.getWriter().write("</body></html>");
        } else {
            // Redirige a index.jsp con un mensaje si la creación del usuario falla
            response.sendRedirect("index.jsp?message=creation_failed");
        }
    }
}
