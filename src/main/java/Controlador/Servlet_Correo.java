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
@WebServlet(name = "Servlet_Correo", urlPatterns = {"/ValidarCorreo"})
public class Servlet_Correo extends HttpServlet {

private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        RecuperarContrasena recuperarContrasena = new RecuperarContrasena();

        boolean existe = recuperarContrasena.validarCorreo(email);

        if (existe) {
            request.getSession().setAttribute("emailValidado", email);
            response.getWriter().write("Correo validado.");
        } else {
            response.getWriter().write("El correo electronico no esta registrado.");
        }
    }
}