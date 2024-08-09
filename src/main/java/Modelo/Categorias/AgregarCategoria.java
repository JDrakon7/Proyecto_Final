/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Modelo.Categorias;  // Paquete para la clase AgregarCategoria

import Modelo.Conexiondb;  // Importa la clase Conexiondb para manejar la conexión a la base de datos
import java.io.IOException;  // Importa IOException para manejar errores de entrada/salida
import java.io.PrintWriter;  // Importa PrintWriter para escribir la respuesta HTTP
import java.sql.Connection;  // Importa Connection para manejar conexiones a la base de datos
import java.sql.PreparedStatement;  // Importa PreparedStatement para ejecutar consultas SQL precompiladas
import java.sql.SQLException;  // Importa SQLException para manejar errores SQL
import javax.servlet.ServletException;  // Importa ServletException para manejar errores en los servlets
import javax.servlet.annotation.WebServlet;  // Importa WebServlet para la anotación del servlet
import javax.servlet.http.HttpServlet;  // Importa HttpServlet como clase base para el servlet
import javax.servlet.http.HttpServletRequest;  // Importa HttpServletRequest para manejar solicitudes HTTP
import javax.servlet.http.HttpServletResponse;  // Importa HttpServletResponse para manejar respuestas HTTP
import org.json.JSONObject;  // Importa JSONObject para manejar datos en formato JSON (no se usa en este código, pero se podría usar para futuras mejoras)

@WebServlet("/AgregarCategoria")  // Anota la clase como un servlet con el URL pattern "/AgregarCategoria"
public class AgregarCategoria extends HttpServlet {
    private Conexiondb conexion;  // Instancia de la clase Conexiondb para manejar la conexión a la base de datos

    // Constructor que inicializa la conexión a la base de datos
    public AgregarCategoria() {
        this.conexion = new Conexiondb();
    }

    // Método para manejar solicitudes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // Establece la codificación de caracteres de la solicitud
        response.setContentType("text/html; charset=UTF-8");  // Establece el tipo de contenido y la codificación de caracteres de la respuesta
        
        String nombreCategoria = request.getParameter("nombre_categoria");  // Obtiene el parámetro de nombre de categoría desde la solicitud

        Connection cx = conexion.Conectar();  // Establece una conexión con la base de datos
        String sql = "INSERT INTO tb_categorias (nombre_categoria) VALUES (?)";  // Consulta SQL para insertar una nueva categoría

        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {  // Prepara la consulta SQL
            pstmt.setString(1, nombreCategoria);  // Establece el valor del parámetro para la consulta SQL
            pstmt.executeUpdate();  // Ejecuta la consulta de actualización
            response.getWriter().write("Categoría agregada exitosamente.");  // Envia una respuesta exitosa al cliente
        } catch (SQLException e) {
            response.getWriter().write("Error al agregar categoría: " + e.getMessage());  // Envia un mensaje de error al cliente si ocurre una excepción SQL
        } finally {
            conexion.desconectar();  // Asegura que la conexión a la base de datos se cierre en el bloque finally
        }
    }
}
