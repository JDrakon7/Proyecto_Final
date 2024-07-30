
package Modelo.Entrenamiento;

import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Leer_entrenamiento {
    
    private Conexiondb conexion;
    
     public Leer_entrenamiento() {
         
        this.conexion = new Conexiondb();
    }
     public void leerEntrenamiento() {
        Connection cx = conexion.Conectar();
        String sql = "SELECT * FROM tb_entrenamiento";
        try (Statement stmt = cx.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id_entrenamiento") + "\t" +
                        rs.getString("pregunta") + "\t" +
                        rs.getString("respuesta") + "\t" +
                        rs.getTimestamp("fecha_actualizacion"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer entrenamiento: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }
}

