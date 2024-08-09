package Modelo;  // Paquete que contiene la clase DAO_login

import Modelo.Conexiondb;  // Importar la clase Conexiondb
import java.sql.Connection;  // Importar la clase Connection de JDBC
import java.sql.PreparedStatement;  // Importar la clase PreparedStatement de JDBC
import java.sql.ResultSet;  // Importar la clase ResultSet de JDBC
import java.sql.SQLException;  // Importar la clase SQLException de JDBC

public class DAO_login {  // Definición de la clase DAO_login

    private Conexiondb conexion;  // Declaración de la variable de conexión

    // Constructor que inicializa la conexión a la base de datos
    public DAO_login() {
        this.conexion = new Conexiondb();  // Inicializar la conexión
    }

    // Método que valida las credenciales del usuario y retorna su rol
    public String validate(String email, String password) {
        String role = null;  // Inicializar la variable para almacenar el rol del usuario
        // Consulta SQL para obtener el rol del usuario basado en el email
        String sql = "SELECT l.password, r.nombre_rol FROM tb_login l "
                + "JOIN tb_usuario u ON l.id_usuario = u.id_usuario "
                + "JOIN tb_roles r ON u.id_rol = r.id_rol "
                + "WHERE l.email = ? AND l.password = ?";

        // Bloque try-with-resources para asegurar el cierre de recursos
        try (Connection cx = conexion.Conectar(); // Obtener la conexión
                 PreparedStatement ps = cx.prepareStatement(sql)) {  // Preparar la consulta SQL

            // Establecer los parámetros de la consulta SQL
            ps.setString(1, email);
            ps.setString(2, password);

            // Ejecutar la consulta
            try (ResultSet rs = ps.executeQuery()) {  // Ejecutar la consulta y obtener los resultados
                if (rs.next()) {  // Verificar si hay resultados
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
        int userId = -1;  // Inicializar la variable para almacenar el id del usuario
        // Consulta SQL para obtener el id del usuario basado en el email
        String sql = "SELECT id_usuario FROM tb_login WHERE email=? AND password=?";

        // Bloque try-with-resources para asegurar el cierre de recursos
        try (Connection cx = conexion.Conectar(); // Obtener la conexión
                 PreparedStatement ps = cx.prepareStatement(sql)) {  // Preparar la consulta SQL

            // Establecer los parámetros de la consulta SQL
            ps.setString(1, email);
            ps.setString(2, password);
            // Ejecutar la consulta
            try (ResultSet rs = ps.executeQuery()) {  // Ejecutar la consulta y obtener los resultados
                if (rs.next()) {  // Verificar si hay resultados
                    userId = rs.getInt("id_usuario");  // Obtener el id del usuario del resultado de la consulta
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepción en caso de error SQL
        }

        return userId;  // Retornar el id del usuario
    }

    // Método combinado para obtener tanto el userId como el rol
    public User validateAndGetUser(String email, String password) {
        User user = null;  // Inicializar la variable para almacenar el usuario
        // Consulta SQL para obtener el id del usuario, la contraseña, el rol y el nombre del usuario basado en el email
        String sql = "SELECT l.id_usuario, r.nombre_rol, u.nombre "
                + "FROM tb_login l "
                + "JOIN tb_usuario u ON l.id_usuario = u.id_usuario "
                + "JOIN tb_roles r ON u.id_rol = r.id_rol "
                + "WHERE l.email = ? AND l.password = ?";

        // Bloque try-with-resources para asegurar el cierre de recursos
        try (Connection cx = conexion.Conectar(); // Obtener la conexión
                 PreparedStatement ps = cx.prepareStatement(sql)) {  // Preparar la consulta SQL

            // Establecer los parámetros de la consulta SQL
            ps.setString(1, email);
            ps.setString(2, password);
            // Ejecutar la consulta
            try (ResultSet rs = ps.executeQuery()) {  // Ejecutar la consulta y obtener los resultados
                if (rs.next()) {  // Verificar si hay resultados
                    int userId = rs.getInt("id_usuario");  // Obtener el id del usuario del resultado de la consulta
                    String role = rs.getString("nombre_rol");  // Obtener el nombre del rol del resultado de la consulta
                    String username = rs.getString("nombre");  // Obtener el nombre del usuario del resultado de la consulta
                    user = new User(userId, role, username);  // Crear una nueva instancia de User con los datos obtenidos
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepción en caso de error SQL
        }

        return user;  // Retornar el usuario
    }
    
    
    // Método para actualizar el rol del usuario
    public boolean updateUserRole(String userId, String newRole) {
        String sql = "UPDATE tb_usuario SET id_rol = ? WHERE id_usuario = ?";
        try (Connection cx = conexion.Conectar();  // Obtener la conexión
             PreparedStatement ps = cx.prepareStatement(sql)) {  // Preparar la consulta

            ps.setInt(1, Integer.parseInt(newRole));  // Establecer el nuevo rol
            ps.setInt(2, Integer.parseInt(userId));  // Establecer el ID del usuario

            int rowsUpdated = ps.executeUpdate();  // Ejecutar la actualización
            return rowsUpdated > 0;  // Retornar true si se actualizaron filas
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de excepción
            return false;  // Retornar false en caso de error
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    // Clase interna para representar al usuario
    public static class User {

        private int userId;  // ID del usuario
        private String role;  // Rol del usuario
        private String username;  // Nombre del usuario

        // Constructor para inicializar los atributos del usuario
        public User(int userId, String role, String username) {
            this.userId = userId;
            this.role = role;
            this.username = username;
        }

        // Método para obtener el ID del usuario
        public int getUserId() {
            return userId;
        }

        // Método para obtener el rol del usuario
        public String getRole() {
            return role;
        }

        // Método para obtener el nombre del usuario
        public String getUsername() {
            return username;
        }
    }
}
