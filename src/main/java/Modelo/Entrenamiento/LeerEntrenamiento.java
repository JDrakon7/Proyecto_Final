/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Modelo.Entrenamiento;

import Modelo.Conexiondb;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LeerEntrenamiento")
public class LeerEntrenamiento extends HttpServlet {

    private Conexiondb conexion;

    public LeerEntrenamiento() {
        this.conexion = new Conexiondb();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Connection cx = conexion.Conectar();
        String sql = "SELECT * FROM tb_entrenamiento";
        List<Entrenamiento> entrenamientos = new ArrayList<>();

        try (Statement stmt = cx.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Entrenamiento entrenamiento = new Entrenamiento();
                entrenamiento.setIdEntrenamiento(rs.getInt("id_entrenamiento"));
                entrenamiento.setPregunta(rs.getString("pregunta"));
                entrenamiento.setRespuesta(rs.getString("respuesta"));
                entrenamiento.setIdCategoria(rs.getInt("id_categoria"));
                entrenamiento.setIdUsuario(rs.getInt("id_usuario"));
                entrenamiento.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion"));
                entrenamientos.add(entrenamiento);
            }
            String json = new Gson().toJson(entrenamientos);
            out.print(json);
            out.flush();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
    }

    public class Entrenamiento {

        private int idEntrenamiento;
        private String pregunta;
        private String respuesta;
        private int idCategoria;
        private int idUsuario;
        private Timestamp fechaActualizacion;

        // Getters y Setters
        public int getIdEntrenamiento() {
            return idEntrenamiento;
        }

        public void setIdEntrenamiento(int idEntrenamiento) {
            this.idEntrenamiento = idEntrenamiento;
        }

        public String getPregunta() {
            return pregunta;
        }

        public void setPregunta(String pregunta) {
            this.pregunta = pregunta;
        }

        public String getRespuesta() {
            return respuesta;
        }

        public void setRespuesta(String respuesta) {
            this.respuesta = respuesta;
        }

        public int getIdCategoria() {
            return idCategoria;
        }

        public void setIdCategoria(int idCategoria) {
            this.idCategoria = idCategoria;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public Timestamp getFechaActualizacion() {
            return fechaActualizacion;
        }

        public void setFechaActualizacion(Timestamp fechaActualizacion) {
            this.fechaActualizacion = fechaActualizacion;
        }
    }
}
