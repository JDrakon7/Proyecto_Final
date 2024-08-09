/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo.Entrenamiento;

// Importaciones necesarias para manejar la conexión a la base de datos y el servlet
import Modelo.Conexiondb;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Clase del servlet que maneja la actualización de un entrenamiento en la base de datos
public class ActualizarEntrenamiento extends HttpServlet {
    
    // Atributo para manejar la conexión a la base de datos
    private Conexiondb conexion;

    // Constructor que inicializa la conexión a la base de datos
    public ActualizarEntrenamiento() {
        this.conexion = new Conexiondb();
    }

    // Método que maneja las solicitudes HTTP POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene los parámetros enviados en la solicitud (id del entrenamiento, pregunta y respuesta)
        int idEntrenamiento = Integer.parseInt(request.getParameter("idEntrenamiento"));
        String pregunta = request.getParameter("pregunta");
        String respuesta = request.getParameter("respuesta");

        // Conexión a la base de datos
        Connection cx = conexion.Conectar();
        
        // Consulta SQL para actualizar el entrenamiento en la base de datos
        String sql = "UPDATE tb_entrenamiento SET pregunta = ?, respuesta = ? WHERE id_entrenamiento = ?";
        
        // Intentar ejecutar la consulta de actualización
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            // Asigna los valores a los parámetros de la consulta
            pstmt.setString(1, pregunta);           // Primera "?" en la consulta SQL será reemplazada por el valor de "pregunta"
            pstmt.setString(2, respuesta);          // Segunda "?" en la consulta SQL será reemplazada por el valor de "respuesta"
            pstmt.setInt(3, idEntrenamiento);       // Tercera "?" en la consulta SQL será reemplazada por el valor de "idEntrenamiento"
            
            // Ejecuta la actualización en la base de datos
            pstmt.executeUpdate();
            
            // Escribe un mensaje de éxito en la respuesta HTTP
            response.getWriter().write("Entrenamiento actualizado exitosamente.");
        } catch (SQLException e) {
            // Si ocurre un error SQL, se captura y se escribe un mensaje de error en la respuesta HTTP
            response.getWriter().write("Error al actualizar entrenamiento: " + e.getMessage());
        } finally {
            // Cierra la conexión a la base de datos
            conexion.desconectar();
        }
    }
}
