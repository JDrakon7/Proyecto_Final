/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;  // Paquete en el que se encuentra la clase ServletActualizarEntrenamiento

/**
 * Servlet para manejar las solicitudes de actualización de entrenamiento.
 * 
 * @author JDBJ
 */

import Modelo.Conexiondb;  // Importa la clase Conexiondb para manejar la conexión a la base de datos
import java.io.IOException;  // Importa la clase IOException para manejar errores de entrada/salida
import java.sql.Connection;  // Importa la interfaz Connection para manejar la conexión a la base de datos
import java.sql.PreparedStatement;  // Importa la clase PreparedStatement para ejecutar consultas SQL precompiladas
import java.sql.ResultSet;  // Importa la clase ResultSet para manejar los resultados de las consultas SQL
import java.sql.SQLException;  // Importa la clase SQLException para manejar errores relacionados con SQL
import javax.servlet.ServletException;  // Importa la clase ServletException para manejar excepciones en servlets
import javax.servlet.annotation.WebServlet;  // Importa la anotación WebServlet para definir el servlet en el archivo de despliegue
import javax.servlet.http.HttpServlet;  // Importa la clase HttpServlet para manejar solicitudes HTTP
import javax.servlet.http.HttpServletRequest;  // Importa la clase HttpServletRequest para manejar las solicitudes HTTP
import javax.servlet.http.HttpServletResponse;  // Importa la clase HttpServletResponse para manejar las respuestas HTTP

@WebServlet("/ActualizarEntrenamiento")  // Define el URL pattern para este servlet
public class ServletActualizarEntrenamiento extends HttpServlet {  // Define la clase ServletActualizarEntrenamiento que extiende HttpServlet

    private Conexiondb conexion;  // Declara una variable para manejar la conexión a la base de datos

    public ServletActualizarEntrenamiento() {  // Constructor del servlet
        this.conexion = new Conexiondb();  // Inicializa la conexión a la base de datos
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // Maneja las solicitudes POST
        request.setCharacterEncoding("UTF-8");  // Establece la codificación de caracteres para la solicitud
        response.setContentType("text/html; charset=UTF-8");  // Establece el tipo de contenido de la respuesta
        response.setCharacterEncoding("UTF-8");  // Establece la codificación de caracteres para la respuesta
        
        int idEntrenamiento = Integer.parseInt(request.getParameter("id_entrenamiento"));  // Obtiene el ID del entrenamiento desde la solicitud
        String pregunta = request.getParameter("pregunta");  // Obtiene la pregunta desde la solicitud
        String respuesta = request.getParameter("respuesta");  // Obtiene la respuesta desde la solicitud
        int idCategoria = Integer.parseInt(request.getParameter("id_categoria"));  // Obtiene el ID de la categoría desde la solicitud

        Connection cx = conexion.Conectar();  // Establece la conexión con la base de datos
        String sql = "UPDATE tb_entrenamiento SET pregunta = ?, respuesta = ?, id_categoria = ? WHERE id_entrenamiento = ?";  // Consulta SQL para actualizar el entrenamiento
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {  // Prepara la consulta SQL
            pstmt.setString(1, pregunta);  // Establece el valor para el primer parámetro (pregunta)
            pstmt.setString(2, respuesta);  // Establece el valor para el segundo parámetro (respuesta)
            pstmt.setInt(3, idCategoria);  // Establece el valor para el tercer parámetro (id_categoria)
            pstmt.setInt(4, idEntrenamiento);  // Establece el valor para el cuarto parámetro (id_entrenamiento)
            pstmt.executeUpdate();  // Ejecuta la actualización en la base de datos
            response.getWriter().write("Entrenamiento actualizado exitosamente.");  // Envía una respuesta exitosa al cliente
        } catch (SQLException e) {  // Captura errores de SQL
            response.getWriter().write("Error al actualizar entrenamiento: " + e.getMessage());  // Envía un mensaje de error al cliente
        } finally {
            conexion.desconectar();  // Cierra la conexión con la base de datos
        }
    }

    private int obtenerIdCategoria(String nombreCategoria, Connection cx) {  // Método privado para obtener el ID de la categoría a partir de su nombre
        String sql = "SELECT id_categoria FROM tb_categoria WHERE nombre_categoria = ?";  // Consulta SQL para obtener el ID de la categoría
        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {  // Prepara la consulta SQL
            pstmt.setString(1, nombreCategoria);  // Establece el valor para el parámetro (nombre_categoria)
            ResultSet rs = pstmt.executeQuery();  // Ejecuta la consulta y obtiene los resultados
            if (rs.next()) {  // Verifica si se encontraron resultados
                return rs.getInt("id_categoria");  // Devuelve el ID de la categoría
            }
        } catch (SQLException e) {  // Captura errores de SQL
            e.printStackTrace();  // Imprime el error en la consola
        }
        return -1;  // Devuelve -1 si no se encontró la categoría
    }
}
