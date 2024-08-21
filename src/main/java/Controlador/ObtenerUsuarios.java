/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.DAO_login;
import Modelo.DAO_login.User;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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