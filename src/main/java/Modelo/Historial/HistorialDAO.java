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

// Clase DAO para realizar operaciones CRUD en la tabla tb_historial
public class HistorialDAO {
    private Connection cx;  // Conexión a la base de datos

    // Constructor que inicializa la conexión a la base de datos
    public HistorialDAO() {
        Conexiondb conexion = new Conexiondb();  // Instancia de la clase de conexión a la base de datos
        cx = conexion.Conectar();  // Conexión establecida y asignada a la variable cx
    }

    // Método para leer el historial de un usuario específico
    // Este método toma como parámetro el id del usuario y devuelve una lista de objetos Historial
    public List<Historial> leerHistorial(int idUsuario) {
        List<Historial> historialList = new ArrayList<>();  // Lista para almacenar los resultados
        String sql = "SELECT * FROM tb_historial WHERE id_usuario = ?";  // Consulta SQL para obtener el historial del usuario

        try (PreparedStatement stmt = cx.prepareStatement(sql)) {  // Preparar la consulta SQL
            stmt.setInt(1, idUsuario);  // Asignar el idUsuario al parámetro de la consulta
            try (ResultSet rs = stmt.executeQuery()) {  // Ejecutar la consulta y obtener los resultados
                while (rs.next()) {  // Iterar sobre los resultados
                    Historial historial = new Historial();  // Crear una nueva instancia de Historial
                    historial.setId(rs.getInt("id_historial"));  // Establecer el ID del historial
                    historial.setFecha(rs.getDate("fecha"));  // Establecer la fecha de la interacción
                    historial.setHora(rs.getTime("hora"));  // Establecer la hora de la interacción
                    historial.setIdUsuario(rs.getInt("id_usuario"));  // Establecer el ID del usuario
                    historial.setPregunta(rs.getString("pregunta"));  // Establecer la pregunta realizada
                    historial.setRespuesta(rs.getString("respuesta"));  // Establecer la respuesta dada
                    historialList.add(historial);  // Agregar el objeto Historial a la lista
                }
            }
        } catch (SQLException e) {  // Capturar cualquier excepción SQL
            System.out.println(e.getMessage());  // Imprimir el mensaje de error
        }
        return historialList;  // Devolver la lista de historial
    }

    // Método para agregar una entrada al historial
    // Este método toma un objeto Historial como parámetro y lo inserta en la base de datos
    public void agregarHistorial(Historial historial) {
        String sql = "INSERT INTO tb_historial (fecha, hora, id_usuario, pregunta, respuesta) VALUES (?, ?, ?, ?, ?)";  // Consulta SQL para insertar un nuevo registro

        try (PreparedStatement stmt = cx.prepareStatement(sql)) {  // Preparar la consulta SQL
            stmt.setDate(1, new java.sql.Date(historial.getFecha().getTime()));  // Asignar la fecha
            stmt.setTime(2, new java.sql.Time(historial.getHora().getTime()));  // Asignar la hora
            stmt.setInt(3, historial.getIdUsuario());  // Asignar el ID del usuario
            stmt.setString(4, historial.getPregunta());  // Asignar la pregunta
            stmt.setString(5, historial.getRespuesta());  // Asignar la respuesta
            stmt.executeUpdate();  // Ejecutar la consulta para insertar el registro
            System.out.println("Historial creado exitosamente");  // Mensaje de éxito
        } catch (SQLException e) {  // Capturar cualquier excepción SQL
            System.out.println(e.getMessage());  // Imprimir el mensaje de error
        }
    }

    // Método para eliminar una entrada del historial
    // Este método toma el ID del historial como parámetro y elimina el registro correspondiente
    public void eliminarHistorial(int id) {
        String sql = "DELETE FROM tb_historial WHERE id_historial = ?";  // Consulta SQL para eliminar un registro

        try (PreparedStatement stmt = cx.prepareStatement(sql)) {  // Preparar la consulta SQL
            stmt.setInt(1, id);  // Asignar el ID del historial al parámetro de la consulta
            stmt.executeUpdate();  // Ejecutar la consulta para eliminar el registro
            System.out.println("Historial eliminado exitosamente");  // Mensaje de éxito
        } catch (SQLException e) {  // Capturar cualquier excepción SQL
            System.out.println(e.getMessage());  // Imprimir el mensaje de error
        }
    }

    // Método para actualizar una entrada del historial
    // Este método toma un objeto Historial como parámetro y actualiza el registro correspondiente en la base de datos
    public void actualizarHistorial(Historial historial) {
        String sql = "UPDATE tb_historial SET fecha = ?, hora = ?, id_usuario = ?, pregunta = ?, respuesta = ? WHERE id_historial = ?";  // Consulta SQL para actualizar un registro

        try (PreparedStatement stmt = cx.prepareStatement(sql)) {  // Preparar la consulta SQL
            stmt.setDate(1, new java.sql.Date(historial.getFecha().getTime()));  // Asignar la nueva fecha
            stmt.setTime(2, new java.sql.Time(historial.getHora().getTime()));  // Asignar la nueva hora
            stmt.setInt(3, historial.getIdUsuario());  // Asignar el nuevo ID del usuario
            stmt.setString(4, historial.getPregunta());  // Asignar la nueva pregunta
            stmt.setString(5, historial.getRespuesta());  // Asignar la nueva respuesta
            stmt.setInt(6, historial.getId());  // Asignar el ID del historial que se actualizará
            stmt.executeUpdate();  // Ejecutar la consulta para actualizar el registro
            System.out.println("Historial actualizado exitosamente");  // Mensaje de éxito
        } catch (SQLException e) {  // Capturar cualquier excepción SQL
            System.out.println(e.getMessage());  // Imprimir el mensaje de error
        }
    }

    // Método para desconectar la base de datos
    public void desconectar() {
        try {
            if (cx != null && !cx.isClosed()) {  // Verificar si la conexión está abierta
                cx.close();  // Cerrar la conexión
                System.out.println("Conexión cerrada exitosamente");  // Mensaje de éxito
            }
        } catch (SQLException ex) {  // Capturar cualquier excepción SQL
            System.out.println(ex.getMessage());  // Imprimir el mensaje de error
        }
    }
}
