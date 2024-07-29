/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Modelo.Entrenamiento;

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
import org.json.JSONObject;

@WebServlet("/AgregarCategoria")
public class AgregarCategoria extends HttpServlet {
    private Conexiondb conexion;

    public AgregarCategoria() {
        this.conexion = new Conexiondb();
    }

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreCategoria = request.getParameter("nombre_categoria");

        Connection cx = conexion.Conectar();
        String sql = "INSERT INTO tb_categorias (nombre_categoria) VALUES (?)";

        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setString(1, nombreCategoria);
            pstmt.executeUpdate();
            response.getWriter().write("Categoría agregada exitosamente.");
        } catch (SQLException e) {
            response.getWriter().write("Error al agregar categoría: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }
}