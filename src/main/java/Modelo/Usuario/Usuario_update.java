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
 public boolean ActualizarPerfil(int userId, String username, String email, String password) {
        Connection cx = conexion.Conectar();
        String sql = "UPDATE tb_usuario SET username = ?, email = ?, password = ? WHERE id_usuario = ?";
        
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password); 
            pstmt.setInt(4, userId);
            pstmt.executeUpdate();
            System.out.println("Perfil actualizado exitosamente");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el perfil: " + e.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }
}