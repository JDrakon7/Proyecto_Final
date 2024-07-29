package Modelo;

import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_login {
    
    private Conexiondb conexion;

    // Constructor que inicializa la conexión a la base de datos
    public DAO_login() {
        this.conexion = new Conexiondb();
    }
    
    // Método que valida las credenciales del usuario y retorna su rol
    public String validate(String email, String password) {
        String role = null;
        // Consulta SQL para obtener el rol del usuario basado en el email y contraseña
        String sql = "SELECT r.nombre_rol FROM tb_login l " +
                     "JOIN tb_usuario u ON l.id_usuario = u.id_usuario " +
                     "JOIN tb_roles r ON u.id_rol = r.id_rol " +
                     "WHERE l.email = ? AND l.password = ?";

        // Bloque try-with-resources para asegurar el cierre de recursos
        try (Connection cx = conexion.Conectar();
             PreparedStatement ps = cx.prepareStatement(sql)) {
             
            // Establecer los parámetros de la consulta SQL
            ps.setString(1, email);
            ps.setString(2, password);
            
            // Ejecutar la consulta
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Obtener el nombre del rol del resultado de la consulta
                    role = rs.getString("nombre_rol");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepción en caso de error SQL
        }

        return role;  // Retornar el rol del usuario
    }


// Método que valida las credenciales del usuario y retorna el id del usuario
    public int validateAndGetUserId(String email, String password) {
        int userId = -1;
        String sql = "SELECT id_usuario FROM tb_login WHERE email=? AND password=?";

        try (Connection cx = conexion.Conectar();
             PreparedStatement ps = cx.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("id_usuario");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }

    // Método combinado para obtener tanto el userId como el rol
    public User validateAndGetUser(String email, String password) {
        User user = null;
        String sql = "SELECT l.id_usuario, r.nombre_rol FROM tb_login l " +
                     "JOIN tb_usuario u ON l.id_usuario = u.id_usuario " +
                     "JOIN tb_roles r ON u.id_rol = r.id_rol " +
                     "WHERE l.email = ? AND l.password = ?";

        try (Connection cx = conexion.Conectar();
             PreparedStatement ps = cx.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("id_usuario");
                    String role = rs.getString("nombre_rol");
                    user = new User(userId, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static class User {
        private int userId;
        private String role;

        public User(int userId, String role) {
            this.userId = userId;
            this.role = role;
        }

        public int getUserId() {
            return userId;
        }

        public String getRole() {
            return role;
        }
    }
}