package Modelo.Historial;

import Modelo.Conexiondb; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Historial_update {
    private Connection cx;

    public Historial_update() {
        // Crear una nueva instancia de Conexiondb y establecer la conexión
        Conexiondb conexion = new Conexiondb();
        cx = conexion.Conectar();
    }

    public void actualizarHistorial(int id, String fecha, int idUsuario, String pregunta, String respuesta) {
        String sql = "UPDATE tb_historial SET fecha = ?, id_usuario = ?, pregunta = ?, respuesta = ? WHERE id_historial = ?";
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setString(1, fecha);
            pstmt.setInt(2, idUsuario);
            pstmt.setString(3, pregunta);
            pstmt.setString(4, respuesta);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            System.out.println("Historial actualizado exitosamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (cx != null && !cx.isClosed()) {
                cx.close();
                System.out.println("Conexión cerrada exitosamente");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

