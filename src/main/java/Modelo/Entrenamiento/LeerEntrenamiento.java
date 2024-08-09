/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Entrenamiento;

import Modelo.Conexiondb;              // Importa la clase que gestiona la conexión a la base de datos
import com.google.gson.Gson;           // Importa la librería Gson para convertir objetos Java a JSON
import java.io.IOException;            // Importa clases necesarias para manejar IO y excepciones
import java.sql.Connection;            // Importa la clase Connection para manejar conexiones a la base de datos
import java.sql.ResultSet;             // Importa la clase ResultSet para manejar resultados de consultas SQL
import java.sql.SQLException;          // Importa la clase SQLException para manejar excepciones SQL
import java.sql.Statement;             // Importa la clase Statement para ejecutar consultas SQL
import javax.servlet.ServletException; // Importa clases para manejar Servlets
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; // Importa clases para manejar solicitudes y respuestas HTTP
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;            // Importa la clase ArrayList para manejar listas de objetos
import java.util.List;                 // Importa la clase List para trabajar con listas

// Define el servlet y su URL de mapeo, aunque esta línea está comentada en tu ejemplo
//@WebServlet("/LeerEntrenamiento")
public class LeerEntrenamiento extends HttpServlet {
    private Conexiondb conexion;       // Declara un objeto para manejar la conexión a la base de datos

    // Constructor del servlet que inicializa la conexión a la base de datos
    public LeerEntrenamiento() {
        this.conexion = new Conexiondb();
    }

    // Método que maneja las solicitudes HTTP GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Entrenamiento> entrenamientos = new ArrayList<>(); // Crea una lista para almacenar los entrenamientos
        Connection cx = conexion.Conectar();                    // Conecta a la base de datos
        String sql = "SELECT * FROM tb_entrenamiento";          // Define la consulta SQL para obtener todos los entrenamientos
        
        // Ejecuta la consulta SQL y procesa los resultados
        try (Statement stmt = cx.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {           // Ejecuta la consulta y obtiene el ResultSet
            while (rs.next()) {                                 // Itera sobre los resultados
                Entrenamiento entrenamiento = new Entrenamiento(); // Crea un objeto Entrenamiento para cada fila
                entrenamiento.setIdEntrenamiento(rs.getInt("id_entrenamiento")); // Asigna el ID del entrenamiento
                entrenamiento.setPregunta(rs.getString("pregunta"));             // Asigna la pregunta
                entrenamiento.setRespuesta(rs.getString("respuesta"));           // Asigna la respuesta
                entrenamiento.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion")); // Asigna la fecha de actualización
                entrenamientos.add(entrenamiento);        // Añade el objeto a la lista de entrenamientos
            }
        } catch (SQLException e) {                        // Captura cualquier excepción SQL
            response.getWriter().write("Error al leer entrenamiento: " + e.getMessage()); // Escribe el error en la respuesta
        } finally {
            conexion.desconectar();                       // Cierra la conexión a la base de datos
        }

        // Convierte la lista de entrenamientos a JSON y la envía en la respuesta
        response.setContentType("application/json");          // Define el tipo de contenido de la respuesta como JSON
        response.getWriter().write(new Gson().toJson(entrenamientos)); // Convierte la lista a JSON y la escribe en la respuesta
    }
}
