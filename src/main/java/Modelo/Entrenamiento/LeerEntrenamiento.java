/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Entrenamiento;

import Modelo.Conexiondb;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


public class LeerEntrenamiento extends HttpServlet {
    private Conexiondb conexion;

    public LeerEntrenamiento() {
        this.conexion = new Conexiondb();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Entrenamiento> entrenamientos = new ArrayList<>();
        Connection cx = conexion.Conectar();
        String sql = "SELECT * FROM tb_entrenamiento";
        try (Statement stmt = cx.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Entrenamiento entrenamiento = new Entrenamiento();
                entrenamiento.setIdEntrenamiento(rs.getInt("id_entrenamiento"));
                entrenamiento.setPregunta(rs.getString("pregunta"));
                entrenamiento.setRespuesta(rs.getString("respuesta"));
                entrenamiento.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion"));
                entrenamientos.add(entrenamiento);
            }
        } catch (SQLException e) {
            response.getWriter().write("Error al leer entrenamiento: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }

        // Convertir la lista de entrenamientos a JSON y enviarla en la respuesta
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(entrenamientos));
    }
}
