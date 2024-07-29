
package Modelo.Entrenamiento;

import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Eliminar_entrenamiento {
     private Conexiondb conexion;

    public Eliminar_entrenamiento() {
        this.conexion = new Conexiondb();
    }
    
      public void eliminarEntrenamiento(int idEntrenamiento) {
        Connection cx = conexion.Conectar();
        String sql = "DELETE FROM tb_entrenamiento WHERE id_entrenamiento = ?";
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setInt(1, idEntrenamiento);
            pstmt.executeUpdate();
            System.out.println("Entrenamiento eliminado exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar entrenamiento: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }
}

