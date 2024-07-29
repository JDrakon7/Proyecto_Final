
package Modelo.Login;

import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Crear_login {
    
    private Conexiondb conexion;
    
    public Crear_login() {
        this.conexion = new Conexiondb();
    }
    public void crearLogin(int idUsuario, String email, String password) {
        Connection cx = conexion.Conectar();
        String sql = "INSERT INTO tb_login (id_usuario, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setInt(1, idUsuario);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            System.out.println("Login creado exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al crear login: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }
}
