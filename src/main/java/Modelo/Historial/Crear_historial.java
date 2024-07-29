package Modelo.Historial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modelo.Conexiondb;

public class Crear_historial {
    public void crearHistorial(String fecha, int idUsuario, String pregunta, String respuesta) {
        Conexiondb conexion = new Conexiondb();
        Connection cx = conexion.Conectar();
        String sql = "INSERT INTO tb_historial (fecha, id_usuario, pregunta, respuesta) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            pstmt.setString(1, fecha);
            pstmt.setInt(2, idUsuario);
            pstmt.setString(3, pregunta);
            pstmt.setString(4, respuesta);
            pstmt.executeUpdate();
            System.out.println("Historial creado exitosamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }
}
