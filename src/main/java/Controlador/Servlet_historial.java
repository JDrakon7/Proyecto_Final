/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Historial.Historial;
import Modelo.Historial.HistorialDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JDBJ
 */
@WebServlet(name = "Servlet_historial", urlPatterns = {"/historial"})
public class Servlet_historial extends HttpServlet {
private static final long serialVersionUID = 1L;

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idUsuario = (int) session.getAttribute("userId");

        HistorialDAO historialDAO = new HistorialDAO();
        List<Historial> historialList = historialDAO.leerHistorial(idUsuario);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write("[");
        for (int i = 0; i < historialList.size(); i++) {
            Historial historial = historialList.get(i);
            out.write("{");
            out.write("\"id\": " + historial.getId() + ",");
            out.write("\"fecha\": \"" + historial.getFecha() + "\",");
            out.write("\"hora\": \"" + historial.getHora() + "\",");
            out.write("\"pregunta\": \"" + historial.getPregunta() + "\",");
            out.write("\"respuesta\": \"" + historial.getRespuesta() + "\",");
            out.write("}");
            if (i < historialList.size() - 1) {
                out.write(",");
            }
        }
        out.write("]");
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idUsuario = (int) session.getAttribute("userId");

        String pregunta = request.getParameter("pregunta");
        String respuesta = request.getParameter("respuesta");

        Historial historial = new Historial();
        historial.setFecha(new Date());
        historial.setHora(new Date());
        historial.setIdUsuario(idUsuario);
        historial.setPregunta(pregunta);
        historial.setRespuesta(respuesta);

        HistorialDAO historialDAO = new HistorialDAO();
        historialDAO.agregarHistorial(historial);

        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}