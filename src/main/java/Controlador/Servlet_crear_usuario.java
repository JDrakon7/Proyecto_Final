package Controlador;

import Modelo.Usuario.Crear_usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/crear_usuario")  // Anotación que mapea este servlet a la URL /crear_usuario
public class Servlet_crear_usuario extends HttpServlet {

    // Método que maneja las solicitudes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros de la solicitud
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String confirmarEmail = request.getParameter("confirmar_email");
        String password = request.getParameter("password");
        String confirmarPassword = request.getParameter("confirmar_password");

        // Verificar que los emails coinciden
        if (!email.equals(confirmarEmail)) {
            response.sendRedirect("index.jsp?error=email_mismatch");
            return;
        }

        // Verificar que las contraseñas coinciden
        if (!password.equals(confirmarPassword)) {
            response.sendRedirect("index.jsp?error=password_mismatch");
            return;
        }

        // Fecha de registro actual (puede ser obtenida del sistema)
        String fechaRegistro = java.time.LocalDate.now().toString();
        // Asignar un rol por defecto (puede ser cambiado según la lógica de la aplicación)
        String rol = "1";  // rol de usuario

        // Crear una instancia de Crear_usuario para interactuar con la base de datos
        Crear_usuario crearUsuario = new Crear_usuario();

        // Llamar al método para crear un usuario
        boolean isUserCreated = crearUsuario.crearUsuario(nombre, email, fechaRegistro, password, Integer.parseInt(rol));

        // Depuración: imprimir en consola el resultado de la creación del usuario
        System.out.println("Usuario creado: " + isUserCreated);

        // Redirigir a una página de éxito o la lista de usuarios
        if (isUserCreated) {
            response.sendRedirect("index.jsp?success=true");
        } else {
            response.sendRedirect("index.jsp?error=creation_failed");
        }
    }
}



         
            
    
    

