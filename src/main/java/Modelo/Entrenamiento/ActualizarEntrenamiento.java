/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Modelo.Entrenamiento;

import Modelo.Conexiondb;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ActualizarEntrenamiento")
public class ActualizarEntrenamiento extends HttpServlet {
    private Conexiondb conexion;

    public ActualizarEntrenamiento() {
        this.conexion = new Conexiondb();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEntrenamiento = Integer.parseInt(request.getParameter("id_entrenamiento"));
        String pregunta = request.getParameter("pregunta");
        String respuesta = request.getParameter("respuesta");
        int categoria = Integer.parseInt(request.getParameter("categoria"));

        Connection cx = conexion.Conectar();
        String sql = "UPDATE tb_entrenamiento SET pregunta = ?, respuesta = ?, id_categoria = ? WHERE id_entrenamiento = ?";

        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setString(1, pregunta);
            pstmt.setString(2, respuesta);
            pstmt.setInt(3, categoria);
            pstmt.setInt(4, idEntrenamiento);
            pstmt.executeUpdate();
            response.getWriter().write("Entrenamiento actualizado exitosamente.");
        } catch (SQLException e) {
            response.getWriter().write("Error al actualizar datos: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }
}

