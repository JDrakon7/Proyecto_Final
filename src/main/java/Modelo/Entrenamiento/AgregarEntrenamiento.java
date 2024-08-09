/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Modelo.Entrenamiento;

// Importaciones necesarias para manejar la conexión a la base de datos, servlets, sesiones y excepciones
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
import javax.servlet.http.HttpSession;

// Clase que maneja la adición de nuevos entrenamientos (pregunta y respuesta) a la base de datos
public class AgregarEntrenamiento extends HttpServlet {
    
    // Atributo para manejar la conexión a la base de datos
    private Conexiondb conexion;

    // Constructor que inicializa la conexión a la base de datos
    public AgregarEntrenamiento() {
        this.conexion = new Conexiondb();
    }

    // Método que maneja las solicitudes HTTP POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene los parámetros enviados en la solicitud (pregunta, respuesta y categoría)
        String pregunta = request.getParameter("pregunta");
        String respuesta = request.getParameter("respuesta");
        String categoria = request.getParameter("categoria");

        // Obtiene la sesión actual del usuario para recuperar su ID
        HttpSession session = request.getSession();
        Integer usuarioId = (Integer) session.getAttribute("userId");

        // Establece la conexión a la base de datos
        Connection cx = conexion.Conectar();
        
        // Consulta SQL para insertar un nuevo registro en la tabla de entrenamiento
        String sql = "INSERT INTO tb_entrenamiento (pregunta, respuesta, id_usuario, id_categoria) VALUES (?, ?, ?, ?)";

        // Intentar ejecutar la consulta de inserción
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {
            // Asigna los valores a los parámetros de la consulta SQL
            pstmt.setString(1, pregunta);           // Primera "?" en la consulta SQL será reemplazada por el valor de "pregunta"
            pstmt.setString(2, respuesta);          // Segunda "?" será reemplazada por el valor de "respuesta"
            pstmt.setInt(3, usuarioId);             // Tercera "?" será reemplazada por el valor del ID del usuario
            pstmt.setString(4, categoria);          // Cuarta "?" será reemplazada por el valor de "categoria"
            
            // Ejecuta la inserción en la base de datos
            pstmt.executeUpdate();
            
            // Escribe un mensaje de éxito en la respuesta HTTP
            response.getWriter().write("Pregunta y respuesta agregadas exitosamente.");
        } catch (SQLException e) {
            // Si ocurre un error SQL, se captura y se escribe un mensaje de error en la respuesta HTTP
            response.getWriter().write("Error al agregar datos: " + e.getMessage());
        } finally {
            // Cierra la conexión a la base de datos
            conexion.desconectar();
        }
    }
}
