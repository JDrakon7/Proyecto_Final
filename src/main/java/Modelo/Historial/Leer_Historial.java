package Modelo.Historial;

import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Leer_Historial {
    private Connection cx;

    public Leer_Historial() {
        // Crear una nueva instancia de Conexiondb y establecer la conexión
        Conexiondb conexion = new Conexiondb();
        cx = conexion.Conectar();
    }

    public List<Historial> leerHistorial() {
        List<Historial> historialList = new ArrayList<>();
        String sql = "SELECT * FROM tb_historial";
        try (Statement stmt = cx.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Historial historial = new Historial();
                historial.setId(rs.getInt("id_historial"));
                historial.setFecha(rs.getDate("fecha"));
                historial.setHora(rs.getTime("hora"));
                historial.setIdUsuario(rs.getInt("id_usuario"));
                historial.setPregunta(rs.getString("pregunta"));
                historial.setRespuesta(rs.getString("respuesta"));
                historialList.add(historial);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return historialList;
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


