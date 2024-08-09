/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;  // Paquete en el que se encuentra la clase ServletEliminarEntrenamiento

import Modelo.Conexiondb;  // Importa la clase Conexiondb para manejar la conexión a la base de datos
import java.io.IOException;  // Importa la clase IOException para manejar errores de entrada/salida
import java.io.PrintWriter;  // Importa la clase PrintWriter para enviar texto al cliente
import java.sql.Connection;  // Importa la interfaz Connection para manejar la conexión a la base de datos
import java.sql.PreparedStatement;  // Importa la clase PreparedStatement para ejecutar consultas SQL precompiladas
import java.sql.SQLException;  // Importa la clase SQLException para manejar errores relacionados con SQL
import javax.servlet.ServletException;  // Importa la clase ServletException para manejar excepciones en servlets
import javax.servlet.annotation.WebServlet;  // Importa la anotación WebServlet para definir el servlet en el archivo de despliegue
import javax.servlet.http.HttpServlet;  // Importa la clase HttpServlet para manejar solicitudes HTTP
import javax.servlet.http.HttpServletRequest;  // Importa la clase HttpServletRequest para manejar las solicitudes HTTP
import javax.servlet.http.HttpServletResponse;  // Importa la clase HttpServletResponse para manejar las respuestas HTTP

/**
 * Servlet para eliminar un entrenamiento de la base de datos.
 */
@WebServlet(name = "ServletEliminarEntrenamiento", urlPatterns = {"/ServletEliminarEntrenamiento"})  // Define el URL pattern para este servlet
public class ServletEliminarEntrenamiento extends HttpServlet {  // Define la clase ServletEliminarEntrenamiento que extiende HttpServlet

    private Conexiondb conexion;  // Declara una variable para manejar la conexión a la base de datos

    public ServletEliminarEntrenamiento() {  // Constructor del servlet
        this.conexion = new Conexiondb();  // Inicializa la conexión a la base de datos
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  // Maneja las solicitudes POST
        int idEntrenamiento;
        try {
            idEntrenamiento = Integer.parseInt(request.getParameter("idEntrenamiento"));  // Obtiene el id_entrenamiento desde la solicitud y lo convierte a entero
        } catch (NumberFormatException e) {
            response.getWriter().write("Error: idEntrenamiento no es un número válido.");  // Envía un mensaje de error si idEntrenamiento no es un número válido
            return;  // Sale del método si idEntrenamiento no es válido
        }

        Connection cx = conexion.Conectar();  // Establece la conexión con la base de datos
        String sql = "DELETE FROM tb_entrenamiento WHERE id_entrenamiento = ?";  // Define la consulta SQL para eliminar un registro

        try (PreparedStatement pstmt = cx.prepareStatement(sql)) {  // Prepara la consulta SQL
            pstmt.setInt(1, idEntrenamiento);  // Establece el valor para el parámetro (id_entrenamiento)
            int rowsAffected = pstmt.executeUpdate();  // Ejecuta la actualización y obtiene el número de filas afectadas

            // Verifica si la eliminación fue exitosa
            if (rowsAffected > 0) {
                response.getWriter().write("Entrenamiento eliminado exitosamente.");  // Envía un mensaje de éxito si se eliminó al menos una fila
            } else {
                response.getWriter().write("No se encontró el entrenamiento con el ID proporcionado.");  // Envía un mensaje si no se encontró el ID para eliminar
            }
        } catch (SQLException e) {
            response.getWriter().write("Error al eliminar entrenamiento: " + e.getMessage());  // Envía un mensaje de error si ocurre una excepción SQL
        } finally {
            conexion.desconectar();  // Cierra la conexión a la base de datos en el bloque finally para asegurar que siempre se desconecte
        }
    }
}
