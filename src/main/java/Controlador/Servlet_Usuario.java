/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Conexiondb;  
import com.google.gson.Gson;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author JDBJ
 */
@WebServlet(name = "Servlet_Usuario", urlPatterns = {"/UsuarioServlet"})
public class Servlet_Usuario extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Conexiondb conexion;

    public Servlet_Usuario() {
        this.conexion = new Conexiondb();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("obtenerInformacionUsuario".equals(action)) {
            obtenerInformacionUsuario(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (null != action) switch (action) {
            case "actualizarUsuario":
                actualizarUsuario(request, response);
                break;
            case "eliminarCuenta":
                eliminarCuenta(request, response);
                break;
            default:
                break;
        }
    }

    private void obtenerInformacionUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
             Usuario usuario = new Usuario(
            (int) session.getAttribute("userId"),
            (String) session.getAttribute("username"),
            (String) session.getAttribute("email"),
            (String) session.getAttribute("role")
        );
        if (usuario != null) {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(new Gson().toJson(usuario));
        } else {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write("{\"error\": \"Usuario no encontrado\"}");
        }
    }
    
    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idUsuarioStr = request.getParameter("id_usuario");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String usuario = request.getParameter("usuario");

        if (idUsuarioStr == null || nombre == null || email == null || usuario == null) {
            response.getWriter().write("Error: todos los parámetros son obligatorios.");
            return;
        }

        int idUsuario;
        try {
            idUsuario = Integer.parseInt(idUsuarioStr);
        } catch (NumberFormatException e) {
            response.getWriter().write("Error: formato de ID no válido.");
            return;
        }

        Connection cx = conexion.Conectar();
        String sql = "UPDATE tb_usuario SET nombre = ?, email = ?, usuario = ? WHERE id_usuario = ?";

        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, email);
            pstmt.setString(3, usuario);
            pstmt.setInt(4, idUsuario);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                response.getWriter().write("Usuario actualizado exitosamente.");
            } else {
                response.getWriter().write("Error: no se encontró el usuario con el ID especificado.");
            }
        } catch (SQLException e) {
            response.getWriter().write("Error al actualizar el usuario: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }

    private void eliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            Connection cx = conexion.Conectar();
            String sql = "DELETE FROM tb_usuario WHERE id_usuario = ?";

            try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
                pstmt.setInt(1, usuario.getIdUsuario());
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    session.invalidate();
                    response.getWriter().write("Cuenta eliminada exitosamente.");
                } else {
                    response.getWriter().write("Error: no se encontró el usuario con el ID especificado.");
                }
            } catch (SQLException e) {
                response.getWriter().write("Error al eliminar la cuenta: " + e.getMessage());
            } finally {
                conexion.desconectar();
            }
        } else {
            response.getWriter().write("Error: Usuario no encontrado.");
        }
    }

    public class Usuario {
        private int idUsuario;
        private String nombre;
        private String email;
        private String role;

        // Constructor
        public Usuario(int idUsuario, String nombre, String email, String role) {
            this.idUsuario = idUsuario;
            this.nombre = nombre;
            this.email = email;
            this.role = role;
        }

        // Getters y setters
        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
