package Modelo.Usuario;

import Modelo.Conexiondb; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Eliminar_usuario {

    private Connection cx;

    public Eliminar_usuario() {
        // Crear una nueva instancia de Conexiondb y establecer la conexión
        Conexiondb conexion = new Conexiondb();
        cx = conexion.Conectar();
    }

    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM tb_usuario WHERE id_usuario = ?";
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void desconectar() {
        try {
            cx.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
