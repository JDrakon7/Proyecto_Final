/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Modelo;

import Modelo.Usuario.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActualizarPerfil {
    private Conexiondb conexion;

    public ActualizarPerfil() {
        this.conexion = new Conexiondb();
    }

    public boolean actualizarPerfil(int userId, String username, String email, String password) {
        boolean isUpdated = false;
        Connection cx = null;
        try {
            cx = conexion.Conectar();
            if (cx == null) {
                throw new SQLException("No se pudo establecer conexión con la base de datos.");
            }

            String checkEmailSql = "SELECT id_usuario FROM tb_usuario WHERE email = ? AND id_usuario != ?";
            String updateProfileSql = "UPDATE tb_usuario SET nombre = ? WHERE id_usuario = ?";
            String updateLoginSql = "UPDATE tb_login SET email = ?, password = ? WHERE id_usuario = ?";

            try (PreparedStatement checkStmt = cx.prepareStatement(checkEmailSql);
                 PreparedStatement updateProfileStmt = cx.prepareStatement(updateProfileSql);
                 PreparedStatement updateLoginStmt = cx.prepareStatement(updateLoginSql)) {

                // Verificar si el correo ya está en uso por otro usuario
                checkStmt.setString(1, email);
                checkStmt.setInt(2, userId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    return false; // El correo ya está en uso por otro usuario
                }

                // Actualizar el perfil del usuario
                updateProfileStmt.setString(1, username);
                updateProfileStmt.setInt(2, userId);
                int profileRowsAffected = updateProfileStmt.executeUpdate();

                // Actualizar las credenciales de inicio de sesión
                updateLoginStmt.setString(1, email);
                updateLoginStmt.setString(2, password);
                updateLoginStmt.setInt(3, userId);
                int loginRowsAffected = updateLoginStmt.executeUpdate();

                isUpdated = (profileRowsAffected > 0 || loginRowsAffected > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            if (cx != null) {
                conexion.desconectar();
            }
        }
        return isUpdated;
    }

    public Usuario getUserInfo(int userId) {
        Usuario usuario = null;
        Connection cx = null;
        try {
            cx = conexion.Conectar();
            if (cx == null) {
                throw new SQLException("No se pudo establecer conexión con la base de datos.");
            }

            String sql = "SELECT id_usuario, nombre, email, role FROM tb_usuario WHERE id_usuario = ?";
            try (PreparedStatement stmt = cx.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id_usuario");
                    String nombre = rs.getString("nombre");
                    String email = rs.getString("email");
                    String role = rs.getString("role");
                    usuario = new Usuario(id, nombre, email, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            if (cx != null) {
                conexion.desconectar();
            }
        }
        return usuario;
    }
}

