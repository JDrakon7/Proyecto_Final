package Modelo.Usuario;

import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crear_usuario {

    private Conexiondb conexion;

    // Constructor que inicializa la conexión a la base de datos
    public Crear_usuario() {
        this.conexion = new Conexiondb();
    }

    // Método para crear un nuevo usuario en la base de datos
    public boolean crearUsuario(String nombre, String email, String fechaRegistro, String password, int rol) {
        String sqlUsuario = "INSERT INTO tb_usuario (nombre, email, fecha_registro, id_rol) VALUES (?, ?, ?, ?)";
        String sqlLogin = "INSERT INTO tb_login (id_usuario, email, password) VALUES (?, ?, ?)";

        Connection cx = null; // Declarar la conexión fuera del bloque try para poder usarla en catch
        try {
            cx = conexion.Conectar(); // Conectar a la base de datos
            cx.setAutoCommit(false);  // Desactivar auto-commit para transacciones

            try (PreparedStatement psUsuario = cx.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS)) {
                // Establecer los parámetros para insertar en tb_usuario
                psUsuario.setString(1, nombre);
                psUsuario.setString(2, email);
                psUsuario.setString(3, fechaRegistro);
                psUsuario.setInt(4, rol);
                psUsuario.executeUpdate();
                
                // Obtener el ID generado del usuario
                try (ResultSet rs = psUsuario.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idUsuario = rs.getInt(1);

                        try (PreparedStatement psLogin = cx.prepareStatement(sqlLogin)) {
                            // Establecer los parámetros para insertar en tb_login
                            psLogin.setInt(1, idUsuario);
                            psLogin.setString(2, email);
                            psLogin.setString(3, password); // Almacenar la contraseña sin encriptar
                            psLogin.executeUpdate();
                        }
                    }
                }
            }

            cx.commit();  // Confirmar la transacción
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (cx != null) cx.rollback();  // Revertir la transacción en caso de error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            if (cx != null) {
                try {
                    cx.close(); // Cerrar la conexión
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

