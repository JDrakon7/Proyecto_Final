/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Conexiondb;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JDBJ
 */
@WebServlet(name = "ServletEliminarEntrenamiento", urlPatterns = {"/ServletEliminarEntrenamiento"})
public class ServletEliminarEntrenamiento extends HttpServlet {

    private Conexiondb conexion;

    public ServletEliminarEntrenamiento() {
        this.conexion = new Conexiondb();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEntrenamiento = Integer.parseInt(request.getParameter("idEntrenamiento"));

        Connection cx = conexion.Conectar();
        String sql = "DELETE FROM tb_entrenamiento WHERE id_entrenamiento = ?";
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setInt(1, idEntrenamiento);
            pstmt.executeUpdate();
            response.getWriter().write("Entrenamiento eliminado exitosamente.");
        } catch (SQLException e) {
            response.getWriter().write("Error al eliminar entrenamiento: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }
}