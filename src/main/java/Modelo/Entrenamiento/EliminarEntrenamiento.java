/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Modelo.Entrenamiento;

// Importaciones necesarias para manejar la conexión a la base de datos, servlets, y excepciones
import Modelo.Conexiondb;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Clase que maneja la eliminación de un entrenamiento específico de la base de datos
public class EliminarEntrenamiento extends HttpServlet {
    
    // Atributo para manejar la conexión a la base de datos
    private Conexiondb conexion;

    // Constructor que inicializa la conexión a la base de datos
    public EliminarEntrenamiento() {
        this.conexion = new Conexiondb();
    }

    // Método que maneja las solicitudes HTTP POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene el parámetro enviado en la solicitud, correspondiente al ID del entrenamiento que se desea eliminar
        int idEntrenamiento = Integer.parseInt(request.getParameter("id_entrenamiento"));

        // Establece la conexión a la base de datos
        Connection cx = conexion.Conectar();
        
        // Consulta SQL para eliminar un registro de la tabla de entrenamiento según el ID
        String sql = "DELETE FROM tb_entrenamiento WHERE id_entrenamiento = ?";

        // Intentar ejecutar la consulta de eliminación
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            // Asigna el valor del ID del entrenamiento al parámetro de la consulta SQL
            pstmt.setInt(1, idEntrenamiento);  // La "?" en la consulta SQL será reemplazada por el valor de "idEntrenamiento"
            
            // Ejecuta la eliminación en la base de datos
            pstmt.executeUpdate();
            
            // Escribe un mensaje de éxito en la respuesta HTTP
            response.getWriter().write("Entrenamiento eliminado exitosamente.");
        } catch (SQLException e) {
            // Si ocurre un error SQL, se captura y se escribe un mensaje de error en la respuesta HTTP
            response.getWriter().write("Error al eliminar datos: " + e.getMessage());
        } finally {
            // Cierra la conexión a la base de datos
            conexion.desconectar();
        }
    }
}
