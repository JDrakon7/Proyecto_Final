package Modelo.Historial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modelo.Conexiondb;

public class Eliminar_historial {
    public void eliminarHistorial(int id) {
        Conexiondb conexion = new Conexiondb();
        Connection cx = conexion.Conectar();
        String sql = "DELETE FROM tb_historial WHERE id_historial = ?";
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Historial eliminado exitosamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }
}

