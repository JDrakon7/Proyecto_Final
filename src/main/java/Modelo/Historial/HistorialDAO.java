/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Historial;

import Modelo.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistorialDAO {
    private Connection cx;

    public HistorialDAO() {
        Conexiondb conexion = new Conexiondb();
        cx = conexion.Conectar();
    }

   // Método para leer el historial de un usuario específico
    public List<Historial> leerHistorial(int idUsuario) {
        List<Historial> historialList = new ArrayList<>();
        String sql = "SELECT * FROM tb_historial WHERE id_usuario = ?";
        try (PreparedStatement stmt = cx.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return historialList;
    }

    // Método para agregar una entrada al historial
    public void agregarHistorial(Historial historial) {
        String sql = "INSERT INTO tb_historial (fecha, hora, id_usuario, pregunta, respuesta, tipo_interaccion) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = cx.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(historial.getFecha().getTime()));
            stmt.setTime(2, new java.sql.Time(historial.getHora().getTime()));
            stmt.setInt(3, historial.getIdUsuario());
            stmt.setString(4, historial.getPregunta());
            stmt.setString(5, historial.getRespuesta());
            stmt.executeUpdate();
            System.out.println("Historial creado exitosamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar una entrada del historial
    public void eliminarHistorial(int id) {
        String sql = "DELETE FROM tb_historial WHERE id_historial = ?";
        try (PreparedStatement stmt = cx.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Historial eliminado exitosamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para actualizar una entrada del historial
    public void actualizarHistorial(Historial historial) {
        String sql = "UPDATE tb_historial SET fecha = ?, hora = ?, id_usuario = ?, pregunta = ?, respuesta = ?, tipo_interaccion = ? WHERE id_historial = ?";
        try (PreparedStatement stmt = cx.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(historial.getFecha().getTime()));
            stmt.setTime(2, new java.sql.Time(historial.getHora().getTime()));
            stmt.setInt(3, historial.getIdUsuario());
            stmt.setString(4, historial.getPregunta());
            stmt.setString(5, historial.getRespuesta());
            stmt.setInt(6, historial.getId());
            stmt.executeUpdate();
            System.out.println("Historial actualizado exitosamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para desconectar
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