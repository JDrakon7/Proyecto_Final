/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Modelo.Historial;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/verHistorial")
public class VerHistorialServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Leer_Historial leerHistorial = new Leer_Historial();
        List<Historial> historialList = leerHistorial.leerHistorial();
        leerHistorial.desconectar();
        
        request.setAttribute("historiales", historialList);
        request.getRequestDispatcher("historial.jsp").forward(request, response);
    }
}

