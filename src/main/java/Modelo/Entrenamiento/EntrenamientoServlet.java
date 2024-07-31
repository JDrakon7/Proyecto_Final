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
import java.sql.PreparedStatement;
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
import javax.servlet.http.HttpSession;

@WebServlet("/EntrenamientoServlet")
public class EntrenamientoServlet extends HttpServlet {
    private Conexiondb conexion;

    public EntrenamientoServlet() {
        this.conexion = new Conexiondb();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("obtenerCategorias".equals(action)) {
            obtenerCategorias(response);
        } else if ("leerEntrenamientos".equals(action)) {
            leerEntrenamientos(response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("agregarEntrenamiento".equals(action)) {
            agregarEntrenamiento(request, response);
        } else if ("actualizarEntrenamiento".equals(action)) {
            actualizarEntrenamiento(request, response);
        } else if ("eliminarEntrenamiento".equals(action)) {
            eliminarEntrenamiento(request, response);
        } else if ("agregarCategoria".equals(action)) {
            agregarCategoria(request, response);
        }
    }

    private void obtenerCategorias(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Connection cx = conexion.Conectar();
        String sql = "SELECT * FROM tb_categorias";
        List<Categoria> categorias = new ArrayList<>();

        try (Statement stmt = cx.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombreCategoria(rs.getString("nombre_categoria"));
                categorias.add(categoria);
            }
            String json = new Gson().toJson(categorias);
            out.print(json);
            out.flush();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
    }

    private void leerEntrenamientos(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Connection cx = conexion.Conectar();
        String sql = "SELECT * FROM tb_entrenamiento";
        List<Entrenamiento> entrenamientos = new ArrayList<>();

        try (Statement stmt = cx.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
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

    private void agregarEntrenamiento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pregunta = request.getParameter("pregunta");
        String respuesta = request.getParameter("respuesta");
        int categoria = Integer.parseInt(request.getParameter("categoria"));

        HttpSession session = request.getSession();
        Integer usuarioId = (Integer) session.getAttribute("userId");

        Connection cx = conexion.Conectar();
        String sql = "INSERT INTO tb_entrenamiento (pregunta, respuesta, id_usuario, id_categoria) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setString(1, pregunta);
            pstmt.setString(2, respuesta);
            pstmt.setInt(3, usuarioId);
            pstmt.setInt(4, categoria);
            pstmt.executeUpdate();
            response.getWriter().write("Pregunta y respuesta agregadas exitosamente.");
        } catch (SQLException e) {
            response.getWriter().write("Error al agregar datos: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }

    private void actualizarEntrenamiento(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    private void eliminarEntrenamiento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idEntrenamiento = Integer.parseInt(request.getParameter("id_entrenamiento"));

        Connection cx = conexion.Conectar();
        String sql = "DELETE FROM tb_entrenamiento WHERE id_entrenamiento = ?";

        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setInt(1, idEntrenamiento);
            pstmt.executeUpdate();
            response.getWriter().write("Entrenamiento eliminado exitosamente.");
        } catch (SQLException e) {
            response.getWriter().write("Error al eliminar datos: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }

    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombreCategoria = request.getParameter("nombre_categoria");

        Connection cx = conexion.Conectar();
        String sql = "INSERT INTO tb_categorias (nombre_categoria) VALUES (?)";

        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setString(1, nombreCategoria);
            pstmt.executeUpdate();
            response.getWriter().write("Categoría agregada exitosamente.");
        } catch (SQLException e) {
            response.getWriter().write("Error al agregar datos: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }

    class Categoria {
        private int idCategoria;
        private String nombreCategoria;

        public int getIdCategoria() {
            return idCategoria;
        }

        public void setIdCategoria(int idCategoria) {
            this.idCategoria = idCategoria;
        }

        public String getNombreCategoria() {
            return nombreCategoria;
        }

        public void setNombreCategoria(String nombreCategoria) {
            this.nombreCategoria = nombreCategoria;
        }
    }

    class Entrenamiento {
        private int idEntrenamiento;
        private String pregunta;
        private String respuesta;
        private int idCategoria;
        private int idUsuario;
        private Timestamp fechaActualizacion;

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
