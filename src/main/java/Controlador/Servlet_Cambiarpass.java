/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.RecuperarContrasena;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JDBJ
 */
@WebServlet(name = "Servlet_Cambiarpass", urlPatterns = {"/contrasena"})
public class Servlet_Cambiarpass extends HttpServlet {

   private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("emailValidado");
        String nuevaContrasena = request.getParameter("password");

        if (email == null) {
            response.getWriter().write("Error: No se ha validado el correo.");
            return;
        }

        RecuperarContrasena recuperarContrasena = new RecuperarContrasena();
        boolean actualizada = recuperarContrasena.actualizarContrasena(email, nuevaContrasena);

        if (actualizada) {
            request.getSession().removeAttribute("emailValidado");
            response.getWriter().write("Contraseña cambiada exitosamente.");
        } else {
            response.getWriter().write("Error al cambiar la contraseña.");
        }
    }
}