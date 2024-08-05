/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;


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

@WebServlet("/AgregarEntrenamiento")
public class ServletAgregarEntrenamiento extends HttpServlet {
    private Conexiondb conexion;

    public ServletAgregarEntrenamiento() {
        this.conexion = new Conexiondb();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pregunta = request.getParameter("pregunta");
        String respuesta = request.getParameter("respuesta");

        Connection cx = conexion.Conectar();
        String sql = "INSERT INTO tb_entrenamiento (pregunta, respuesta) VALUES (?, ?)";

        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setString(1, pregunta);
            pstmt.setString(2, respuesta);
            pstmt.executeUpdate();
            response.getWriter().write("Pregunta y respuesta agregadas exitosamente.");
        } catch (SQLException e) {
            response.getWriter().write("Error al agregar datos: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }
}
