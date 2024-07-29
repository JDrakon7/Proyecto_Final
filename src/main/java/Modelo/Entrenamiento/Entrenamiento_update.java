
package Modelo.Entrenamiento;

import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Entrenamiento_update {
    
     private Conexiondb conexion;

    public Entrenamiento_update() {
        this.conexion = new Conexiondb();
     
    }
        public void Entrenamiento_update(int idEntrenamiento, String pregunta, String respuesta) {
        Connection cx = conexion.Conectar();
        String sql = "UPDATE tb_entrenamiento SET pregunta = ?, respuesta = ? WHERE id_entrenamiento = ?";
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setString(1, pregunta);
            pstmt.setString(2, respuesta);
            pstmt.setInt(3, idEntrenamiento);
            pstmt.executeUpdate();
            System.out.println("Entrenamiento actualizado exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar entrenamiento: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }
}
    
