package Modelo.Usuario;

import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Usuario_update {
    
    private Conexiondb conexion;
    
     public Usuario_update() {
        this.conexion = new Conexiondb();
    }
    public void actualizarUsuario(int id, String nombre, String email, String fechaRegistro, String rol) {
        Connection cx = conexion.Conectar();
        String sql = "UPDATE tb_usuario SET nombre = ?, email = ?, fecha_registro = ?, rol = ? WHERE id_usuario = ?";
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, email);
            pstmt.setString(3, fechaRegistro);
            pstmt.setString(4, rol);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            System.out.println("Usuario actualizado exitosamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
